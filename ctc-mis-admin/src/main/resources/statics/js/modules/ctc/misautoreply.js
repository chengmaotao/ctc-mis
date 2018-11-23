$(function() {
	$("#jqGrid").jqGrid({
		url : baseURL + 'ctc/misautoreply/list',
		datatype : "json",
		colModel : [ {
			label : 'id',
			name : 'id',
			index : 'id',
			width : 50,
			key : true
		}, {
			label : '类型',
			name : 'type',
			index : 'type',
			width : 80,
			formatter : function(cellvalue, options, rowObject) {
				if (cellvalue == 'text') {
					return "文本消息";
				} else if (cellvalue == 'image') {
					return "图片消息";
				} else if (cellvalue == 'news') {
					return "图文消息";
				} else {
					return "未知";
				}
			}
		}, {
			label : '标题',
			name : 'name',
			index : 'name',
			width : 80
		}, {
			label : '关键词',
			name : 'keyword',
			index : 'keyword',
			width : 80
		}, {
			label : '自动回复文本内容',
			name : 'text',
			index : 'text',
			width : 80
		} ],
		viewrecords : true,
		height : 385,
		rowNum : 10,
		rowList : [ 10, 30, 50 ],
		rownumbers : true,
		rownumWidth : 25,
		autowidth : true,
		multiselect : true,
		pager : "#jqGridPager",
		jsonReader : {
			root : "page.list",
			page : "page.currPage",
			total : "page.totalPage",
			records : "page.totalCount"
		},
		prmNames : {
			page : "page",
			rows : "limit",
			order : "order"
		},
		gridComplete : function() {
			// 隐藏grid底部滚动条
			$("#jqGrid").closest(".ui-jqgrid-bdiv").css({
				"overflow-x" : "hidden"
			});
		}
	});
});

var vm = new Vue({
	el : '#rrapp',
	data : {
		showList : true,
		title : null,
		isadd : false,
		misAutoReply : {},
		updateData : {}
	},
	methods : {
		query : function() {
			vm.reload();
		},
		add : function() {
			vm.showList = false;
			vm.title = "新增";
			vm.misAutoReply = {};
			vm.isadd = true;
		},
		update : function(event) {
			var id = getSelectedRow();
			if (id == null) {
				return;
			}
			vm.showList = false;
			vm.title = "修改";
			vm.isadd = false;
			vm.getInfo(id)
		},
		saveOrUpdate : function(event) {
			if (vm.misAutoReply.id == null) {
				if (vm.misAutoReply.type != 'text') {
					alert("只能增加文本消息");
					return;
				}
			} else {
				if (vm.updateData.type != vm.misAutoReply.type) {
					vm.misAutoReply.type = vm.updateData.type;
				}
			}
			var url = vm.misAutoReply.id == null ? "ctc/misautoreply/save"
					: "ctc/misautoreply/update";
			$.ajax({
				type : "POST",
				url : baseURL + url,
				contentType : "application/json",
				data : JSON.stringify(vm.misAutoReply),
				success : function(r) {
					if (r.code === 0) {
						alert('操作成功', function(index) {
							vm.reload();
						});
					} else {
						alert(r.msg);
					}
				}
			});
		},
		del : function(event) {
			var ids = getSelectedRows();
			if (ids == null) {
				return;
			}
			confirm('确定要删除选中的记录？', function() {
				$.ajax({
					type : "POST",
					url : baseURL + "ctc/misautoreply/delete",
					contentType : "application/json",
					data : JSON.stringify(ids),
					success : function(r) {
						if (r.code == 0) {
							alert('操作成功', function(index) {
								$("#jqGrid").trigger("reloadGrid");
							});
						} else {
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo : function(id) {
			$.get(baseURL + "ctc/misautoreply/info/" + id, function(r) {
				vm.misAutoReply = r.misAutoReply;
				vm.updateData = r.misAutoReply;
			});
		},
		reload : function(event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				
				page : page,
				postData : {
					'type' : vm.misAutoReply.type,
					"name" :vm.misAutoReply.name  
				},
			}).trigger("reloadGrid");
		},
		newsSyn : function() {
			$.get(baseURL + "ctc/misautoreply/sysnData?type=news", function(r) {
				if (r.code == 0) {
					vm.reload();
				}
				alert(r.msg)
			});
		},
		imageSyn : function() {
			$.get(baseURL + "ctc/misautoreply/sysnData?type=image",
					function(r) {
						if (r.code == 0) {
							vm.reload();
						}
						alert(r.msg)
					});
		}
	}
});
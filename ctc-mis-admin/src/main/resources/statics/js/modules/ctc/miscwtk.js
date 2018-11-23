function checkNum(e) {
	var re = /^\d+(?=\.{0,1}\d+$|$)/
	if (e.value != "") {
		if (!re.test(e.value)) {
			e.value = "";
			e.focus();
		} else {
			e.value = parseFloat(e.value).toFixed(8);
		}

	}
}
$(function() {
	$("#jqGrid").jqGrid({
		url : baseURL + 'ctc/miscwtk/list',
		datatype : "json",
		colModel : [ {
			label : 'id',
			name : 'id',
			index : 'id',
			width : 50,
			key : true,
			hidden : true
		}, {
			label : '手机号',
			name : 'telno',
			index : 'telNo',
			width : 80
		}, {
			label : '用户姓名',
			name : 'name',
			index : 'name',
			width : 80
		},
		/*
		 * { label: '银行卡号', name: 'bankno', index: 'bankNo', width: 80 }, {
		 * label: '开户支行', name: 'bankbanchname', index: 'bankBanchName', width:
		 * 80 },
		 */
		{
			label : 'CTC数量',
			name : 'ctcamount',
			index : 'ctcAmount',
			width : 80
		}, {
			label : '单价',
			name : 'price',
			index : 'price',
			width : 80
		}, {
			label : '总金额',
			name : 'amount',
			index : 'amount',
			width : 80
		}, {
			label : '主地址',
			name : 'mainaddress',
			index : 'mainAddress',
			width : 80
		}, {
			label : '子地址',
			name : 'subaddress',
			index : 'subAddress',
			width : 80
		}, {
			label : '主+子地址',
			name : 'mainsubaddress',
			index : 'mainSubAddress',
			width : 80,
			formatter : function(cellvalue, options, rowObject) {
				return rowObject.mainaddress + rowObject.subaddress;
			}
		}, {
			label : '微信号',
			name : 'wxAppid',
			index : 'wx_appid',
			width : 80
		}, {
			label : '微信昵称',
			name : 'wxNickname',
			index : 'wx_nickname',
			width : 80
		}, {
			label : '打款方地址',
			name : 'personAddress',
			index : 'person_address',
			width : 80
		}, {
			label : '退款状态',
			name : 'tStatus',
			index : 't_status',
			width : 80,
			formatter : function(cellvalue, options, rowObject) {
				if (cellvalue == '0') {
					return "待处理";
				} else if (cellvalue == '1') {
					return "已处理";
				} else {
					return "未知";
				}
			}
		}, {
			label : '时间',
			name : 'createTimeVo',
			index : 'createTimeVo',
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
			/*
			 * $("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" :
			 * "hidden" });
			 */
		}
	});
});

var vm = new Vue({
	el : '#rrapp',
	data : {
		showList : true,
		title : null,
		misCwTk : {}
	},
	methods : {
		query : function() {
			vm.reload();
		},
		add : function() {
			vm.showList = false;
			vm.title = "新增";
			vm.misCwTk = {};
			vm.getUuId();
		},
		update : function(event) {
			var id = getSelectedRow();
			if (id == null) {
				return;
			}
			vm.showList = false;
			vm.title = "修改";

			vm.getInfo(id)
		},
		getUuId : function() {
			$.get(baseURL + "ctc/miscwtk/getuuid", function(r) {
				vm.misCwTk = r.misCwTk;
			});
		},
		saveOrUpdate : function(event) {
			vm.misCwTk.tstatus = $("#tStatus").val();
			var url = vm.misCwTk.id == null ? "ctc/miscwtk/save"
					: "ctc/miscwtk/update";
			$.ajax({
				type : "POST",
				url : baseURL + url,
				contentType : "application/json",
				data : JSON.stringify(vm.misCwTk),
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
					url : baseURL + "ctc/miscwtk/delete",
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
			$.get(baseURL + "ctc/miscwtk/info/" + id, function(r) {
				vm.misCwTk = r.misCwTk;
			});
		},
		reload : function(event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				page : 1,//$('.ui-pg-input.form-control').val(),
				postData:{
					telNo:$("#telNo").val(),
				}
			}).trigger("reloadGrid");
		}
	}
});

$(function() {
	var now = new Date();
	var year = now.getFullYear();       // 年
    var month = now.getMonth() + 1;     // 月
    var day = now.getDate();
	var start = {
		format : "YYYY-MM-DD",
		minDate : year+'-'+month+'-'+day,
		choosefun : function(elem, datas) {
			end.minDate = datas; // 开始日选好后，重置结束日的最小日期
		}
	};
	jeDate("#beginDateId", start);
	$("#jqGrid")
			.jqGrid(
					{
						url : baseURL + 'ctc/misyydh/list',
						datatype : "json",
						colModel : [
								{
									label : '兑换码数量',
									name : 'dhCount',
									index : 'dhCount',
									width : 50,
									key : true
								},
								{
									label : '兑换码类型',
									name : 'type',
									index : 'type',
									width : 80,
									formatter : function(cellvalue, options,
											rowObject) {
										if (cellvalue == 0) {
											return "信用钻";
										} else if (cellvalue == 1) {
											return "算力";
										} else if (cellvalue == 2) {
											return "CTC";
										}
									}
								},
								{
									label : '奖励数量',
									name : 'sumCount',
									index : 'sumCount',
									width : 80
								},
								{
									label : '生成时间',
									name : 'createTimeVo',
									index : 'createTimeVo',
									width : 80
								},
								{
									label : '超时时间',
									name : 'expireTimeVo',
									index : 'expireTimeVo',
									width : 80
								},
								{
									label : '批次号',
									name : 'pId',
									index : 'pId',
									width : 80
								},
								{
									label : '备注',
									name : 'remark',
									index : 'remark',
									width : 80
								},
								{
									label : '操作',
									name : 'pId',
									index : 'pId',
									width : 80,
									formatter : function(cellvalue, options,
											rowObject) {
										return '<a class="btn btn-default" onclick="vm.reloadXq(\''
												+ rowObject.pId + '\')">详情</a>'
									}
								} ],
						viewrecords : true,
						height : 385,
						rowNum : 10,
						rowList : [ 10, 30, 50 ],
						rownumbers : true,
						rownumWidth : 25,
						autowidth : true,
						multiselect : false,
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
							$("#jqGrid").closest(".ui-jqgrid-bdiv").css({
								"overflow-x" : "hidden"
							});
						}
					});

	$("#jqGridXq").jqGrid({
		url : baseURL + 'ctc/misyydh/listmx',
		datatype : "local",
		colModel : [ {
			label : '兑换码',
			name : 'dhcode',
			index : 'dhcode',
			width : 50,
			key : true
		}, {
			label : '兑换码类型',
			name : 'type',
			index : 'type',
			width : 80,
			formatter : function(cellvalue, options, rowObject) {
				if (cellvalue == 0) {
					return "信用钻";
				} else if (cellvalue == 1) {
					return "算力";
				} else if (cellvalue == 2) {
					return "CTC";
				}
			}
		}, {
			label : '奖励数量',
			name : 'count',
			index : 'count',
			width : 80
		}, {
			label : '生成时间',
			name : 'createTimeVo',
			index : 'createTimeVo',
			width : 80
		}, {
			label : '超时时间',
			name : 'expireTimeVo',
			index : 'expireTimeVo',
			width : 80
		}, {
			label : '批次号',
			name : 'pid',
			index : 'pid',
			width : 80
		}, {
			label : '备注',
			name : 'remark',
			index : 'remark',
			width : 80
		}, {
			label : '状态',
			name : 'status',
			index : 'status',
			width : 80,
			formatter : function(cellvalue, options, rowObject) {
				if (cellvalue == 0) {
					return "未核销";
				} else if (cellvalue == 1) {
					return "已核销";
				} else {
					return "未知状态";
				}
			}
		} ],
		viewrecords : true,
		height : 385,
		rowNum : 10,
		rowList : [ 10, 30, 50 ],
		rownumbers : true,
		rownumWidth : 25,
		autowidth : true,
		multiselect : false,
		pager : "#jqGridPagerXq",
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
			$("#jqGridXq").setGridWidth($(window).width()*0.99);　
			$("#jqGridXq").closest(".ui-jqgrid-bdiv").css({
				"overflow-x" : "hidden"
			});
		}
	});
});
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate =  date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
    return currentdate;
}
var vm = new Vue({
	el : '#rrapp',
	data : {
		showList : true,
		showListXq : false,
		showListadd : false,
		title : null,
		misYyDh : {}
	},
	methods : {
		query : function() {
			vm.reload();
		},
		add : function() {
			vm.showList = false;
			vm.showListXq = false;
			vm.showListadd = true;
			vm.title = "生成兑换码";
			vm.misYyDh = {};
		},
		save : function(event) {
			var beginDateId=$("#beginDateId").val();
			vm.misYyDh.expireTime=beginDateId;
			if(vm.misYyDh.sumCount<=0){
				alert("兑换数量必须大于0")
				return;
			}
			if(vm.misYyDh.count<=0){
				alert("奖励数量必须大于0")
				return;
			}
			if(vm.misYyDh.expireTime==''||vm.misYyDh.expireTime==null){
				alert("请选择有效期")
				return;
			}
			$.ajax({
				type : "POST",
				url : baseURL + "ctc/misyydh/save",
				contentType : "application/json",
				data : JSON.stringify(vm.misYyDh),
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
		reloadXq : function(pId) {
			vm.showList = false;
			vm.showListXq = true;
			vm.showListadd = false;
			var page = $("#jqGridXq").jqGrid('getGridParam', 'page');
			$("#jqGridXq").jqGrid('setGridParam', {
				page : 1,
				datatype : "json",
				postData : {
					pId : pId
				}
			}).trigger("reloadGrid");
		},
		reload : function(event) {
			vm.showList = true;
			vm.showListXq = false;
			vm.showListadd = false;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				page : 1//$('.ui-pg-input.form-control').val()
			}).trigger("reloadGrid");
		}
	}
});
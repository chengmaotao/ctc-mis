$(function() {
	$("#jqGrid").jqGrid(
			{
				url : baseURL + 'ctc/withdraworder/withdraworderlist',
				datatype : "local",
				colModel : [ {
					label : '申请提现时间',
					name : 'vocreatetime',
					width : 150,
					sortable : false
				}, {
					label : '完成时间',
					name : 'vofinishTime',
					width : 150,
					sortable : false
				}, {
					label : '用户ID',
					name : 'cid',
					width : 80,
					sortable : false
				}, {
					label : '手机号',
					name : 'username',
					width : 100,
					sortable : false
				}, {
					label : '提现CTC数量',
					name : 'amount',
					sortable : false
				}, {
					label : '提现地址',
					name : 'toAddress',
					width : 300,
					sortable : false
				}, {
					label : '提现状态',
					name : 'status',
					width : 80,
					sortable : false,
					formatter : function(cellvalue, options, rowObject) {
						if (cellvalue == 'W') {
							return "待处理";
						} else if (cellvalue == 'U') {
							return "处理中";
						} else if (cellvalue == 'C') {
							return "已提交";
						} else if (cellvalue == 'S') {
							return "成功";
						} else if (cellvalue == 'F') {
							return "失败";
						} else if (cellvalue == 'NQ') {
							return "待查询";
						}else{
							return "未知";
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
				loadComplete : function(xhr) {
					if (xhr.code != '0' && typeof (xhr.code) != 'undefined'
							&& xhr.code != '') {
						alert(xhr.msg)
					}
				}
			});
});

var vm = new Vue({
	el : '#rrapp',
	data : {
		showList : true,
		title : null,
		withdraworder : {}
	},
	methods : {
		query : function() {
			vm.reload();
			totalCount()
		},
		reload : function(event) {
			vm.showList = true;
			if ($("#beginDateId").val() == '' || $("#endDateId").val() == '') {
				alert("请选择开始日期和结束日期")
				return;
			}
			if ($("#beginDateId").val() > $("#endDateId").val()) {
				alert("开始日期不能大于结束日期")
				return;
			}
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				postData : {
					'beginDate' : $("#beginDateId").val() + " 00:00:00",
					"endDate" : $("#endDateId").val() + " 00:00:00",
					"id" : $("#customerId").val(),
					"username" : $("#username").val(),
					"status":$("#status").val()
				},
				datatype : "json",
				page : 1//$('.ui-pg-input.form-control').val()
			}).trigger("reloadGrid");
		}
	}
});
$(function() {
	document.onkeydown = function(e) {
		var ev = document.all ? window.event : e;
		if (ev.keyCode == 13) {
			vm.query(ev);
		}
	}
});

function totalCount(){
	var beginDate = $("#beginDateId").val()+" 00:00:00";
	var endDate = $("#endDateId").val()+" 00:00:00";
	var id =  $("#customerId").val();
	var username = $("#username").val();;
	$.ajax({
        type: "GET",
        url: "../../ctc/withdraworder/withdraworderlistTotal",
        data: {beginDate:beginDate,endDate:endDate,id:id,username:username},
        dataType: "json",
        success: function(data){
        	$("#userCount").html("累计用户："+data.page.tatalUserCountInThisCondition);
        	$("#ctcCount").html("累计CTC数量:"+data.page.ctcCount);
        }
    });
	
}
$(function() {
	$("#jqGrid").jqGrid(
			{
				url : baseURL + 'ctc/dbdataCount/dbdataCountList',
				datatype : "local",
				colModel : [ {
					label : '日期',
					name : 'voStrDate',
					index : 'voStrDate',
					width : 100,
					sortable : false
				}, {
					label : '钻石矿场注册用户数 ',
					name : 'customerCount',
					width : 180,
					sortable : false
				}, {
					label : '白名单用户数',
					name : 'whiteCustomerCount',
					width : 150,
					sortable : false
				}, {
					label : '完成任务的用户数',
					name : 'completeCustomer',
					width : 150,
					sortable : false
				}, {
					label : '任务购买完成次数',
					name : 'completeCount',
					width : 150,
					sortable : false
				}, {
					label : '任务完成赠送CTC数量',
					name : 'ctcNum',
					width : 300,
					sortable : false
				}, {
					label : '提取成功用户数',
					name : 'withdrawCustomer',
					width : 150,
					sortable : false
				}, {
					label : '提取成功次数',
					name : 'withdrawNum',
					width : 150,
					sortable : false
				}, {
					label : '提取成功CTC总量',
					name : 'withdrawCtcNum',
					width : 300,
					sortable : false
				} ],
				viewrecords : true,
				height : 385,
				rowNum : 60,
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
				gridComplete : function() {
					// 隐藏grid底部滚动条
					$("#jqGrid").closest(".ui-jqgrid-bdiv").css({
						"overflow-x" : "hidden"
					});
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
		title : null
	},
	methods : {
		query : function() {
			vm.reload();
			//totalCount();
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
					"endDate" : $("#endDateId").val() + " 00:00:00"
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
        url: "../../ctc/dbdataCount/dbdataCountList",
        data: {beginDate:beginDate,endDate:endDate,id:id,username:username},
        dataType: "json",
        success: function(data){
        	$("#userCount").html("累计用户："+data.page.tatalUserCountInThisCondition);
        	$("#whiteUserCount").html("累计白名单用户:"+data.page.buyCount);
        	$("#completeUserCount").html("累计完成任务用户数:"+data.page.moneyCount);
        	$("#completeCount").html("累计任务购买完成数:"+data.page.ctcCount);
        	$("#ctcCount").html("任务完成赠送ctc:"+data.page.ctcCount);
        	$("#withdrawCustomer").html("提取成功用户数:"+data.page.ctcCount);
        	$("#withdrawNum").html("累计提取成功次数:"+data.page.ctcCount);
        	$("#withdrawCtcNum").html("提取成功ctc数量:"+data.page.ctcCount);
        }
    });
	
}
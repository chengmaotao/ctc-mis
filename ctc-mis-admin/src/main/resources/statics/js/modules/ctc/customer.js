$(function() {
	$("#jqGrid").jqGrid(
			{
				url : baseURL + 'ctc/customer/customerDataCount',
				datatype : "local",
				colModel : [ {
					label : '日期',
					name : 'voStrDate',
					width : 100,
					sortable : false
				}, {
					label : '成功发起邀请用户数',
					name : 'sumInviteCount',
					sortable : false
				}, {
					label : '被邀请注册用户数',
					name : 'counts',
					sortable : false
				}, {
					label : '被邀请并登录用户数',
					name : 'loginCounts',
					sortable : false
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
		title : null,
		customer : {}
	},
	methods : {
		query : function() {
			vm.reload();
			totalCount();
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
	$.ajax({
        type: "GET",
        url: "../../ctc/customer/customerDataCountTotal",
        data: {beginDate:beginDate,endDate:endDate},
        dataType: "json",
        success: function(data){
        	$("#userCount1").html("累计邀请用户数  :"+data.page.tatalUserCountInThisCondition);
        	$("#userCount2").html("累计注册用户数 :"+data.page.ctcCount);
        	$("#userCount3").html("被邀请且登录用户数 :"+data.page.buyCount);
        }
    });
	
}
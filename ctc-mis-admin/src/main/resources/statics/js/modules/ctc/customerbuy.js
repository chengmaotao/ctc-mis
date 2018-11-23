$(function() {
	$("#jqGrid").jqGrid(
			{
				url : baseURL + 'ctc/customer/customerbuylist',
				datatype : "local",
				colModel : [ {
					label : '首次开通时间',
					name : 'vocreatetime',
					index : 'create_time',
					width : 180,
					sortable : false
				}, {
					label : '用户ID',
					name : 'id',
					index : 'id',
					width : 80,
					sortable : false
				}, {
					label : '手机号',
					name : 'username',
					index : 'username',
					width : 150,
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
					"endDate" : $("#endDateId").val() + " 00:00:00",
					"id" : $("#customerId").val(),
					"username" : $("#username").val()
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
	var id= $("#customerId").val();
	var username= $("#username").val();
	$.ajax({
        type: "GET",
        url: "../../ctc/customer/customerbuylistTotal",
        data: {beginDate:beginDate,endDate:endDate,username:username,id:id},
        dataType: "json",
        success: function(data){
        	$("#userCount").html("累计开通报告用户数："+data.page.tatalUserCountInThisCondition);
        	
        }
    });
	
}

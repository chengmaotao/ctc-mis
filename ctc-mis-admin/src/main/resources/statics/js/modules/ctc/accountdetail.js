$(function() {
	$("#jqGrid").jqGrid(
			{
				url : baseURL + 'ctc/accountdetail/list',
				datatype : "local",
				colModel : [ {
					label : '日期',
					name : 'dayTime',
					index : 'dayTime',
					width : 80
				}, {
					label : '每日领钻用户数',
					name : 'personCount',
					index : 'personCount',
					width : 80
				}, {
					label : '领取信用钻总量',
					name : 'amountBalance',
					index : 'amountBalance',
					width : 80
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
				beforeRequest : function() {
					if ($("#beginDateId").val() == ''
							|| $("#endDateId").val() == '') {
						return;
					}
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
		accountDetail : {}
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
				page : 1// $('.ui-pg-input.form-control').val()
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
        url: "../../ctc/accountdetail/listTotal",
        data: {beginDate:beginDate,endDate:endDate},
        dataType: "json",
        success: function(data){
        	$("#userCount").html("累计领钻用户数："+data.page.tatalUserCountInThisCondition);
        	$("#jewelCount").html("累计领取信用钻数:"+data.page.totalDetailsInThisCondition);
        }
    });
	
}




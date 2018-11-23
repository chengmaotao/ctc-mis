$(function() {
	$("#jqGrid").jqGrid(
			{
				url : baseURL + 'ctc/accountdetail/listmx',
				datatype : "local",
				colModel : [ {
					label : '时间',
					name : 'createTimeVo',
					index : 'createTimeVo',
					width : 80
				}, {
					label : '手机号',
					name : 'phoneNo',
					index : 'phoneNo',
					width : 80
				}, {
					label : '领取信用钻数量 ',
					name : 'amountVo',
					index : 'amountVo',
					width : 80
				}, {
					label : '用户ID',
					name : 'cid',
					index : 'cid',
					width : 80
				}, {
					label : '类型',
					name : 'reason',
					index : 'reason',
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
		accountDetailmx : {}
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
					"userId" : $("#userId").val(),
					"phoneNo" : $("#phoneNo").val(),
					
				},
				datatype : 'json',
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
	var userId =  $("#userId").val();
	var phoneNo = $("#phoneNo").val();;
	$.ajax({
        type: "GET",
        url: "../../ctc/accountdetail/listmxTotal",
        data: {beginDate:beginDate,endDate:endDate,userId:userId,phoneNo:phoneNo},
        dataType: "json",
        success: function(data){
        	$("#userCount").html("累计领钻用户数："+data.page.tatalUserCountInThisCondition);
        	$("#jewelCount").html("累计领取信用钻数:"+data.page.totalDetailsInThisCondition);
        }
    });
	
}
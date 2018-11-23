$(function() {
	$("#jqGrid").jqGrid(
			{
				url : baseURL + 'ctc/analyst/list',
				datatype : "local",
				colModel : [ {
					label : '用户手机号',
					name : 'phoneNo',
					index : 'phoneNo',
					width : 80
				}, {
					label : '用户ID',
					name : 'id',
					index : 'id',
					width : 80
				}, {
					label : '完成财神管家任务获得CTC数量',
					name : 'ctcAmount',
					index : 'ctcAmount',
					width : 80
				}, {
					label : '信用钻兑换获得CTC数量',
					name : 'dhCtcAmount',
					index : 'dhCtcAmount',
					width : 80
				}, {
					label : '提取CTC数量',
					name : 'txCtcAmount',
					index : 'txCtcAmount',
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
		analyst : {}
	},
	methods : {
		query : function() {
			vm.reload();
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
					"endDate" : $("#endDateId").val() + " 24:00:00"
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
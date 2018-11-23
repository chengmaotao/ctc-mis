$(function() {
	$("#jqGrid").jqGrid(
			{
				url : baseURL + 'ctc/customer/customerbeInvitedlist',
				datatype : "local",
				colModel : [ {
					label : '创建时间',
					name : 'vocreatetime',
					index : 'create_time',
					width : 180,
					sortable : false
				}, {
					label : '用户ID',
					name : 'id',
					index : 'id',
					width : 50,
					sortable : false
				}, {
					label : '被邀请人',
					name : 'username',
					index : 'username',
					width : 150,
					sortable : false
				}, {
					label : '邀请人',
					name : 'fromUsername',
					index : 'username',
					width : 150,
					sortable : false
				},
				{
					label : '状态',
					name : 'status',
					index : 'status',
					width : 80,
					sortable : false,
					formatter : function(cellvalue, options, rowObject) {
						if (cellvalue == '0') {
							return "注册未登录";
						} else if (cellvalue == '1') {
							return "登录过";
						} else {
							return "未知";
						}
					}
				},{
					label : '验证码',
					name : 'inviteCode',
					index : 'inviteCode',
					width : 150,
					sortable : false
					
				},
				
				],
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
					"endDate" : $("#endDateId").val() + " 24:00:00",
					"id" : $("#customerId").val(),
					"username" : $("#username").val(),
					"status":$("#status").val(),
					"phoneN":$("#phoneN").val()
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
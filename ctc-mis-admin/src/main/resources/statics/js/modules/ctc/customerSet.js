$(function() {
	$("#jqGrid")
			.jqGrid(
					{
						url : baseURL + 'ctc/customer/list',
						datatype : "local",
						colModel : [
								{
									label : '时间',
									name : 'voupdatetime',
									width : 100,
									sortable : false
								},
								{
									label : '手机号',
									name : 'username',
									sortable : false
								},
								{
									label : '邀请次数设置',
									name : 'inviteRewardCount',
									sortable : false
								},
								{
									label : '已邀请人数',
									name : 'inviteCount',
									sortable : false
								},
								{
									label : '操作',
									name : '',
									formatter : function(cellvalue, options,
											rowObject) {
										return '<a class="btn btn-default" onclick="vm.update('
												+ rowObject.id + ')">修改</a>'
									}
								} ],
						viewrecords : true,
						height : 385,
						rowNum : 10,
						rowList : 60,
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
							if (xhr.code != '0'
									&& typeof (xhr.code) != 'undefined'
									&& xhr.code != '') {
								alert(xhr.msg)
							} else if (xhr.code == 0) {
								if (xhr.page.totalCount == 0) {
									alert("没有查到数据")
								}
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
		getInfo : function(id) {
			$.get(baseURL + "ctc/customer/info/" + id, function(r) {
				vm.customer = r.customer;
			});
		},
		update : function(id) {
			vm.showList = false;
			vm.title = "修改";

			vm.getInfo(id)
		},
		saveOrUpdate : function(event) {
			if(vm.customer.status=='已发送验证码'){
				vm.customer.status=0;
			}
			if(vm.customer.status=='已注册'){
				vm.customer.status=1;
			}
			if(vm.customer.status=='冻结'){
				vm.customer.status=2;
			}
			if(vm.customer.status=='已登录'){
				vm.customer.status=9;
			}
			$.ajax({
				type : "POST",
				url : baseURL + "ctc/customer/update",
				contentType : "application/json",
				data : JSON.stringify(vm.customer),
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
		reload : function(event) {

			if ($("#phoneNo").val() == '') {
				alert("请输入手机号")
				return;
			}
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				postData : {
					"username" : $("#phoneNo").val()
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
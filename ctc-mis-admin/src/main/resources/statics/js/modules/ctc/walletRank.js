$(function() {
	$("#jqGrid")
			.jqGrid(
					{
						url : baseURL + 'ctc/walletasset/rank',
						datatype : "local",
						colModel : [
								{
									label : '时间',
									name : 'voCreateTime',
									width : 100,
									sortable : false
								},
								{
									label : '钱包地址',
									name : 'coinaddr',
									sortable : false
								},
								{
									label : '余额',
									name : 'totalamt',
									sortable : false
								},
								{
									label : '操作',
									name : '',
									formatter : function(cellvalue, options,
											rowObject) {
										return '<a class="btn btn-default" onclick="vm.update('
												+ rowObject.coinaddr + ')">详情</a>'
									}
								} ],
						viewrecords : true,
						height : 385,
						rowNum : 9999,
						//rowList : 60,
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
							//rows : "limit",
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
//								if (xhr.page.totalCount == 0) {
//									alert("没有查到数据")
//								}
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
			console.log("跳转到详情");
//			$.get(baseURL + "ctc/customer/info/" + id, function(r) {
//				vm.customer = r.customer;
//			});
		},
		update : function(coinaddr) {
			vm.showList = false;
			vm.title = "修改";

			vm.getInfo(coinaddr);
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

			if ($("#beginnum").val() == '') {
				alert("请输入排名起点")
				return;
			}
			if ($("#endnum").val() == '') {
				alert("请输入排名终点")
				return;
			}
			vm.showList = true;
			$("#jqGrid").jqGrid('setGridParam', {
				postData : {
					"beginnum" : $("#beginnum").val(),
					'endnum' :$("#endnum").val(),
					'walletaddr' : $("#walletaddr").val(),
					'orderby' : $("#orderby").val(),
				},
				datatype : "json",
				page : 1
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
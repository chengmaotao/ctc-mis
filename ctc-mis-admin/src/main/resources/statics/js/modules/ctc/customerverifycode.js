$(function() {
	$("#jqGrid").jqGrid(
			{
				url : baseURL + 'ctc/customerverifycode/list',
				datatype : "local",
				colModel : [ {
					label : 'id',
					name : 'id',
					index : 'id',
					width : 50,
					key : true
				}, {
					label : '时间',
					name : 'createTimeVo',
					index : 'createTimeVo',
					width : 80
				}, {
					label : '手机号',
					name : 'userName',
					index : 'userName',
					width : 80
				}, {
					label : '验证码',
					name : 'verifyCode',
					index : 'verify_code',
					width : 80
				}],
				viewrecords : true,
				height : 385,
				rowNum : 10,
				rowList : [ 10, 30, 50 ],
				rownumbers : true,
				rownumWidth : 25,
				autowidth : true,
				multiselect : true,
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
		customerVerifyCode : {}
	},
	methods : {
		query : function() {
			vm.reload();
		},
		reload : function(event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				page : 1,//$('.ui-pg-input.form-control').val(),
				postData : {
					"vcode" : $("#vcode").val(),
					"phoneNo" : $("#phoneNo").val()
				},
				datatype : "json"
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
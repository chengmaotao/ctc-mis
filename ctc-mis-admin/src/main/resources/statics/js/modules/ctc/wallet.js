$(function() {
	$("#jqGrid").jqGrid(
			{
				url : baseURL + 'ctc/wallet/walletList',
				datatype : "local",
				colModel : [ {
					label : '创建时间',
					name : 'vocreatetime',
					index : 'createtime',
					width : 150,
					sortable : false
				}, {
					label : '钱包ID',
					name : 'walletid',
					index : 'walletid',
					sortable : false
				}, {
					label : '钱包地址',
					name : 'coinaddr',
					index : 'coinaddr',
					sortable : false
				}

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
		q : {},
		showList : true,
		title : null,
		wallet : {}
	},
	methods : {
		query : function() {

			if (vm.q.walletAddress != null
					&& vm.q.walletAddress.trim().length < 4) {
				console.log(vm.q.walletAddress);
				if(vm.q.walletAddress==''){
					
				}else{
					alert("钱包地址至少输入4位长度");
					return;
				}
				
			}
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
					"walletAddress" : vm.q.walletAddress
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
	var status=$("#status").val();
	$.ajax({
        type: "GET",
        url: "../../ctc/wallet/walletListTotal",
        data: {beginDate:beginDate,endDate:endDate,username:username,id:id,status:status},
        dataType: "json",
        success: function(data){
        	$("#wallet").html("累计新增钱包  :"+data.page.tatalUserCountInThisCondition);
        	$("#addr").html("累计新增钱包地址 :"+data.page.tatalUserCountInThisCondition);
        	
        }
    });
	
}
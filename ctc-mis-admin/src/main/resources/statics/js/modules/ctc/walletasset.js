$(function() {
	$("#jqGrid").jqGrid(
			{
				url : baseURL + 'ctc/walletasset/walletDataCount',
				datatype : "local",
				colModel : [ {
					label : '日期',
					name : 'voStrDate',
					index : 'voStrDate',
					width : 100,
					sortable : false
				}, {
					label : '新增钱包地址数',
					name : 'walletAddressCount',
					index : 'walletAddressCount',
					width : 150,
					sortable : false
				}, {
					label : '交易总量',
					name : 'tranAmtCount',
					index : 'tranAmtCount',
					width : 300,
					sortable : false
				}, {
					label : '交易总笔数',
					name : 'tranCount',
					index : 'tranCount',
					width : 150,
					sortable : false
				}, {
					label : '交易地址数',
					name : 'tranAddressCount',
					index : 'tranAddressCount',
					width : 300,
					sortable : false
				}, ],
				viewrecords : true,
				height : 385,
				rowNum : 60,
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
		walletAsset : {}
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
        url: "../../ctc/walletasset/walletDataCountTotal",
        data: {beginDate:beginDate,endDate:endDate},
        dataType: "json",
        success: function(data){
        	$("#walletCount").html("累计钱包地址数 :"+data.page.buyCount);
        	$("#exSum").html("累计交易总量:"+data.page.moneyCount);
        	$("#exnum").html("累计交易笔数::"+data.page.tatalUserCountInThisCondition);
        	$("#exAddr").html("累计交易地址数 :"+data.page.exchangeNum);
        	
        }
    });
	
}
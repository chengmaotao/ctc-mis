$(function() {
	$("#jqGrid").jqGrid(
			{
				url : baseURL + 'ctc/wallettran/wallettranList',
				datatype : "local",
				colModel : [ {
					label : '交易创建时间',
					name : 'vocreatetime',
					index : 'createtime',
					sortable : false
				}, {
					label : '货币类型',
					name : 'cointype',
					index : 'cointype',
					width : 80,
					sortable : false
				}, {
					label : '交易ID',
					name : 'txid',
					index : 'txid',
					width : 320,
					sortable : false
				}, {
					label : '交易金额',
					name : 'tranamt',
					index : 'tranamt',
					sortable : false
				}, {
					label : '交易手续费',
					name : 'tranfee',
					index : 'tranfee',
					sortable : false
				}, {
					label : '交易支付地址',
					name : 'srcaddr',
					index : 'srcaddr',
					width : 300,
					sortable : false
				}, {
					label : '交易收款地址',
					name : 'dstaddr',
					index : 'dstaddr',
					width : 300,
					sortable : false
				}, {
					label : '备注',
					name : 'bak',
					index : 'bak'
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
		q : {},
		showList : true,
		title : null,
		walletTran : {}
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
        url: "../../ctc/wallettran/wallettranListTotal",
        data: {beginDate:beginDate,endDate:endDate},
        dataType: "json",
        success: function(data){
        	$("#free").html("累计交易手续费 :"+data.page.ctcCount);
        	$("#amt").html("累计交易金额 :"+data.page.moneyCount);
        }
    });
	
}

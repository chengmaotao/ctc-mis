$(function() {
    $("#jqGrid").jqGrid({
        url: baseURL + 'ctc/misyyzlbd/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true , hidden:true},
			{ label: '标题', name: 'title', index: 'title', width: 80 }, 			
			{ label: '频道', name: 'channel', index: 'channel', width: 80 }, 			
			{ label: '时间', name: 'time', index: 'time', width: 80 }, 			
			{ label: '链接', name: 'url', index: 'url', width: 80 }, 			
			{ label: '语言', name: 'language', index: 'language', width: 80 }, 			
			{ label: '创建时间', name: 'voCreateTime', index: 'create_time', width: 80 }, 			
			{ label: '修改时间', name: 'voUpdateTime', index: 'update_time', width: 80 }			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		misYyZlbd: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.misYyZlbd = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.misYyZlbd.id == null ? "ctc/misyyzlbd/save" : "ctc/misyyzlbd/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.misYyZlbd),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "ctc/misyyzlbd/delete",
                    contentType: "application/json",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get(baseURL + "ctc/misyyzlbd/info/"+id, function(r){
                vm.misYyZlbd = r.misYyZlbd;
            });
		},
		reload: function (event) {
			vm.showList = true;
			
			if ($("#beginDateId").val() != '' && $("#endDateId").val() != '') {
				if ($("#beginDateId").val() > $("#endDateId").val()) {
					alert("开始日期不能大于结束日期")
					return;
				}
			}
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{
					'beginTime' : $("#beginDateId").val() + " 00:00:00",
					"endTime" : $("#endDateId").val() + " 24:00:00",
					"title" : $("#title").val(),
					"langu" : $("#langu").val(),
					"source" : $("#source").val()
				},
				
                page: 1//$('.ui-pg-input.form-control').val()
            }).trigger("reloadGrid");
		}
	}
});
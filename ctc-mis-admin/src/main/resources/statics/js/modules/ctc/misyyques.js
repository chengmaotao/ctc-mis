$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'ctc/misyyques/list',
        datatype: "json",
        colModel: [
			{ label: '问题', name: 'question', index: 'question', width: 500 },
			{ label: '选项A', name: 'answerA', index: 'answer_a', width: 300 },
			{ label: '选项B', name: 'answerB', index: 'answer_b', width: 300 },
			{ label: '选项C', name: 'answerC', index: 'answer_c', width: 300 },
			{ label: '选项D', name: 'answerD', index: 'answer_d', width: 300 },
            { label: '正确答案', name: 'answerRight', index: 'answer_right', width: 80 },
            { label: '关键字', name: 'keyword', index: 'keyword', width: 150 },
			{ label: '奖励类型', name: 'type', index: 'type', width: 100,
				formatter: function (cellvalue, options, rowObject) {
                    if (cellvalue == 0) {
                        return "信用钻";
                    } else if (cellvalue == 1) {
                        return "算力";
                    } else if (cellvalue == 2) {
                        return "CTC";
                    }
                }
            },
			{ label: '奖励数量', name: 'count', index: 'count', width: 80 },
			{ label: '更新时间', name: 'updateTimeVo', index: 'update_time_vo', width: 200 },
			{ label: '语言设置', name: 'language', index: 'language', width: 80}
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        shrinkToFit:false,
        autoScroll: true,
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
        	// $("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });
    var btnimport = document.getElementById("import_file");
    $("#ajaxForm").submit(function () {
        btnimport.setAttribute("disabled", true);
        $(this).ajaxSubmit({
            success: function (responseText, statusText, xhr, $form) {
                if (responseText.code == 0) {
                    alert("导入成功", function () {
                        vm.reload();
                    });
                    // showDialog("导入成功",true);
                } else if (responseText.code == 1) {
                    alert("请先导入Excel文件")
                } else if (responseText.code == 2) {
                    alert("文件格式错误，请重新选择")
                } else if (responseText.code == 3) {
                    alert("导入失败");
                } else if(responseText.code == 4){
                    alert(responseText.msg)
                }
                var inputFile = document.getElementById("file_input");
                inputFile.outerHTML = inputFile.outerHTML;
                btnimport.removeAttribute("disabled");
            }
        });
        return false;
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		misYyQues: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.misYyQues = {};
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
			var url = vm.misYyQues.id == null ? "ctc/misyyques/save" : "ctc/misyyques/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.misYyQues),
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
				    url: baseURL + "ctc/misyyques/delete",
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
			$.get(baseURL + "ctc/misyyques/info/"+id, function(r){
                vm.misYyQues = r.misYyQues;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page: 1//$('.ui-pg-input.form-control').val()
            }).trigger("reloadGrid");
		}
	}
});
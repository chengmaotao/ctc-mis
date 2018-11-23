$(function () {
    // confirm("1233")
    $("#jqGrid").jqGrid({
        url: baseURL + 'ctc/misyyexc/list',
        datatype: "json",
        colModel: [
            {label: '手机号', name: 'phone', index: 'phone', width: 80},
            {label: '兑换码', name: 'dhcode', index: 'dhcode', width: 60},
            {label: '兑换数量', name: 'count', index: 'count', width: 50},
            {
                label: '奖励类型', name: 'type', index: 'type', width: 50,
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
            {label: '创建时间', name: 'createTimeVo', index: 'create_time_vo', width: 120},
            {label: '过期时间', name: 'expireTimeVo', index: 'expire_time_vo', width: 120},
            {label: '微信昵称', name: 'wxNickName', index: 'wx_nick_name', width: 80},
            {label: '标签', name: 'mark', index: 'mark', width: 80},
            {
                label: '使用状态', name: 'useState', index: 'use_state', width: 80,
                formatter: function (cellvalue, options, rowObject) {
                    if (cellvalue == 0) {
                        return "未核销";
                    } else if (cellvalue == 1) {
                        return "已核销";
                    }
                }
            }
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: false,
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
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

function showDialog(msg, isShow) {
    var motai_html = '<div class="motai"> <div class="title_box"> <p>' + msg + '</p > <button id="motai_btn">确定</button> </div> </div>';
    $('body').append(motai_html)
    $(document).on('click', '#motai_btn', function () {
        $('motai').remove();
        if (isShow) {
            window.location.reload();
        }
    })
}

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        misYyExc: {}
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.misYyExc = {};
        },
        update: function (event) {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id)
        },
        saveOrUpdate: function (event) {
            var url = vm.misYyExc.id == null ? "ctc/misyyexc/save" : "ctc/misyyexc/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.misYyExc),
                success: function (r) {
                    if (r.code === 0) {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        del: function (event) {
            var ids = getSelectedRows();
            if (ids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "ctc/misyyexc/delete",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function (r) {
                        if (r.code == 0) {
                            alert('操作成功', function (index) {
                                $("#jqGrid").trigger("reloadGrid");
                            });
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        getInfo: function (id) {
            $.get(baseURL + "ctc/misyyexc/info/" + id, function (r) {
                vm.misYyExc = r.misYyExc;
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                page: 1,//$('.ui-pg-input.form-control').val(),
                postData: {
                    phone: vm.misYyExc.phone,
                    mark: vm.misYyExc.mark
                }
            }).trigger("reloadGrid");
        },
    }
});
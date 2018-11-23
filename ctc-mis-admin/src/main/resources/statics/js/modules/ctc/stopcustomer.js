$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'ctc/stopcustomer/list',
        datatype: "json",
        colModel: [
            {label: '用户名', name: 'username', index: 'username', width: 80},
            {label: '状态', name: 'status', index: 'status', width: 80,
                formatter : function(cellvalue, options,
                                                                                                               rowObject) {
                    if (cellvalue == 0) {
                        return "已发送验证码";
                    } else if (cellvalue == 1) {
                        return "已注册";
                    } else if (cellvalue == 2) {
                        return "冻结";
                    } else if(cellvalue == 3){
                        return "注销";
                    }
                }},
            {label: '创建时间', name: 'createTimeVo', index: 'create_time_vo', width: 80},
            {
                label: '操作',
                name: 'pId',
                index: 'pId',
                width: 80,
                formatter: function (cellvalue, options,
                                     rowObject) {
                    return '<a class="btn btn-default" onclick="vm.freeze(\''
                        + rowObject.id + '\')">冻结</a>' +
                        '<a class="btn btn-default" style="margin-left: 20px" onclick="vm.thaw(\''
                        + rowObject.id + '\')">解冻</a>'
                }
            },

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
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        stopCustomer: {}
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.stopCustomer = {};
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
            var url = vm.stopCustomer.id == null ? "ctc/stopcustomer/save" : "ctc/stopcustomer/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.stopCustomer),
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
                    url: baseURL + "ctc/stopcustomer/delete",
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
            $.get(baseURL + "ctc/stopcustomer/info/" + id, function (r) {
                vm.stopCustomer = r.stopCustomer;
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                page:  1,//$('.ui-pg-input.form-control').val(),
                postData: {
                    phone: vm.stopCustomer.phone,
                }
            }).trigger("reloadGrid");
        },
        freeze: function (id) {
            var url = "ctc/stopcustomer/freeze";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(id),
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
        thaw: function (id) {
            var url = "ctc/stopcustomer/thaw";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(id),
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
        }
    }
});
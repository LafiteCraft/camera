/**
 * Created by Gangdan on 2017/4/26.
 */
var vm = new Vue({
    el:'#app',
    data:{           //向后台传递的信息，得到数据。
        dataList:[],
        dataCurr:1,
        dataRoleName:'',
        dataTotalResult : 0,
        dataRoleId:''
    },
    mounted:function(){
        this.queryListByName();    //执行methods里面的定义的方法，获取数据和page信息。
    },
    methods:{
        seltAll:function(){     //全选方法。
            var checkBoxBut = document.getElementById("CheckAll");
            var checkBox = document.getElementsByName("checkBox");
            if(checkBoxBut.checked){
                for(var i = 0 ; i < checkBox.length ; i ++){
                    checkBox.value =
                        checkBox[i].checked = true;
                }
            }else{
                for(var i = 0 ; i < checkBox.length ; i ++){
                    checkBox[i].checked = false;
                }
            }
        },
        delecheckBox:function(){    //多项删除方法和单项删除方法。
            var checkBox = document.getElementsByName("checkBox");
            var str = [];
            for(var i = 0 ; i < checkBox.length; i ++){
                if(checkBox[i].checked){
                    str.push(checkBox[i].value);
                };
            };
            if(str!=""){
                layer.confirm('确定要删除所选信息？', //layer的询问弹出窗口，代替if，
                  function(){
                    $.ajax({
                        data:{"idList":JSON.stringify(str)},
                        type:'post',
                        url:'/role/deleteRoleByIdList',
                        dataType:'json',
                        success:function(data){
                            layer.msg('已删除!',{icon:1,time:1000});
                            vm.reloadList();
                        },
                        error:function(data){
                            layer.msg('删除失败!',{icon:1,time:1000});
                        }
                    })
                });
            }
        },
        queryList : function(){     //点击查询事件
            this.$set(vm,'dataCurr',1);
            this.$set(vm,'dataRoleName',$("#roleName").val());
            this.queryListByName();
        },
        queryListByName : function(){                       //请求数据
            var checkBox = document.getElementsByName("checkBox");
            for(var i = 0 ; i < checkBox.length ; i ++){
                checkBox[i].checked = false;
            }
            document.getElementById('CheckAll').checked = false;
            var lest = this;
            $.ajax({
                url:"/role/findRoleByName",
                type:"post",
                data:{
                    pageNum:lest.dataCurr,
                    pageSize:10,
                    roleName:lest.dataRoleName,
                    dataTotalResult:lest.total
                },
                success:function(result){
                    lest.dataTotalResult = result.total;
                    if(result.list == ""){
                        layer.msg('没有相关角色名称!',{icon:1,time:1500});
                        var sotext = document.getElementById("roleName");
                        sotext.value = "";

                    }else{
                        lest.$set(vm,'dataList',result.list);
                        var totalPage = result.navigateLastPage;
                        laypage({                               //分页插件。
                            cont:$('#page1'),
                            pages:totalPage,
                            curr:lest.dataCurr,
                            jump:function(obj,frist){
                                if(!frist){
                                    lest.$set(vm,'dataCurr',obj.curr);
                                    lest.queryListByName();
                                }
                            }
                        })
                    }
                }
            })
        },
        reloadList : function(){    //删除成功要从新渲染页面。
            var checkBox = document.getElementsByName("checkBox");
            for(var i = 0 ; i < checkBox.length ; i ++){
                checkBox[i].checked = false;
            }
            document.getElementById('CheckAll').checked = false;
            this.$set(vm,'dataCurr',1);
            this.queryListByName();
        },
        delAdmin:function(id){
            var lest = this;
            layer.confirm('确定要删除吗？',function(index){
                $.ajax({
                    type:"post",
                    url:"/role/deleteRole",
                    data:{id:id},
                    dataType:"json",
                    success:function (result) {
                        if(result > 0){
                            layer.msg('已删除!',{icon:1,time:1000});
                            lest.reloadList();
                        }else {
                            layer.msg('删除失败!',{icon:1,time:1000});
                        }
                    }
                })
            });
        },
        addAmin:function(title,url,w,h){
            layer_show(title,url,w,h);
        },
        editAdmin:function(id){
            title = "修改角色";
            url= "/role/updateFor?roleId="+id;
            w = 570;
            h = 400;
            layer_show(title,url,w,h);
        }
    }
})
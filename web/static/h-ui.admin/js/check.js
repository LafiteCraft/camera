/**
 * Created by Gangdan on 2017/4/19.
 */
function showTip(title,id){
    layer.tips(title,"#" + id,{tips : [3,"#5a98de"]})
};
/*var regList = [
    {
        name : 'name',
        id : 'name',
        reg : ['notnull','textlength','textnumber','textcn']
    }
];*/
var validate = function(regList){
    for(var i = 0,l = regList.length;i < l;i = i + 1){
        var obj = regList[i];
        var objList = obj.reg;
        for(var j = 0,ll = objList.length;j < ll;j = j + 1){
            if(objList[j] === 'notnull'){
                if(document.getElementById(obj.id).value === ''){
                    showTip(obj.name + '名称不能为空',obj.id);
                    window.scrollTo(0,obj.id.scrollHeight);
                    return false;
                }
            }else if(objList[j] === 'textlength'){
                if(document.getElementById(obj.id).value.length<3 || document.getElementById(obj.id).value.length>16){
                    showTip(obj.name + '名称过长或过短',obj.id);
                    window.scrollTo(0,obj.id.scrollHeight);
                    return false;
                }
            }else if(objList[j] === 'textnumber'){
                var reg = /[0-9]/g;
                if(!reg.test(document.getElementById(obj.id).value)){
                    showTip(obj.name + '只能为数字',obj.id);
                    window.scrollTo(0,obj.id.scrollHeight);
                    return false;
                }
            }else if(objList[j] === 'textcn'){
                if(!/[\u4e00-\u9fa5]/.test(document.getElementById(obj.id).value)){
                    showTip(obj.name + '只能为汉字',obj.id);
                    window.scrollTo(0,obj.id.scrollHeight);
                    return false;
                }
            }else if(objList[j] === 'notspecial'){
                var special = /(?=[\x21-\x7e]+)[^A-Za-z0-9]/;
                if(special.test(document.getElementById(obj.id).value)){
                    showTip(obj.name + '内部含有非法字符',obj.id);
                    window.scrollTo(0,obj.id.scrollHeight);
                    return false;
                }
            }else if(objList[j] === 'namelength'){
                if(document.getElementById(obj.id).value.length<2 || document.getElementById(obj.id).value.length>6){
                    showTip(obj.name+ '请输入正确姓名',obj.id);
                    window.scrollTo(0,obj.id.scrollHeight);
                    return false;
                }
            }else if(objList[j] === 'meID'){
                if(!/^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/.test(document.getElementById(obj.id).value)){
                    showTip(obj.name + '输入有误！',obj.id);
                    window.scrollTo(0,obj.id.scrollHeight);
                    return false;
                }
            }else if(objList[j] === 'phone'){
                if(!/^1[34578]\d{9}$/.test(document.getElementById(obj.id).value)){
                    showTip(obj.name + '填写有误!',obj.id);
                    window.scrollTo(0,obj.id.scrollHeight);
                    return false;
                }
            }else if(objList[j] === 'email'){
                if(!/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/.test(document.getElementById(obj.id).value)){
                    showTip(obj.name + '填写有误!',obj.id);
                    window.scrollTo(0,obj.id.scrollHeight);
                    return false;
                }
            }else if(objList[j] === 'qq'){
                if(!/^\d{5,10}$/.test(document.getElementById(obj.id).value)){
                    showTip(obj.name + '填写有误!',obj.id);
                    window.scrollTo(0,obj.id.scrollHeight);
                    return false;
                }
            }else if(objList[j] === 'mobile'){
                var mymobile = /^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
                var myphone = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
                if(!myphone.test(document.getElementById(obj.id).value) || !mymobile.test(document.getElementById(obj.id).value)){
                    showTip(obj.name + '填写有误!',obj.id);
                    window.scrollTo(0,obj.id.scrollHeight);
                    return false;
                }
            }else if(objList[j] === 'notcn'){
                if(/[\u4e00-\u9fa5]/.test(document.getElementById(obj.id).value)){
                    showTip(obj.name + '是由字母，数字组成的，和符号组成的',obj.id);
                    window.scrollTo(0,obj.id.scrollHeight);
                    return false;
                }
            }else if(objList[j] === 'minlength'){
                if(document.getElementById(obj.id).value.length>2){
                    showTip(obj.name + '输入错误！',obj.id);
                    window.scrollTo(0,obj.id.scrollHeight);
                    return false;
                }
            }
        }
    }
    return true;
}

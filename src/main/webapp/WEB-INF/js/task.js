/**
 * Created by yp on 2017/4/26.
 */

//存放主要交互逻辑js代码
//JavaScript模块化
var task = {
    //封装秒杀相关ajax的url
    URL : {
        login: function (phone) {
            return '/task/login'
        },
        list : function () {
            return '/task/list';
        },
        receive : function () {
            return '/task/';
        },
        detail : function (tid) {
            return '/task/'+tid+'/'+'detail';
        }
    },

}
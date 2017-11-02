<%@ page contentType="text/html; charset=UTF-8"  %>
<html>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<style>
    .taskTitle{text-align:center}
</style>
<script type="text/javascript">

    var uid = $.cookie('uid');
    if (!uid) {
        var phone = input();

        $.ajax({
            type: "post",
            dataType: "json",
            url: '/task/user/login',
            data: {phone: phone},
            success: function (data) {
                if (data != "" && data.msg=='success') {
                    alert("登录success");
                }
                getScore();
                getTaskList();

            },
            erro: function (data) {
                alert("erro");
            }
        });
    } else {
        getScore();
        getTaskList();
    }

    function input() {
        return window.prompt("输入手机号登录", "testPhone");
    }

    function getScore() {
        $.ajax({
            type: "get",
            dataType: "json",
            url: '/task/user/score',
            data: {uid: $.cookie('uid')},
            success: function (data) {
                if (data != "") {
                    $("#score").html("用户积分："+data.data);
                }
            },
            erro: function (data) {
                alert("erro");
            }
        });
    }

    function getTaskList() {
        $.ajax({
            type: "get",
            dataType: "json",
            url: '/task/list',
            data: {uid: $.cookie('uid')},
            success: function (data) {
                if (data != "") {
                    //alert("success");
                    var list = data.data;
                    if (data.msg == 'fail') {
                        alert("参数异常");
                        return;
                    }
                    for(var i in list) {
                        var data = list[i];
                        var node;
                        if (list[i].type == 0) {
                            node = "#onceTask"
                        } else {
                            node = "#generalTask";
                        }
                        var state;
                        if (data.state == -1) {
                            state = "可领取";
                        } else if (data.state == 0) {
                            state = "进行中";
                        } else {
                            state = "已完成";
                        }
                        var stateNode = "<br/><span>" + state + "</span>";
                        var scoreNode = "<br/><span>" + "进度：" +data.curScore +"/" + data.totalScore + "</span>";
                        var joinNumNode = "<br/><span>参与人数:" + data.joinNum + "</span>";
                        var rbtn = "<br/><button class='rbtn' value='" + list[i].tid + "'>领取</button>";
                        var dbtn = "<button class='dbtn' value='" + list[i].tid + "'>做任务</button>";
                        $(node).append("<div>"+list[i].desc
                            + stateNode + scoreNode
                            + joinNumNode + rbtn + dbtn
                            + "</div>");

                    }
                    registerBtn();
                }
            },
            erro: function (data) {
                alert("erro");
            }
        });
    }


    function registerBtn() {

        // 领取任务
        $(".rbtn").click(function(){
            $.post("/task/"+$(this).val()+"/receive",
                {
                    uid : $.cookie('uid')
                },
                function(data,status){
                    if (data.msg == 'fail') {
                        alert("参数异常");
                        return;
                    }
                    $(".task").html('');
                    alert("执行成功");
                    getScore();
                    getTaskList();
                });
        });

        // 做任务

        $(".dbtn").click(function(){

            $.post("/task/"+$(this).val()+"/finish",
                {
                    uid : $.cookie('uid')
                },
                function(data,status){
                    if (data.msg == 'fail') {
                        alert("参数异常");
                        return;
                    }
                    $(".task").html('');
                    alert("执行成功");
                    getScore();
                    getTaskList();
                });
        });
    }


</script>
<body>
    <h2 id="score">用户积分：0</h2>
    <div class="taskTitle" >新手任务</div><hr/>
    <dev class="task" id="onceTask"></dev>
    <div class="taskTitle" >常见任务</div><hr/>
    <dev class="task" id="generalTask"></dev>
</body>
</html>

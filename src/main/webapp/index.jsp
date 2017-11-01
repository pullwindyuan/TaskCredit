<html>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">

    $.ajax({
        type: "get",
        dataType: "json",
        url: '/task/list',
        data: {uid:'testUid'},
        success: function (data) {
            if (data != "") {
                alert("success");

            }
        },
        erro: function (data) {

        }
    });

    $("btn_receive").click(function(){
        $.post("/try/ajax/demo_test_post.php",
            {
                name:"菜鸟教程",
                url:"http://www.runoob.com"
            },
            function(data,status){
                alert("数据: \n" + data + "\n状态: " + status);
            });
    });

</script>
<body>
<h2>Hello World!</h2>
<button id="btn_receive" name="领取"/>
</body>
</html>

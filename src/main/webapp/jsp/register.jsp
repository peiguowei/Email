<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户注册</title>
    <!--导入bootstrap样式-->
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
    <link href="../css/fileUpload.css" rel="stylesheet" type="text/css" />
    <!--导入jquery依赖-->
    <script type="text/javascript" src="../js/jquery-1.12.3.min.js"></script>
    <!--导入bootstrap js库-->
    <script type="text/javascript" src="../js/bootstrap.min.js"></script>
    <script src="../js/test.js" type="text/javascript"></script>
    <script>
        $(function () {
            $("#up").uploadPreview({ Img: "ImgPr", Width: 120, Height: 120 });
        });
        $(document).ready(function () {
            $("form").submit(function () {
                var email = $("input[name='email']").val();
                var pass1 = $("input[name='password1']").val();
                var pass2 = $("input[name='password2']").val();
                var age = $("input[name='age']").val();
                var file = $("input[name='file']").val();
                if (isEmpty(email)){
                    event.preventDefault();//阻止默认行为
                    $("#error").text("邮箱不能为空")
                    return;
                }
                if (isEmpty(pass1)){
                    event.preventDefault();//阻止默认行为
                    $("#error").text("密码不能为空")
                    return;
                }
                if (isEmpty(pass2)){
                    event.preventDefault();//阻止默认行为
                    $("#error").text("再次输入密码不能为空")
                    return;
                }
                if (pass1!=pass2){
                    event.preventDefault();//阻止默认行为
                    $("#error").text("两次输入密码不一致")
                    return;
                }
                if (isEmpty(age)){
                    event.preventDefault();//阻止默认行为
                    $("#error").text("年龄不能为空")
                    return;
                }
                var hobbies = document.getElementsByName("hobbies");
                if(hobbies[0].checked==false&&hobbies[1].checked==false){
                    event.preventDefault();
                    $("#error").text("爱好不能为空")
                    return;
                }
                if (isEmpty(file)){
                    event.preventDefault();//阻止默认行为
                    $("#error").text("头像不能为空")
                    return;
                }
            })
        })
        function isEmpty(str) {
            if (str==null||str==undefined||str==""){
                return true;
            }
            return false;
        }
    </script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <h2 class="text-center">用户注册</h2>
        <div class="col-xs-4 col-xs-offset-4">
            <!--提示信息-->
            <h6 id="error" style="color: red">${requestScope.error}</h6>
            <form role="form" action="/register" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="exampleInputEmail1">用户名</label>
                    <input type="email" class="form-control" id="exampleInputEmail1" placeholder="请输入邮箱" name="email">
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">密码</label>
                    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="请输入密码" name="password1">
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword2">再次输入密码</label>
                    <input type="password" class="form-control" id="exampleInputPassword2" placeholder="请再次输入密码" name="password2">
                </div>
                <div class="form-group">
                    <label for="exampleInputAge">年龄</label>
                    <input type="text" class="form-control" id="exampleInputAge" placeholder="请输入年龄" name="age">
                </div>
                <div class="form-group">
                    <label>性别:</label>
                    <input type="radio"  name="gender" value="true" checked>男
                    <input type="radio"   name="gender" value="false">女
                </div>
                <div class="form-group">
                    <label>爱好:</label>
                    <input type="checkbox"  name="hobbies" value="look">看书
                    <input type="checkbox"  name="hobbies" value="write">写作
                </div>
                <!--<div class="form-group">
                    <label for="exampleInputFile">上传头像</label>
                    <input type="file" id="exampleInputFile">
                </div>-->
                <div id="header">
                    <h6 class="title">
                        <strong>头像显示</strong></h6>
                    <div id="main">
                        <div class="card" id="up_img">
                            <div class="summary">
                                <ul>
                                    <li>
                                        <div style=" width:120px; height:120px;">
                                            <img id="ImgPr" width="120" height="120" style="display: inherit;" /></div>
                                        <input type="file" id="up" name="file"/>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xs-offset-3 col-xs-9">
                <button type="submit" class="btn btn-primary">注册</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>邮箱</title>
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
    <script type="text/javascript" src="../js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="../js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="../css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="../css/normalize.css" />
    <%--<link rel="stylesheet" type="text/css" href="../css/default.css">--%>
    <link rel="stylesheet" type="text/css" href="../css/wysiwyg-editor.css" />
    <link  rel="stylesheet" type="text/css" href="../css/demo.css"/>
    <script type="text/javascript" src="../js/wysiwyg.js"></script>
    <script type="text/javascript" src="../js/wysiwyg-editor.js"></script>
    <script type="text/javascript" src="../js/diy/content.js"></script>
    <script type="text/javascript" src="../js/demo.js"></script>
    <%--<script>
        $(document).ready(function () {
            $(".first").click(function () {
                $(".first").addClass("active")
                $(".two").css("color","black")
            })
            $(".first").mouseleave(function () {
                $(".first").removeClass("active")
                $(".two").css("color","white")
            })
            $("#three").hover(function () {
                $("#three").addClass("active")
                $("#submit").css("color","black")
            },function () {
                $("#three").removeClass("active")
                $("#submit").css("color","white")
            })
            $(".a").hover(function () {
                $(this).addClass("btn-primary")
                $(".a").css("color","black")
                $(this).css("color","white")
            },function () {
                $(".a").css("color","black")
            })
            $("#a1").click(function () {
                $("#a1").addClass("btn-primary")
                $("#a1").removeClass("btn-info")
                $("#collectEmail").hide()
                $("#sendEmail").show(function () {
                    $("#save").click(function () {
                        var data = $("#write").serialize();
                        var recipients = $("input[name='recipients']").val();
                        var content = $("#content").val();
                        var title = $("input[name='title']").val();
                        if (isEmpty(recipients)){
                            event.preventDefault()//阻止默认行为
                            $("#errorOne").html("收件人不能为空")
                            return;
                        }
                        if (isEmpty(title)){
                            event.preventDefault()//阻止默认行为
                            $("#errorOne").html("主题不能为空")
                            return;
                        }
                        if (isEmpty(content)){
                            event.preventDefault()//阻止默认行为
                            $("#errorOne").html("内容不能为空")
                            return;
                        }
                        var data = $("#write").serialize();
                        $.post("/saveDraft",data,function (message) {
                            if(message.indexOf("success")!=-1){
                                alert("保存草稿成功")
                            }else{
                                $("#errorOne").html(message)
                            }
                        },"text")
                    })
                })
            })
            $("#a2").click(function () {
                $("#a2").addClass("btn-primary")
                $("#a2").removeClass("btn-info")
                $("#sendEmail").hide()
                $("#collectEmail").show(function () {
                  $.get("/seeEmail").success(function (mess) {
                      $("#collectMessage").html(mess)
                      showEmail();
                  })
                })
            })
            $("#a3").click(function () {
                $("#a3").addClass("btn-primary")
                $("#a3").removeClass("btn-info")
                $("#sendEmail").hide()
                $("#collectEmail").hide()
            })
            $("#submit").click(function () {
                $("#modal").show();
            })
            $("#down").click(function () {
                $("#modal").hide();
            })
            $("#downOne").click(function () {
                $("#modal").hide();
            })
            $("#login").click(function () {
                var email = $("input[name='emailName']").val();
                var password = $("input[name='password']").val();
                if(email==null||email==undefined||email==""){
                    event.preventDefault()//阻止默认行为
                    $("#error").html("邮箱不能为空")
                    return;
                }
                if(password==null||password==undefined||password==""){
                    event.preventDefault()//阻止默认行为
                    $("#error").html("密码不能为空")
                    return;
                }
                var data = $("form").serialize();
                //$.post(path,传输参数对象，成功回调函数，错误回调函数)
                $.post("/login",data,function (message) {
                    if (message.indexOf("success")!=-1){
                       self.location="/content.html"

                    }else{
                        $("#error").html(message)
                    }
                },"text")
            })
        })
        function isEmpty(value) {
            if(value==null||value==undefined||value==""){
                return true;
            }
            return false;
        }
        function showEmail() {
            var $test = $("#collect tr td[send-id]")
            $.each($test,function () {
                var $2 = $(this);
                var sendId = $2.attr("send-id");//取值
                $.get("/queryUserByName?id="+sendId).success(function (mess) {
                    $2.html(mess);
                })
            })
        }
    </script>--%>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <nav class="navbar navbar-default" role="navigation" style="background-color: #6089d4">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" style="color: black;font-size: 30px;cursor:pointer"><b>邮箱</b></a>
                </div>
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li class="first"><a class="two" style="color: white;cursor:pointer">邮箱首页</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <c:if test="${sessionScope.user==null}">
                            <li id="three"><a id="submit" style="color: white;cursor:pointer">登录</a></li>
                        </c:if>
                        <li><a style="color: white;cursor: pointer" id="userEmail">${sessionScope.user.email}</a></li>
                        <form class="navbar-form navbar-left" role="search">
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="邮件搜索...">
                            </div>
                            <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
                        </form>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
    <div class="row">
        <ul class="list-group col-xs-1">
            <li class="list-group-item a" id="a1" style="cursor: pointer"><span class="glyphicon glyphicon-pencil"></span>&nbsp;<b>写&nbsp;信</b></li>
            <li class="list-group-item a" id="a2" style="cursor: pointer"><span class="glyphicon glyphicon-envelope"></span>&nbsp;<b>收件箱</b></li>
            <li class="list-group-item a" id="a3" style="cursor: pointer"><span class="glyphicon glyphicon-file"></span>&nbsp;<b>草稿箱</b></li>
        </ul>
        <div class="col-xs-9" id="sendEmail" style="display: none">
            <h4  style="color: red" id="errorOne" align="center"></h4>
            <form id="write">
                <h3>写信:</h3>
                <hr style="border-color:#FFFFFF"/>
                <div class="input-group">
                    <input type="hidden" name="sendEmailId" value="${sessionScope.user.id}">
                    <span class="input-group-addon"><b>收件人:</b></span>
                    <input type="text" class="form-control" placeholder="请输入收件人" name="recipients">
                </div>
                <hr style="border-color:#FFFFFF"/>
                <div class="input-group">
                    <span class="input-group-addon"><b>主&nbsp;&nbsp;&nbsp;题:</b></span>
                    <input type="text" class="form-control" placeholder="请输入主题" name="title">
                </div>
                <hr style="border-color:#FFFFFF"/>
                <div class="container">
                    <div class="col-xs-5">
                        <div class="main">
                            <div style="width:860px; margin: 30px auto;">
                                <textarea id="editor1" name="content" placeholder="请将信件内容写入此处"></textarea>
                            </div>
                        </div>
                    </div>
                </div>
                <hr style="border-color:#FFFFFF"/>
                <div class="col-xs-4 col-xs-offset-10">
                    <button type="button" class="btn btn-primary" id="save">保存草稿</button>
                    <button type="button" class="btn btn-primary" id="send">发送邮件</button>
                </div>
            </form>
        </div>
        <div class="col-xs-9" id="collectEmail" style="display: none">
            <div id="collectMessage">

            </div>
        </div>
        <div class="col-xs-9" id="oneEmail" style="display: none">
            <div id="oneEmailMessage">

            </div>
        </div>
    </div>
    <div class="row">
        <div class="modal fade in" id="modal">
            <div class="modal-backdrop fade in"></div>
            <div class="modal-dialog in">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close">
                            <span aria-hidden="true" id="downOne">&times;</span></button>
                        <h4 class="modal-title">用户登录</h4>
                        <h6 id="error" style="color: red"></h6>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label class="col-xs-3">用户名</label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" name="emailName">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-3">密码</label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" name="password">
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" id="login">登录</button>
                                <button type="button" class="btn btn-primary" id="down">取消</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>

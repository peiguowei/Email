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
        $("#oneEmail").hide()
        $("#sendEmail").show(function () {
            $("#save").click(function () {
                var recipients = $("input[name='recipients']").val();
                var title = $("input[name='title']").val();
                var content = $("#editor1").val();
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
                        $("#errorOne").text(message)
                    }
                },"json")
            })
            $("#send").click(function () {
                var recipients = $("input[name='recipients']").val();
               var title = $("input[name='title']").val();
                var content = $("#editor1").val();
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
                $.post("/saveEmail",data,function (message) {
                        if (message.indexOf("success")!=-1){
                            alert("发送邮件成功")
                        }else{
                            $("#errorOne").text(message)
                        }
                },"json")
            })
        })

    })
    $("#a2").click(function () {
        $("#a2").addClass("btn-primary")
        $("#a2").removeClass("btn-info")
        $("#sendEmail").hide()
        $("#oneEmail").hide()
        $("#collectEmail").show(function () {
            $.get("/seeEmail").success(function (mess) {
                $("#collectMessage").html(mess)
                showEmail();
                $(".emailHomePage").click(function () {
                    $("#collectEmail").hide()
                    $("#oneEmail").show()
                    var temp = $(this).find(".emailId").attr("email-id");
                    $.get("/emailMessageContent?id="+temp).success(function (message) {
                        $("#oneEmailMessage").html(message)
                    })
                })
            })
        })
    })
    $("#a3").click(function () {
        $("#a3").addClass("btn-primary")
        $("#a3").removeClass("btn-info")
        $("#sendEmail").hide()
        $("#collectEmail").hide()
        $("#oneEmail").hide()
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
        $.post("/login",data,function (result) {
            if (result.message.indexOf("success")!=-1){
               var email = result.user.email;
               $("#userEmail").html(email);
               $("#modal").hide();
               $("#three").hide();
               /*$("#a2").trigger("click")*/
                triggerCollectEmail();//收件箱自动提交一次get服务
                $("input[name='sendEmailId']").val(result.user.id);
            }else{
                $("#error").html(result.message)
            }
        },"json")
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
function triggerCollectEmail() {
    $.get("/seeEmail").success(function (mess) {
        $("#collectMessage").html(mess)
        showEmail();
    })
}

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<button type="button" class="btn btn-primary">返回</button>
<hr style="border-color:#FFFFFF"/>
<div style="background-color: #f3f7ff">
    <h4><strong>${requestScope.emailMessageContent.title}</strong></h4>
    <p style="color: #9d9d9d">发件人:${requestScope.emailMessageContent.sendName}</p>
    <p style="color: #9d9d9d">时间:<f:formatDate value="${requestScope.emailMessageContent.sendDate}"
                                               pattern="yyyy年MM月dd日 HH:mm:ss"></f:formatDate></p>
    <p style="color: #9d9d9d">收件人:${requestScope.emailMessageContent.collectName}</p>
</div>
<hr style="border-color:#FFFFFF"/>
<div id="top">
    <script>
        $("#top").load("/emailText/${requestScope.emailMessageContent.content}")
    </script>
</div>


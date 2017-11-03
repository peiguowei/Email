<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h4  style="color: red" align="center">${requestScope.err}</h4>
<h3>收件箱:</h3>
<hr style="border-color:#FFFFFF"/>
<table class="table table-hover" id="collect">
    <tr>
        <th style="display: none">#</th>
        <th>发件人</th>
        <th>主题</th>
        <th>时间</th>
    </tr>
    <c:forEach items="${requestScope.emailList}" var="email">

       <tr style="cursor: pointer" class="emailHomePage">
           <td style="display: none" class="emailId" email-id="${email.id}">${email.id}</td>
           <td send-id="${email.sendEmailId}"></td>
           <td>${email.title}</td>
           <td><f:formatDate value="${email.sendDate}" pattern="yyyy-MM-dd HH:mm:ss"></f:formatDate></td>
        </tr>

    </c:forEach>

</table>

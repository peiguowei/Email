<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: pei
  Date: 2017/2/17
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    HashMap map = new HashMap();
    map.put("1","a");
    map.put("1","b");

%>

${map["1"]}

</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户登录</title>
    <link type="text/css" rel="stylesheet" href="../css/bootstrap.min.css">
    <script type="text/javascript" src="../js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="../js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function () {

        })
    </script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div align="center">
            <button type="button" class="btn btn-primary" id="submit">登录</button>
            <h1>${sessionScope.user.email}</h1>
        </div>

    </div>
</div>
</body>
</html>

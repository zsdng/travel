<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>执行成功</title>
</head>
<body>

<h2>执行成功！！！</h2>

${list}<br>

<c:forEach items="${list}" var="account">

    ${account.name}+${account.money}
</c:forEach>

</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Delete Result</title>
</head>
<body bgcolor="aqua">
<c:choose>
    <c:when test="${status eq 1}">
        <h1 style='color:green; text-align:center;'>Deleted Successfully...</h1>
    </c:when>
    <c:when test="${status eq -1}">
        <h1 style='color:red; text-align:center;'>Deletion Failed as some exception occurred...</h1>
    </c:when>
    <c:when test="${status eq 0}">
        <h1 style='color:red; text-align:center;'>Given ID isn't available...</h1>
    </c:when>
</c:choose>
</body>
</html>

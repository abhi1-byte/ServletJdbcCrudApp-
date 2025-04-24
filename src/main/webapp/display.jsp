<%@ page import="com.example.dto.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Student Information</title>
</head>
<body>
<c:choose>
    <c:when test="${student ne null || ! empty student}">
        <h1 style='color:green; text-align:center;'>STUDENT DETAILS:</h1>
        <table border=1 align="center">
            <tr>
                <th>SID</th>
                <th>SNAME</th>
                <th>SAGE</th>
                <th>SADDRESS</th>
            </tr>
            <tr>
                <td>${student.sid}</td>
                <td>${student.sname}</td>
                <td>${student.sage}</td>
                <td>${student.saddress}</td>
            </tr>
        </table>
    </c:when>
    <c:otherwise>
        <h1 style='color:red; text-align:center;'>STUDENT DETAILS NOT FOUND...</h1>
    </c:otherwise>
</c:choose>
</body>
</html>
<!-- ${student.sid} -- Internally EL calls student.getSid()
${requestScope.student.sid}
${student} -- EL automatically looks up the variable name in the following scopes, in order:
Page scope,Request scope,Session scope,Application scope
For example, ${student.sname} is equivalent to calling student.getSname()
-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Update</title>
</head>
<body>
<c:choose>
    <c:when test="${student ne null || ! empty student}">
        <form method='post' action='./controller/updateRecord'>
            <table align="center">
                <tr>
                    <th>ID</th>
                    <td>${student.sid}</td>
                </tr>
                <input type='hidden' name='sid' value='${student.sid}'/>
                <tr>
                    <th>NAME</th>
                    <td>
                        <input type='text' name='sname' value='${student.sname}'/>
                    </td>
                </tr>
                <tr>
                    <th>AGE</th>
                    <td>
                        <input type='text' name='sage' value='${student.sage}'/>
                    </td>
                </tr>
                <tr>
                    <th>ADDRESS</th>
                    <td>
                        <input type='text' name='saddress' value='${student.saddress}'/>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type='submit' value='update'/>
                    </td>
                </tr>
            </table>
        </form>
    </c:when>
    <c:otherwise>
        <h1 style='color:red;text-align:center;'>Record not available for the given id :: ${param.sid}</h1>
    </c:otherwise>
</c:choose>
</body>
</html>

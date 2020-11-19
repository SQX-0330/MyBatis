<%--
  Created by IntelliJ IDEA.
  User: sqx
  Date: 2020/11/18
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>员工列表</title>
</head>
<body>
    <table border="1">
        <tr>
            <td>id</td>
            <td>empName</td>
            <td>email</td>
            <td>gender</td>
            <td>loginAccount</td>
        </tr>
        <c:forEach items="${allEmps}" var="emp">
            <tr>
                <td>${emp.id}</td>
                <td>${emp.empName}</td>
                <td>${emp.email}</td>
                <td>${emp.gender}</td>
                <td>${emp.loginAccount}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>

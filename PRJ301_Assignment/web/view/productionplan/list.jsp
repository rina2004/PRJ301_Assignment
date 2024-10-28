<%-- 
    Document   : list
    Created on : Oct 29, 2024, 1:55:00 AM
    Author     : Rinaaaa
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Production Plan List</title>
    </head>
    <body>
        <table border="1px">
            <tr>
                <td>Plan</td>
                <td>Department</td>
                <td>From</td>
                <td>To</td>
                <td>Detail</td>
            </tr>

            <tr>
                <c:forEach items="${requestScope.plans}" var="pl">
                    <td>${pl.id}</td>
                    <td>${pl.dept.name}</td>
                    <td>${pl.start}</td>
                    <td>${pl.end}</td>
                    <td>
                        <a href="detail?id=${pl.id}">Detail</a>
                    </td>
                </c:forEach>
            </tr>
        </table>
    </body>
</html>

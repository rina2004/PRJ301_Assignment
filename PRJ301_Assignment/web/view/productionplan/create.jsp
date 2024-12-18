<%-- 
    Document   : create
    Created on : Oct 22, 2024, 3:32:53 PM
    Author     : Rinaaaa
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Production Plan</title>
    </head>
    <body>
        <form action="create" method="POST"> 
            From: <input type="date" name="from" /> 
            To: <input type="date" name="to"/>
            <br/>
            Workshop: <select name="did">
                <c:forEach items="${requestScope.depts}" var="d">
                    <option value="${d.id}">${d.name}</option>
                </c:forEach>
            </select>
            <br/>
            <table border="1px">
                <tr>
                    <td>Product</td>
                    <td>Quantity</td>
                    <td>Estimated Effort</td>
                </tr>
                <c:forEach items="${requestScope.products}" var="p">
                <tr>
                    <td>${p.name}<input type="hidden" name="pid" value="${p.id}"/></td>
                    <td><input type="text" name="quantity${p.id}"/></td>
                    <td><input type="text" name="effort${p.id}"/></td>
                </tr>   
                </c:forEach>
            </table>
            <input type="submit" name="Save"/>
        </form>
    </body>
</html>
<%-- 
    Document   : detail
    Created on : Oct 27, 2024, 2:36:11 AM
    Author     : Rinaaaa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Production Plan Detail</title>
    </head>
    <body>
        <h2>Details for Plan ID: ${plan.id}</h2>
        <p>Department: ${plan.dept.name}</p>
        <p>Start Date: ${plan.start}</p>
        <p>End Date: ${plan.end}</p>

        <table border="1">
            <tr>
                <th>Campaign</th>
                <th>Product</th>
                <th>Quantity</th>
                <th>Estimated Effort</th>
            </tr>
            <c:forEach items="${plan.campaigns}" var="campaign">
                <tr>
                    <td>${campaign.id}</td>
                    <td>${campaign.product.name}</td>
                    <td>${campaign.quantity}</td>
                    <td>${campaign.estimatedEffort}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>

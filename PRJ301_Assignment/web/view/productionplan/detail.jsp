<%-- 
    Document   : detail
    Created on : Oct 27, 2024, 2:36:11 AM
    Author     : Rinaaaa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Production Plan Schedule</title>
    </head>
    <body>
        <h1>Details for Plan ID: ${plan.id}</h1>
        <p>Department: 
            <c:choose>
                <c:when test="${not empty plan.dept.name}">
                    ${plan.dept.name}
                </c:when>
                <c:otherwise>
                    Not available
                </c:otherwise>
            </c:choose>
        </p>
        <p>Start Date: ${plan.start}</p>
        <p>End Date: ${plan.end}</p>

        <h3>Campaigns</h3>
        <c:if test="${not empty plan.campaigns}">
            <table border="1">
                <thead>
                    <tr>
                        <th>Campaign</th>
                        <th>Product Name</th>
                        <th>Quantity</th>
                        <th>Estimated Effort</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${plan.campaigns}" var="campaign">
                        <tr>
                            <td>${campaign.id}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${not empty campaign.product.name}">
                                        ${campaign.product.name}
                                    </c:when>
                                    <c:otherwise>
                                        Not specified
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${campaign.quantity}</td>
                            <td>${campaign.estimatedEffort}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty plan.campaigns}">
            <p>No campaigns available for this plan.</p>
        </c:if>
    </body>
</html>

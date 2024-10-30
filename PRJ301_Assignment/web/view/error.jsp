<%-- 
    Document   : error
    Created on : Oct 30, 2024, 8:12:50 AM
    Author     : Rinaaaa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <style>
        body { font-family: Arial, sans-serif; }
        .error-container {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            border: 1px solid #f44336;
            background-color: #ffe6e6;
            color: #d32f2f;
            border-radius: 8px;
            text-align: center;
        }
        .error-title {
            font-size: 24px;
            font-weight: bold;
        }
        .error-message {
            font-size: 16px;
            margin-top: 10px;
        }
        .back-link {
            margin-top: 20px;
            display: inline-block;
            padding: 10px 20px;
            background-color: #d32f2f;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        .back-link:hover {
            background-color: #b71c1c;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <div class="error-title">Oops! An error occurred</div>
        <div class="error-message">
            <c:choose>
                <c:when test="${not empty error}">
                    ${error}
                </c:when>
                <c:otherwise>
                    An unexpected error has occurred. Please try again later.
                </c:otherwise>
            </c:choose>
        </div>
        <a href="${pageContext.request.contextPath}/home.jsp" class="back-link">Return to Home</a>
    </div>
</body>
</html>
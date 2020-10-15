<%--
  Created by IntelliJ IDEA.
  User: mihey
  Date: 13.10.2020
  Time: 18:38
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table>
    <thead>
    <tr>
        <th>Дата</th>
        <th>Описание</th>
        <th>Калорий</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="meal" items="${meals}">
        <c:set var="color" value="green"/>
        <c:if test="${meal.excess}">
            <c:set var="color" value="red"/>
        </c:if>
            <tr bgcolor="${color}">
                <fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                <td><fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${parsedDateTime}" /></td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
            </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
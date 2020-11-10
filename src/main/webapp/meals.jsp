<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>
<html>
<head>
    <title>Meal list</title>
    <style>
        .normal {
            color: green;
        }

        .excess {
            color: red;
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<section>
    <hr/>
    <h2>Meals</h2>

    <h3>Filter</h3>
    <style>
        input {
            display:inline;
        }
    </style>
    <form method="get" action="meals">
        <input type="hidden" name="action" value="filter">
        <%--        <b>Дата с (включая):</b><input type="date" value="" name="dateFrom">--%>
        <%--        <b>Дата по (включая):</b><input type="date" value="" name="dateTill">--%>
        <%--        <b>Время с (включая):</b><input type="time" value="" name="timeFrom">--%>
        <%--        <b>Время по (исключая):</b><input type="time" value="" name="timeTill">--%>
        <dl>
            <b>Дата с (включая):</b>
            <b>Дата по (включая):</b>
            <b>Время с (включая):</b>
            <b>Время по (исключая):</b>
        </dl>
        <dl>
            <input type="date" value="" name="dateFrom">
            <input type="date" value="" name="dateTill">
            <input type="time" value="" name="timeFrom">
            <input type="time" value="" name="timeTill">
        </dl>
        <p>
            <button type="submit">Filter</button>
        </p>
    </form>
    <a href="meals?action=create">Add Meal</a>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" type="ru.javawebinar.topjava.to.MealTo"/>
            <tr class="${meal.excess ? 'excess' : 'normal'}">
                <td>
                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                        ${fn:formatDateTime(meal.dateTime)}
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>
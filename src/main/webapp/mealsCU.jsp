<%--
  Created by IntelliJ IDEA.
  User: mihey
  Date: 17.10.2020
  Time: 22:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Add meal</title>
</head>
<body>
<form method="POST" action='meals' name="frmAddMeal">
    id : <input type="hidden" readonly="readonly" name="id"
                     value="<c:out value="${meal.id}" />" /> <br />
    dateTime : <input
        type="datetime-local" name="dateTime"
        <fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
        value="<fmt:formatDate pattern="yyyy-MM-dd'T'HH:mm"  value="${parsedDateTime}" />" /> <br />
    description : <input
        type="text" name="description"
        value="<c:out value="${meal.description}" />" /> <br />
    calories : <input
        type="number" name="calories"
        value="<c:out value="${meal.calories}" />" /> <br />
     <input
        type="submit" value="Submit" />
</form>
</body>
</html>

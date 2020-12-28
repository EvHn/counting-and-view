<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Counting result</title>
    </head>
    <body>
        <h1></h1>
        <jstl:set var="orderValues" value="<%=example.view.data.Order.values()%>" />
        <form action="countingResults" method="get">
            <select type="text" name="order" value="${param.order}">
                <jstl:forEach var="item" items="${orderValues}">
                <option>${item}</option>
                </jstl:forEach>
            </select><br>
            <input type="submit">
        </form>
    </body>
</html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Put</title>
    </head>
    <body>
        <form action="put" method="post">
            Type:  <jstl:set var="types" value="<%=example.counting.data.Type.values()%>"/>
            <select type="text" name="type" value="${param.type}">
                <jstl:forEach var="item" items="${types}">
                <option>${item}</option>
                </jstl:forEach>
            </select><br><br>
            Count: <input type="integer" name="count" value="${param.count}" pattern="^[0-9]+$" required><br><br>
            Date:  <input type="date" name="date" value="${param.date}" required><br><br>
            <input type="submit">
        </form>
    </body>
</html>
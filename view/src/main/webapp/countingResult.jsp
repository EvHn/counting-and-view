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
        <div align="center">
            <table border="1">
                <caption><h2>Counting result</h2></caption>
                <tr>
                    <th>Type</th>
                    <th>Count</th>
                    <th>Date</th>
                </tr>
                <jstl:forEach var="item" items="${countingResults}">
                    <tr>
                        <td><jstl:out value="${item.type}"/></td>
                        <td><jstl:out value="${item.count}"/></td>
                        <td><jstl:out value="${item.date}"/></td>
                    </tr>
                </jstl:forEach>
            </table>
        </div>
    </body>
</html>
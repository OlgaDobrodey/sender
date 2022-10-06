<%--
  Created by IntelliJ IDEA.
  User: Olga
  Date: 10/4/2022
  Time: 5:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
hear
<c:forEach var="user" items="${listBooks}">

    <tr>
        <td> I am</td>>
        <td>${user.getNickName()}</td>
        <td>${user.getTask()}</td>
    </tr>
</c:forEach>

</body>
</html>

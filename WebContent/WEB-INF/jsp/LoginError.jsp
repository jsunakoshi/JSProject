<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.LoginUser" %>
<%LoginUser loginUser = (LoginUser) session.getAttribute("LoginUser");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<P><%= loginUser.getError() %></p>
<p>ログイン</p>
<form action="/JSProject/CheckLoginUser" method="post">
ログインID：<input type="text" name="id"><%= loginUser.getLogin_Id() %><br>
パスワード：<input type="password" name="pass"><%= loginUser.getPass() %><br>
<br>
<input type="submit" value="ログイン">
</form>
<br>
<br>

</body>
</html>
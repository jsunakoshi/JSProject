<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.LoginUser" %>
<%LoginUser registerUser = (LoginUser) session.getAttribute("registerUser");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録</title>
</head>

<body>
<p>下記のユーザーを登録します。</p>

<p>
ログインID：<%= registerUser.getLogin_Id()%><br>
パスワード：<%= registerUser.getPass() %><br>
名前      ：<%= registerUser.getName()%><br>
</p>
<br>
<a href="/JSProject/RegisterLoginUser">戻る</a>
<br>
<a href="/JSProject/RegisterLoginUser?action=done">登録</a>
</body>
</html>
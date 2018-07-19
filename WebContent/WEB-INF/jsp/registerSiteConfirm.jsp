<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.SiteInfo" %>
<%SiteInfo siteinfo = (SiteInfo) session.getAttribute("SiteInfo");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>サイト登録</title>
</head>

<body>
<p>下記のサイトを登録します。</p>

<p>
サイト　　：<%= siteinfo.getSite() %><br>
ログインID：<%= siteinfo.getId()%><br>
パスワード：<%= siteinfo.getPass()%><br>
email 　　：<%= siteinfo.getEmail() %><br>
コメント　：<textarea rows="4" cols="50"><%= siteinfo.getComment() %></textarea>
</p>
<br>
<a href="/JSProject/RegisterSiteInfo">戻る</a>
<a href="/JSProject/RegisterSiteInfo?action=done" >登録</a>
</body>
</html>
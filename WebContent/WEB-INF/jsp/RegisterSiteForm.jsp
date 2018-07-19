<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>サイト登録</title>
</head>
<body>
<form action="/JSProject/RegisterSite" method="post">
サイト名　：<input type="text" name="site"><br>
ログインID：<input type="text" name="id"><br>
パスワード：<input type="text" name="pass"><br>
email     ：<input type="text" name="email"><br>
コメント　：<TEXTAREA name="comment" rows=4 cols=50 >コメントを入れて下さい。</TEXTAREA>

<br>
<input type="submit" value="確認">
</form>

</body>
</html>
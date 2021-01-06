<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>

<body>
<h1>第一种方式：利用a标签直接指向服务器资源</h1>
<a href="/servlet/firstUdservlet?fileName=冲锋.png">冲锋.png</a><br />
<a href="/servlet/firstUdservlet?fileName=冲锋.txt">冲锋.txt</a><br />
<a href="/servlet/firstUdservlet?fileName=冲锋损坏.txt">文件损坏的冲锋.txt</a><br />
<h1>第一种直接贴链接的方式有缺陷，遇到不能解析的文件它才会显示下载否则仅仅打开</h1>
<hr>
</body>
</html>
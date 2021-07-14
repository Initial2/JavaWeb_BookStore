<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<meta charset="UTF-8">
<title>当当书城会员注册页面</title>
    <%@include file="/pages/common/header.jsp"%>
<style>
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
</style>
</head>
<body>
		<div id="header">
				<img class="logo_img" alt="" src="static/img/logo.gif" >
				<span class="wel_word"></span>
				<%@include file="/pages/common/navigation.jsp"%>
		</div>
		
		<div id="main">
		
			<h1>注册成功! <a href="index.jsp">转到主页</a></h1>
	
		</div>


        <div id="bottom">
            <%@include file="/pages/common/footer.jsp"%>
        </div>

</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: initial
  Date: 2021/7/3
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" %>


<!-- 不同客户端登录，就修改Base地址 -->
<%
    String scheme = request.getScheme();
    String serverName = request.getServerName();
    int port = request.getServerPort();
    String pathTo = scheme + "://" + serverName + ":" + port;
    String basePath = pathTo+"/Book/";
%>

<base href=<%= basePath %>>

<link type="text/css" rel="stylesheet" href="static/css/style.css">
<script src="static/js/jquery.js"></script>

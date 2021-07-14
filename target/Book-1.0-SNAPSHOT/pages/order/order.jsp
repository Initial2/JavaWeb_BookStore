<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
    <%@include file="/pages/common/header.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">我的订单</span>
			<%@ include file="/pages/common/navigation.jsp"%>
	</div>
	
	<div id="main">
		
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
			</tr>

            <c:if test="${empty requestScope.orders}" >
                <tr>
                    <td colspan="5" > 当前没有订单! </td>
                </tr>
            </c:if>

            <c:forEach items="${requestScope.orders}" var="order">
                <tr>
                    <td>${order.createTime}</td>
                    <td>${order.price}</td>
                    <td>
                        <c:choose>
                            <c:when test="${order.status == 0}">
                                未发货
                            </c:when>
                            <c:when test="${order.status == 1}">
                                已发货
                            </c:when>
                            <c:when test="${order.status == 2}">
                                已签收
                            </c:when>
                        </c:choose>
                    </td>
                    <td><a href="orderServlet?action=orderDetails&orderId=${order.orderId}">查看详情</a></td>
                    <td>
                        <c:if test="${order.status != 2}">
                            <a href="orderServlet?action=receiveOrder&orderId=${order.orderId}">确认签收</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>

		</table>
		
	
	</div>


    <div id="bottom">
        <%@include file="/pages/common/footer.jsp"%>
    </div>

</body>
</html>
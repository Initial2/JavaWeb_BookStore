<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单详情</title>
    <%@include file="/pages/common/header.jsp" %>
</head>
<body>


<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">我的订单</span>
    <%@ include file="/pages/common/navigation.jsp" %>
</div>

<div id="main">

    <table>
        <tr>
            <td>订单号</td>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
        </tr>

        <%--如果订单为空，则显示提示消息--%>
        <c:if test="${empty sessionScope.orderItems}" >
            <tr>
                <td colspan="5" href="/Book/index.jsp"> 当前没有订单!. </td>
            </tr>

        </c:if>>

        <%--如果订单不为空，才去显示订单的商品信息--%>
        <c:if test="${not empty sessionScope.orderItems}">
            <c:forEach items="${sessionScope.orderItems}" var="order">

                <tr>
                    <td>${order.orderId}</td>
                    <td>
                        ${order.name}
                    </td>
                    <td>${order.count}</td>
                    <td>${order.price}</td>
                    <td>${order.totalPrice}</td>
                </tr>

            </c:forEach>
        </c:if>

    </table>
</div>


<div id="bottom">
    <%@include file="/pages/common/footer.jsp" %>
</div>

</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <title>书城首页</title>

    <%@ include file="/pages/common/header.jsp" %>

    <script>
        $(function () {
            $("#searchByPageNo").click(function () {
                let pageNo = $("#pn_input").val();
                if (pageNo < 1) {
                    pageNo = 1;
                }

                if (pageNo > ${requestScope.page.pageTotal}) {
                    pageNo =
                    ${requestScope.page.pageTotal}
                }

                location.href = "/Book/client/bookServlet?action=page&pageNo=" + pageNo;
            })

            $(".addItem").click(function () {
                var bookID = $(this).attr("bookID");
                /*使用ajax异步方式，将商品添加到购物车*/
                $.getJSON("/Book/cartServlet?", "action=addItem&bookID=" + bookID, function (data) {
                    $("#prompt span:first").text("您的购物车中有" + data.totalCount + "件商品")
                    $("#prompt div:first span").text(data.lastName)
                })

            })
        })
    </script>


</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">网上书城</span>
    <div>

        <%--如果sessionScope没有username属性，证明用户还没有登录--%>
        <c:if test="${empty sessionScope.username}">
            <a href="pages/user/login.jsp">登录</a> |
            <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
        </c:if>

        <%--如果sessionScope已经有username属性，证明用户已经登陆成功--%>
        <c:if test="${not empty sessionScope.username}">
            <span>欢迎<span class="um_span">${sessionScope.username}</span>光临尚硅谷书城</span>
            <a href="orderServlet?action=myOrders">我的订单</a>
            <a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;
        </c:if>

        <a href="pages/cart/cart.jsp">购物车</a>
        <a href="pages/manager/manager.jsp">后台管理</a>
    </div>

</div>


<div id="main">
    <div id="book">
        <div class="book_cond">
            <form action="client/bookServlet" method="get">
                <input type="hidden" name="action" value="pageByPrice">
                <label for="min">价格：</label><input id="min" type="text" name="min" value="${param.min}"> 元 -
                <label for="max"> 元 -</label><input id="max" type="text" name="max" value="${param.max}"> 元
                <input type="submit" value="查询"/>
            </form>
        </div>

        <div id="prompt" style="text-align: center">
            <%--如果购物车中有商品，就提醒商品数量，以及最后一次加入购物车的书名--%>
            <c:if test="${not empty sessionScope.cart.items}">
                <span>  </span>
                <div>
                    您刚刚将<span style="color: red"> </span>加入到了购物车中
                </div>
            </c:if>

            <%--如果购物车中没有商品，就不提醒商品数量--%>
            <c:if test="${empty sessionScope.cart.items}">
                <span> &nbsp </span>
                <div>
                    <span style="color: red"> &nbsp; </span>
                </div>
            </c:if>
        </div>


        <c:forEach items="${requestScope.page.items}" var="book">
            <div class="b_list">
                <div class="img_div">
                    <img class="book_img" alt="" src="${book.img_path}"/>
                </div>
                <div class="book_info">
                    <div class="book_name">
                        <span class="sp1">书名:</span>
                        <span class="sp2">${book.name}</span>
                    </div>
                    <div class="book_author">
                        <span class="sp1">作者:</span>
                        <span class="sp2">${book.author}</span>
                    </div>
                    <div class="book_price">
                        <span class="sp1">价格:</span>
                        <span class="sp2">￥${book.price}</span>
                    </div>
                    <div class="book_sales">
                        <span class="sp1">销量:</span>
                        <span class="sp2">${book.sales}</span>
                    </div>
                    <div class="book_amount">
                        <span class="sp1">库存:</span>
                        <span class="sp2">${book.stock}</span>
                    </div>
                    <div class="book_add">
                        <button class="addItem" bookID="${book.id}">加入购物车</button>
                    </div>
                </div>
            </div>
        </c:forEach>

    </div>

    <%@include file="/pages/common/page_nav.jsp" %>

</div>

<div id="bottom">
    <%@include file="/pages/common/footer.jsp" %>
</div>


</body>
</html>
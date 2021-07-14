<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <%@include file="/pages/common/header.jsp" %>

    <script>
        $(function () {

            /*删除确认*/
            $(".deleteItem").click(function () {
                let name = $(this).attr("name");
                return confirm("你确定要删除"+name+"吗？")
            })

            /*清空购物车确认*/
            $(".clearCart").click(function () {
                return confirm("你确定要清空购物车吗？")
            })

            /*确认修改购物车中的商品数量*/
            $(".updateCount").change(function () {
                let bookName = $(this).parent().prev().val();
                let count =this.value

                if (confirm("你确定要将"+ bookName+"的数量修改为："+ count+"吗？")){
                    //如果确认修改，则请求servlet处理
                    let bookID = $(this).attr("bookID");
                    location.href="/Book/cartServlet?action=updateCount&id="+bookID+"&count="+count
                }else{
                    //否则重新将数量设置为默认值
                    this.value = this.defaultValue
                }

            })
        })


    </script>
</head>
<body>


<div id="header">
    <img class="logo_img" alt="" src="">
    <span class="wel_word">购物车</span>
    <%@ include file="/pages/common/navigation.jsp" %>
</div>

<div id="main">
    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
            <td>操作</td>
        </tr>

        <%--如果购物车为空，则显示提示消息--%>
        <c:if test="${empty sessionScope.cart.items}" >
            <tr>
                <td colspan="5" href="/Book/index.jsp"> 当前购物车中没有商品!. </td>
            </tr>

        </c:if>

        <%--如果购物车不为空，才去显示购物车的商品信息--%>
        <c:if test="${not empty sessionScope.cart.items}">
            <c:forEach items="${sessionScope.cart.items}" var="item">
                <tr>
                    <td>${item.value.name}</td>
                    <td>
                        <input style="width: 40px" class="updateCount" bookid="${item.value.id}"  type="text" value="${item.value.count}">
                    </td>
                    <td>${item.value.price}</td>
                    <td>${item.value.totalPrice}</td>
                    <td><a  class= "deleteItem" name="${item.value.name}"  href="cartServlet?action=deleteItem&id=${item.value.id} "> 删除 </a> </td>
                </tr>
            </c:forEach>
        </c:if>

    </table>



    <%--如果购物车不为空，才去显示购物车的操作--%>
    <c:if test="${not empty sessionScope.cart.items}" >
        <div class="cart_info">
            <span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
            <span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
            <span class="cart_span"><a  class="clearCart" href="cartServlet?action=clear">清空购物车</a></span>
            <span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
        </div>
    </c:if>
</div>


<div id="bottom">
    <%@include file="/pages/common/footer.jsp" %>
</div>

</body>
</html>
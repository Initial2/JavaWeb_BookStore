<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ taglib prefix="canvas" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书管理</title>
    <%@include file="/pages/common/header.jsp" %>

    <script>
        $(function () {
            $("td a.delete").click(function () {
                return confirm("你确认要删除" + $(this).parent().parent().find("td:first").text() + "吗？");
            })

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

                    location.href = "/Book/manager/bookServlet?action=page&pageNo=" + pageNo;
                })
            })
        })
    </script>



    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">图书管理系统</span>
    <%@include file="/pages/common/manager_menu.jsp" %>
</div>

<div id="main">
    <table>
        <tr>
            <td>名称</td>
            <td>价格</td>
            <td>作者</td>
            <td>销量</td>
            <td>库存</td>
            <td colspan="2">操作</td>
        </tr>

        <%--使用JSTL表达式，将获取到的图书列表信息输出。--%>
        <c:forEach items="${requestScope.page.items}" var="book">
            <tr>
                <td>${book.name}</td>
                <td>${book.price}</td>
                <td>${book.author}</td>
                <td>${book.sales}</td>
                <td>${book.stock}</td>
                <td><a href="manager/bookServlet?action=getBook&id=${book.id}&pageNo=${requestScope.page.pageNo}">修改</a></td>
                <td><a class="delete" href="manager/bookServlet?action=deleteBookById&id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
            </tr>
        </c:forEach>


        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td><a href="pages/manager/book_edit.jsp?pageNo=${requestScope.page.pageNo}">添加图书</a></td>
        </tr>

    </table>

    <%@include file="/pages/common/page_nav.jsp"%>

</div>


<div id="bottom">
    <%@include file="/pages/common/footer.jsp" %>
</div>

</body>
</html>
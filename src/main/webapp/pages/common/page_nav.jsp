<%--
  Created by IntelliJ IDEA.
  User: initial
  Date: 2021/7/7
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


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
    })
</script>





<div id="page_nav">
    <c:if test="${requestScope.page.pageNo > 1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo - 1}">上一页</a>
    </c:if>

    <c:choose>
        <%--情况1： 如果总页码数量小于等于5，页码的范围是1-5--%>
        <c:when test="${requestScope.page.pageTotal <=5}">
            <c:set var="begin" value="1"> </c:set>
            <c:set var="end" value="${requestScope.page.pageTotal}"> </c:set>
        </c:when>

        <%--情况2： 总页码数大于5--%>
        <c:when test="${requestScope.page.pageTotal > 5}">
            <c:choose>
                <%--小情况1： 当前页面是1,2,3前三页--%>
                <c:when test="${requestScope.page.pageNo <= 3}">
                    <c:set var="begin" value="1"></c:set>
                    <c:set var="end" value="5"></c:set>
                </c:when>

                <%--小情况2： 当前页面是最后三页--%>
                <c:when test="${requestScope.page.pageNo  + 2 >= requestScope.page.pageTotal}">
                    <c:set var="begin" value="${requestScope.page.pageTotal - 4}"></c:set>
                    <c:set var="end" value="${requestScope.page.pageTotal}"></c:set>
                </c:when>

                <%-- 小情况3： 页码是中间数--%>
                <c:otherwise>
                    <c:set var="begin" value="${requestScope.page.pageNo - 2}"></c:set>
                    <c:set var="end" value="${requestScope.page.pageNo+2}"></c:set>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>
    <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${requestScope.page.pageNo == i}">
            【${i}】
        </c:if>
        <c:if test="${requestScope.page.pageNo != i}">
            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>

    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo + 1}">下一页</a>
    </c:if>

    <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    <label for="pn_input">共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录 到第</label><input
        value="${param.pageNo}" name="pn" id="pn_input"/>页
    <input id="searchByPageNo" type="button" value="确定">
</div>


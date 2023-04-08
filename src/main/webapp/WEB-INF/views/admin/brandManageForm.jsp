<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <link rel="stylesheet" href="<c:url value='/resources/css/include/admin/header.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/css/admin/brandManage.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/css/include/admin/footer.css'/>">
</head>
<style>

</style>
<body>
<%@include file="../include/admin/header.jsp" %>
<div class="admin_content_wrap">
    <div class="admin_content_subject">
        <span>브랜드 관리</span>
    </div>
    <div class="admin_table_wrap">
        <table class="admin_table">
            <thead>
            <tr>
                <td class="th_column_1">브랜드 번호</td>
                <td class="th_column_2">브랜드 이름</td>
                <td class="th_column_3">등록날짜</td>
                <td class="th_column_4">수정날짜</td>
            </tr>
            </thead>
            <c:forEach items="${list}" var="brand">
                <tr>
                    <td>
                        <c:out value="${brand.brandId}"></c:out></td>
                    <td class="title">
                        <a href="<c:url value="/admin/brandDetail${ph.sc.queryString}&brandId=${brand.brandId}"/>"><c:out
                                value="${brand.brandName}"></c:out></a></td>
                    <td><fmt:formatDate value="${brand.reg_date}" pattern="yyyy-MM-dd"/></td>
                    <td><fmt:formatDate value="${brand.up_date}" pattern="yyyy-MM-dd"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>


    <div class="search-container">
        <form action="<c:url value="/admin/brandManage"/>" class="search-form" method="get">
            <input type="text" name="keyword" class="search-input" type="text" value="${ph.sc.keyword}"
                   placeholder="검색어를 입력해주세요">
            <input type="submit" class="search-button" value="검색">
        </form>
    </div>

    <div class="pageMaker_wrap">

        <ul class="pageMaker">

            <c:if test="${ph.totalCnt==null || ph.totalCnt==0}">
                <div> 게시물이 없습니다.</div>
            </c:if>

            <c:if test="${ph.totalCnt!=null && ph.totalCnt!=0}">
                <c:if test="${ph.showPrev}">
                    <li class="pageMaker_btn prev">
                        <a href="<c:url value='/admin/brandManage${ph.sc.getQueryString(ph.beginPage-1)}'/> ">이전</a>
                    </li>
                </c:if>

                <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                    <%--현재페이지 : ph.page == i 와 같은 버튼에서는 class = active 속성 넣어준다--%>
                    <li class="pageMaker_btn  ${ph.sc.page == i ? "active":""}">
                        <a href="<c:url value='/admin/brandManage${ph.sc.getQueryString(i)}'/> ">${i}</a>
                    </li>
                </c:forEach>

                <c:if test="${ph.showNext}">
                    <li class="pageMaker_btn next">
                        <a href="<c:url value='/admin/brandManage${ph.sc.getQueryString(ph.endPage+1)}'/> ">다음</a>
                    </li>
                </c:if>
            </c:if>
        </ul>
    </div>
</div>

<%@include file="../include/admin/footer.jsp" %>
</body>
</html>

<script>
    $(document).ready(function () {
        let result = "<c:out value='${enroll_result}'/>";

        checkResult(result);

        function checkResult(result) {

            if (result === '') {
                return;
            }

            alert("브랜드 : '${enroll_result}' 을 등록하였습니다.");

        }

    });
</script>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <link rel="stylesheet" href="<c:url value='/css/admin/brandManage.css'/>">
</head>
<style>
    .author_table_wrap {
        padding: 20px 35px
    }

    .author_table {
        width: 100%;
        border: 1px solid #d3d8e1;
        text-align: center;
        border-collapse: collapse;
    }

    .author_table td {
        padding: 10px 5px;
        border: 1px solid #e9ebf0;
    }

    .author_table thead {
        background-color: #f8f9fd;
        font-weight: 600;
    }

    .th_column_1 {
        width: 120px;
    }

    .th_column_3 {
        width: 110px;
    }

    .th_column_4 {
        width: 140px;
    }


    <%-- 페이지 처리 --%>
    .pageMaker_wrap {
        text-align: center;
        margin-top: 30px;
        margin-bottom: 40px;
    }

    .pageMaker_wrap a {
        color: black;
    }

    .pageMaker {
        list-style: none;
        display: inline-block;
    }

    .pageMaker_btn {
        float: left;
        width: 40px;
        height: 40px;
        line-height: 40px;
        margin-left: 20px;
    }

    .next, .prev {
        border: 1px solid #ccc;
        padding: 0 10px;
    }

    .next a, .prev a {
        color: #ccc;
    }

    .active { /* 현재 페이지 버튼 */
        border: 2px solid black;
        font-weight: 400;
    }
</style>
<body>
<%@include file="../include/admin/header.jsp" %>
<div class="admin_content_wrap">
    <div class="admin_content_subject">
        <span>브랜드 관리</span>
    </div>
    <div class="author_table_wrap">
        <table class="author_table">
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
                    <td><c:out value="${brand.brandId}"></c:out></td>
                    <td><c:out value="${brand.brandName}"></c:out></td>
                    <td><fmt:formatDate value="${brand.reg_date}" pattern="yyyy-MM-dd"/></td>
                    <td><fmt:formatDate value="${brand.up_date}" pattern="yyyy-MM-dd"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="pageMaker_wrap">

        <ul class="pageMaker">

            <c:if test="${ph.showPrev}">
                <li class="pageMaker_btn prev">
                    <a href="<c:url value='/admin/brandManage?page=${ph.beginPage-1}&pageSize=${ph.pageSize}'/> ">이전</a>
                </li>
            </c:if>

            <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                <%--현재페이지 : ph.page == i 와 같은 버튼에서는 class = active 속성 넣어준다--%>
                <li class="pageMaker_btn  ${ph.page == i ? "active":""}">
                    <a href="<c:url value='/admin/brandManage?page=${i}&pageSize=${ph.pageSize}'/> ">${i}</a>
                </li>
            </c:forEach>

            <c:if test="${ph.showNext}">
                <li class="pageMaker_btn next">
                    <a href="<c:url value='/admin/brandManage?page=${ph.endPage+1}&pageSize=${ph.pageSize}'/> ">다음</a>
                </li>
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
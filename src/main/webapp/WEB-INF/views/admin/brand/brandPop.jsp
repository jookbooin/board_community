<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <link rel="stylesheet" href="<c:url value='/resources/css/admin/brandPop.css'/>">
</head>
<body>
<div class="admin_content_wrap">
    <div class="subject_name_warp">
        <span>브랜드 선택</span>
    </div>

    <div class="content_wrap">
        <div class="admin_table_wrap">
            <table class="admin_table">
                <thead>
                <tr>
                    <td class="th_column_1">브랜드 번호</td>
                    <td class="th_column_2">브랜드 이름</td>
                </tr>
                </thead>

                <c:forEach items="${list}" var="brand">
                    <tr>
                        <td><c:out value="${brand.brandId}"/></td>
                        <td class="title">
                                <%--<a href="<c:url value="/admin/brandDetail${ph.sc.queryString}&brandId=${brand.brandId}"/>">--%>
                            <a class="move" data-id="<c:url value='${brand.brandId}'/>"
                               data-name="<c:out value='${brand.brandName}'/>"><c:out value="${brand.brandName}"/></a>

                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

    <div class=" search-container">
        <form action="<c:url value="/admin/brandPop"/>" class="search-form" method="get">
            <input type="text" name="keyword" class="search-input" type="text" value="${ph.sc.keyword}"
                   placeholder="검색어를 입력해주세요">
            <input type="submit" class="search-button" value="검색">
        </form>
    </div>

    <div class="pageMaker_wrap">

        <ul class="pageMaker">

            <c:if test="${ph.totalCnt==null || ph.totalCnt==0}">
                <div> 등록된 브랜드가 없습니다.</div>
            </c:if>

            <c:if test="${ph.totalCnt!=null && ph.totalCnt!=0}">
                <c:if test="${ph.showPrev}">
                    <li class="pageMaker_btn prev">
                        <a href="<c:url value='/admin/brandPop${ph.sc.getQueryString(ph.beginPage-1)}'/> ">이전</a>
                    </li>
                </c:if>

                <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                    <%--현재페이지 : ph.page == i 와 같은 버튼에서는 class = active 속성 넣어준다--%>
                    <li class="pageMaker_btn  ${ph.sc.page == i ? "active":""}">
                        <a href="<c:url value='/admin/brandPop${ph.sc.getQueryString(i)}'/> ">${i}</a>
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
</body>
</html>

<script>
    $('.move').on("click", function (e) {

        e.preventDefault();
        let productId = $(this).attr("data-id");
        let productName = $(this).attr("data-name");
        $(opener.document).find("#productId_input").val(productId);
        $(opener.document).find("#productName_input").val(productName);

        window.close();
    });
    
</script>

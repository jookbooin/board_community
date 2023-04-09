<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <link rel="stylesheet" href="<c:url value='/resources/css/include/admin/header.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/css/admin/brandDetail.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/css/include/admin/footer.css'/>">

</head>
<style>

</style>
<body>
<%@include file="../../include/admin/header.jsp" %>
<div class="admin_content_wrap">
    <div class="admin_content_subject">
        <span>브랜드 정보</span>
    </div>
    <div class="admin_content_main">
        <div class="form_section">
            <div class="form_section_title">
                <label>브랜드 번호</label>
            </div>
            <div class="form_section_content">
                <input class="input_block" name="brandId" readonly="readonly"
                       value="<c:out value='${authorInfo.authorId }'></c:out>">

            </div>
        </div>
    </div>
</div>
<%@include file="../../include/admin/footer.jsp" %>
</body>
</html>

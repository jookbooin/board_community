<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <link rel="stylesheet" href="<c:url value='/resources/css/admin/productManage.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/css/include/admin/footer.css'/>">

</head>
<body>
<%@include file="../../include/admin/header.jsp" %>
<div class="admin_content_wrap">
    <div class="admin_content_subject"><span>상품 관리</span></div>
</div>
<%@include file="../../include/admin/footer.jsp" %>
</body>
</html>

<script>
    $(document).ready(function () {

        let result = '<c:out value="${enroll_result}"/>';

        checkResult(result);

        function checkResult(result) {

            if (result === '') {
                return;
            }
            alert("상품'" + '${enroll_result}' + "을 등록하였습니다.");
        }
    });
</script>
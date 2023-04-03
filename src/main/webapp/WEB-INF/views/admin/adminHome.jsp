<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value='/css/include/admin/header.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/include/admin/footer.css'/>">
</head>
<body>
<h1>관리자 메인 페이지</h1>

<%@include file="../include/admin/header.jsp" %>

<div class="admin_content_wrap">
    <div>관리자 페이지 입니다.</div>
</div>

<%@include file="../include/admin/footer.jsp" %>
</body>
</html>

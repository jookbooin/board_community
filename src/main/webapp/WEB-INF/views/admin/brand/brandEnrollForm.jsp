<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <link rel="stylesheet" href="<c:url value='/resources/css/include/admin/header.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/css/admin/brandEnroll.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/css/include/admin/footer.css'/>">
</head>
<style>

</style>
<body>
<%@include file="../../include/admin/header.jsp" %>
<div class="admin_content_wrap">
    <div class="admin_content_subject"><span>브랜드 등록</span></div>
    <div class="admin_content_main">

        <form action="<c:url value='/admin/brandEnroll'/>" method="post" id="enrollForm">
            <div class="form_section">
                <div class="form_section_title">
                    <label>브랜드 이름</label>
                </div>
                <div class="form_section_content">
                    <input name="brandName">
                    <span id="warn_brandName">브랜드 이름을 입력 해주세요.</span>
                </div>
            </div>

            <div class="form_section">
                <div class="form_section_title">
                    <label>브랜드소개</label>
                </div>
                <div class="form_section_content">
                    <input name="brandIntro" type="text">
                    <span id="warn_brandIntro">브랜드 소개를 입력 해주세요.</span>
                </div>
            </div>
        </form>

        <div class="btn_section">
            <button type="button" id="cancelBtn" class="btn">취 소</button>
            <button type="button" id="enrollBtn" class="btn enroll_btn">등 록</button>
        </div>

    </div>
</div>

<%@include file="../../include/admin/footer.jsp" %>
</body>
</html>
<script>
    $("#cancelBtn").click(function () {
        location.href = "<c:url value='/admin/brandManage'/>";
    });

    $("#enrollBtn").click(function () {
        let nameCheck = false;
        let introCheck = false;

        let brandName = $('input[name=brandName]').val();
        let brandIntro = $('input[name=brandIntro]').val();

        let wBrandName = $('#warn_brandName');
        let wBrandIntro = $('#warn_brandIntro');


        if (brandName === '') {
            wBrandName.css('display', 'block');
            nameCheck = false;
        } else {
            wBrandName.css('display', 'none');
            nameCheck = true;
        }

        if (brandIntro === '') {
            wBrandIntro.css('display', 'block');
            introCheck = false;
        } else {
            wBrandIntro.css('display', 'none');
            introCheck = true;
        }

        if (nameCheck && introCheck) {
            $("#enrollForm").submit();
        } else {
            return;
        }

    });
</script>

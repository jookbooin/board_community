<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <link rel="stylesheet" href="<c:url value='/resources/css/include/admin/header.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/css/admin/productEnroll.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/css/include/admin/footer.css'/>">
    <script src="https://cdn.ckeditor.com/ckeditor5/36.0.1/classic/ckeditor.js"></script>
</head>
<style>
    .ck-content { /* ckeditor 높이 */
        height: 170px;
    }
</style>
<body>
<%@include file="../../include/admin/header.jsp" %>
<div class="admin_content_wrap">
    <div class="admin_content_subject"><span>상품 등록</span></div>

    <div class="admin_content_main">
        <form action="<c:url value='/admin/productEnroll'/>" method="post" id="enrollForm">

            <div class="form_section">
                <div class="form_section_title">
                    <label>상품명</label>
                </div>
                <div class="form_section_content">
                    <input name="productName">
                </div>
            </div>

            <div class="form_section">
                <div class="form_section_title">
                    <label>브랜드</label>
                </div>
                <div class="form_section_content">
                    <input id="productName_input" readonly="readonly">
                    <input id="productId_input" name="productId" type="hidden">
                    <button class="productId_btn">브랜드 선택</button>
                </div>
            </div>

            <div class="form_section">
                <div class="form_section_title">
                    <label>상품 카테고리</label>
                </div>
                <div class="form_section_content">
                    <input name="cateCode">
                </div>
            </div>

            <div class="form_section">
                <div class="form_section_title">
                    <label>상품 가격</label>
                </div>
                <div class="form_section_content">
                    <input name="productPrice" value="0">
                </div>
            </div>

            <div class="form_section">
                <div class="form_section_title">
                    <label>상품 재고</label>
                </div>
                <div class="form_section_content">
                    <input name="productStock" value="0">
                </div>
            </div>

            <div class="form_section">
                <div class="form_section_title">
                    <label>상품 할인율</label>
                </div>
                <div class="form_section_content">
                    <input name="productDiscount" value="0">
                </div>
            </div>

            <div class="form_section">
                <div class="form_section_title">
                    <label>상품 소개</label>
                </div>
                <div class="form_section_content">
                    <textarea name="productIntro" id="productIntro_textarea"></textarea>
                </div>
            </div>

            <div class="form_section">
                <div class="form_section_title">
                    <label>상품 세부사항 </label>
                </div>
                <div class="form_section_content">
                    <textarea name="productContents" id="productContents_textarea"></textarea>
                </div>
            </div>
        </form>

        <div class="btn_section">
            <button id="cancelBtn" class="btn">취 소</button>
            <button id="enrollBtn" class="btn enroll_btn">등 록</button>
        </div>
    </div>
</div>

<%@include file="../../include/admin/footer.jsp" %>
</body>
</html>
<script>
    $('.productId_btn').on("click", function (e) {

        e.preventDefault(); // 원래 페이지의 이벤트를 막아 변하지 않고 , 밑의 popUrl만 띄워서 움직이도록 설정하는 것

        let popUrl = "<c:url value='/admin/brandPop'/>";
        let popOption = "width = 650px, height=550px, top=300px, left=300px, scrollbars=yes";

        window.open(popUrl, "브랜드 찾기", popOption);

    });
</script>

<script>
    // let enrollForm = $("#enrollForm")

    /* 취소 버튼 */
    $("#cancelBtn").click(function () {

        location.href = "<c:url value='/admin/productManage'/>";

    });

    /* 상품 등록 버튼 */
    $("#enrollBtn").on("click", function (e) {

        // alert("click");
        // e.preventDefault();
        $("#enrollForm").submit();

    });

</script>

<script>
    /* 위지윅 적용 */
    /* 책 소개 */
    ClassicEditor
        .create(document.querySelector('#productIntro_textarea'))
        .catch(error => {
            console.error(error);
        });

    /* 책 목차 */
    ClassicEditor
        .create(document.querySelector('#productContents_textarea'))
        .catch(error => {
            console.error(error);
        });
</script>


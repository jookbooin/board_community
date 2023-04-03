<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value='/css/admin/brandEnroll.css'/>">
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <%--    <link rel="stylesheet" href="../../../resources/css/admin/brandEnroll.css">--%>
</head>
<style>
    .form_section {
        width: 95%;
        margin-left: 2%;
        margin-top: 20px;
        border: 1px solid #dbdde2;
        background-color: #efefef;
    }

    .form_section_title {
        padding: 20px 35px;
    }

    .form_section_title label {
        display: block;
        font-size: 20px;
        font-weight: 800;
    }

    .form_section_content {
        padding: 20px 35px;
        border-top: 1px solid #dbdde2;
    }

    .form_section_content input {
        width: 98%;
        height: 25px;
        font-size: 20px;
        padding: 5px 1%;
    }

    .form_section_content span {
        display: none;
        padding-top: 10px;
        text-align: center;
        color: #e05757;
        font-weight: 300;
    }

    .btn_section {
        text-align: center;
        margin: 80px 0;
    }

    .btn {
        min-width: 180px;
        padding: 4px 30px;
        font-size: 25px;
        font-weight: 600;
        line-height: 40px;
    }

    .enroll_btn {
        background-color: #dbdde2;
        margin-left: 15px;
    }
</style>
<body>
<div class="wrapper">
    <div class="wrap">
        <!-- gnv_area -->
        <div class="top_gnb_area">
            <ul class="list">
                <li><a href="<c:url value='/'/>">메인 페이지</a></li>
                <li><a href="<c:url value='/login/logout'/>">로그아웃</a></li>
                <li><a href="<c:url value='/board/list'/>">고객센터</a></li>
            </ul>
        </div>
        <!-- top_subject_area -->
        <div class="admin_top_wrap">
            <span>관리자 페이지</span>

        </div>
        <!-- contents-area -->
        <div class="admin_wrap">
            <!-- 네비영역 -->
            <div class="admin_navi_wrap">
                <ul>
                    <li>
                        <a class="admin_list_01" href="/admin/goodsEnroll">상품 등록</a>
                    </li>
                    <li>
                        <a class="admin_list_02" href="/admin/goodsManage">상품 관리</a>
                    </li>
                    <li>
                        <a class="admin_list_03" href="<c:url value='/admin/brandEnroll'/>">브랜드 등록</a>
                    </lI>
                    <lI>
                        <a class="admin_list_04" href="/admin/authorManage">브랜드 관리</a>
                    </lI>
                    <lI>
                        <a class="admin_list_05">회원 관리</a>
                    </lI>
                </ul>
            </div>

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
        </div>
        <div class="clearfix"></div>
    </div>
</div>
</div>
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

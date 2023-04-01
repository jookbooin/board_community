<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/admin/adminHome.css'/>">
</head>
<body>
<h1>관리자 메인 페이지</h1>

<div class="wrapper">
    <div class="wrap">
        <div class="top_gnb_area">
            <ul class="list">
                <li><a href="<c:url value='/'/>">Home</a></li>
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
                        <a class="admin_list_01">상품 등록</a>
                    </li>
                    <li>
                        <a class="admin_list_02">상품 목록</a>
                    </li>
                    <lI>
                        <a class="admin_list_03">작가 등록</a>
                    </lI>
                    <lI>
                        <a class="admin_list_04">작가 관리</a>
                    </lI>
                    <lI>
                        <a class="admin_list_05">회원 관리</a>
                    </lI>
                </ul>
            </div>

            <div class="admin_content_wrap">
                <div>관리자 페이지 입니다.</div>
            </div>
        </div>
    </div>
</div>


</body>
</html>
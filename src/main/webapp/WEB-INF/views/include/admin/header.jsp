<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                        <a class="admin_list_01" href="<c:url value='/admin/productEnroll'/>">상품 등록</a>
                    </li>
                    <li>
                        <a class="admin_list_02" href="<c:url value='/admin/productManage'/>">상품 관리</a>
                    </li>
                    <lI>
                        <a class="admin_list_03" href="<c:url value='/admin/brandEnroll'/>">브랜드 등록</a>
                    </lI>
                    <lI>
                        <a class="admin_list_04" href="<c:url value='/admin/brandManage'/>">브랜드 관리</a>
                    </lI>
                    <lI>
                        <a class="admin_list_05">회원 관리</a>
                    </lI>
                </ul>
            </div>
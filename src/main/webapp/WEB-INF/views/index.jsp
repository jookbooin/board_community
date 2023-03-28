<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--session false 로 유지하는것이 좋지만 ... 나는 관리자페이지 + index 에서 고객정보로 바꾸기 위해서 세션을 사용한다..--%>
<%--<%@ page session="false" %>--%>
<%--<c:set var="loginId"--%>
<%--       value="${pageContext.request.getSession(false)==null ? '' : pageContext.request.session.getAttribute('id')}"/>--%>
<%--<c:set var="loginId"--%>
<%--       value="${sessionScope.user==null ? '' : pageContext.request.session.getAttribute('id')}"/>--%>
<%--<c:set var="loginOutLink" value="${loginId=='' ? '/login/login' : '/login/logout'}"/>--%>
<%--<c:set var="loginOut" value="${loginId=='' ? '로그인' : '로그아웃'}"/>--%>

<c:set var="loginOutLink" value="${sessionScope.user==null ? '/login/login' : '/login/logout'}"/>
<c:set var="loginOut" value="${sessionScope.user==null ? '로그인' : '로그아웃'}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Index</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/index.css'/>">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
</head>
<body>

<%--<div id="gnb">--%>
<%--    gnb 영역--%>

<%--</div>--%>
<%--<div id="search">--%>
<%--    검색 영역--%>
<%--</div>--%>
<%--<div id="menu">--%>
<%--    <ul>--%>
<%--        <li id="logo"><a href="<c:url value='/'/>">Home</a></li>--%>
<%--        <li><a href="<c:url value='/test'/>">test</a></li>--%>
<%--        <li><a href="<c:url value='/board/list'/>">Board</a></li>--%>
<%--        <li><a href="<c:url value='${loginOutLink}'/>">${loginOut}</a></li>--%>
<%--        <li><a href="<c:url value='/register/add'/>">Sign in</a></li>--%>
<%--        <li><a href=""><i class="fa fa-search"></i></a></li>--%>
<%--    </ul>--%>
<%--</div>--%>

<div class="wrapper">
    <div class="wrap">
        <div class="top_gnb_area">
            <ul class="list">
                <c:if test="${member == null}">


                    <li>
                        <a href="<c:url value='${loginOutLink}'/>">로그인</a>
                    </li>
                    <li>
                        <a href="<c:url value='/register/add'/>">회원가입</a>
                    </li>
                </c:if>
                <li>
                    <a href="<c:url value='/board/list'/>">고객센터</a>
                </li>
            </ul>
        </div>
        <div class="top_area">
            <div class="logo_area">
                <h1>logo area</h1>
            </div>
            <div class="search_area">
                <h1>Search area</h1>
            </div>
            <div class="login_area">
                <%--                로그인 상태 x--%>
                <c:if test="${user==null}">
                    <div class="login_button"><a href="<c:url value='${loginOutLink}'/>">${loginOut}</a></div>
                    <span><a href="<c:url value='/register/add'/>">회원가입</a></span>
                </c:if>
                <%--                로그인한 상태--%>
                <c:if test="${user!=null}">
                    <div class="login_success_area">
                        <span>닉네임 : ${user.nickname}</span>
                        <span>등급 : ${user.id}</span>
                        <span>아이디 : ${user.id}</span>
                        <a href="<c:url value='${loginOutLink}'/>">${loginOut}</a>
                    </div>
                </c:if>
            </div>
            <div class="clearfix"></div>
        </div>
        <div class="navi_bar_area">
            <h1>navi area</h1>
        </div>
        <div class="content_area">
            <h1>content area</h1>
        </div>
    </div>
</div>

</body>
</html>

<div style="text-align:center">
    <h1>This is HOME</h1>
    <h1>This is HOME</h1>
    <h1>This is HOME</h1>
</div>
</body>
</html>
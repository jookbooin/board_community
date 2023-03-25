<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ page import="java.net.URLDecoder" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/registerForm/registerinput.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/registerForm/registerform.css'/>">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script src="<c:url value='/js/daum_address.js'/>"></script>
    <script src="<c:url value='/js/registerForm/formCheck.js'/>"></script>

    <title>Register</title>
</head>
<body>
<div id="menu">
    <ul>
        <li id="logo"><a href="<c:url value='/'/>">Home</a></li>
        <%--        <li><a href="<c:url value='/'/>">Home</a></li>--%>
        <%--        <li><a href="<c:url value='/board/list'/>">Board</a></li>--%>
        <%--        <li><a href="<c:url value='${loginOutLink}'/>">${loginOut}</a></li>--%>
        <%--        <li><a href="<c:url value='/register/add'/>">Sign in</a></li>--%>
        <%--        <li><a href=""><i class="fa fa-search"></i></a></li>--%>
    </ul>
</div>

<form:form modelAttribute="userDto" onsubmit="return formCheck(this)">
    <div class="title">Register</div>
    <%--    <div id="msg" class="msg"><form:errors path="id"/></div>--%>

    <div class="form-group">
        <form:input path="id" class="input-field" id="id-input" type="text" name="id" placeholder="아이디"/>
        <div class="msg">
            <form:errors path="id" id="id_error"/>
            <span id="id_available">사용 가능한 아이디입니다.</span>
            <span id="id_exist">사용중인 아이디 입니다</span>
        </div>
    </div>

    <div class="form-group">
        <form:input path="pwd" class="input-field" id="pwd-input" type="text" name="pwd" placeholder="비밀번호"/>
        <div class="msg">
                <%--            <form:errors path="pwd" id="pwd_error"/>--%>

        </div>
            <%--        비밀번호 확인 --%>
        <input class="input-field" id="pwd-check" name="pwd_ck">
        <div class="msg">
            <span id="pwd_check_eq" style="display: none">비밀번호가 일치합니다</span>
            <span id="pwd_check_ne" style="display: none">비밀번호가 일치하지 않습니다.</span>
                <%--            <form:errors path="pwd" id="pwd_error"/>--%>
        </div>
    </div>

    <div class="form-group">
        <form:input path="name" class="input-field" type="text" name="name" placeholder="이름"/>
        <div class="msg"></div>
    </div>

    <div class="form-group">
        <form:input path="nickname" class="input-field" id="nickname-input" type="text" name="nickname"
                    placeholder="닉네임"/>
        <div class="msg">
            <span id="nick_available">사용 가능한 닉네임입니다.</span>
            <span id="nick_exist">사용 중인 닉네임입니다.</span>
            <form:errors path="nickname" id="nickname_error"/>
        </div>
    </div>

    <div class="form-group">
        <form:input path="number" class="input-field" id="number-input" type="text" name="number" placeholder="전화번호"/>
        <div class="msg">
            <span id="num_available">등록가능한 번호 입니다.</span>
            <span id="num_exist">이미 등록된 번호 입니다</span>
            <form:errors path="number" id="number_error"/>

        </div>
    </div>

    <div class="form-group">
        <form:input path="email" class="input-field" id="email-input" type="text" name="email" placeholder="이메일"/>
        <div class="msg">
            <span id="email_available">등록가능한 이메일 입니다.</span>
            <span id="email_exist">등록된 이메일 입니다</span>
            <form:errors path="email" id="email_error"/>
        </div>

    </div>

    <div class="form-group">
        <form:input path="birth" class="input-field" type="text" name="birth" placeholder="생년월일"/>
        <div class="msg"></div>
    </div>

    <div class="form-group">
        <div id="addr_num">
            <form:input path="addr_num" class="input-field" type="text" name="addr_num" placeholder="우편번호"
                        readonly="readonly"/>
                <%--            <button type="button">우편번호 찾기</button>--%>
            <input id="address_num" type="button" onclick="daum_address()" value="우편번호 찾기"/>
        </div>
        <div id="addr_area">
            <form:input path="addr_area" class="input-field" type="text" name="addr_area" placeholder="사는 지역"
                        readonly="readonly"/>
        </div>

        <div id="addr_detail">
            <form:input path="addr_detail" class="input-field" type="text" name="addr_detail" placeholder="상세주소"
                        readonly="readonly"/>
        </div>

        <input class="input-field" type="hidden" name="admin" value="0">
    </div>

    <button>회원 가입</button>
</form:form>
<


<%--AJAX : 중복체크 --%>
<script>
    let id_check = false;
    let nickname_check = false;
    let mail_check = false;
    let number_check = false;
    let pwd_eq_check = false;


    $('#id-input').on("propertychange change keyup paste input", function () {
        let check = $('#id-input').val(); // 입력되는 값
        let data = {check: check}      // controller 전달
        $.ajax({
            type: "post",
            url: "<c:url value='/register/idCheck'/>",
            data: data,
            success: function (result) {
                if (result == 'success') {
                    $('#id_error').css("display", "none");
                    $('#id_available').css("display", "inline-block");
                    $('#id_exist').css("display", "none");
                    id_check = true;
                } else {
                    $('#id_error').css("display", "none");
                    $('#id_exist').css("display", "inline-block");
                    $('#id_available').css("display", "none");
                    id_check = false;
                }
            }
        });
    });
    $('#nickname-input').on("propertychange change keyup paste input", function () {
        let check = $('#nickname-input').val(); // 입력되는 값
        let data = {check: check}      // controller 전달
        $.ajax({
            type: "post",
            url: "<c:url value='/register/nicknameCheck'/>",
            data: data,
            success: function (result) {
                if (result == 'success') {
                    $('#nickname_error').css("display", "none");
                    $('#nick_available').css("display", "inline-block");
                    $('#nick_exist').css("display", "none");
                    nickname_check = true;
                } else {
                    $('#nickname_error').css("display", "none");
                    $('#nick_exist').css("display", "inline-block");
                    $('#nick_available').css("display", "none");
                    nickname_check = false;
                }
            }
        });
    });

    $('#email-input').on("propertychange change keyup paste input", function () {
        let check = $('#email-input').val(); // 입력되는 값
        let data = {check: check}      // controller 전달
        $.ajax({
            type: "post",
            url: "<c:url value='/register/emailCheck'/>",
            data: data,
            success: function (result) {
                if (result == 'success') {
                    $('#email_error').css("display", "none");
                    $('#email_available').css("display", "inline-block");
                    $('#email_exist').css("display", "none");
                    mail_check = true;
                } else {
                    $('#email_error').css("display", "none");
                    $('#email_exist').css("display", "inline-block");
                    $('#email_available').css("display", "none");
                    mail_check = false;
                }
            }
        });
    });

    $('#number-input').on("propertychange change keyup paste input", function () {
        let check = $('#number-input').val(); // 입력되는 값
        let data = {check: check}      // controller 전달
        $.ajax({
            type: "post",
            url: "<c:url value='/register/numberCheck'/>",
            data: data,
            success: function (result) {
                if (result == 'success') {
                    $('#number_error').css("display", "none");
                    $('#num_available').css("display", "inline-block");
                    $('#num_exist').css("display", "none");
                    number_check = true;
                } else {
                    $('#number_error').css("display", "none");
                    $('#num_exist').css("display", "inline-block");
                    $('#num_available').css("display", "none");
                    number_check = false;
                }
            }
        });
    });
</script>

<script>
    $('#pwd-check').on("propertychange change keyup paste input", function () {
        let pw = $('#pwd-input').val();
        let pwck = $('#pwd-check').val();

        if (pw == pwck) {
            $('#pwd_check_eq').css('display', 'block');
            $('#pwd_check_ne').css('display', 'none');
            pwd_eq_check = true;
        } else {
            $('#pwd_check_eq').css('display', 'none');
            $('#pwd_check_ne').css('display', 'block');
            pwd_eq_check = false;
        }

    });
</script>
</body>
</html>
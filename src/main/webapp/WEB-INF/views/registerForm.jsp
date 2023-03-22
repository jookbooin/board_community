<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import="java.net.URLDecoder" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="<c:url value='/css/menu.css'/>">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <style>
        * {
            box-sizing: border-box;
        }

        form {
            /*width: 800px;*/
            /*height: 1000px;*/
            /*display: flex;*/
            flex-direction: column;
            align-items: center;
            position: absolute;
            top: 10%;
            left: 40%;
            /*transform: translate(-50%, -50%);*/
            /*border: 1px solid rgb(89, 117, 196);*/
            /*border-radius: 10px;*/
        }

        .input-field {
            width: 300px;
            height: 30px;
            border: 1px solid rgb(89, 117, 196);
            border-radius: 5px;
            padding: 0 10px;
            margin-bottom: 5px;
        }

        label {
            width: 300px;
            height: 30px;
            margin-top: 4px;
        }

        button {
            background-color: rgb(89, 117, 196);
            color: white;
            width: 300px;
            height: 50px;
            font-size: 17px;
            border: none;
            border-radius: 5px;
            margin: 20px 0 30px 0;
        }

        .title {
            font-size: 50px;
            margin: 40px 0 30px 0;
        }

        .msg {
            height: 15px;
            text-align: center;
            font-size: 10px;
            color: red;
            margin-bottom: 5px;
        }

        .form-group {

        }

        /*    중복아이디 존재하지 않는 경우*/
        .id_available {
            color: green;
            display: none;
        }

        /* 중복아이디 존재하는 경우 */
        .id_exist {
            color: red;
            display: none;
        }

    </style>
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

<form:form modelAttribute="userDto">
    <div class="title">Register</div>
    <%--    <div id="msg" class="msg"><form:errors path="id"/></div>--%>

    <div class="form-group">
        <input class="input-field" id="id_input" type="text" name="id" placeholder="아이디">
        <div class="msg">
            <form:errors path="id" cssClass="msg"/>
            <span class="id_available">사용 가능한 아이디입니다.</span>
            <span class="id_exist">아이디가 이미 존재합니다.</span>
        </div>
    </div>

    <div class="form-group">

        <input class="input-field" type="text" name="pwd" placeholder="비밀번호">
        <div class="msg">
            <form:errors path="pwd"/>

        </div>
            <%--        비밀번호 확인 --%>
        <input class="input-field" name="pwd_ck">
        <div class="msg">
                <%--            <form:errors path="pwd_ck"/>--%>
        </div>
    </div>

    <div class="form-group">
        <input class="input-field" type="text" name="name" placeholder="이름">
        <div class="msg"></div>
    </div>

    <div class="form-group">
        <input class="input-field" type="text" name="nickname" placeholder="닉네임">
        <div class="msg"></div>
    </div>

    <div class="form-group">
        <input class="input-field" type="text" name="number" placeholder="전화번호">
        <div class="msg"></div>
    </div>

    <div class="form-group">
        <input class="input-field" type="text" name="email" placeholder="이메일">
        <div class="msg">
            <form:errors path="email"/>
        </div>
        <div id="email_num">
            <input class="input-field" name="email_num">
            <span>인증번호 전송</span>
        </div>
        <div class="msg">
                <%--            <form:errors path="email_num"/>--%>
        </div>
    </div>

    <div class="form-group">
        <input class="input-field" type="text" name="birth" placeholder="생년월일">
        <div class="msg"></div>
    </div>

    <div class="form-group">
        <div id="addr_num">
            <input class="input-field" type="text" name="addr_num" placeholder="우편번호">
                <%--            <button type="button">우편번호 찾기</button>--%>
            <span type="button">우편번호 찾기</span>
        </div>
        <div id="addr_area">
            <input class="input-field" type="text" name="addr_area" placeholder="사는 지역">
        </div>

        <div id="addr_detail">
            <input class="input-field" type="text" name="addr_detail" placeholder="상세주소">
        </div>

        <input class="input-field" type="hidden" name="admin" value="0">
    </div>

    <button>회원 가입</button>
</form:form>

<script>
    function formCheck(frm) {
        let msg = '';
        if (frm.id.value.length < 3) {
            setMessage('id의 길이는 3이상이어야 합니다.', frm.id);
            return false;
        }
        if (frm.pwd.value.length < 3) {
            setMessage('pwd의 길이는 3이상이어야 합니다.', frm.pwd);
            return false;
        }

        return true;
    }

    function setMessage(msg, element) {
        document.getElementById("msg").innerHTML = `<i class="fa fa-exclamation-circle"> ${'${msg}'}</i>`;
        if (element) {
            element.select();
        }
    }

    $('#id_input').on("propertychange change keyup paste input", function () {
        let id = $('#id_input').val(); // 입력되는 값
        let data = {id: id}      // controller 전달
        $.ajax({
            type: "post",
            url: "<c:url value='/register/idCheck'/>",
            data: data,
            success: function (result) {
                if (result == 'success') {
                    $('.id_available').css("display", "inline-block");
                    $('.id_exist').css("display", "none");
                } else {
                    $('.id_exist').css("display", "inline-block");
                    $('.id_available').css("display", "none");
                }
            }
        });

    });

</script>
</body>
</html>
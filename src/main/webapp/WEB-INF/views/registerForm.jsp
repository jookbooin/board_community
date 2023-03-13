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
    <style>
        * {
            box-sizing: border-box;
        }

        form {
            width: 400px;
            height: 600px;
            display: flex;
            flex-direction: column;
            align-items: center;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            border: 1px solid rgb(89, 117, 196);
            border-radius: 10px;
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
            <%--        <label for=""></label>--%>
        <input class="input-field" type="text" name="id" placeholder="아이디">
        <div class="msg">
            <form:errors path="id" cssClass="msg"/>
        </div>
    </div>

    <div class="form-group">
            <%--        <label for=""></label>--%>
        <input class="input-field" type="text" name="pwd" placeholder="비밀번호">
        <div class="msg">
            <form:errors path="pwd"/>
        </div>
    </div>

    <div class="form-group">
            <%--        <label for=""></label>--%>
        <input class="input-field" type="text" name="name" placeholder="이름">
        <div class="msg"></div>
    </div>

    <div class="form-group">
            <%--        <label for=""></label>--%>
        <input class="input-field" type="text" name="nickname" placeholder="닉네임">
        <div class="msg"></div>
    </div>

    <div class="form-group">
            <%--        <label for=""></label>--%>
        <input class="input-field" type="text" name="number" placeholder="전화번호">
        <div class="msg"></div>
    </div>

    <div class="form-group">
            <%--        <label for=""></label>--%>
        <input class="input-field" type="text" name="email" placeholder="이메일">
        <div class="msg">
            <form:errors path="email"/>
        </div>
    </div>
    <div class="form-group">
        <input class="input-field" type="text" name="birth" placeholder="생년월일">
        <div class="msg"></div>
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
</script>
</body>
</html>
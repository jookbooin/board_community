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
    <link rel="stylesheet" href="<c:url value='/css/registerForm/registerinput.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/registerForm/registerform.css'/>">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script src="<c:url value='/resources/js/daum_address.js'/>"></script>

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
        <input class="input-field" id="id-input" type="text" name="id" placeholder="아이디">
        <div class="msg">
            <form:errors path="id" cssClass="msg"/>
            <span id="id_available">사용 가능한 아이디입니다.</span>
            <span id="id_exist">사용중인 아이디 입니다</span>
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
        <input class="input-field" id="nickname-input" type="text" name="nickname" placeholder="닉네임">
        <div class="msg">
            <span id="nick_available">사용 가능한 닉네임입니다.</span>
            <span id="nick_exist">사용 중인 닉네임입니다.</span>
        </div>
    </div>

    <div class="form-group">
        <input class="input-field" id="number-input" type="text" name="number" placeholder="전화번호">
        <div class="msg">
            <span id="num_available">등록가능한 번호 입니다.</span>
            <span id="num_exist">이미 등록된 번호 입니다</span>
        </div>
    </div>

    <div class="form-group">
        <input class="input-field" id="email-input" type="text" name="email" placeholder="이메일">
        <div class="msg">
            <span id="email_available">등록가능한 이메일 입니다.</span>
            <span id="email_exist">등록된 이메일 입니다</span>
            <form:errors path="email"/>
        </div>
            <%--        <div id="email_num">--%>
            <%--            <input class="input-field" name="email_num">--%>
            <%--            <span>인증번호 전송</span>--%>
            <%--        </div>--%>
            <%--        <div class="msg">--%>
            <%--                &lt;%&ndash;            <form:errors path="email_num"/>&ndash;%&gt;--%>
            <%--        </div>--%>
    </div>

    <div class="form-group">
        <input class="input-field" type="text" name="birth" placeholder="생년월일">
        <div class="msg"></div>
    </div>

    <div class="form-group">
        <div id="addr_num">
            <input class="input-field" type="text" name="addr_num" placeholder="우편번호" readonly="readonly">
                <%--            <button type="button">우편번호 찾기</button>--%>
            <input id="address_num" type="button" onclick="daum_address()" value="우편번호 찾기"/>
        </div>
        <div id="addr_area">
            <input class="input-field" type="text" name="addr_area" placeholder="사는 지역" readonly="readonly">
        </div>

        <div id="addr_detail">
            <input class="input-field" type="text" name="addr_detail" placeholder="상세주소" readonly="readonly">
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


</script>

<%--AJAX : 중복체크 --%>
<script>
    $('#id-input').on("propertychange change keyup paste input", function () {
        let check = $('#id-input').val(); // 입력되는 값
        let data = {check: check}      // controller 전달
        $.ajax({
            type: "post",
            url: "<c:url value='/register/idCheck'/>",
            data: data,
            success: function (result) {
                if (result == 'success') {
                    $('#id_available').css("display", "inline-block");
                    $('#id_exist').css("display", "none");
                } else {
                    $('#id_exist').css("display", "inline-block");
                    $('#id_available').css("display", "none");
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
                    $('#nick_available').css("display", "inline-block");
                    $('#nick_exist').css("display", "none");
                } else {
                    $('#nick_exist').css("display", "inline-block");
                    $('#nick_available').css("display", "none");
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
                    $('#email_available').css("display", "inline-block");
                    $('#email_exist').css("display", "none");
                } else {
                    $('#email_exist').css("display", "inline-block");
                    $('#email_available').css("display", "none");
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
                    $('#num_available').css("display", "inline-block");
                    $('#num_exist').css("display", "none");
                } else {
                    $('#num_exist').css("display", "inline-block");
                    $('#num_available').css("display", "none");
                }
            }
        });
    });
</script>

<script>
    // function daum_address() {
    //     new daum.Postcode({
    //         oncomplete: function (data) {
    //             // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
    //
    //             // 각 주소의 노출 규칙에 따라 주소를 조합한다.
    //             // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
    //             var addr = ''; // 주소 변수
    //             var extraAddr = ''; // 참고항목 변수
    //
    //             //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
    //             if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
    //                 addr = data.roadAddress;
    //             } else { // 사용자가 지번 주소를 선택했을 경우(J)
    //                 addr = data.jibunAddress;
    //             }
    //
    //             // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
    //             if (data.userSelectedType === 'R') {
    //                 // 법정동명이 있을 경우 추가한다. (법정리는 제외)
    //                 // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
    //                 if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
    //                     extraAddr += data.bname;
    //                 }
    //                 // 건물명이 있고, 공동주택일 경우 추가한다.
    //                 if (data.buildingName !== '' && data.apartment === 'Y') {
    //                     extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
    //                 }
    //                 // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
    //                 if (extraAddr !== '') {
    //                     extraAddr = ' (' + extraAddr + ')';
    //                 }
    //                 // 주소변수 문자열과 참고항목 문자열 합치기
    //                 addr += extraAddr;
    //
    //             } else {
    //                 addr += ' ';
    //             }
    //
    //             // 우편번호와 주소 정보를 해당 필드에 넣는다.
    //             $("[name=addr_num]").val(data.zonecode);
    //             $("[name=addr_area]").val(addr);
    //             // 커서를 상세주소 필드로 이동한다.
    //             $("[name=addr_detail]").attr("readonly", false);
    //             $("[name=addr_detail]").focus();
    //
    //         }
    //     }).open();
    // }
</script>
</body>
</html>
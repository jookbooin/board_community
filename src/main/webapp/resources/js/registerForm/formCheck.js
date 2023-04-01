function formCheck(frm) {
    let msg = '';
    if (frm.id.value.length == 0) {
        alert("아이디를 입력하세요!");
        return false;
    } else if (frm.pwd.value.length == 0) {
        alert("비밀번호를 입력하세요");
        return false;
    } else if (frm.pwd_ck.value.length == 0) {
        alert("비밀번호 확인을 해주세요");
        return false;
    } else if (frm.name.value.length == 0) {
        alert("이름을 입력하세요");
        return false;
    } else if (frm.nickname.value.length == 0) {
        alert("닉네임을 입력하세요");
        return false;
    } else if (frm.number.value.length == 0) {
        alert("번호를 입력하세요");
        return false;
    } else if (frm.email.value.length == 0) {
        alert("이메일을 입력하세요");
        return false;
    } else if (frm.birth.value.length == 0) {
        alert("생년월일을 입력하세요");
        return false;
    } else if (frm.addr_num.value.length == 0) {
        alert("주소를 입력하세요");
        return false;
    } else if (!(id_check && number_check && mail_check && nickname_check)) {
        alert("이미 등록된 정보를 기입한 칸이 있습니다. 다시 입력하세요!");
        return false;
    }

    return true;
}

function mailFormCheck(email) {
    let form = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
    return form.test(email); // email이 form에 저장된 정규표현식에 부합할 경우 true
                             // 부합하지 않을 경우 false

}

function setMessage(msg, element) {
    document.getElementById("msg").innerHTML = `<i class="fa fa-exclamation-circle"> ${'${msg}'}</i>`;
    if (element) {
        element.select(); // 값을 잘못 입력했을 떄 그 위치가 선택되도록
    }
}
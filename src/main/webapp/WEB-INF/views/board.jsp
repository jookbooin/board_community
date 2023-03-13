<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page session="true" %>
<c:set var="loginId" value="${sessionScope.id}"/>
<c:set var="loginOutLink" value="${loginId=='' ? '/login/login' : '/login/logout'}"/>
<c:set var="loginOut" value="${loginId=='' ? 'Login' : 'Logout'}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>board</title>
    <%--    <link rel="stylesheet" href="<c:url value='/css/menu.css'/>">--%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <style>
        /** {*/
        /*    box-sizing: border-box;*/
        /*    margin: 0;*/
        /*    padding: 0;*/
        /*    font-family: "Noto Sans KR", sans-serif;*/
        /*}*/

        /*.container {*/
        /*    width: 50%;*/
        /*    margin-left: auto;*/
        /*    margin-right: auto;*/
        /*    margin-bottom: 50px;*/
        /*    background: #009f47;*/
        /*}*/

        /*.writing-header {*/
        /*    position: relative;*/
        /*    margin: 20px 0 0 0;*/
        /*    padding-bottom: 10px;*/
        /*    border-bottom: 1px solid #323232;*/
        /*}*/

        /*input {*/

        /*    width: 100%;*/ /*input 태그에 이거는 지워야 할듯 싶다.*/
        /*    height: 35px;*/
        /*    margin: 5px 0px 10px 0px;*/
        /*    border: 1px solid #e9e8e8;*/
        /*    padding: 8px;*/
        /*    background: #f8f8f8;*/
        /*    outline-color: #e6e6e6;*/
        /*}*/

        /*textarea {*/
        /*    display: block;*/
        /*    width: 100%;*/
        /*    min-height: 17px;*/
        /*    padding: 0 20px;*/
        /*    border: 1px;*/
        /*    outline: 0;*/
        /*    font-size: 13px;*/
        /*    resize: none;*/
        /*    box-sizing: border-box;*/
        /*    background: transparent;*/
        /*    overflow-wrap: break-word;*/
        /*    overflow-x: hidden;*/
        /*    overflow-y: auto;*/

        /*}*/

        /*.frm {*/
        /*    width: 100%;*/
        /*}*/

        /*.btn {*/
        /*    font-size: 10pt;*/
        /*    color: black;*/
        /*    background-color: #eff0f2;*/
        /*    text-decoration: none;*/
        /*    padding: 9px 12px 9px 12px;*/
        /*    border-radius: 5px;*/
        /*    float: right;*/
        /*    cursor: pointer;*/
        /*}*/

        /*.btn:hover {*/
        /*    text-decoration: underline;*/
        /*}*/

        /*#comment {*/
        /*    width: 50%;*/
        /*    !*height: 100%;*!*/
        /*    margin: auto;*/
        /*    background: red;*/
        /*}*/

        /*#commentList {*/
        /*    !*width: 50%;*!*/
        /*    !*    height: 100%;*!*/
        /*    margin: auto;*/
        /*    background: khaki;*/
        /*}*/

        /* commenter commenter-writebox*/
        /*.commenter {*/
        /*    font-size: 12pt;*/
        /*    font-weight: bold;*/
        /*}*/

        /*.commenter-writebox {*/
        /*    padding: 15px 20px 20px 20px;*/
        /*}*/

        /*댓글 입력*/
        /*.writebox-bottom {*/
        /*    padding: 0px 10px 10px 10px;*/
        /*    min-height: 45px;*/
        /*}*/

        /*#comment-Form {*/
        /*    margin-top: 10px;*/
        /*    background-color: white;*/
        /*    border: 1px solid #e5e5e5;*/
        /*    border-radius: 5px;*/
        /*    margin: 10px;*/
        /*}*/

        /*#comment-rep-Form,*/
        /*#comment-mod-Form {*/
        /*    display: none;*/
        /*    background-color: white;*/
        /*    border: 1px solid #e5e5e5;*/
        /*    border-radius: 5px;*/
        /*    margin: 10px;*/
        /*}*/


        /*.btn {*/
        /*    font-size: 10pt;*/
        /*    color: black;*/
        /*    background-color: #eff0f2;*/
        /*    text-decoration: none;*/
        /*    padding: 9px 10px 9px 10px;*/
        /*    border-radius: 5px;*/
        /*    float: right;*/
        /*}*/

        /*#comment-wriBtn,*/
        /*#modcomment-wriBtn,*/
        /*#recomment-wriBtn {*/
        /*    color: #009f47;*/
        /*    background-color: #e0f8eb;*/
        /*}*/

    </style>
</head>
<body>
<div id="menu">
    <ul>
        <li id="logo"><a href="<c:url value='/'/>">Home</a></li>

        <li><a href="<c:url value='/board/list'/>">Board</a></li>
        <li><a href="<c:url value='${loginOutLink}'/>">${loginOut}</a></li>
        <li><a href="<c:url value='/register/add'/>">Sign in</a></li>
        <li><a href=""><i class="fa fa-search"></i></a></li>
    </ul>
</div>
<script>
    let msg = "${msg}";
    if (msg == "WRT_ERR") alert("게시물 등록에 실패하였습니다. 다시 시도해 주세요.");
    if (msg == "MOD_ERR") alert("게시물 수정에 실패하였습니다. 다시 시도해 주세요.");
</script>

<%--전체 페이지--%>
<article id="board-All">

    <%--    게시판--%>
    <section class="container" id="board-container">
        <h2 class="writing-header">게시판 ${mode=="new" ? "글쓰기" : "읽기"}</h2>

        <form id="board-Form" class="frm" action="" method="post" enctype="multipart/form-data">
            <input type="hidden" name="bno" value="${boardDto.bno}">
            <div>
                <label>제목</label>
                <input name="title" type="text" value="<c:out value="${boardDto.title}"/>"
                       placeholder=" 제목을 입력해 주세요." ${mode=="new" ? "" : "readonly='readonly'"}>
            </div>
            <br>
            <div>
                <label>본문</label>
                <textarea name="content" rows="20" cols="40"
                          placeholder=" 내용을 입력해 주세요." ${mode=="new" ? "" : "readonly='readonly'"}><c:out
                        value="${boardDto.content}"/></textarea>
            </div>
            <br>

            <%--    파일 업로드 form : mode = new 일 때   --%>
            <c:if test="${mode eq 'new'}">
                <div>
                    <label>파일 선택</label>
                    <input type="file" multiple="multiple" name="upfile">
                        <%--                <input type="submit" value="전송">--%>
                    <br>
                    <button type="button" id="board-wriBtn" class="btn btn-write-board"><i class="fa fa-pencil"></i> 등록
                    </button>
                </div>
            </c:if>


            <div>
                <c:if test="${mode ne 'new'}">
                    <button type="button" id="board-new-wriBtn" class="btn btn-write-board"><i class="fa fa-pencil"></i>
                        글쓰기
                    </button>
                </c:if>

                <c:if test="${boardDto.id eq loginId}">
                    <button type="button" id="board-modBtn" class="btn btn-write-board"><i class="fa fa-edit"></i> 수정
                    </button>
                    <button type="button" id="board-delBtn" class="btn btn-write-board"><i class="fa fa-trash"></i> 삭제
                    </button>
                </c:if>
                <button type="button" id="board-listBtn" class="btn btn-list"><i class="fa fa-bars"></i> 목록</button>
            </div>
            <br>
            <div>
                <%--     업로드 확인 form :  mod != new--%>
                <c:if test="${mode ne 'new'}">
                    <tr>
                        <td colspan="2">
                            <ul>
                                <c:forEach var="fileDto" items="${boardDto.fileDtolist}">
                                <li>${fileDto.ofname}<a href="#" class="download"
                                                        folder="${fileDto.folder}" sfile="${fileDto.sfname}"
                                                        ofile="${fileDto.ofname}">[다운로드]</a> <img
                                    <%--src="${pageContext.request.contextPath}/${fileDto.folder}/${fileDto.sfname}"--%>
                                        src="<c:url value='/${fileDto.folder}/${fileDto.sfname}'/>"
                                        alt="/${fileDto.folder}/${fileDto.sfname}" height="200" width="400"/>
                                    </c:forEach>
                            </ul>
                        </td>
                    </tr>
                </c:if>
            </div>
        </form>
    </section>


    <%--게시판 밑 댓글--%>
    <c:if test="${mode ne 'new'}">
        <section class="container" id="comment-container">

            <div id="commentList"></div>

                <%--            댓글 입력하는 부분 --%>
            <div id="comment-Form">
                <div class="commenter commenter-writebox">${loginId}</div>
                <div class="writebox">
                    <textarea name="comment-textarea" id=" " cols="30" rows="3" placeholder="댓글을 남겨보세요"></textarea>
                </div>
                <div class="writebox-bottom" id="comment-writebox-bottom">
                    <div class="register-box">
                        <a href="#" class="btn" id="comment-wriBtn">등록</a>
                    </div>
                </div>
            </div>

                <%--            답글 쓰는부분 hidden--%>
            <div id="comment-rep-Form">
                <div class="commenter commenter-writebox">댓글 쓰기</div>
                <div class="writebox" id="repcomment-writebox">
                    <textarea name="reply-textarea" id="" cols="30" rows="3" placeholder="댓글을 남겨보세요"></textarea>
                </div>
                <div class="writebox-bottom" id="repcomment-writebox-bottom">
                    <div class="register-box">
                        <a href="#" class="btn" id="recomment-wriBtn">등록</a>
                        <a href="#" class="btn" id="recomment-canBtn">취소</a>
                    </div>
                </div>
            </div>

                <%--수정 창 --%>
            <div id="comment-mod-Form">
                <div class="commenter commenter-writebox">댓글 수정</div>
                <div class="writebox" id="modcomment-writebox">
                    <textarea name="modify-textarea" id="" cols="30" rows="3" placeholder="댓글을 남겨보세요"></textarea>
                </div>
                <div class="writebox-bottom" id="modcomment-writebox-bottom">
                    <div class="register-box">
                        <a href="#" class="btn" id="modcomment-wriBtn">등록</a>
                        <a href="#" class="btn" id="modcomment-canBtn">취소</a>
                    </div>
                </div>
            </div>


        </section>
    </c:if>

    <%--  board 랑 comment 묶는 div   --%>
</article>


<script>

    $(document).ready(function () {

        let formCheck = function () {
            let form = document.getElementById("board-Form");
            if (form.title.value == "") {
                alert("제목을 입력해 주세요.");
                form.title.focus();
                return false;
            }
            if (form.content.value == "") {
                alert("내용을 입력해 주세요.");
                form.content.focus();
                return false;
            }
            return true;
        }

        $("#board-new-wriBtn").on("click", function () {
            location.href = "<c:url value='/board/write'/>";
        });

        $("#board-wriBtn").on("click", function () {
            let form = $("#board-Form");
            form.attr("action", "<c:url value='/board/write'/>");
            form.attr("method", "post");
            if (formCheck())
                form.submit();
        });

        $("#board-modBtn").on("click", function () {
            let form = $("#board-Form");
            let isReadonly = $("input[name=title]").attr('readonly');
            // 1. 읽기 상태이면, 수정 상태로 변경
            if (isReadonly == 'readonly') {
                $(".writing-header").html("게시판 수정");
                $("input[name=title]").attr('readonly', false);
                $("textarea").attr('readonly', false);
                $("#board-modBtn").html("<i class='fa fa-pencil'></i> 수정");
                return;
            }
            // 2. 수정 상태이면, 수정된 내용을 서버로 전송
            form.attr("action", "<c:url value='/board/modify${searchCondition.queryString}'/>");
            form.attr("method", "post");
            if (formCheck())
                form.submit();
        });

        $("#board-delBtn").on("click", function () {
            if (!confirm("정말로 삭제하시겠습니까?")) return;
            let form = $("#board-Form");
            form.attr("action", "<c:url value='/board/remove${searchCondition.queryString}'/>");
            form.attr("method", "post");
            form.submit();
        });

        $("#board-listBtn").on("click", function () {

            location.href = "<c:url value='/board/list${searchCondition.queryString}'/>";
        });


        // 댓글
        let bno = ${mode ne 'new'} ? "${boardDto.bno}" : "0";
        // jsp 하나에서 모든 기능을 담으려하다보니 <form>으로 새로운 글을 작성할때 ajax 들에서
        // boardDto.bno 에 값이 부여되지 않고 빈칸으로 나오게된다.
        // 이를 해결하기위해 bno 가 1부터 시작하므로 아무런 관련없는 0을 쓰기모드에 부여했다 --> 동작을 안하면 상관없지 않을까??
        // bno값이 있는것 처럼 보이기 위해 문자열 처리를 해주었고  bno를 사용할 때는 "" 를 빼주는 방식을 사용했다. --> 처음 만들어보는 것이니 나중에 생각해보자..
        showList(bno);


        // 댓글 쓰기
        $("#comment-wriBtn").click(function () {
            let comment = $("textarea[name=comment-textarea]").val();
            if (comment.trim() == '') {
                alert("댓글을 입력해주세요");
                $("textarea[name=comment-textarea]").focus()
                return;
            }


            $.ajax({
                type: 'POST',       // 요청 메서드
                url: "<c:url value='/comments'/>?bno=" + (bno - ""),
                headers: {"content-type": "application/json"}, // 요청 헤더
                data: JSON.stringify({bno: bno, comment: comment}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
                success: function (result) {
                    alert("POST:success");
                    showList(bno);
                },
                error: function () {
                    alert("POST:error")
                } // 에러가 발생했을 때, 호출될 함수
            });
            $("textarea[name=comment-textarea]").val('');
            $("textarea[name=reply-textarea]").val('');
            $("#comment-rep-Form").css("display", "none");
            $("#comment-mod-Form").css("display", "none");
            $("#comment-rep-Form").appendTo($("#comment-container"));
            $("#comment-mod-Form").appendTo($("#comment-container"));
        });


        // 삭제버튼
        $("#commentList").on("click", ".comment-delBtn", function () {
            if (!confirm("정말로 삭제하시겠습니까?")) return;
            let cno = $(this).parent().attr("data-cno");
            let bno = $(this).parent().attr("data-bno");

            $.ajax({
                type: 'DELETE',
                url: "<c:url value='/comments'/>/" + cno + "?bno=" + bno,
                success: function (result) {
                    $("#commentList").html(result);
                    alert("Comment:DEL_OK");
                    showList(bno);
                },
                error: function () {
                    alert("comment:DEL_error");
                }
            });
            $("#comment-rep-Form").css("display", "none");
            $("#comment-mod-Form").css("display", "none");
            $("#comment-rep-Form").appendTo($("#comment-container"));
            $("#comment-mod-Form").appendTo($("#comment-container"));
        });


        // 답글 버튼
        $("#commentList").on("click", ".comment-replyBtn", function () {
            let bno = $(this).parent().attr("data-bno");
            // 수정 칸이 있다면 삭제한다 .
            $("#comment-mod-Form").css("display", "none");
            // 숨긴 답글 위치를 가져온다.
            $("#comment-rep-Form").appendTo($(this).parent());
            // 댓글의 버튼에 bno 전달
            $("#recomment-wriBtn").attr("data-bno", bno);
            // 보이게 한다
            $("#comment-rep-Form").css("display", "block");

        });

        // 답글 버튼 내부
        $("#recomment-canBtn").click(function (e) {
            $("#comment-rep-Form").css("display", "none");
            $("#comment-rep-Form").appendTo($("#comment-container"));
            $("#comment-mod-Form").appendTo($("#comment-container"));
        });

        $("#recomment-wriBtn").click(function (e) {
            let comment = $("textarea[name=reply-textarea]").val();
            let bno = $(this).attr("data-bno");

            let pcno = $("#comment-rep-Form").parent().attr("data-pcno");

            if (comment.trim() == '') { // 비어있으면  alert + 중단
                alert("댓글을 입력해주세요");
                $("input[name=comment]").focus()
                return;
            }

            $.ajax({
                type: 'POST',       // 요청 메서드
                url: "<c:url value='/comments'/>?bno=" + bno,
                headers: {"content-type": "application/json"}, // 요청 헤더
                data: JSON.stringify({pcno: pcno, bno: bno, comment: comment}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
                success: function (result) {
                    alert("POST-pcno:success");
                    showList(bno);
                },
                error: function () {
                    alert("POST-pcno:error")
                } // 에러가 발생했을 때, 호출될 함수
            });
            $("textarea[name=reply-textarea]").val('');
            $("#comment-rep-Form").css("display", "none");
            $("#comment-rep-Form").appendTo($("#comment-container"));
            $("#comment-mod-Form").appendTo($("#comment-container"));
        });

        // 수정 버튼
        $("#commentList").on("click", ".comment-modBtn", function () {

            // comment-rep-Form 초기화
            $("#comment-rep-Form").css("display", "none");
            // 숨긴 수정 위치를 가져온다 .
            $("#comment-mod-Form").appendTo($(this).parent());
            //comment 내용 수정하려는 modify-textarea에 뿌려주기
            let cno = $(this).parent().attr("data-cno");
            let bno = $(this).parent().attr("data-bno");
            // li -> span 태그의 comment 가져옴
            let comment = $('span.comment', $(this).parent()).text();
            // comment-mod-Form textarea에
            $("textarea[name=modify-textarea]").val(comment);
            // comment-mod-Form 의 등록버튼에 cno 전달하기
            $("#modcomment-wriBtn").attr("data-cno", cno);
            // ajax 보낸후 showList(bno)로 보여주려고 bno도 전달했음
            $("#modcomment-wriBtn").attr("data-bno", bno);
            // comment-mod-Form 보이게 설정
            $("#comment-mod-Form").css("display", "block");
        });

        // 수정 버튼 내부
        $("#modcomment-canBtn").click(function () {
            $("#comment-mod-Form").css("display", "none");
            $("textarea[name=modify-textarea]").val('');
            $("#comment-rep-Form").appendTo($("#comment-container"));
            $("#comment-mod-Form").appendTo($("#comment-container"));
        });

        $("#modcomment-wriBtn").click(function () {
            let cno = $(this).attr("data-cno");
            let bno = $(this).attr("data-bno");
            let comment = $("textarea[name=modify-textarea]").val();

            if (comment.trim() == '') {
                alert("댓글을 입력해주세요");
                $("textarea[name=comment-textarea]").focus()
                return;
            }

            $.ajax({
                type: 'PATCH',       // 요청 메서드
                url: "<c:url value='/comments'/>/" + cno,  //  /ch4/comments/{cno}
                headers: {"content-type": "application/json"}, // 요청 헤더
                data: JSON.stringify({cno: cno, comment: comment}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
                success: function (result) {
                    alert("Patch:success");
                    showList(bno);
                },
                error: function () {
                    alert("Patch:error")
                } // 에러가 발생했을 때, 호출될 함수
            }); // $.ajax()
            $("textarea[name=modify-textarea]").val('');
            $("#comment-mod-Form").css("display", "none");
            $("#comment-rep-Form").appendTo($("#comment-container"));
            $("#comment-mod-Form").appendTo($("#comment-container"));
        });


    }); // document - function

    // 리스트 가져오기
    let showList = function (bno) {

        $.ajax({
            type: 'GET',       // 댓글 목록 가져오기
            url: "<c:url value='/comments'/>?bno=" + bno,  // 요청 URI
            success: function (result) {
                $("#commentList").html(toHtml(result)); // result로 댓글목록 가져온것을 commentList에 넣는다.
            },
            error: function () {
                alert("showList error")
            } // 에러가 발생했을 때, 호출될 함수
        }); // $.ajax()
    }

    // html로 반환
    let toHtml = function (comments) {
        let tmp = "<ul>";

        comments.forEach(function (comment) {
            tmp += '<li data-cno=' + comment.cno
            tmp += ' data-pcno=' + comment.pcno
            tmp += ' data-bno=' + comment.bno + '>'
            if (comment.cno != comment.pcno)
                tmp += 'ㄴ'
            tmp += ' commenter=<span class="commenter">' + comment.commenter + '</span>'
            tmp += ' comment=<span class ="comment">' + comment.comment + '</span>'
            tmp += ' up_date=' + comment.up_date
            tmp += '<button class="comment-delBtn" >삭제</button>'
            tmp += '<button class="comment-modBtn" >수정</button>'
            tmp += '<button class="comment-replyBtn">답글</button>'
            tmp += '</li>'
        })

        return tmp + "</ul>"
    }


</script>
</body>
</html>
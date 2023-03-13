// 외부(클라이언트) js에서
// jsp (서버)bno를 어떻게 받아오는 것일까? --> jsp 내부 코드가 너무 많아 분리하려 했는데 싫패했다.

// $(document).ready(function () {
//     showList(bno);
//
//     // 댓글 쓰기
//     $("#comment-wriBtn").click(function () {
//         let comment = $("textarea[name=comment-textarea]").val();
//         if (comment.trim() == '') {
//             alert("댓글을 입력해주세요");
//             $("textarea[name=comment-textarea]").focus()
//             return;
//         }
//
//
//         $.ajax({
//             type: 'POST',       // 요청 메서드
//             url: "<c:url value='/comments'/>?bno=" + (bno - ""),
//             headers: {"content-type": "application/json"}, // 요청 헤더
//             data: JSON.stringify({bno: bno, comment: comment}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
//             success: function (result) {
//                 alert("POST:success");
//                 showList(bno);
//             },
//             error: function () {
//                 alert("POST:error")
//             } // 에러가 발생했을 때, 호출될 함수
//         });
//         $("textarea[name=comment-textarea]").val('');
//         $("textarea[name=reply-textarea]").val('');
//         $("#comment-rep-Form").css("display", "none");
//         $("#comment-mod-Form").css("display", "none");
//         $("#comment-rep-Form").appendTo($("#comment-container"));
//         $("#comment-mod-Form").appendTo($("#comment-container"));
//     });
//
//
//     // 삭제버튼
//     $("#commentList").on("click", ".comment-delBtn", function () {
//         if (!confirm("정말로 삭제하시겠습니까?")) return;
//         let cno = $(this).parent().attr("data-cno");
//         let bno = $(this).parent().attr("data-bno");
//
//         $.ajax({
//             type: 'DELETE',
//             url: "<c:url value='/comments'/>/" + cno + "?bno=" + bno,
//             success: function (result) {
//                 $("#commentList").html(result);
//                 alert("Comment:DEL_OK");
//                 showList(bno);
//             },
//             error: function () {
//                 alert("comment:DEL_error");
//             }
//         });
//         $("#comment-rep-Form").css("display", "none");
//         $("#comment-mod-Form").css("display", "none");
//         $("#comment-rep-Form").appendTo($("#comment-container"));
//         $("#comment-mod-Form").appendTo($("#comment-container"));
//     });
//
//
//     // 답글 버튼
//     $("#commentList").on("click", ".comment-replyBtn", function () {
//         let bno = $(this).parent().attr("data-bno");
//         // 수정 칸이 있다면 삭제한다 .
//         $("#comment-mod-Form").css("display", "none");
//         // 숨긴 답글 위치를 가져온다.
//         $("#comment-rep-Form").appendTo($(this).parent());
//         // 댓글의 버튼에 bno 전달
//         $("#recomment-wriBtn").attr("data-bno", bno);
//         // 보이게 한다
//         $("#comment-rep-Form").css("display", "block");
//
//     });
//
//     // 답글 버튼 내부
//     $("#recomment-canBtn").click(function (e) {
//         $("#comment-rep-Form").css("display", "none");
//         $("#comment-rep-Form").appendTo($("#comment-container"));
//         $("#comment-mod-Form").appendTo($("#comment-container"));
//     });
//
//     $("#recomment-wriBtn").click(function (e) {
//         let comment = $("textarea[name=reply-textarea]").val();
//         let bno = $(this).attr("data-bno");
//
//         let pcno = $("#comment-rep-Form").parent().attr("data-pcno");
//
//         if (comment.trim() == '') { // 비어있으면  alert + 중단
//             alert("댓글을 입력해주세요");
//             $("input[name=comment]").focus()
//             return;
//         }
//
//         $.ajax({
//             type: 'POST',       // 요청 메서드
//             url: "<c:url value='/comments'/>?bno=" + bno,
//             headers: {"content-type": "application/json"}, // 요청 헤더
//             data: JSON.stringify({pcno: pcno, bno: bno, comment: comment}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
//             success: function (result) {
//                 alert("POST-pcno:success");
//                 showList(bno);
//             },
//             error: function () {
//                 alert("POST-pcno:error")
//             } // 에러가 발생했을 때, 호출될 함수
//         });
//         $("textarea[name=reply-textarea]").val('');
//         $("#comment-rep-Form").css("display", "none");
//         $("#comment-rep-Form").appendTo($("#comment-container"));
//         $("#comment-mod-Form").appendTo($("#comment-container"));
//     });
//
//     // 수정 버튼
//     $("#commentList").on("click", ".comment-modBtn", function () {
//
//         // comment-rep-Form 초기화
//         $("#comment-rep-Form").css("display", "none");
//         // 숨긴 수정 위치를 가져온다 .
//         $("#comment-mod-Form").appendTo($(this).parent());
//         //comment 내용 수정하려는 modify-textarea에 뿌려주기
//         let cno = $(this).parent().attr("data-cno");
//         let bno = $(this).parent().attr("data-bno");
//         // li -> span 태그의 comment 가져옴
//         let comment = $('span.comment', $(this).parent()).text();
//         // comment-mod-Form textarea에
//         $("textarea[name=modify-textarea]").val(comment);
//         // comment-mod-Form 의 등록버튼에 cno 전달하기
//         $("#modcomment-wriBtn").attr("data-cno", cno);
//         // ajax 보낸후 showList(bno)로 보여주려고 bno도 전달했음
//         $("#modcomment-wriBtn").attr("data-bno", bno);
//         // comment-mod-Form 보이게 설정
//         $("#comment-mod-Form").css("display", "block");
//     });
//
//     // 수정 버튼 내부
//     $("#modcomment-canBtn").click(function () {
//         $("#comment-mod-Form").css("display", "none");
//         $("textarea[name=modify-textarea]").val('');
//         $("#comment-rep-Form").appendTo($("#comment-container"));
//         $("#comment-mod-Form").appendTo($("#comment-container"));
//     });
//
//     $("#modcomment-wriBtn").click(function () {
//         let cno = $(this).attr("data-cno");
//         let bno = $(this).attr("data-bno");
//         let comment = $("textarea[name=modify-textarea]").val();
//
//         if (comment.trim() == '') {
//             alert("댓글을 입력해주세요");
//             $("textarea[name=comment-textarea]").focus()
//             return;
//         }
//
//         $.ajax({
//             type: 'PATCH',       // 요청 메서드
//             url: "<c:url value='/comments'/>/" + cno,  //  /ch4/comments/{cno}
//             headers: {"content-type": "application/json"}, // 요청 헤더
//             data: JSON.stringify({cno: cno, comment: comment}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
//             success: function (result) {
//                 alert("Patch:success");
//                 showList(bno);
//             },
//             error: function () {
//                 alert("Patch:error")
//             } // 에러가 발생했을 때, 호출될 함수
//         }); // $.ajax()
//         $("textarea[name=modify-textarea]").val('');
//         $("#comment-mod-Form").css("display", "none");
//         $("#comment-rep-Form").appendTo($("#comment-container"));
//         $("#comment-mod-Form").appendTo($("#comment-container"));
//     });
// });
//
// let showList = function (bno) {
//
//     $.ajax({
//         type: 'GET',       // 댓글 목록 가져오기
//         url: "<c:url value='/comments'/>?bno=" + bno,  // 요청 URI
//         success: function (result) {
//             $("#commentList").html(toHtml(result)); // result로 댓글목록 가져온것을 commentList에 넣는다.
//         },
//         error: function () {
//             alert("showList error")
//         } // 에러가 발생했을 때, 호출될 함수
//     }); // $.ajax()
// }
//
// // html로 반환
// let toHtml = function (comments) {
//     let tmp = "<ul>";
//
//     comments.forEach(function (comment) {
//         tmp += '<li data-cno=' + comment.cno
//         tmp += ' data-pcno=' + comment.pcno
//         tmp += ' data-bno=' + comment.bno + '>'
//         if (comment.cno != comment.pcno)
//             tmp += 'ㄴ'
//         tmp += ' commenter=<span class="commenter">' + comment.commenter + '</span>'
//         tmp += ' comment=<span class ="comment">' + comment.comment + '</span>'
//         tmp += ' up_date=' + comment.up_date
//         tmp += '<button class="comment-delBtn" >삭제</button>'
//         tmp += '<button class="comment-modBtn" >수정</button>'
//         tmp += '<button class="comment-replyBtn">답글</button>'
//         tmp += '</li>'
//     })
//
//     return tmp + "</ul>"
// }
//

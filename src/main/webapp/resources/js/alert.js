let bno = 296;

let showList = function (bno) {
    $.ajax({
        type: 'GET',       // 댓글 목록 가져오기
        url: '/boardch/comments?bno=' + bno,  // 요청 URI
        // headers: {"content-type": "application/json"}, // 요청 헤더  [ --> 보낼 데이터 없으니 생략 ]
        // dataType: 'text', // 전송받을 데이터의 타입  [ --> datatype의 default는 json ]
        // data: JSON.stringify(person),  // [--> 이미 default로 json 이기 때문에 Stringify 필요 x ]
        success: function (result) {
            // person2 = JSON.parse(result);  //  [--> 이미 default로 json 이기 때문에 parse 필요 x ]
            $("#commentList").html(toHtml(result)); // result로 댓글목록 가져온것을 commentList에 넣는다.
            // alert("success")
        },
        error: function () {
            alert("GET:error")
        } // 에러가 발생했을 때, 호출될 함수
    }); // $.ajax()

}

$(document).ready(function () {
    showList(bno);

    // id = modBtn
    $("#modBtn").click(function () {
        let cno = $(this).attr("data-cno");


        let comment = $("input[name=comment]").val()

        if (comment.trim() == '') {
            alert("댓글을 입력해주세요");
            $("input[name=comment]").focus()
            return;
        }
        $.ajax({
            type: 'PATCH',       // 요청 메서드
            url: '/boardch/comments/' + cno,  //  /boardch/comments/{cno}
            headers: {"content-type": "application/json"}, // 요청 헤더
            data: JSON.stringify({cno: cno, comment: comment}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
            success: function (result) {
                alert(result);
                showList(bno);
            },
            error: function () {
                alert("PATCH:error")
            } // 에러가 발생했을 때, 호출될 함수
        }); // $.ajax()
    });

    $("#sendBtn").click(function () {
        let comment = $("input[name=comment]").val() // name=comment인 input 태그의 value 가져온다

        if (comment.trim() == '') { // 비어있으면  alert + 중단
            alert("댓글을 입력해주세요");
            $("input[name=comment]").focus()
            return;
        }
        $.ajax({
            type: 'POST',       // 요청 메서드
            url: '/boardch/comments?bno=' + bno,  // /boardch/comments?bno=1085  POST
            headers: {"content-type": "application/json"}, // 요청 헤더
            data: JSON.stringify({bno: bno, comment: comment}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
            success: function (result) {
                alert(result);
                showList(bno);
            },
            error: function () {
                alert("POST:error")
            } // 에러가 발생했을 때, 호출될 함수
        });
    });

    $("#wrtRepBtn").click(function () {
        let comment = $("input[name=replyComment]").val() // name=comment인 input 태그의 value 가져온다
        let pcno = $("#replyForm").parent().attr("data-pcno");
        if (comment.trim() == '') { // 비어있으면  alert + 중단
            alert("댓글을 입력해주세요");
            $("input[name=comment]").focus()
            return;
        }
        $.ajax({
            type: 'POST',       // 요청 메서드
            url: '/boardch/comments?bno=' + bno,  // /boardch/comments?bno=1085  POST
            headers: {"content-type": "application/json"}, // 요청 헤더
            data: JSON.stringify({pcno: pcno, bno: bno, comment: comment}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
            success: function (result) {
                alert(result);
                showList(bno);
            },
            error: function () {
                alert("POST:error")
            } // 에러가 발생했을 때, 호출될 함수
        }); // $.ajax()
        // $("#replyForm").css("display", "none");
        $("input[name=replyComment]").val('');
        $("#replyForm").appendTo("body");
    });

    $("#commentList").on("click", ".replyBtn", function () {
        //replyForm 을 'replyBtn의 부모 위치로 옮긴다 --> 원래 위치는 밖
        $("#replyForm").appendTo($(this).parent());

        //repyForm을 보이게 해야함
        $("#replyForm").css("display", "block");
    });

    //class = modBtn
    $("#commentList").on("click", ".modBtn", function () {
        let cno = $(this).parent().attr("data-cno");

        let comment = $("span.comment", $(this).parent()).text();
        // commenet 내용 input으로 전달
        $("input[name=comment]").val(comment);
        //cno 전달한다 .
        $("#modBtn").attr("data-cno", cno);


    });

    $("#commentList").on("click", ".delBtn", function () {
        let cno = $(this).parent().attr("data-cno");
        // let bno = $(this).parent().attr("data-bno");

        $.ajax({
            type: 'DELETE',       // 댓글 목록 가져오기
            url: '/boardch/comments/' + cno + '?bno=' + bno,  // 요청 URI
            success: function (result) {
                // result = JSON.parse(result);  //  [--> 이미 default로 json 이기 때문에 parse 필요 x ]
                $("#commentList").html(result); // result로 댓글목록 가져온것을 commentList에 넣는다.
                alert("DEL_OK")
                showList(bno);
            },
            error: function () {
                alert("DELETE:error")
            } // 에러가 발생했을 때, 호출될 함수
        });

    });
});


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
        tmp += '<button class="delBtn">삭제</button>'
        tmp += '<button class="modBtn">수정</button>'
        tmp += '<button class="replyBtn">답글</button>'
        tmp += '</li>'
    })

    return tmp + "</ul>"
}
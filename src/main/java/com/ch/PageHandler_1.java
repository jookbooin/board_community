package com.ch;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageHandler_1 {
    private int totalCnt; // 총 게시물 갯수
    private int pageSize; // 한 페이지의 크기
    private int naviSize = 10; // 페이지 내비게이션의 크기
    private int totalPage; // 현재 페이지의 갯수
    private int page; // 현재 페이지
    private int beginPage; // 내비게이션의 첫번째 페이지
    private int endPage; // 내비게이션의 마지막 페이지
    private boolean showPrev; // 이전페이지로 이동하는 링크를 보여줄 것인지 여부
    private boolean showNext; // 다음 페이지로 이동하는 링크를 보여줄 것인지 여부

    public PageHandler_1() {
    }

    public PageHandler_1(int totalCnt, int page) {
        this(totalCnt, page, 10);
    }

    public PageHandler_1(int totalCnt, int page, int pageSize) {
        this.totalCnt = totalCnt;
        this.page = page;
        this.pageSize = pageSize;

        totalPage = (int) Math.ceil(totalCnt / (double) pageSize);  // double 줘야 소수점 올림
        beginPage = (page - 1) / naviSize * naviSize + 1;
        endPage = Math.min(beginPage + naviSize - 1, totalPage); // (1 + 10) -1

        showPrev = beginPage != 1;       // 첫 페이지면 안보여줌
        showNext = endPage != totalPage; // 마지막 페이지면 안보여줌
    }

    void print() {
        System.out.println("page = " + page);
        System.out.print(showPrev ? "[PREV]" : "");

        for (int i = beginPage; i <= endPage; i++)
            System.out.print(i + " ");
        System.out.println(showNext ? "[NEXT]" : "");

    }
}

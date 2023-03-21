package com.ch.dto;

import java.util.Date;
import java.util.List;
import java.util.Objects;


public class BoardDto {
    private Integer bno;
    private String title;
    private String content;
    private String id;
    private String nickname;
    private int view_cnt;
    private int comment_cnt;

    private int file_cnt;
    private List<FileDto> fileDtolist;
    private int[] delFno; // 삭제할 파일 번호..?
    private Date reg_date;

    public BoardDto() {
    }

    public BoardDto(Integer bno, String title, String content, String id) {
        this.bno = bno;
        this.title = title;
        this.content = content;
        this.id = id;
    }

    public BoardDto(String title, String content, String id, String nickname) {
        this.title = title;
        this.content = content;
        this.id = id;
        this.nickname = nickname;
    }

    public BoardDto(String title, String content, String id) {
        this.title = title;
        this.content = content;
        this.id = id;
    }

    public List<FileDto> getFileDtolist() {
        return fileDtolist;
    }

    public void setFileDtolist(List<FileDto> fileDtolist) {
        this.fileDtolist = fileDtolist;
    }

    public Integer getBno() {
        return bno;
    }

    public void setBno(Integer bno) {
        this.bno = bno;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getView_cnt() {
        return view_cnt;
    }

    public void setView_cnt(int view_cnt) {
        this.view_cnt = view_cnt;
    }

    public int getComment_cnt() {
        return comment_cnt;
    }

    public void setComment_cnt(int comment_cnt) {
        this.comment_cnt = comment_cnt;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    public int getFile_cnt() {
        return file_cnt;
    }

    public void setFile_cnt(int file_cnt) {
        this.file_cnt = file_cnt;
    }

    public int[] getDelFno() {
        return delFno;
    }

    public void setDelFno(int[] delFno) {
        this.delFno = delFno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardDto boardDto = (BoardDto) o;
        return Objects.equals(bno, boardDto.bno) && Objects.equals(title, boardDto.title) && Objects.equals(content, boardDto.content) && Objects.equals(id, boardDto.id) && Objects.equals(nickname, boardDto.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bno, title, content, id, nickname);
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                "bno=" + bno +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", view_cnt=" + view_cnt +
                ", comment_cnt=" + comment_cnt +
                ", file_cnt=" + file_cnt +
                ", fileDtolist=" + fileDtolist +
                ", reg_date=" + reg_date +
                '}';
    }
}

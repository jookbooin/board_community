package com.ch.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
public class CommentDto {
    private Integer cno;
    private Integer bno;
    private Integer pcno;
    private String comment;
    private String commenter;
    private String commenterId;
    private Date reg_date;
    private Date up_date;

    public CommentDto() {
    }

    public CommentDto(Integer bno, Integer pcno, String comment, String commenter, String commenterId) {
        this.bno = bno;
        this.pcno = pcno;
        this.comment = comment;
        this.commenter = commenter;
        this.commenterId = commenterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentDto that = (CommentDto) o;
        return Objects.equals(cno, that.cno) && Objects.equals(bno, that.bno) && Objects.equals(pcno, that.pcno) && Objects.equals(comment, that.comment) && Objects.equals(commenter, that.commenter) && Objects.equals(commenterId, that.commenterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cno, bno, pcno, comment, commenter, commenterId);
    }

}

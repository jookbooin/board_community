package com.ch.dto;

import java.util.Date;


public class FileDto {
    private Integer fno;
    private String Folder; // 파일 저장 위치
    private String ofname; // 원래 이름
    private String sfname; // 저장 이름

    private Integer bno;
    private Date upload_date;


    public FileDto() {
    }

    public FileDto(String folder, String ofname, String sfname, Integer bno) {
        Folder = folder;
        this.ofname = ofname;
        this.sfname = sfname;
        this.bno = bno;
    }

    public Integer getFno() {
        return fno;
    }

    public void setFno(Integer fno) {
        this.fno = fno;
    }

    public String getFolder() {
        return Folder;
    }

    public void setFolder(String folder) {
        Folder = folder;
    }

    public String getOfname() {
        return ofname;
    }

    public void setOfname(String ofname) {
        this.ofname = ofname;
    }

    public String getSfname() {
        return sfname;
    }

    public void setSfname(String sfname) {
        this.sfname = sfname;
    }

    public Integer getBno() {
        return bno;
    }

    public void setBno(Integer bno) {
        this.bno = bno;
    }

    public Date getUpload_date() {
        return upload_date;
    }

    public void setUpload_date(Date upload_date) {
        this.upload_date = upload_date;
    }

    @Override
    public String toString() {
        return "FileDto{" +
                "fno='" + fno + '\'' +
                ", Folder='" + Folder + '\'' +
                ", ofname='" + ofname + '\'' +
                ", sfname='" + sfname + '\'' +
                ", bno=" + bno +
                ", upload_date=" + upload_date +
                '}';
    }
}

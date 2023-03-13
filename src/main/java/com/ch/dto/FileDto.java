package com.ch.dto;

import java.util.Date;

public class FileDto {
    private String Folder; // 파일 저장 위치
    private String ofname; // 원래 이름
    private String sfname; // 저장 이름

    private Integer bno;
    private Date upload_date;


    public FileDto() {
    }


    public FileDto(String ofname, String sfname, int bno) {
        this.ofname = ofname;
        this.sfname = sfname;
        this.bno = bno;
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

    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
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
        return "FileDto={" +
                "Folder='" + Folder + '\'' +
                ", ofname='" + ofname + '\'' +
                ", sfname='" + sfname + '\'' +
                ", bno=" + bno +
                '}';
    }
}

package com.ch.util;

import org.imgscalr.Scalr;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

public class UploadFileUtils {

    private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);

    // 파일 업로드 처리
    public static String uploadFile(MultipartFile file, HttpServletRequest request) throws Exception {

        String originalFileName = file.getOriginalFilename(); // 파일명
        byte[] fileData = file.getBytes();  // 파일 데이터

        //1. 파일명 중복 방지 처리
        String uuidFileName = getUuidFileName(originalFileName);

        //2. 파일 경로 설정
        String rootPath = getRootPath(originalFileName, request); // 기본 경로 추출 (이미지 + 일반파일)
        String datePath = getDatePath(rootPath); // 날짜 경로 , 날짜 폴더 생성

        //3. 서버에 파일 저장
        File target = new File(rootPath + datePath, uuidFileName);// 파일 객체 생성
        FileCopyUtils.copy(fileData, target); // 파일 객체에 파일 데이터 복사

//        // 4. 이미지 파일인 경우 썸네일이미지 생성
//        if (MediaUtils.getMediaType(originalFileName) != null) {
//            uuidFileName = makeThumbnail(rootPath, datePath, uuidFileName);
//        }
        return replaceSavedFilePath(datePath, uuidFileName);
    }

    // 파일 삭제 처리
    public static void deleteFile(String fileName, HttpServletRequest request) {

        String rootPath = getRootPath(fileName, request); // 기본경로 추출(이미지 or 일반파일)

        // 1. 원본 이미지 파일 삭제
        MediaType mediaType = MediaUtils.getMediaType(fileName); // 타입 여부를 판별한다. (JPG , GIP , PNG)
        if (mediaType != null) {
            String originalImg = fileName.substring(0, 12) + fileName.substring(14);
            new File(rootPath + originalImg.replace('/', File.separatorChar)).delete();
        }

        // 2. 파일 삭제(썸네일이미지 or 일반파일)
        new File(rootPath + fileName.replace('/', File.separatorChar)).delete(); // 절대경로 사용
    }


    // 파일 출력을 위한 HttpHeader 설정
    public static HttpHeaders getHttpHeaders(String fileName) throws Exception {

        MediaType mediaType = MediaUtils.getMediaType(fileName); // 파일타입 확인
        HttpHeaders httpHeaders = new HttpHeaders();

        // 이미지 파일 O
        if (mediaType != null) {
            httpHeaders.setContentType(mediaType);
            return httpHeaders;
        }

        // 이미지 파일 X
        fileName = fileName.substring(fileName.indexOf("_") + 1); // UUID 제거
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM); // 다운로드 MIME 타입 설정
        // 파일명 한글 인코딩처리
        httpHeaders.add("Content-Disposition",
                "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"),
                        "ISO-8859-1") + "\"");

        return httpHeaders;
    }

    private static String replaceSavedFilePath(String datePath, String fileName) {
        String savedFilePath = datePath + File.separator + fileName;
        return savedFilePath.replace(File.separatorChar, '/');
    }

    //파일명 중복방지 처리
    private static String getUuidFileName(String originalFileName) {
        // 중복된 이름의 파일을 저장하지 않기 위해 UUID 키값 생성
        // 실제 저장할 파일명 = UUID + _ + 원본파일명
        return UUID.randomUUID().toString() + "_" + originalFileName;
    }

    // 기본경로 추출
    public static String getRootPath(String fileName, HttpServletRequest request) {
        String rootPath = "/resources/upload";
        MediaType mediaType = MediaUtils.getMediaType(fileName);
        if (mediaType != null)
            return request.getSession().getServletContext().getRealPath(rootPath + "/images");// 이미지 파일 경로

        return request.getSession().getServletContext().getRealPath(rootPath + "/files"); // 일반파일 경로
    }

    private static String getDatePath(String rootPath) {
        Calendar calendar = Calendar.getInstance();
        String yearPath = File.separator + calendar.get(Calendar.YEAR);
        String monthPath = yearPath + File.separator + new DecimalFormat("00").format(calendar.get(Calendar.MONTH) + 1);
        String datePath = monthPath + File.separator + new DecimalFormat("00").format(calendar.get(Calendar.DATE));

        makeDateDir(rootPath, yearPath, monthPath, datePath);
        return datePath;
    }

    // 날짜별 폴더 생성
    private static void makeDateDir(String rootPath, String... paths) {
        if (new File(rootPath + paths[paths.length - 1]).exists())
            return;
        for (String path : paths) {
            File dirPath = new File(rootPath + path);
            if (!dirPath.exists()) // 디렉토리 존재 하지않으면
                dirPath.mkdir();  // 최하위에 폴더를 만든다 ( root -> 년 -> 월 -> 일 ) 폴더 생성
        }
    }

    // 썸네일 이미지 생성
    private static String makeThumbnail(String uploadRootPath, String datePath, String fileName) throws Exception {
        // BufferedImage : 실제 이미지 X, 메모리상의 이미지를 의미하는 객체
        // 원본 이미지파일을 메모리에 로딩
        BufferedImage originalImg = ImageIO.read(new File(uploadRootPath + datePath, fileName));
        // 정해진 크기에 맞게 원본이미지를 축소
        BufferedImage thumbnailImg = Scalr.resize(originalImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);
        // 썸네일 이미지 파일명
        String thumbnailImgName = "s_" + fileName;
        // 썸네일 업로드 경로
        String fullPath = uploadRootPath + datePath + File.separator + thumbnailImgName;
        // 썸네일 이미지 파일 객체 생성
        File newFile = new File(fullPath);
        // 썸네일 파일 확장자 추출
        String formatName = MediaUtils.getFormatName(fileName);
        // 썸네일 파일 저장
        ImageIO.write(thumbnailImg, formatName, newFile);

        return thumbnailImgName;
    }

}
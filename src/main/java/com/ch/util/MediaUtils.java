package com.ch.util;

import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

class MediaUtils {

    private static Map<String, MediaType> mediaTypeMap;

    // meidaMap에 이미지확장자명에 따른 MINEType 저장
    static {
        mediaTypeMap = new HashMap<>();
        mediaTypeMap.put("JPG", MediaType.IMAGE_JPEG);
        mediaTypeMap.put("GIF", MediaType.IMAGE_GIF);
        mediaTypeMap.put("PNG", MediaType.IMAGE_PNG);
    }

    // 이미지 MINEType 꺼내서 반환, 이미지 파일이 아닐 경우 null 반환
    static MediaType getMediaType(String fileName) {
        String formatName = getFormatName(fileName);  //파일 타입 뽑아내었음
        return mediaTypeMap.get(formatName);
    }

    static String getFormatName(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase();
    }


}
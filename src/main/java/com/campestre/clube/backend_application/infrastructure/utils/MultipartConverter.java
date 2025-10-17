package com.campestre.clube.backend_application.infrastructure.utils;

import com.campestre.clube.backend_application.core.domain.valueobject.Image;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.multipart.MultipartFile;

import static com.campestre.clube.backend_application.core.exceptions.ExceptionExtensions.*;

public class MultipartConverter {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            throw INTERNAL_ERROR_CONVERT_JSON;
        }
    }

    public static Image toImage(MultipartFile file) {
        try {
            if(file.getContentType() == null) throw INTERNAL_ERROR_IMAGE_FORMAT;
            return Image.of(file.getBytes(), file.getContentType());
        } catch (Exception e) {
            throw INTERNAL_ERROR_CONVERT_IMAGE;
        }
    }
}

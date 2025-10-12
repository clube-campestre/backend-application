package com.campestre.clube.backend_application.infrastructure.web.mappers;

import com.campestre.clube.backend_application.core.domain.valueobject.Image;
import org.springframework.web.multipart.MultipartFile;

import static com.campestre.clube.backend_application.core.exceptions.ExceptionExtensions.*;

public class ImageMapper {

    public static Image toImage(MultipartFile file) {
        try {
            if(file.getContentType() == null) throw INTERNAL_ERROR_IMAGE_FORMAT;
            return Image.of(file.getBytes(), file.getContentType());
        } catch (Exception e) {
            throw INTERNAL_ERROR_CONVERT_IMAGE;
        }
    }
}
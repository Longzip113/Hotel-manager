package com.hotelManager.constants;

import java.util.Arrays;
import java.util.List;

public class Constants {
    // - Chỉ cho phép upload các loại tập tin sau: zip,rar,pdf, doc, docx, ppt,
    // pptx,xls, xlsx, jpg, png, bmp, gif
    public static final List<String> LIST_MIME_TYPE = Arrays.asList("application/zip", "application/vnd.rar",
            "application/pdf", "application/msword",  "application/x-rar-compressed", "application/octet-stream",
            "application/octet-stream", "application/x-zip-compressed", "multipart/x-zip", "application/x-rar",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document", "application/vnd.ms-powerpoint",
            "application/vnd.openxmlformats-officedocument.presentationml.presentation", "application/vnd.ms-excel",
            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "image/jpeg", "image/png", "image/bmp",
            "image/gif");

    // - Chỉ cho phép upload các loại tập tin sau: zip,rar,pdf, doc, docx, ppt,
    // pptx,xls, xlsx, jpg, png, bmp, gif
    public static final List<String> LIST_EXTENSION = Arrays.asList("zip", "rar", "pdf", "doc", "docx", "ppt", "pptx",
            "xls", "xlsx", "jpg", "png", "bmp", "gif");
    public static final List<String> LIST_IMAGE_FILE_EXTENSION = Arrays.asList("jpg", "png");
    public static final List<String> LIST_PATH_FORBIDDEN = Arrays.asList("/e7ab229d-f000-49a6-83f9-7b81f5831f95", "/");
    public static final List<String> LIST_PROJECT_PATH = Arrays.asList("/e7ab229d-f000-49a6-83f9-7b81f5831f95/a45b4b89-d2e6-426e-9c76-b63074021f74", "/e7ab229d-f000-49a6-83f9-7b81f5831f95/6e4155b7-edda-4c50-8c8b-89442688b3c7");
    public static final String ROOT_PATH = "/";
    public static final String RESULT_OCR = "result";
    public static final String PROC_STATUS_OCR = "proc_status";
    public static final String AUTHORIZATION = "Authorization";
    public static final String SUB_ROOT_PATH = "/e7ab229d-f000-49a6-83f9-7b81f5831f95";
    public static final int FOLDER = 0;
    public static final int FILE = 1;
    public static final Integer RECENT_FOLDER_LIMIT = 5;
    public static final Integer RECENT_FILE_LIMIT = 10;
    public static final int BATCH_SIZE = 2;
    public static final String SORT_BY_DISPLAY_NAME = "displayName";
    public static final String SORT_BY_LAST_VIEW_AT = "lastViewAt";
    public static final int REMOVE_FIRST = 0;
    public static final int INDEX_PATH_ROOT = 2;
    public static final String SORT_OR_DER_ASC = "ASC";
    public static final int KEYCLOAK_MAX_CONNECTION_POOL_SIZE = 100;
    public static final int APPROVED = 0;
    public static final int FILE_COPY_BUFFER_SIZE = 100000000;
    public static final int THUMBNAIL_WIDTH = 300;
    public static final int TOKEN_TIME_OUT = 10000;
    public static final List<String> LIST_OCR_EXTENSION = Arrays.asList("jpg", "png", "pdf");
}

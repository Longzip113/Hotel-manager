package com.hotelManager.constants.enums;

import lombok.Getter;

@Getter
public enum HotelManagerResponseCode {
    SUCCESS                             (1,     "Success"),
    UNAUTHORIZED                        (400,   "Unauthorized"),
    FORBIDDEN                           (403,   "You don't have permission"),
    ERROR_SERVER                        (500,   "Server error"),
    ERROR_ID_NOT_EXISTED                (-1,    "Id not existed"),
    ERROR_FOLDER_NOT_EXISTED            (-2,    "Folder not existed"),
    ERROR_FOLDER_CAN_NOT_UPLOAD_FILE    (-3,    "Can not upload file at this folder"),
    ERROR_FOLDER_ALREADY_EXISTED        (-4,    "Folder already existed"),
    ERROR_FILE_UPLOAD_INVALID           (-5,    "File upload invalid"),
    ERROR_FILE_NOT_EXISTED              (-6,    "File not existed"),
    ERROR_FILE_ALREADY_EXISTED          (-7,    "File already existed"),
    ERROR_FILE_NAME_ALREADY_EXISTED     (-8,    "File name already existed"),
    ERROR_FILE_STORAGE_NOT_FOUND        (-9,    "File storage not found"),
    ERROR_FILE_DUPLICATED               (-11,   "File duplicated"),
    ERROR_FOLDER_DUPLICATED             (-12,   "Folder duplicated"),
    ERROR_SAVE_LOG                      (-13,   "Save log failed"),
    ERROR_UPDATE_TAG                    (-14,   "Update tag failed"),
    ERROR_FOLDER_CAN_NOT_WRITE_TAG      (-15,   "Can not write tag at this folder"),
    ERROR_ACTION_NOT_EXISTED            (-16,   "Action does not exist"),
    ERROR_CAN_NOT_DELETE_FOLDER_ROOT    (-17,   "Can not delete root folder"),
    ERROR_GET_TAG_FAILED                (-18,   "Get tags failed"),
    ERROR_DOWNLOAD_EXPIRED_TOKEN        (-19,   "Token expired"),
    ERROR_FOLDER_CAN_NOT_RENAME         (-20,    "Can not rename this folder"),
    ERROR_UPLOAD_FILE                   (-21,   "Error when upload file"),
    ERROR_USER_NOT_EXISTED              (-22,   "User not existed"),
    ERROR_CAN_NOT_CREATE_FOLDER         (-23,   "Can not create folder at here"), 
    ERROR_FOLDER_CAN_NOT_MOVE_FILE      (-24,   "Can not move file at this folder"),
    ERROR_FOLDER_CAN_NOT_MOVE           (-25,   "Can not move file/folder at this folder"),
    ERROR_ITEM_NOT_EXISTED              (-26,   "Item not existed"),
    ERROR_ITEM_HAVE_BEEN_APPROVED       (-27,   "Item have been approved"),
    ERROR_FAIL_TO_APPROVE_DOCUMENT      (-28,   "Error when approve document"),
    ERROR_FILE_NAME                     (-29,   "File name not empty or null"),
    FILE_STORAGE_NOT_FOUND              (-30,   "File storage not found"),
    CREATE_OCR_FAIL                     (-31,   "Create ocr fail"),
    CAN_NOT_READ_FILE                   (-32,   "Can not read file"),
    ERROR_CAN_NOT_OCR_FILE              (-33,   "This file cannot be OCR"),
    ERROR_UPDATE_STATUS_OCR             (-34,   "Update status OCR fail"),
    ERROR_SAVE_OCR                      (-35,   "Save NodeOcr failed"),
    ERROR_UPDATE_CONTENT_OCR            (-36,   "Update Ocr content failed"),
    ERROR_UPDATE_APPROVE_OCR            (-37,   "Update approve ocr failed"),
    ERROR_FILE_OCR_NOT_FOUND            (-38,   "File has not been ocr"),
    ERROR_FILE_OCR_NOT_APPROVE          (-39,   "File is not in a waiting approve state");

    private final int responseCode;

    private final String message;

    HotelManagerResponseCode(int responseCode, String message) {
        this.responseCode = responseCode;
        this.message = message;
    }
}

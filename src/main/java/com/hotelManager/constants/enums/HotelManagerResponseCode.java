package com.hotelManager.constants.enums;

import lombok.Getter;

@Getter
public enum HotelManagerResponseCode {
    SUCCESS                             (1,     "Success"),
    UNAUTHORIZED                        (400,   "Unauthorized"),
    FORBIDDEN                           (403,   "You don't have permission"),
    ERROR_SERVER                        (500,   "Server error"),
    ERROR_PASSWORD_EMAIL                (-1,    "Email address or password is incorrect."),
    ERROR_DELETE_TYPE_ROOM              (-2,    "Có phòng đang chứa loại phòng xin kiểm tra lại !"),
    ERROR_ROOM_NOT_EXISTED              (-3,    "Phòng không tồn tại !"),
    ERROR_ROOM_ALREADY_EXISTED          (-4,    "Tên phòng đã tồn tại !"),
    ERROR_TYPE_ROOM_NOT_EXISTED         (-5,    "Loại phòng không tồn tại !"),
    ERROR_TYPE_ROOM_ALREADY_EXISTED     (-6,    "Loại phòng đã tồn tại !"),
    ERROR_SERVICE_ALREADY_EXISTED       (-7,    "Dịch vụ đã tồn tại !"),
    ERROR_SERVICE_NOT_EXISTED           (-8,    "Dịch vụ không tồn tại !"),
    ERROR_EMAIL_ALREADY_EXISTED         (-9,    "Email đã tồn tại !"),
    ERROR_PHONE_ALREADY_EXISTED         (-10,    "Phone đã tồn tại !"),
    ERROR_ID_CARD_ALREADY_EXISTED       (-11,    "IdCard đã tồn tại !"),
    ERROR_ID_NOT_EXISTED                (-12,    "Id không tồn tại !"),
    ERROR_DEVICE_ALREADY_EXISTED        (-13,    "Thiết bị đã tồn tại !"),
    ERROR_DELETE_DEVICE                 (-14,    "Có phòng đang chứa thiết bị xin kiểm tra lại !"),
    ERROR_ROLE_ALREADY_EXISTED          (-15,    "Code hoặc tên role đã tồn tại !"),
    ERROR_EMAIL_NOT_EXISTED             (-16,    "Email không tồn tại !"),
    ERROR_VERIFICATION                  (-17,    "Mã xác nhận không tồn tại !"),
    ERROR_DELEGATION_ALREADY_EXISTED    (-18,    "Tên đoàn đã tồn tại !"),
    ERROR_ROLE_ALREADY                  (-19,    "Hiện tại role đang được sử dụng !"),
    ERROR_ROOM_CHECK_IN                 (-20,    "Phòng không đang ở trạng thái checkin !"),
    ERROR_ROOM_CHECK_OUT                (-21,    "Phòng không đang ở trạng thái checkout !");

    private final int responseCode;

    private final String message;

    HotelManagerResponseCode(int responseCode, String message) {
        this.responseCode = responseCode;
        this.message = message;
    }
}

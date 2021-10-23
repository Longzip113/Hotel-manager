package com.hotelManager.constants;

import com.hotelManager.constants.enums.DocumentType;
import org.apache.commons.lang3.StringUtils;

public class MessageWriteLog {

    public static String READ_FOLDER (String displayName) {
        return "Đọc folder " + displayName;
    }

    public static String CHANGE_OWNER (String userNameOld, String userNameNew) {
        return "Thay đổi chủ sở hữu Từ: " + userNameOld + " sang: " + userNameNew;
    }

    public static String MOVE (String moveFrom, String moveTo) {
        return "Di chuyển từ: " + moveFrom + " đến: " + moveTo;
    }

    public static String APPROVED(String displayName, Integer type) {
        if (type.equals(DocumentType.FILE.getValue())) {
            return String.format("Duyệt file tên %s", displayName);
        } else {
            return String.format("Duyệt folder tên %s", displayName);
        }
    }

    //TODO: update log Download
    public static String DOWNLOAD(String displayName) {
        return "Download file tên " + displayName;
    }

    public static String CREATE(String displayName, Integer type) {
        if (type.equals(DocumentType.FILE.getValue())) {
            return String.format("upload file %s", displayName);
        } else {
            return String.format("Tạo folder tên %s", displayName);
        }
    }

    public static String TAG(String tagNew, String tagOld) {
        if (StringUtils.isNotBlank(tagOld)) {
            return String.format("Chỉnh sửa tag từ %s thành %s", tagOld, tagNew);
        } else {
            return String.format("Tạo tag %s", tagNew);
        }
    }

    public static String DELETE(String displayName, Integer type) {
        if (type.equals(DocumentType.FILE.getValue())) {
            return String.format("Xoá file %s", displayName);
        } else {
            return String.format("Xoá folder %s", displayName);
        }
    }

    public static String RENAME(String displayNameNew, String displayNameOld) {
        return "Đổi tên từ " + displayNameOld + " thành " + displayNameNew;
    }
}

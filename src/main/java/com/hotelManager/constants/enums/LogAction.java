package com.hotelManager.constants.enums;

public enum LogAction {
    READ(0), CREATE(1), DELETE(2), MOVE(3), RENAME(4), CHANGE_OWNER(5), APPROVE(6), DOWNLOAD(7);

    private final int value;

    LogAction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

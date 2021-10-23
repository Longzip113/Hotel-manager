package com.hotelManager.constants.enums;

public enum DocumentType {
    FOLDER(0), FILE(1);

    private final int value;

    DocumentType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}

package com.hotelManager.constants.enums;

public enum TypeRegister {
    BOOK_ROOM(0), CHECK_IN(1), CHECK_OUT(2), CANCEL(3);

    private final int value;

    TypeRegister(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

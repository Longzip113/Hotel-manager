package com.hotelManager.constants.enums;

public enum BookingType {
    DELEGATION(0), GUEST(1);

    private final int value;

    BookingType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}

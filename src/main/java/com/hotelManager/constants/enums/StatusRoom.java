package com.hotelManager.constants.enums;

public enum StatusRoom {
    NOT_BOOKED_YET(-1), ALREADY_BOOKED(0), LIVE_IN_BOOKED(1), MAINTENANCE(3),
    CLEANING_UP(2);

    private final int value;

    StatusRoom(int value) {
        this.value = value;
    }

    StatusRoom getValue(int value) {
        for(StatusRoom e: StatusRoom.values()) {
            if(e.value == value) {
                return e;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }
    
}

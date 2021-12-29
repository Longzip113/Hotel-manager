package com.hotelManager.constants.enums;

public enum StatusSchedule {
    NOT_CLEANED_YET(-1), CLEANING(0),
    CLEANING_UP(1);

    private final int value;

    StatusSchedule(int value) {
        this.value = value;
    }

    StatusSchedule getValue(int value) {
        for(StatusSchedule e: StatusSchedule.values()) {
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

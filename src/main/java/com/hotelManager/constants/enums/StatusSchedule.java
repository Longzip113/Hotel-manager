package com.hotelManager.constants.enums;

public enum StatusSchedule {
    NOT_CLEANED_YET(-1), NEED_CLEAN(0), CLEANING(1), CLEANING_UP(2);

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

package com.hotelManager.constants.enums;

public enum TypeDetailofTypeRoom {
    SERVICE(1), DEVICE(2);

    private final int value;

    TypeDetailofTypeRoom(int value) {
        this.value = value;
    }

    TypeDetailofTypeRoom getValue(int value) {
        for(TypeDetailofTypeRoom e: TypeDetailofTypeRoom.values()) {
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

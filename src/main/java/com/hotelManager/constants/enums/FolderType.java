package com.hotelManager.constants.enums;

public enum FolderType {
    ROOT(1), SUB_ROOT(2), NORMAL(3);

    private final int value;

    FolderType(int value) {
        this.value = value;
    }

    FolderType getValue(int value) {
        for(FolderType e: FolderType.values()) {
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

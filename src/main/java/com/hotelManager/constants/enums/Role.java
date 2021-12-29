package com.hotelManager.constants.enums;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("ADMIN"), USER_CLEANING("USER_CLEANING");

    Role(String name) {
        this.name = name;
    }

    private String name;
}

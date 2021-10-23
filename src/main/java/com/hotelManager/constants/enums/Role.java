package com.hotelManager.constants.enums;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("ADMIN"), USER("ADMIN");

    Role(String name) {
        this.name = name;
    }

    private String name;
}

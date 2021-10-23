package com.hotelManager.constants.enums;

public enum FolderName {
    MY_FOLDER(0, "/e7ab229d-f000-49a6-83f9-7b81f5831f95/612a1b87-7ca6-4144-862c-c894b2c0673d"),
    PROJECT(1, "/e7ab229d-f000-49a6-83f9-7b81f5831f95/a45b4b89-d2e6-426e-9c76-b63074021f74"),
    DEPARTMENT(2, "/e7ab229d-f000-49a6-83f9-7b81f5831f95/6e4155b7-edda-4c50-8c8b-89442688b3c7");

    private final int type;
    private final String path;

    FolderName(int type, String path) {
        this.type = type;
        this.path = path;
    }

    public int getType() {
        return type;
    }

    public String getPath() {
        return path;
    }

    public static boolean contains(int type) {

        for (FolderName folderName : FolderName.values()) {
            if (folderName.getType() == type) {
                return true;
            }
        }

        return false;
    }

    public static String getPathByType(int type) {

        for (FolderName folderName : FolderName.values()) {
            if (folderName.getType() == type) {
                return folderName.getPath();
            }
        }

        return null;
    }
}

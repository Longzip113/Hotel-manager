package com.hotelManager.services;

public interface QLKSUser {
    void addUser(Integer a);

    String findUser();

    void userThrowException() throws Exception;
}

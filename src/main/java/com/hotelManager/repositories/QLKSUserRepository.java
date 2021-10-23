package com.hotelManager.repositories;

import com.hotelManager.Users.User;

import java.util.Optional;

public interface QLKSUserRepository {

    Optional<User> getByEmail(String email);
}

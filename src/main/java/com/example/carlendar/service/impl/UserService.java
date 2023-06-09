package com.example.carlendar.service.impl;

import com.example.carlendar.dto.UserDto;
import com.example.carlendar.model.User;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);
}

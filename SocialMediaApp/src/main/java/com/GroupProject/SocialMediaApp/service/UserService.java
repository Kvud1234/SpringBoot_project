package com.GroupProject.SocialMediaApp.service;

import com.GroupProject.SocialMediaApp.dto.UserRegistrationDto;
import com.GroupProject.SocialMediaApp.model.User;

public interface UserService {
    User registerNewUser(UserRegistrationDto registrationDto) throws Exception;
    User findByEmail(String email);
}

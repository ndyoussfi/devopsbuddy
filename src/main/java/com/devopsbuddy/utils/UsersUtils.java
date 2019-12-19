package com.devopsbuddy.utils;

import com.devopsbuddy.backend.persistence.domain.backend.User;

public class UsersUtils {

    /**
     * Non instantiable
     * */
    // good practice for creating non instantiable classes
    private UsersUtils(){
        throw new AssertionError("Non instantiable");
    }

    /**
     * creates a user with basic attributes set
     * @return a User entity
     * */

    public static User createBasicUser(){

        User user = new User();
        user.setUsername("basicUser");
        user.setPassword("secret");
        user.setEmail("me@example.com");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setPhoneNumber("123456789123");
        user.setCountry("GB");
        user.setEnabled(true);
        user.setDescription("A basic user");
        user.setProfileImageUrl("https://blabla.images.com/basicuser");

        return user;
    }
}

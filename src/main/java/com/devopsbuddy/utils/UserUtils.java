package com.devopsbuddy.utils;

import com.devopsbuddy.backend.persistence.domain.backend.User;

public class UserUtils {

    /**
     * Non instantiable
     * */
    // good practice for creating non instantiable classes
    private UserUtils(){
        throw new AssertionError("Non instantiable");
    }

    /**
     * creates a user with basic attributes set
     * @param email the user email
     * @param username the user username
     * @return a User entity
     * */

    public static User createBasicUser(String username, String email){

        User user = new User();
        user.setUserName(username);
        user.setPassword("secret");
        user.setEmail(email);
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

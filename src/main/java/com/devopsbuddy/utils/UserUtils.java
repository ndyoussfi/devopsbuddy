package com.devopsbuddy.utils;

import com.devopsbuddy.backend.persistence.domain.backend.User;
import com.devopsbuddy.web.controllers.ForgotMyPasswordController;

import javax.servlet.http.HttpServletRequest;

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

    /**
     * Builds and returns the URL to reset the user password
     * @param request The http Servlet request
     * @param userId the user id
     * @param token the token
     * @return the URL to reset the user password
     */
    public static String createPasswordResetUrl(HttpServletRequest request, long userId, String token) {
        String passwordResetUrl=
                request.getScheme() +
                        "://" +
                        request.getServerName() +
                        ":" +
                        request.getServerPort() +
                        request.getContextPath() +
                        ForgotMyPasswordController.CHANGE_PASSWORD_PATH +
                        "?id=" +
                        userId +
                        "&token=" +
                        token;


        return passwordResetUrl;
    }
}

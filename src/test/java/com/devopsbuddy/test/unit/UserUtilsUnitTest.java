package com.devopsbuddy.test.unit;


// when setting up spring boot,
// the spring initializer declared the spring test dependencies for us
// this dependency makes available for us various test objects

import com.devopsbuddy.utils.UserUtils;
import com.devopsbuddy.web.controllers.ForgotMyPasswordController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.UUID;

public class UserUtilsUnitTest {

    // one of the test objects is this
    // this obj mocks a real http server request
    // no need for web app to execute test ;)
    private MockHttpServletRequest mockHttpServletRequest;

    @Before
    public void init(){
        mockHttpServletRequest = new MockHttpServletRequest();
    }

    @Test
    public void testPasswordResetEmailUrlConstruction() throws Exception{
        mockHttpServletRequest.setServerPort(8080);

        String token = UUID.randomUUID().toString();
        long userId = 123456;

        String expectedUrl = "http://localhost:8080" +
                ForgotMyPasswordController.CHANGE_PASSWORD_PATH + "?id=" + userId + "&token=" + token;

        // createPasswordResetUrl (test driven development tdd, write test before implementation)
        String actualUrl = UserUtils.createPasswordResetUrl(mockHttpServletRequest, userId, token);

        // make sure that expected and actual urls are the same
        Assert.assertEquals(expectedUrl, actualUrl);
    }
}

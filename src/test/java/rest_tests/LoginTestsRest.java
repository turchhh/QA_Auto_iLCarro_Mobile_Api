package rest_tests;

import api_rest.AuthenticationController;
import dto.RegistrationBodyDto;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class LoginTestsRest extends AuthenticationController {
    RegistrationBodyDto user;

    @BeforeMethod
    public void registrationUser(){
        int i = new Random().nextInt(1000);
        user = RegistrationBodyDto.builder()
                .username("banana" + i + "@gmail.com")
                .password("Banana" + i + "!")
                .firstName("Banana")
                .lastName("Mama")
                .build();
        System.out.println("registration result --> " + registrationLogin(user, REGISTRATION_URL).getStatusCode());

    }

    @Test
    public void loginPositiveTest() {
        Assert.assertEquals(registrationLogin(user, LOGIN_URL).getStatusCode(), 200);
    }

    @Test
    public void loginNegativeTest_WrongEmail_UnregisteredUser(){
        user.setUsername("mango1234@gmail.com");
        Response response = registrationLogin(user, LOGIN_URL);
        System.out.println(response.getBody().print());
        System.out.println(response.getStatusLine());
        Assert.assertEquals(response.getStatusCode(), 401);
    }
}

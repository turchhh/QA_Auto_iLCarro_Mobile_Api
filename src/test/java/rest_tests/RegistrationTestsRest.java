package rest_tests;

import api_rest.AuthenticationController;
import dto.ErrorMessageDtoString;
import dto.RegistrationBodyDto;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Random;

public class RegistrationTestsRest extends AuthenticationController {
SoftAssert softAssert = new SoftAssert();
    @Test
    public void registrationPositiveTest(){
        int i = new Random().nextInt(1000);
        RegistrationBodyDto user = RegistrationBodyDto.builder()
                .username("banana" + i + "@gmail.com")
                .password("Banana" + i + "!")
                .firstName("Banana")
                .lastName("Mama")
                .build();
        Assert.assertEquals(registrationLogin(user, REGISTRATION_URL).getStatusCode(), 200);
    }

    @Test
    public void registrationNegativeTest_WrongEmail(){
        int i = new Random().nextInt(1000);
        RegistrationBodyDto user = RegistrationBodyDto.builder()
                .username("banana" + i + "gmail.com")
                .password("Banana" + i + "!")
                .firstName("Banana")
                .lastName("Mama")
                .build();
        Assert.assertEquals(registrationLogin(user, REGISTRATION_URL).getStatusCode(), 400);
    }

    @Test
    public void registrationNegativeTest_WrongPassword(){
        int i = new Random().nextInt(1000);
        RegistrationBodyDto user = RegistrationBodyDto.builder()
                .username("banana" + i + "@gmail.com")
                .password("banana" + i + "!")
                .firstName("Banana")
                .lastName("Mama")
                .build();
        Response response = registrationLogin(user, REGISTRATION_URL);
        softAssert.assertEquals(response.getStatusCode(), 400, "validate status code");
        ErrorMessageDtoString errorMessageDtoString = response.getBody().as(ErrorMessageDtoString.class);
        softAssert.assertEquals(errorMessageDtoString.getError(), "Bad Request", "validate error");
        softAssert.assertAll();
        System.out.println(response.getBody().asString());
    }
}

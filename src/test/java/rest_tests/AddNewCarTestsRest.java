package rest_tests;

import api_rest.CarController;
import dto.CarDto;
import dto.ErrorMessageDtoString;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Random;

public class AddNewCarTestsRest extends CarController {
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void addNewCarPositiveTest(){
        int i = new Random().nextInt(1000)+1000;
        CarDto car = CarDto.builder()
                .serialNumber("345" + i)
                .manufacture("Renault")
                .model("Clio")
                .year("2022")
                .fuel("Electric")
                .seats(5)
                .carClass("B")
                .pricePerDay(400.5)
                .city("Raanana")
                .build();
        Response response = addNewCar(car);
        softAssert.assertEquals(response.getStatusCode(),200, "validate status code");
        System.out.println(response.getBody().print());
        softAssert.assertTrue(response.getBody().print().contains("added successfully"));
        softAssert.assertAll();
    }
    @Test
    public void addNewCarNegativeTest_WrongAuthorization(){
        int i = new Random().nextInt(1000)+1000;
        CarDto car = CarDto.builder()
                .serialNumber("345" + i)
                .manufacture("Renault")
                .model("Clio")
                .year("2022")
                .fuel("Electric")
                .seats(5)
                .carClass("B")
                .pricePerDay(400.5)
                .city("Raanana")
                .build();
        Response response = addNewCarNegative_WrongToken(car, "eyJhbGciOiJIUzI1NiJ9");
        softAssert.assertEquals(response.getStatusCode(),401, "validate status code");
        System.out.println(response.getBody().print());
        softAssert.assertTrue(response.getBody().print().contains("strings must contain"), "validate message");
        softAssert.assertAll();
    }

    @Test
    public void addNewCarNegativeTest_WOSerialNumber() {
        int i = new Random().nextInt(1000) + 1000;
        CarDto car = CarDto.builder()
                .serialNumber("")
                .manufacture("Renault")
                .model("Clio")
                .year("2022")
                .fuel("Electric")
                .seats(5)
                .carClass("B")
                .pricePerDay(400.5)
                .city("Raanana")
                .build();
        Response response = addNewCar(car);
        softAssert.assertEquals(response.getStatusCode(), 400, "validate status code");
        System.out.println(response.getBody().print());
        softAssert.assertTrue(response.getBody().print().contains("must not be blank"), "validate message");
        ErrorMessageDtoString errorMessageDtoString = response.getBody().as(ErrorMessageDtoString.class);
        softAssert.assertTrue(errorMessageDtoString.getError().equals("Bad Request"), "validate error");
        softAssert.assertAll();
    }
}

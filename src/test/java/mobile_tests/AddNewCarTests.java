package mobile_tests;

import config.AppiumConfig;
import dto.CarDto;
import dto.RegistrationBodyDto;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.LoginScreen;
import screens.MyCarsScreen;
import screens.SearchScreen;
import screens.SplashScreen;

import java.util.Random;

public class AddNewCarTests extends AppiumConfig {

    @BeforeMethod
    public void login(){
        RegistrationBodyDto user = RegistrationBodyDto.builder()
                .username("valeriya.qa@gmail.com")
                .password("678512Lera!")
                .build();
        new SplashScreen(driver).goToSearchScreen(5);
        SearchScreen searchScreen = new SearchScreen(driver);
        searchScreen.clickBtnDots();
        searchScreen.clickBtnLogin();
        LoginScreen loginScreen = new LoginScreen(driver);
        loginScreen.typeLoginForm(user);
        loginScreen.clickBtnYalla();
    }

    @Test
    public void addNewCarPositiveTest(){
        SearchScreen searchScreen = new SearchScreen(driver);
        searchScreen.clickBtnDots();
        searchScreen.clickBtnMyCars();
        MyCarsScreen myCarsScreen = new MyCarsScreen(driver);
        myCarsScreen.clickBtnAddNewCar();
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
    }
}

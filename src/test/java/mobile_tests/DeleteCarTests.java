package mobile_tests;

import config.AppiumConfig;
import dto.RegistrationBodyDto;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.LoginScreen;
import screens.MyCarsScreen;
import screens.SearchScreen;
import screens.SplashScreen;


public class DeleteCarTests extends AppiumConfig {

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
        searchScreen.clickBtnDots();
        searchScreen.clickBtnMyCars();
    }

    @Test
    public void deleteCarPositiveTest(){
        MyCarsScreen myCarsScreen = new MyCarsScreen(driver);
        myCarsScreen.deleteCar();
        myCarsScreen.clickBtnYes();
    }
}

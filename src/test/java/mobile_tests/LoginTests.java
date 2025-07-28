package mobile_tests;

import config.AppiumConfig;
import dto.RegistrationBodyDto;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.*;

public class LoginTests extends AppiumConfig {

    @BeforeMethod
    public void openScreenLogin(){
        new SplashScreen(driver).goToSearchScreen(5);
        SearchScreen searchScreen = new SearchScreen(driver);
        searchScreen.clickBtnDots();
        searchScreen.clickBtnLogin();
    }

    @Test
    public void loginPositiveTest(){
        RegistrationBodyDto user = RegistrationBodyDto.builder()
                .username("valeriya.qa@gmail.com")
                .password("678512Lera!")
                .build();
        SearchScreen searchScreen = new SearchScreen(driver);
        LoginScreen loginScreen = new LoginScreen(driver);
        loginScreen.typeLoginForm(user);
        loginScreen.clickBtnYalla();
        Assert.assertTrue(searchScreen.textInElementPresent_popUpMessageSuccess("Login success!"));
    }

    @Test
    public void loginNegativeTest_WrongPassword_WO_Symbol(){
        RegistrationBodyDto user = RegistrationBodyDto.builder()
                .username("valeriya.qa@gmail.com")
                .password("678512Lera")
                .build();
        LoginScreen loginScreen = new LoginScreen(driver);
        loginScreen.typeLoginForm(user);
        loginScreen.clickBtnYalla();
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("Login or Password incorrect"));
    }

    @Test
    public void loginNegativeTest_WrongPassword_WO_Numbers(){
        RegistrationBodyDto user = RegistrationBodyDto.builder()
                .username("valeriya.qa@gmail.com")
                .password("Lera!!!")
                .build();
        LoginScreen loginScreen = new LoginScreen(driver);
        loginScreen.typeLoginForm(user);
        loginScreen.clickBtnYalla();
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("Login or Password incorrect"));
    }

    @Test
    public void loginNegativeTest_EmptyEmail(){
        RegistrationBodyDto user = RegistrationBodyDto.builder()
                .username("")
                .password("678512Lera!")
                .build();
        LoginScreen loginScreen = new LoginScreen(driver);
        loginScreen.typeLoginForm(user);
        loginScreen.clickBtnYalla();
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("All fields must be filled and agree terms"));
    }



}

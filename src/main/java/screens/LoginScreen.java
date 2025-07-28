package screens;

import dto.RegistrationBodyDto;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

public class LoginScreen extends BaseScreen{
    public LoginScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(id = "com.telran.ilcarro:id/editLoginEmail")
    AndroidElement inputEmail;

    @FindBy(id = "com.telran.ilcarro:id/editLoginPassword")
    AndroidElement inputPassword;

    @FindBy(xpath = "//*[@resource-id='com.telran.ilcarro:id/loginBtn']")
    AndroidElement btnYalla;

    public void typeLoginForm(RegistrationBodyDto user) {
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
    }

    public void clickBtnYalla(){
        btnYalla.click();
    }

}

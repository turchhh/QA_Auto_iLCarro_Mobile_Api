package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.support.FindBy;
import static config.AppiumConfig.*;

public class MyCarsScreen extends BaseScreen{
    public MyCarsScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(id = "com.telran.ilcarro:id/addCarBtn")
    AndroidElement btnAddNewCar;

    @FindBy(id = "android:id/button1")
    AndroidElement btnYes;

    public void clickBtnAddNewCar(){
        btnAddNewCar.click();
    }

    public void deleteCar(){
        TouchAction<?> touchAction = new TouchAction<>(driver);
        touchAction.longPress(PointOption.point(width/8, height/8*2)).moveTo(PointOption.point(width/8*7, height/8*2)).release().perform();

    }

    public void clickBtnYes(){
        clickWait(btnYes, 3);
    }

}

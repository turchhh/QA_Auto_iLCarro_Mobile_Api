package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

public class AddNewCarScreen extends BaseScreen {
    public AddNewCarScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(id = "com.telran.ilcarro:id/editSerial")
    AndroidElement fieldSerialNumber;
    @FindBy(id = "com.telran.ilcarro:id/editMan")
    AndroidElement fieldManufacture;
    @FindBy(id = "com.telran.ilcarro:id/editModel")
    AndroidElement fieldModel;
    @FindBy(id = "com.telran.ilcarro:id/editCity")
    AndroidElement fieldCity;
    @FindBy(id = "com.telran.ilcarro:id/editPrice")
    AndroidElement fieldPrice;
    @FindBy(id = "com.telran.ilcarro:id/editCarClass")
    AndroidElement fieldCarClass;
    @FindBy(id = "com.telran.ilcarro:id/text1")
    AndroidElement fieldFuel;
    @FindBy(id = "com.telran.ilcarro:id/editYear")
    AndroidElement fieldYear;
    @FindBy(id = "com.telran.ilcarro:id/editSeats")
    AndroidElement fieldSeats;

}
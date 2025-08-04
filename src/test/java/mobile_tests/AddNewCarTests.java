package mobile_tests;

import config.AppiumConfig;
import dto.CarDto;
import dto.ErrorMessageDtoString;
import dto.RegistrationBodyDto;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.*;

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
        int i = new Random().nextInt(1000)+1000;
        CarDto car = CarDto.builder()
                .serialNumber("001-" + i)
                .manufacture("Renault")
                .model("Clio")
                .city("Raanana")
                .pricePerDay(400.5)
                .carClass("B")
                .fuel("Diesel")
                .year("2022")
                .seats(5)
                .about("My lovely car")
                .build();
        SearchScreen searchScreen = new SearchScreen(driver);
        searchScreen.clickBtnDots();
        searchScreen.clickBtnMyCars();
        MyCarsScreen myCarsScreen = new MyCarsScreen(driver);
        myCarsScreen.clickBtnAddNewCar();
        AddNewCarScreen addNewCarScreen = new AddNewCarScreen(driver);
        addNewCarScreen.typeAddNewCarForm(car);
        addNewCarScreen.clickBtnAdd();
        Assert.assertTrue(searchScreen.textInElementPresent_popUpMessageSuccess("Car was added!"));
    }

    @Test
    public void addNewCarNegativeTest(){
        CarDto car = CarDto.builder()
                .serialNumber("001-1218")
                .manufacture("Renault")
                .model("Clio")
                .city("Raanana")
                .pricePerDay(400.5)
                .carClass("B")
                .fuel("Diesel")
                .year("2022")
                .seats(5)
                .about("My lovely car")
                .build();
        SearchScreen searchScreen = new SearchScreen(driver);
        searchScreen.clickBtnDots();
        searchScreen.clickBtnMyCars();
        MyCarsScreen myCarsScreen = new MyCarsScreen(driver);
        myCarsScreen.clickBtnAddNewCar();
        AddNewCarScreen addNewCarScreen = new AddNewCarScreen(driver);
        addNewCarScreen.typeAddNewCarForm(car);
        addNewCarScreen.clickBtnAdd();
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("Car with serial number 001-1218 already exists"));
    }

    @Test
    public void addNewCarNegativeTest_AllFieldsEmpty() {
        CarDto car = CarDto.builder()
                .serialNumber("")
                .manufacture("")
                .model("")
                .city("")
                .pricePerDay(0.0)
                .carClass("")
                .fuel("")
                .year("")
                .seats(0)
                .about("")
                .build();
        SearchScreen searchScreen = new SearchScreen(driver);
        searchScreen.clickBtnDots();
        searchScreen.clickBtnMyCars();
        MyCarsScreen myCarsScreen = new MyCarsScreen(driver);
        myCarsScreen.clickBtnAddNewCar();
        AddNewCarScreen addNewCarScreen = new AddNewCarScreen(driver);
        addNewCarScreen.typeAddNewCarForm(car);
        addNewCarScreen.clickBtnAdd();
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("Fields: Serial number, Manufacture, Model, City, Price per day is required!"));

    }

    @Test
    public void addNewCarNegativeTest_EmptyManufacture(){
        int i = new Random().nextInt(1000)+1000;
        CarDto car = CarDto.builder()
                .serialNumber("001-" + i)
                .manufacture("")
                .model("Clio")
                .city("Raanana")
                .pricePerDay(400.5)
                .carClass("B")
                .fuel("Diesel")
                .year("2022")
                .seats(5)
                .about("My lovely car")
                .build();
        SearchScreen searchScreen = new SearchScreen(driver);
        searchScreen.clickBtnDots();
        searchScreen.clickBtnMyCars();
        MyCarsScreen myCarsScreen = new MyCarsScreen(driver);
        myCarsScreen.clickBtnAddNewCar();
        AddNewCarScreen addNewCarScreen = new AddNewCarScreen(driver);
        addNewCarScreen.typeAddNewCarForm(car);
        addNewCarScreen.clickBtnAdd();
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("Fields: Serial number, Manufacture, Model, City, Price per day is required!"));
    }

    @Test
    public void addNewCarNegativeTest_WrongCity(){
        int i = new Random().nextInt(1000)+1000;
        CarDto car = CarDto.builder()
                .serialNumber("001-" + i)
                .manufacture("Audi")
                .model("A3")
                .city("Tel-Aviv")
                .pricePerDay(400.5)
                .carClass("A")
                .fuel("Electric")
                .year("2021")
                .seats(3)
                .about("My last car")
                .build();
        SearchScreen searchScreen = new SearchScreen(driver);
        searchScreen.clickBtnDots();
        searchScreen.clickBtnMyCars();
        MyCarsScreen myCarsScreen = new MyCarsScreen(driver);
        myCarsScreen.clickBtnAddNewCar();
        AddNewCarScreen addNewCarScreen = new AddNewCarScreen(driver);
        addNewCarScreen.typeAddNewCarForm(car);
        addNewCarScreen.clickBtnAdd();
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("City Tel-Aviv not available"));
    }

}

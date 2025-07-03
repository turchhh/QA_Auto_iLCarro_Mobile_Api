package api_rest;

import dto.RegistrationBodyDto;
import interfaces.BaseApi;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;


public class AuthenticationController implements BaseApi {

    public Response registrationLogin(RegistrationBodyDto user, String url){
        return given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(BASE_API + url)
                .thenReturn();
    }
}

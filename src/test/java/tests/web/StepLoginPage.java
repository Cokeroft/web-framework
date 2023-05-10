package tests.web;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

public class StepLoginPage extends PageObject {

    private Response response;
    @Step("Enter Username")
    public void inputUserName(String userName) {
        $(By.id("user_login")).sendKeys(userName);
        //response = SerenityRest.given().get("test.com").then().statusCode(200).extract().response();
    }

    @Step("Enter Password")
    public void inputPassword(String password) {
        $(By.id("user_pass")).sendKeys(password);
    }

    @Step("Click Submit Button")
    public void clickLogin() {
        $(By.id("wp-submit")).click();
    }

    @Step("Error Message on unsuccessful login")
    public String errorMessage() {
        String actualErrorMessage = $(By.id("login_error")).getText();
        return actualErrorMessage;
    }

    @Step("Click Forget Password Link")
    public void clickForgetPasswordLink() {
        $(By.id("forgotPasswordLink")).click();
    }
}

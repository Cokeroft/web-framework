package tests.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import tests.web.StepDashboardPage;
import tests.web.StepForgetPasswordPage;
import tests.web.StepLoginPage;

public class LoginPage {
    @Steps
    StepLoginPage loginPage;

    @Steps
    StepDashboardPage dashPage;

    @Steps
    StepForgetPasswordPage forgetPasswordPage;

    @Given("User is on Home page")
    public void openApplication() {
        loginPage.open();
        System.out.println("Page is opened");
        }

    @When("User enters username as {string}")
    public void enterUsername(String userName) {
        System.out.println("Enter Username");
        loginPage.inputUserName(userName);
    }

    @When("User enters password as {string}")
    public void enterPassword(String password) {
        loginPage.inputPassword(password);
        loginPage.clickLogin();
    }

    @Then("User should be able to login successfully")
    public void clickOnLoginButton() {
        dashPage.loginVerify();
    }

    @Then("User should be able to see error message {string}")
    public void unsucessfulLogin(String expectedErrorMessage) throws InterruptedException {
        String actualErrorMessage = loginPage.errorMessage();
        //Assert.assertThat(expectedErrorMessage, actualErrorMessage);
        Assert.assertTrue(expectedErrorMessage.contains(actualErrorMessage));
    }

    @When("User clicks on Forgot your password link")
    public void clickForgetPasswordLink() {
        loginPage.clickForgetPasswordLink();
    }

    @Then("User should be able to see new page which contains Reset Password button")
    public void verifyForgetPasswordPage() {
        Assert.assertTrue(forgetPasswordPage.ForgetPasswordPage());
    }
}

package tests.web;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

public class StepForgetPasswordPage extends PageObject {
    @Step("Verify Forget Password Page")
    public boolean ForgetPasswordPage() {
        Boolean resetPasswordButton = $(By.id("btnSearchValues")).isDisplayed();
        return resetPasswordButton;
    }
}

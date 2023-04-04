package tests.web;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class StepDashboardPage extends PageObject {
    @Step("Successful login")
    public void loginVerify() {
        String dashboardTitle = $(By.className("logged_in")).getText();
        assertThat(dashboardTitle, containsString("My Account"));
    }
}

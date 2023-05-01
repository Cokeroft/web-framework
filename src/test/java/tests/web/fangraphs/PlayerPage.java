package tests.web.fangraphs;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Shared;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.PageObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import tests.GlobalVariables;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class PlayerPage extends PageObject {
    @Shared
    GlobalVariables globalVariables;

    @Step("Correct Player Page")
    public void playerPageVerify() {
        waitForCondition().until(ExpectedConditions.elementToBeClickable(By.className("player-info-box-name")));
        String actualName = $(By.className("player-info-box-name")).getText();
        assertThat(actualName, containsString(globalVariables.getSearchPlayerName()));
    }

    @Step("Player Stats Table")
    public void playerStatsTable() {
        waitForCondition().until(ExpectedConditions.elementToBeClickable(By.className("player-page-table")));
        Assert.assertTrue(getDriver().findElement(By.className("player-page-table")).isDisplayed());
    }
}

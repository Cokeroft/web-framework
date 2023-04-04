package tests.web.fangraphs;

import net.thucydides.core.annotations.Shared;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import tests.GlobalVariables;

public class HomePage extends PageObject {
    @Shared
    GlobalVariables globalVariables;


    @Step("Search Player Name")
    public void searchPlayer(String playerName) {
        $(By.className("search-query")).sendKeys(playerName);
        // TODO - If the player is on the initial drop down, it will click the first player
        // TODO - Need a way to click those drop downs based on name
        waitForCondition().until(
                ExpectedConditions.textToBePresentInElement(find(By.className("search-term-highlight")), playerName)
        );
        $(By.className("player-name")).click();
        globalVariables.setSearchPlayerName(playerName);
        try {
            //blah
        } catch (NoSuchElementException e1) {
            e1.getCause();
        }
    }
}

package tests.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import org.junit.Test;
import tests.web.fangraphs.HomePage;
import tests.web.fangraphs.PlayerPage;
import tests.web.fangraphs.PlayerSearch;

public class Fangraphs {
    @Steps
    HomePage homePage;

    @Steps
    PlayerSearch playerSearch;

    @Steps
    PlayerPage playerPage;

    private Response response;

    @Given("User is on Fangraphs Home Page")
    public void openFangraphs() {
        homePage.open();
        System.out.println("Home Page is open");
    }

    @When("User searches for player {string}")
    public void playerSearch(String playerName) {
        homePage.searchPlayer(playerName);
    }

    @Then("User should be on the correct Player Page")
    public void playerPageCheck() {
        playerPage.playerPageVerify();
    }

    @Then("Player should have their stats table shown")
    public void playerStatsTable() {
        playerPage.playerStatsTable();
    }

    @Given("Testing")
    @Test
    public void testing() {
        response = SerenityRest.given().post("https://www.pricecharting.com/api/products?t=463baf4d3590649d0111235492d030731d81ded0&q=secret of mana")
                .then().statusCode(200).extract().response();
    }
}

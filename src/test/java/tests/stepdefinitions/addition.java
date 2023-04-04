package tests.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import tests.SharedValues;
import tests.math.AdditionHelp;
import static org.junit.Assert.*;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

public class addition {
    Response response;
    AdditionHelp additionHelp;

    public Integer realResult;
    public List<Integer> integerListReal;

    @Given("the user generates {int} random integers")
    public void generateIntegers(int count) {
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            integerList.add(additionHelp.generateInt());
        }
        integerListReal = integerList;
        System.out.println("GENERATE INTEGERS PLACEHOLDER " + count);
    }

    @When("the user adds the integers together")
    public void addIntegers() {
        System.out.println("ADD INTEGERS PLACEHOLDER");
    }

    @Then("the total {int} is correct")
    public void totalCheck(int result) {
        assertEquals((int) realResult, result);
    }

    @When("the user adds {int} plus {int} together")
    public void addTwoIntegers(int num1, int num2) {
        int result = num1 + num2;
        realResult = result;
        System.out.println("Your result is " + result);
    }
}

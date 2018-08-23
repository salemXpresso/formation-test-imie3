package com.imie.money.functional.steps;

import com.imie.money.functional.driverfactory.DriverFactory;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.*;

public class CurrencySteps {

    private WebDriver webDriver;

    public CurrencySteps() {
        DriverFactory driverFactory = new DriverFactory();
        webDriver = driverFactory.getDriver();
    }

    @Given("^A running platform$")
    public void testRunningPlatform() {
        // Test de vie sur la plateforme
        System.out.println("Should be running");
    }

    @When("the user opens the page 'je voyage'")
    public void openJeVoyage() {

        webDriver.get("https://www.visa.fr/je-voyage/");
    }

    @Then("the url on the browser contains 'je-voyage'")
    public void checkUrl() {
        assertThat(webDriver.getCurrentUrl()).contains("je-voyage");
    }
}

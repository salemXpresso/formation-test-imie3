package com.imie.money.functional.steps;

import com.imie.money.functional.driverfactory.DriverFactory;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.data.Offset;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

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

    @When("^the user opens the page 'je voyage'$")
    public void openJeVoyage() {

        webDriver.get("https://www.visa.fr/je-voyage/");
    }

    @Then("^the url on the browser contains 'je-voyage'$")
    public void checkUrl() {
        assertThat(webDriver.getCurrentUrl()).contains("je-voyage");
    }

    @When("^the user clicks on 'mes outils pratiques'$")
    public void clickMesOutils() {

        webDriver.findElement(By.id("MainContent_LeftNav1_outerRepeater_HyperLink2_4")).click();
    }

    @When("^the user clicks on 'convertisseur de devises'$")
    public void clickConvertisseur() {

        webDriver.findElement(By.id("MainContent_LeftNav1_outerRepeater_innerRepeater_4_HyperLink2_2")).click();
    }

    @Then("^the currency form is displayed$")
    public void checkCurrencyFormDisplayed() {

        WebElement formElement = webDriver.findElement(By.id("MainContent_MainContent_ctl00_lblCardCurrency"));
        assertThat(formElement).isNotNull();
    }

    @When("^the user selects <(.*)> for his card$")
    public void selectCard(String currency) {
        Select select = new Select(webDriver.findElement(By.id("MainContent_MainContent_ctl00_ddlCardCurrency")));
        select.selectByValue(currency);
    }

    @When("^the user selects <(.*)> for his transaction$")
    public void selectTransaction(String currency) {
        Select select = new Select(webDriver.findElement(By.id("MainContent_MainContent_ctl00_ddlTransactionCurrency")));
        select.selectByValue(currency);
    }

    @When("^the user submits the form$")
    public void submitForm() {
        webDriver.findElement(By.id("MainContent_MainContent_ctl00_btnSubmit")).click();
    }

    @Then("^the result page is displayed$")
    public void checkResultDisplayed() {
        List<WebElement> webElementList = webDriver.findElements(By.tagName("h1"));

        boolean resultatBoxIsDisplayed = false;
        for(WebElement anElement : webElementList) {
            String text = anElement.getText();
            if("Résultats".equals(text)) {
                resultatBoxIsDisplayed = true;
                break;
            }
        }
        assertThat(resultatBoxIsDisplayed).isTrue();
    }

    @Then("^the conversion result is <(.*)>$")
    public void checkResult(float expectedResult) {

        List<WebElement> paragraphs = webDriver.findElement(By.className("col-md-8")).findElement(By.className("box-grey")).findElements(By.className("col-lg-12"));
        List<WebElement> boldTextList = paragraphs.get(1).findElement(By.tagName("p")).findElements(By.tagName("strong"));
        String result = boldTextList.get(1).getText();
        float parsedResult = Float.parseFloat(result.replace(',', '.'));
        assertThat(parsedResult).isCloseTo(expectedResult, Offset.offset(0.1f));
    }

    @Then("^The user closes his Web browser$")
    public void closeBrowser() {
        webDriver.close();
    }
}

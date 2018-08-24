package com.imie.money.functional.driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.util.HashMap;

public class DriverFactory {

    private WebDriver driver;

    public DriverFactory() {

        // VM arg : -ea -Dchrome.driver.path=C:/_work/tools/GoogleChromePortable/chromedriver.exe -Dchrome.portable.path=C:/_work/tools/GoogleChromePortable/GoogleChromePortable.exe
        final String driverPath = System.getProperty("chrome.driver.path");
        final String browserPath = System.getProperty("chrome.portable.path");

        // VM arg: -ea -Dwebdriver.gecko.driver=/home/remi/geckodriver
        final String geckoDriverPath = System.getProperty("webdriver.gecko.driver");


        if(geckoDriverPath == null) {
            ChromeOptions options = new ChromeOptions();
            options.setBinary(browserPath);

            HashMap<String, Object> chromePrefs = new HashMap<>();
            options.setExperimentalOption("prefs", chromePrefs);
            // Play with the window size
            options.addArguments("--start-maximized"); // full screen
            // options.addArguments("window-size=1024,768"); // or special size

            System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, driverPath);

            ChromeDriverService service = new ChromeDriverService.Builder()
                    .usingDriverExecutable(new File(driverPath))
                    .usingAnyFreePort()
                    .build();
            driver = new ChromeDriver(service, options);
        } else {
            driver = new FirefoxDriver();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}

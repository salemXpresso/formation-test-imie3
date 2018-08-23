package com.imie.money.functional.driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.HashMap;

public class DriverFactory {

    private WebDriver driver;

    public DriverFactory() {

        // VM arg : -Dchrome.driver.path=C:/_work/tools/GoogleChromePortable/chromedriver.exe
        final String driverPath = System.getProperty("chrome.driver.path");
        // VM arg : -Dchrome.portable.path=C:/_work/tools/GoogleChromePortable/GoogleChromePortable.exe
        final String browserPath = System.getProperty("chrome.portable.path");

        // VM arg: -Dwebdriver.gecko.driver=/home/remi/geckodriver
        /*final String geckoDriverPath = System.getProperty("webdriver.gecko.driver");
        if(geckoDriverPath != )
        System.setProperty("webdriver.gecko.driver", "C:\\Selenium\\geckodriver.exe");
        */

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
    }

    public WebDriver getDriver() {
        return driver;
    }
}

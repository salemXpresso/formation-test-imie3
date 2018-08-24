package com.imie.money.functional.driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Level;

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
            LoggingPreferences loggingPrefs = new LoggingPreferences();
            loggingPrefs.enable(LogType.BROWSER, Level.ALL);
            loggingPrefs.enable(LogType.CLIENT, Level.ALL);
            loggingPrefs.enable(LogType.DRIVER, Level.ALL);
            loggingPrefs.enable(LogType.PERFORMANCE, Level.ALL);
            loggingPrefs.enable(LogType.PROFILER, Level.ALL);
            loggingPrefs.enable(LogType.SERVER, Level.ALL);

            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability("marionette", true);
            desiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            desiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS, loggingPrefs);

            FirefoxOptions options = new FirefoxOptions();
            options.merge(desiredCapabilities);
            options.setLogLevel(FirefoxDriverLogLevel.TRACE);
            driver = new FirefoxDriver(options);
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}

package com.imie.money.functional;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = {"pretty", "html:target/html"},
        glue = {"com/imie/money/functional"}
        //, tags = {"@wip"}
        )

public class CucumberRunner {

}
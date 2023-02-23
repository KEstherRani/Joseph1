package Cucumber;


import org.testng.annotations.Test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

public class s1 {
	
	
	@CucumberOptions(features="src\\test\\java\\Cucumber",glue="joseph.stepDefinitions",monochrome=true,
			tags="@Regression",plugin={"html:target\\cucumber.html"} )
			public class TestNGTestRunner extends AbstractTestNGCucumberTests {
	}
}

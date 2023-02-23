package Cucumber;


import org.testng.annotations.Test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;



@CucumberOptions(features="src\\test\\Cucumber",glue="joseph.stepDefinitions",monochrome=true,
plugin={"html:target\\cucumber.html"} )
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
	
	
	
	


}

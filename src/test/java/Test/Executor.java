package Test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

@CucumberOptions(features = "src/test/resources/features",
glue = "Steps",
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
        ,publish = true)

public class Executor extends AbstractTestNGCucumberTests {


    public static String browser;
    public Executor(String browser){
        this.browser = browser;
    }

}

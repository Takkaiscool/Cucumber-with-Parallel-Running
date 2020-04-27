package Utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
@CucumberOptions(features = "/test/resources/features",
        glue = "Steps",
        plugin = {"pretty","html:/target/cucumber"})
public class BasePage {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    public static void setDriver(String browserName){
        switch (browserName.toLowerCase()){
            case "firefox":driver.set( new FirefoxDriver());
            break;
            case "chrome":ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-notifications");
                driver.set( new ChromeDriver(options));
            break;
        }

    }

    public static WebDriver getDriver(){
        return driver.get();
    }
}

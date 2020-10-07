package Utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.URL;

@CucumberOptions(features = "/test/resources/features",
        glue = "Steps",
        plugin = {"pretty","html:/target/cucumber"})
public class BasePage {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    public static void setDriver(String browserName){
        DesiredCapabilities capabilities =null;
        switch (browserName.toLowerCase()){
            case "firefox": driver.set(new FirefoxDriver());
            break;
            case "chrome":ChromeOptions options=new ChromeOptions();
                options.setHeadless(true);
                driver.set(new ChromeDriver(options));
            break;
        }
        try {
            //driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static WebDriver getDriver(){
        return driver.get();
    }
}

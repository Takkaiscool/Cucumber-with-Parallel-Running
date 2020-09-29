package Steps;

import Pages.CartPage;
import Pages.HomePage;
import Pages.WelcomePage;
import Test.Executor;
import Utils.BasePage;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import java.util.concurrent.TimeUnit;

public class addProduct {
    WelcomePage welcomePage;
    HomePage homePage;
    WebDriver driver;
    CartPage cartPage;
    @Then("^I will open a application in \"(.*)\" browser")
    public void iWillOpenBrowser(String browser){
        BasePage.setDriver(Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser"));
        driver = BasePage.getDriver();
        driver.get("https://www.google.com/");
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }
    @When("^I login to the application with password \"(.*)\"")
    public void iLoginToTheApplicationWithPassword(String password) {
        driver.findElement(By.name("q")).sendKeys(password);
    }

    @Then("^I search the product \"(.*)\"")
    public void iSearchTheProduct(String product) {
        homePage.searchProduct(product);

    }

    @Then("^I select the product \"(.*)\" from the search")
    public void iSelctTheProduct(String product){
        homePage.selectProduct(product);
    }

    @Then("^I click on Add to cart")
    public void iClickOnAddToCart(){
        homePage.clickOnAddToCart();
    }

    @Then("^I click on view cart")
    public void iClickOnViewCart(){
       cartPage= homePage.clickOnViewCart();
    }
    String qty;
    @Then("^I change quantity to \"(.*)\" in cart")
    public void iChangeQuantity(String qty){
        this.qty = qty;
        cartPage.changeQuantity(qty);
    }

    @Then("^I verify total amount of product is changed as per quantity")
    public void iVerifyTotalAmount(){
        cartPage.verifyAmount(Integer.parseInt(qty));
    }

    @Then("^I goto Featured Collection and add first item")
    public void iGotoFeaturedCollection(){
        homePage.clickOnFirstFeaturedItem();
    }

    @Then("^I select size as \"(.*)\"")
    public void iSelectSize(String size){
        homePage.selectSizeAs(size);
    }

    @Then("^I select color as \"(.*)\"")
    public void iSelectColor(String color){
        homePage.selectColorAs(color);
    }

    @Then("^I verify subtotal amount is sum of individual total amount")
    public void iVerifySubtotalAmout(){
        cartPage.verifyTotalAmt();
    }
    @AfterStep
    public void screenShot(Scenario scenario){
        byte[] screeshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screeshot,"image/png","error");

    }
    @After
    public void tearDown(Scenario scenario){
        if(scenario.isFailed()){
            byte[] screeshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screeshot,"image/png","error");
        }

        BasePage.getDriver().quit();
    }
}

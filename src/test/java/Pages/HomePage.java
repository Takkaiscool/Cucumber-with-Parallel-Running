package Pages;

import Utils.BasePage;
import io.cucumber.java.eo.Se;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    @FindBy(xpath = "//span[text()='Search']/ancestor::button")
    private WebElement searchButton;

    @FindBy(name = "q")
    private WebElement searchField;

    @FindBy(xpath = "//a[contains(@class,'grid-view')]")
    private WebElement firstFeaturedItem;
    @FindBy(name = "add")
    private WebElement addToCart;

    @FindBy(xpath = "//select[@id='SingleOptionSelector-1']")
    private WebElement size;

    @FindBy(xpath = "//select[@id='SingleOptionSelector-0']")
    private WebElement color;

    @FindBy(xpath = "//a[contains(text(),'View cart')]")
    private WebElement viewCart;
    private WebDriver driver;
    public HomePage(){
        this.driver= BasePage.getDriver();
        PageFactory.initElements(driver,this);
    }

    public void searchProduct(String productName){
        WebDriverWait wait = new WebDriverWait(driver,60);
        searchButton.click();
        wait.until(ExpectedConditions.visibilityOf(searchField));
        searchField.sendKeys(productName);

    }

    public void selectProduct(String product){
        WebDriverWait wait = new WebDriverWait(driver,60);
        String xpath = "//span[@class='predictive-search-item__title-text'][text()='"+product+"']";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        driver.findElement(By.xpath(xpath)).click();
    }

    public void clickOnAddToCart(){
        addToCart.click();
        WebDriverWait wait=new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOf(viewCart));

    }

    public CartPage clickOnViewCart(){
        WebDriverWait wait=new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(viewCart));
        viewCart.click();
        return new CartPage();
    }

    public void clickOnFirstFeaturedItem(){
        firstFeaturedItem.click();
    }

    public void selectSizeAs(String size){
        Select sel = new Select(this.size);
        sel.selectByVisibleText(size);
    }

    public void selectColorAs(String color){
        Select sel = new Select(this.color);
        sel.selectByVisibleText(color);
    }

}

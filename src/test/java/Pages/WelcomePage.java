package Pages;

import Utils.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WelcomePage{

    @FindBy(xpath = "//a[contains(text(),'password')]")
    private WebElement enterUsingPassword;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(xpath = "//button[contains(text(),'Enter')]")
    private WebElement enterButton;

    private WebDriver driver;

    public WelcomePage(){
        this.driver=BasePage.getDriver();
        System.out.println(BasePage.getDriver());
        PageFactory.initElements(this.driver,this);
    }


    public HomePage loginWithPassword(String passwd){
        enterUsingPassword.click();
        WebDriverWait wait = new WebDriverWait(this.driver,60);
        wait.until(ExpectedConditions.visibilityOf(password));
        password.sendKeys(passwd);
        wait.until(ExpectedConditions.elementToBeClickable(enterButton));
        enterButton.click();
        return new HomePage();
    }
}

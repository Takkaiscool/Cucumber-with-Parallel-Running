package Pages;

import Utils.BasePage;
import io.cucumber.java.eo.Do;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class CartPage {

    @FindBy(xpath = "//dd[@data-cart-item-regular-price]")
    private WebElement priceOfItem;

    @FindBy(xpath = "//div[@class='cart__qty']//input")
    private WebElement quantity;

    @FindBy(xpath = "//span[@data-cart-item-regular-price]")
    private WebElement totalValue;

    @FindBy(xpath = "//span[@data-cart-item-regular-price]")
    private List<WebElement> totalValueOfEachEle;

    @FindBy(xpath = "//span[@data-cart-subtotal]")
    private WebElement subTotal;

    private WebDriver driver;

    public CartPage(){
        driver = BasePage.getDriver();
        PageFactory.initElements(driver,this);
    }

    public void changeQuantity(String qty){
        quantity.clear();
        quantity.sendKeys(qty);
        priceOfItem.click();
        try {
            Thread.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void verifyAmount(int qty){
        String eachValue = priceOfItem.getText().replaceAll(",","").replaceAll("Rs. ","");
        double eachPrice = Double.parseDouble(eachValue);
        String totalValuue = totalValue.getText().replaceAll(",","").replaceAll("Rs.","");
        double totalValue = Double.parseDouble(totalValuue);
        Assert.assertEquals(totalValue,eachPrice*qty);
    }

    public void verifyTotalAmt(){
        double sum = 0;
        for(int i=0;i<totalValueOfEachEle.size();i++){
            sum +=Double.parseDouble(totalValueOfEachEle.get(i).getText().replaceAll("Rs. ","").replaceAll(",",""));
        }
        Assert.assertEquals(Double.parseDouble(subTotal.getText().replaceAll("Rs. ","").replaceAll(",","")),sum);


    }

}

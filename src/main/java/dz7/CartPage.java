package dz7;

import dz6.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage extends AbstractPage {
    public CartPage(WebDriver driver) {

        super(driver);
    }
    @FindBy(xpath="//input[@id='promoCode']")
    private WebElement promo;

    @FindBy(xpath="//span[contains(.,'Применить')]")
    private WebElement applyPromo;

    final String promoInputXpath="//input[@id='promoCode']";
    public void setPromo() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(promo));
        promo.sendKeys("bio20");
        new WebDriverWait(getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(applyPromo));
        getDriver().findElement(By.xpath("//span[contains(.,'Применить')]")).click();
    }
}

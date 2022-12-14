package dz6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ToothpastePage extends AbstractPage {
    public ToothpastePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[.='В корзину']/ancestor::button")
    private WebElement addBasket;
    @FindBy(xpath = "//span/span[.='В корзине']")
    private WebElement inBasket;

    public void toBuy() {
        getDriver().get("https://www.detmir.ru/product/index/id/4884451/");
        new WebDriverWait(getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(addBasket));
        addBasket.click();
    }

    public CartPage toCart() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(60)).until(ExpectedConditions.elementToBeClickable(inBasket));
        inBasket.click();
        return new CartPage(getDriver());
    }
}

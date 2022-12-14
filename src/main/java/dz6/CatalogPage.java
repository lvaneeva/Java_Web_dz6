package dz6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CatalogPage extends AbstractPage{
    @FindBy(xpath="//span[.='Игрушки и игры']")
    private WebElement toysMenu;
    @FindBy(xpath="//ul[@role='menubar']//a/span[.='Конструкторы']/ancestor::a")
    private WebElement konstryktoryItem;
    @FindBy(xpath="//div[.='до']/label/input")
    private WebElement input;

    //@FindBy(xpath="//button/div/div/span[contains(.,'Показать (')]")
    @FindBy(xpath="//aside//button[2]/div/div/span")
    private WebElement showButton;

    @FindBy(xpath="//a[contains(@href,'product/index/id')]/preceding-sibling::div/span[2]")
    private List<WebElement> filterItems;

    Actions actions;
    public CatalogPage(WebDriver driver) {
        super(driver);
        actions=new Actions(driver);
    }

    public boolean toFilter() throws InterruptedException {
        new WebDriverWait(getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(toysMenu));
        actions.moveToElement(toysMenu)
                //.click(tShirtsButtonInSubmenu)
                .perform();
        //toysMenu.click();
        new WebDriverWait(getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(konstryktoryItem));
        konstryktoryItem.click();
        new WebDriverWait(getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(input));
        input.sendKeys("119");
        new WebDriverWait(getDriver(), Duration.ofSeconds(40)).until(ExpectedConditions.elementToBeClickable(showButton));
        showButton.click();
        new WebDriverWait(getDriver(), Duration.ofSeconds(40)).until(ExpectedConditions.elementToBeClickable(showButton));
        int numberOfListElements = filterItems.size();
        String tempPrice;
        double price;
        boolean result=true;
        if (numberOfListElements > 0) {
            for (int i = 0; i < numberOfListElements; i++) {
                tempPrice=filterItems.get(i).getText().substring(0, filterItems.get(i).getText().indexOf(" "));
                tempPrice = tempPrice.replaceAll("(?U)\\s+", "");
                if (Double.parseDouble(tempPrice)>119.0) result=false;
            }
        }
        return result;
    }
}

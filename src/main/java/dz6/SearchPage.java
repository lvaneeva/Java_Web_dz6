package dz6;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchPage extends AbstractPage {
    @FindBy(xpath = "//span[text()='Поиск']/ancestor::button")
    private WebElement search;

    @FindBy(xpath = "//input[@data-dy='input']")
    private WebElement input;
    @FindBy(xpath = "//span[.='Найти']/ancestor::button")
    private WebElement searchButton;

    @FindBy(xpath = "//h4[contains(text(),'Biorepair')]/ancestor::a")
    private List<WebElement> searchItems;

    @FindBy(xpath = "//*[contains(text(),'земляник')]/ancestor::a/following-sibling::div[2]/div/div[1]/button")
    private WebElement addBasketButton;

    @FindBy(xpath = "//span[contains(.,'цена')]/ancestor::button")
    private WebElement priceSortButton;
    @FindBy(xpath = "//span[contains(.,'популярность')]/ancestor::button")
    private WebElement popularSortButton;

    @FindBy(xpath = "//a[contains(@href,'product/index/id') and 1]/preceding-sibling::div/span[2]")
    private WebElement product;

    @FindBy(xpath = "//a[contains(@href,'product/index/id')]/preceding-sibling::div/span[2]")
    private List<WebElement> prices;

    public SearchPage(WebDriver driver) {
        super(driver);

    }

    public void toSearch(String value) {
        List<WebElement> webElements = getDriver().findElements(By.xpath("//div[.='Верно!']/ancestor::button"));
        int numberOfListElements = webElements.size();
        if (numberOfListElements > 0) {
            for (int i = 0; i < numberOfListElements; i++) {
                webElements = getDriver().findElements(By.xpath("//div[.='Верно!']/ancestor::button"));
                webElements.get(i).click();
            }
        }
        search.click();
        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(input));
        input.sendKeys(value);
        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(searchButton));
        searchButton.click();

    }

    public void toBuy() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(addBasketButton));
        addBasketButton.click();
    }

    public void takeItem(int n) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOf(addBasketButton));
        searchItems.get(n).click();
    }

    public boolean toSort() throws InterruptedException {
        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(priceSortButton));
        priceSortButton.click();
//не смогла придумать ожидание, чтобы дать страничке время отсортировать элементы
        Thread.sleep(10000);
        //new WebDriverWait(getDriver(), Duration.ofSeconds(40)).until(ExpectedConditions.elementToBeClickable(popularSortButton));
        //List<WebElement> webElements = getDriver().findElements(By.xpath("//a[contains(@href,'product/index/id')]/preceding-sibling::div/span[2]"));
        int numberOfListElements = prices.size();
        String tempPrice;
        double price = 0.0;
        boolean result = true;
        if (numberOfListElements > 0) {
            for (int i = 0; i < numberOfListElements; i++) {
                tempPrice = prices.get(i).getText().substring(0, prices.get(i).getText().indexOf(" ₽"));
                //tempPrice = tempPrice.replaceAll("&thinsp;","");
                tempPrice = tempPrice.replaceAll("\\P{Print}", "");
                if (Double.parseDouble(tempPrice) >= price) {
                    price = Double.parseDouble(tempPrice);
                } else result = false;
            }
        }
        return result;
    }
}

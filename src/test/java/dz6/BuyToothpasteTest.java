package dz6;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BuyToothpasteTest extends AbstractTest {

    @Test
    void searchTest() {
        SearchPage searchPage = new SearchPage(getWebDriver());
        searchPage.toSearch("biorepair");
        Assertions.assertTrue(getWebDriver().getCurrentUrl().contains("https://www.detmir.ru/search"));
        Assertions.assertDoesNotThrow(() -> searchPage.takeItem(9));
    }

    @Test
    @Disabled //В настоящий момент дизайн сайта изменился и кнопки добавления вкорзину на странице с результатами поиска нет
    @DisplayName("поиск и покупка з/пасты")
    void searchAndBuyToothpasteTest() {
        SearchPage searchPage = new SearchPage(getWebDriver());
        searchPage.toSearch("biorepair");
        searchPage.toBuy();
        Assertions.assertTrue(getWebDriver().findElement(By.xpath("//h4[contains(.,'земляник')]/ancestor::a/following-sibling::div/div/div/div/label//input[@type='number']")).isDisplayed());
    }

    @Test
    void buyToothpasteTest() {
        ToothpastePage toothpastePage = new ToothpastePage(getWebDriver());
        toothpastePage.toBuy();
        new WebDriverWait(getWebDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span/span[.='В корзине']")));
        Assertions.assertTrue(getWebDriver().findElement(By.xpath("//span/span[.='В корзине']")).isDisplayed());
        Assertions.assertTrue(getWebDriver().findElement(By.xpath("//input[@data-testid='productQuantityInput']")).isDisplayed());

    }

    @Test
    @DisplayName("Фильтрация по цене")
    void filterTest() throws InterruptedException {
        CatalogPage konstructoryPage = new CatalogPage(getWebDriver());
        boolean result = konstructoryPage.toFilter();
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Сортировка по цене")
    void sortToothpasteTest() throws InterruptedException {
        SearchPage searchPage = new SearchPage(getWebDriver());
        searchPage.toSearch("biorepair");
        boolean result = searchPage.toSort();
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Ввод промокода")
    void promoTest() {
        ToothpastePage toothpastePage = new ToothpastePage(getWebDriver());
        toothpastePage.toBuy();
        toothpastePage.toCart()
                      .setPromo();
        Assertions.assertTrue(getWebDriver().findElement(By.xpath("//div[.='Промокод не найден']")).isDisplayed());

    }
}

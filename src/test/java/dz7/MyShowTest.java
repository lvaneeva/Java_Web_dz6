package dz7;


import dz6.CatalogPage;
import dz6.SearchPage;
import dz6.ToothpastePage;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.util.List;

@Story("Моя пользовательская история")
public class MyShowTest extends AbstractTest {

    @Test
    @DisplayName("Пустой скрипт")
    @Description("Тест ничего не делает - такой тест нам тут не нужен")
    @Link("http://google.com")
    @Issue("https://bbc.com")
    @TmsLink("")
    @Severity(SeverityLevel.MINOR)
    void testTrue(){
        Assertions.assertTrue(true);
    }

    @Test
    @DisplayName("Сделай скрин")
    @Description("Тест создает скрин")
    @Link("http://google.com")
    @Issue("https://bbc.com")
    @Severity(SeverityLevel.BLOCKER)
    void testFalse() throws InterruptedException, IOException {
        Thread.sleep(1000);
        File file = MyUtils.makeScreenshot(getWebDriver(),"failure- org.example.bbc.MyShowTest.testFalse" + System.currentTimeMillis() + ".png");
        saveScreenshot(Files.readAllBytes(file.toPath()));
        Assertions.assertTrue(true);
    }

    @Step("Степ 1")
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }

    @Test
    @DisplayName("Дай логи")
    @Description("Создаем логи")
    @Link("http://google.com")
    @Issue("https://bbc.com")
    @Severity(SeverityLevel.NORMAL)
    void testLogs(){
        LogEntries browserLogs = getWebDriver().manage().logs().get(LogType.BROWSER);
        List<LogEntry> allLogRows = browserLogs.getAll();
       /* Assertions.assertEquals(0,allLogRows.size());
        Assertions.assertTrue(allLogRows.isEmpty());*/
        if (allLogRows.size() > 0 ) {
            allLogRows.forEach(logEntry -> {
                System.out.println(logEntry.getMessage());
            });

        }
    }

    @Feature("Фича 1")
    @Test
    void testTrue2(){
        Assertions.assertTrue(true);
    }

    @Feature("Фича 1")
    @Test
    void testTrue3(){
        Assertions.assertTrue(true);
    }

    @Feature("Фича 1")
    @Test
    void testTrue4() {
        Assertions.assertTrue(false);

    }

    @Test
    void searchTest() {
        SearchPage searchPage = new SearchPage(getWebDriver());
        searchPage.toSearch("biorepair");
        Assertions.assertTrue(getWebDriver().getCurrentUrl().contains("https://www.detmir.ru/search"));

        Assertions.assertDoesNotThrow(() -> searchPage.takeItem(1));
    }

    @Test
   // @Disabled
    //В настоящий момент дизайн сайта изменился и кнопки добавления вкорзину на странице с результатами поиска нет
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


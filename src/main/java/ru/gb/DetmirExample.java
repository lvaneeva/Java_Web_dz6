package ru.gb;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DetmirExample {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("disable-popup-blocking");
        WebDriver driver = new ChromeDriver(chromeOptions);
        //driver.manage().window().maximize();
        driver.get("https://detmir.ru/");

        Thread.sleep(10000);
        List<WebElement> webElements = driver.findElements(By.xpath("//div[.='Верно!']/ancestor::button"));
        int numberOfListElements = webElements.size();
        if (numberOfListElements > 0) {
            for (int i = 0; i < numberOfListElements; i++) {
                webElements = driver.findElements(By.xpath("//div[.='Верно!']/ancestor::button"));
                webElements.get(i).click();
            }
        }

        // Thread.sleep(5000);
        driver.findElement(By.xpath("//span[text()='Поиск']")).click();
        driver.findElement(By.xpath("//input[@data-dy='input']")).sendKeys("biorepair");

        //Thread.sleep(5000);
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span[.='Найти']/ancestor::button"))));
        driver.findElement(By.xpath("//span[.='Найти']/ancestor::button")).click();

        //Thread.sleep(10000);
        webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("flocktory-widget"))));
        driver.switchTo().frame(driver.findElement(By.className("flocktory-widget")));
        webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@class='close']"))));
        driver.findElement(By.xpath("//button[@class='close']")).click();
        driver.switchTo().parentFrame();

        //Thread.sleep(5000);
        webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[contains(text(),'земляник')]/ancestor::a/following-sibling::div[2]/div/div[1]/button"))));
        driver.findElement(By.xpath("//*[contains(text(),'земляник')]/ancestor::a/following-sibling::div[2]/div/div[1]/button")).click();


        //
        //Thread.sleep(5000);
        driver.findElement(By.xpath("//a[@href='/cart/']")).click();

        Thread.sleep(10000);
        webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@id='promoCode']"))));
        driver.findElement(By.xpath("//input[@id='promoCode']")).sendKeys("bio20");

        //Thread.sleep(5000);
        webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[contains(.,'Применить')]"))));
        driver.findElement(By.xpath("//span[contains(.,'Применить')]")).click();

        Thread.sleep(5000);
        driver.quit();
    }
}



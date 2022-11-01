package ru.gb;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.List;
import java.util.NoSuchElementException;

public class DetmirExample {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        // chromeOptions.addArguments("--disable -notifications");
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

        Thread.sleep(5000);
        driver.findElement(By.xpath("//span[text()='Поиск']")).click();
        driver.findElement(By.xpath("//input[@data-dy='input']")).sendKeys("biorepair");

        Thread.sleep(5000);
        driver.findElement(By.xpath("//form[@role='search']/div/button[1]")).click();

        Thread.sleep(10000);
        driver.switchTo().frame(driver.findElement(By.className("flocktory-widget")));
        driver.findElement(By.xpath("//button[@class='close']")).click();
        driver.switchTo().parentFrame();

        Thread.sleep(5000);
        try {
            driver.findElement(By.xpath("//*[contains(text(),'земляник')]/ancestor::a/following-sibling::div[2]/div/div[1]/button")).click();
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        Thread.sleep(5000);
        driver.quit();
    }
}



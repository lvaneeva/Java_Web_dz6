package ru.gb;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DoctorsAppointment {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        //driver.get("https://esia.gosuslugi.ru/login/"); //
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://www.gosuslugi.ru/600204/1/form");
        Thread.sleep(10000);
        driver.findElement(By.id("login")).sendKeys("9278536666");
        driver.findElement(By.id("password")).sendKeys("123456");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//button[.=' Войти ']")).click();
        // driver.get("https://esia.gosuslugi.ru/profile/user/personal/");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//span[.=' Начать ']")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//span[.='Ребёнка']")).click();

        Thread.sleep(5000);
        driver.findElement(By.xpath("//font[.='Выберите из списка']")).click();
        Thread.sleep(5000);
        //driver.findElement(By.xpath("//span[.='Анна']/ancestor::div[@class='dropdown-item-container']")).click();
        driver.findElement(By.xpath("//span[.='Анна']")).click();
        WebDriverWait webDriverWait=new WebDriverWait(driver, Duration.ofSeconds(30));
         Thread.sleep(5000);
       // webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[.=' Продолжить ']/ancestor::button[not(@disabled)]"))); //.visibilityOfElementLocated(By.xpath("//span[.=' Продолжить ']")));

        driver.findElement(By.xpath("//span[.=' Продолжить ']/ancestor::button[not(@disabled)]")).click(); ////span[.=' Продолжить ']
        Thread.sleep(5000);
        driver.findElement(By.xpath("//span[.=' Продолжить ']")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//span[.=' Верно ']")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//span[.=' Верно ']")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//span[.=' Продолжить ']")).click();

        Thread.sleep(5000);
        driver.findElement(By.xpath("//h6[contains(text(),'специалисты Поликлиника № 4')]")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//span[.='Выбрать ']")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//input")).sendKeys("врач-офтальмолог");
        //driver.findElement(By.xpath("//input")).sendKeys("врач - детский хирург");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//span[contains(text(),'врач-офтальмолог')]")).click();
       // driver.findElement(By.xpath("//span[contains(text(),'врач - детский хирург')]")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//div[@class='expand-collapse ng-star-inserted']")).click();
        driver.findElement(By.xpath("//input[@class='search-input ng-pristine ng-valid ng-touched']")).sendKeys("Петрова Людмила Аркадьевна"); //Михайлова Надежда Анатольевна");
        //driver.findElement(By.xpath("//input[@class='search-input ng-pristine ng-valid ng-touched']")).sendKeys("Краснов Сергей Леонидович");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//span[contains(text(),'Петрова Людмила Аркадьевна')]")).click();//Михайлова Надежда Анатольевна')]")).click();
       Thread.sleep(5000);
        driver.findElement(By.xpath("//div[@class='calendar-day ng-star-inserted' and not(@locked)]")).click();

        // driver.findElement(By.xpath("//div[@class='expand-collapse ng-star-inserted']")).click();
       // driver.findElement(By.xpath("//span[contains(text(),'Краснов Сергей Леонидович')]")).click();
    }
}

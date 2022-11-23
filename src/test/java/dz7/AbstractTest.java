package dz7;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.time.Duration;
import java.util.List;

public abstract class AbstractTest {
    static EventFiringWebDriver eventDriver;

   // static WebDriver webDriver;

    @BeforeAll
    static void setDriver(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        //options.addArguments("--headless");
        options.addArguments("start-maximized");
        //options.setPageLoadTimeout(Duration.ofSeconds(10));
       // webDriver = new ChromeDriver(options);
       // webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        eventDriver = new EventFiringWebDriver(new ChromeDriver(options));
        eventDriver.register(new MyWebDriverEventListener());

        eventDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    @BeforeEach
    void initMainPage() throws InterruptedException {
        Assertions.assertDoesNotThrow( ()-> getWebDriver().navigate().to("https://www.detmir.ru"),
                "Страница не доступна");
        Assertions.assertTrue(true);
    }

    @AfterAll
    public static void exit(){
        //   if(webDriver !=null) webDriver.quit();
        if(eventDriver !=null) eventDriver.quit();

    }

    @AfterEach
    public void checkBrowser(){
        List<LogEntry> allLogRows = getWebDriver().manage().logs().get(LogType.BROWSER).getAll();
        if(!allLogRows.isEmpty()){

            if (allLogRows.size() > 0 ) {
                allLogRows.forEach(logEntry -> {
                    System.out.println(logEntry.getMessage());
                });

            }

        }

    }

    public WebDriver getWebDriver(){
        return this.eventDriver;
    }
}


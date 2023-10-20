import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class mtsTest extends Data {
    private static By enterCookie = By.xpath("//button[@id='cookie-agree']");
    private By inputPhone = By.xpath("//input[@id='connection-phone']");
    private By inputCash = By.xpath("//input[@id='connection-sum']");
    private By inputEmail = By.xpath("//input[@id='connection-email']");
    //private By enter = By.xpath("//a[@class='button button__default '][1]");
    private By enter = By.xpath("//a[@class='button button__default my-mts__button']");
    private By getCash = By.xpath("//div[@class='header__container']//p[@class='header__payment-amount']");

    @Before
    public void open() {
        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        Data getDate = new Data();
        driver.get(getDate.getUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @After
    public void close() {
        // driver.quit();
    }

    @Test
    public void mtsTestOne() {
        WebDriver driver = new ChromeDriver();
        Data getDate = new Data();
        driver.findElement(enterCookie).click();
        driver.findElement(inputPhone).sendKeys(getDate.phone);
        driver.findElement(inputCash).sendKeys(getDate.cash);
        driver.findElement(inputEmail).sendKeys(getDate.email);
        driver.findElement(enter).click();
        WebElement frame = driver.findElement(By.xpath("//iframe[@class='bepaid-iframe']"));
        driver.switchTo().frame(frame);
        WebElement header = driver.findElement(By.xpath("//p[@class='header__payment-amount']"));
        Assert.assertEquals("10.00", header.getAttribute("textContent"));

        @Test
        public void mtsTestTwo() {
            WebElement sum = driver.findElement(By.xpath("//button[@class='colored disabled ng-star-inserted']"));
            Assert.assertEquals("10.00", sum.getAttribute("textContent"));
        }
        @Test
        public void mtsTestThree() {
            WebElement numPhone = driver.findElement(By.xpath("//p[@class='header__payment-info']"));
            Assert.assertEquals("375297777777", numPhone.getAttribute("textContent"));
        }
        @Test
        public void mtsTestFour() {
            WebElement cardNumber = driver.findElement(By.xpath("//label[@class='ng-tns-c46-5 ng-star-inserted']"));
            Assert.assertEquals("Номер карты", cardNumber.getAttribute("textContent"));

            WebElement date = driver.findElement(By.xpath("//label[@class='ng-tns-c46-4 ng-star-inserted']"));
            Assert.assertEquals("Срок действия", date.getAttribute("textContent"));

            WebElement cvc = driver.findElement(By.xpath("//label[@class='ng-tns-c46-5 ng-star-inserted']"));
            Assert.assertEquals("CVC", cvc.getAttribute("textContent"));

            WebElement cardName = driver.findElement(By.xpath("//label[@class='ng-tns-c46-3 ng-star-inserted']"));
            Assert.assertEquals("Имя держателя (как на карте)", cardName.getAttribute("textContent"));
        }
        @Test
        public void mtsTestFive() {
            List<WebElement> icon = driver.findElements(By.xpath("//div[@class='cards-brands ng-tns-c46-1']//.//img"));
            icon.forEach((x) -> assertTrue(x.isEnabled()));
        }
    }
}


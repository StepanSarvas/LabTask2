import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import static org.testng.AssertJUnit.assertEquals;

public class RozetkaTest {
    private WebDriver driver;
    int coffeeMachinePriceValue;
    int QLED_TV_PriceValue;
    int ajaxSafetyPriceValue;
    int expectedSummaryPrice;
    int actualSummaryPrice;


    @BeforeTest
    public void webDriverProperty() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
    }

    @BeforeMethod
    public void browserConfiguration() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rozetka.com.ua/");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

    @Test
    public void checkPrice() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement householdEquipment = driver.findElement(By.xpath("//ul[@class='menu-categories menu-categories_type_main']/li[4]/a"));
        householdEquipment.click();
        WebElement philipsBrandButton = driver.findElement(By.cssSelector("img.ng-lazyloaded[src*='bytovaya_tehnika_interer/brendi/philips']"));
        philipsBrandButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul/li[3]/button[contains(@class, 'ng-star-inserted')]")));
        WebElement moreHouseholdButton = driver.findElement(By.xpath("//ul/li[3]/button[contains(@class, 'ng-star-inserted')]"));
        moreHouseholdButton.click();
        WebElement coffeeMachines = driver.findElement(By.cssSelector("a.categories-filter__link[href*='coffee_machines']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(coffeeMachines);
        coffeeMachines.click();
        WebElement sortButton = driver.findElement(By.cssSelector(".catalog-settings__sorting select"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".catalog-settings__sorting select")));
        Select sortingSelect = new Select(sortButton);
        sortingSelect.selectByIndex(2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[1]/app-goods-tile-default/div/div[2]/a[contains(@title, 'EP5447/90')]")));
        WebElement CoffeeMachinePrice = driver.findElement(By.xpath("//li[1]/app-goods-tile-default/div/div[2]/div[4]/div[2]/p/span[contains(@class, 'value')]"));
        coffeeMachinePriceValue = Integer.parseInt(CoffeeMachinePrice.getText().replace(" ", ""));
        WebElement coffeeMachineCartButton = driver.findElement(By.xpath("//li[1]/app-goods-tile-default/div/div[2]/div[4]/div[2]/app-buy-button"));
        coffeeMachineCartButton.click();
        WebElement menuButton = driver.findElement(By.id("fat-menu"));
        menuButton.click();
        WebElement phonesAndTVCategory = driver.findElement(By.cssSelector("a.menu-categories__link[href*='telefony-tv-i-ehlektronika']"));
        phonesAndTVCategory.click();
        WebElement QLED_TVs = driver.findElement(By.partialLinkText("QLED"));
        QLED_TVs.click();
        WebElement sortButtonTV = driver.findElement(By.cssSelector(".catalog-settings__sorting select"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".catalog-settings__sorting select")));
        Select sortingSelectTV = new Select(sortButtonTV);
        sortingSelectTV.selectByIndex(2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@title, 'Samsung QE85QN900AUXUA')]")));
        WebElement QLED_TV_Price = driver.findElement(By.xpath("//li[1]/app-goods-tile-default/div/div[2]/div[4]/div[2]/p/span[contains(@class, 'price-value')]"));
        QLED_TV_PriceValue = Integer.parseInt(QLED_TV_Price.getText().replace(" ", ""));
        WebElement QLED_TV_CartButton = driver.findElement(By.xpath("//li[1]/app-goods-tile-default/div/div[2]/div[4]/div[2]/app-buy-button"));
        QLED_TV_CartButton.click();
        WebElement menuButton2 = driver.findElement(By.id("fat-menu"));
        menuButton2.click();
        WebElement stuffForBusiness = driver.findElement(By.cssSelector("a.menu-categories__link[href*='tovary-dlya-biznesa']"));
        stuffForBusiness.click();
        WebElement safetySystems = driver.findElement(By.cssSelector("a.tile-cats__heading[href*='sistemy-okhrany-i-bezopasnosti']"));
        actions.moveToElement(safetySystems);
        safetySystems.click();
        WebElement ajaxSafety = driver.findElement(By.xpath("//label[contains(@for, 'Ajax')]"));
        ajaxSafety.click();
        WebElement sortButtonSafety = driver.findElement(By.cssSelector(".catalog-settings__sorting select"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".catalog-settings__sorting select")));
        Select sortingSelectSafety = new Select(sortButtonSafety);
        sortingSelectSafety.selectByIndex(2);
        WebElement ajaxSafetyPrice = driver.findElement(By.xpath("//li[1]/app-goods-tile-default/div/div[2]/div[4]/div[2]/p/span[contains(@class, 'goods-tile__price-value')]"));
        ajaxSafetyPriceValue = Integer.parseInt(ajaxSafetyPrice.getText().replace(" ", ""));
        WebElement ajaxSafetyCartButton = driver.findElement(By.xpath("//li[1]/app-goods-tile-default/div/div[2]/div[4]/div[2]/app-buy-button"));
        ajaxSafetyCartButton.click();
        WebElement cartMenuButton = driver.findElement(By.xpath("//rz-cart/button"));
        cartMenuButton.click();
        WebElement cartPrice = driver.findElement(By.cssSelector("div.cart-receipt__sum-price :nth-child(1)"));
        actualSummaryPrice = Integer.parseInt(cartPrice.getText());
        expectedSummaryPrice = coffeeMachinePriceValue + QLED_TV_PriceValue + ajaxSafetyPriceValue;
        System.out.println("Expected Result = " + expectedSummaryPrice);
        System.out.println("Actual Result = " + actualSummaryPrice);
        System.out.println("coffee = " + coffeeMachinePriceValue + " TV = " + QLED_TV_PriceValue + " AJAX = " + ajaxSafetyPriceValue);
        assertEquals(expectedSummaryPrice, actualSummaryPrice);
    }
}

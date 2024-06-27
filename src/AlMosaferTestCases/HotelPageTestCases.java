package AlMosaferTestCases;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HotelPageTestCases extends TestData {
    
    public void HotelSelectionTest() {
        WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
        HotelTab.click();
        WebElement SearchHotel = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input"));

        if (driver.getCurrentUrl().contains("en")) {
            String[] EnglishCities = { "dubai", "jeddah", "riyadh" };
            int randomIndex = rand.nextInt(EnglishCities.length);
            SearchHotel.sendKeys(EnglishCities[randomIndex]);
        } else if (driver.getCurrentUrl().contains("ar")) {
            String[] ArabicCities = { "دبي", "جدة" };
            int randomIndex = rand.nextInt(ArabicCities.length);
            SearchHotel.sendKeys(ArabicCities[randomIndex]);
        }
    }

    public void HotelNumberOfVisitorTest() {
        WebElement MyElement = driver.findElement(By.xpath("//select[@data-testid='HotelSearchBox__ReservationSelect_Select']"));
        Select mySelector = new Select(MyElement);

        int RandomIndex = rand.nextInt(2);
        mySelector.selectByIndex(RandomIndex);

        driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']")).click();
    }

    public void HotelPageIsFullyLoadedTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
        WebElement resultsTab = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@data-testid='HotelSearchResult__resultsFoundCount']")));
        org.testng.Assert.assertEquals(resultsTab.getText().contains("found") || resultsTab.getText().contains("وجدنا"), true);
    }

    public void HotelSortingTest() throws InterruptedException {
        WebElement LowestPriceButton = driver.findElement(By.xpath("//button[@data-testid='HotelSearchResult__sort__LOWEST_PRICE']"));
        LowestPriceButton.click();

        Thread.sleep(2000);

        WebElement PricesContainer = driver.findElement(By.cssSelector(".sc-htpNat.KtFsv.col-9"));
        List<WebElement> prices = PricesContainer.findElements(By.className("Price__Value"));

        String firstPrice = prices.get(0).getText();
        String lastPrice = prices.get(prices.size() - 1).getText();

        int firstPriceAsInt = Integer.parseInt(firstPrice);
        int lastPriceAsInt = Integer.parseInt(lastPrice);
        org.testng.Assert.assertEquals(firstPriceAsInt < lastPriceAsInt, true);
    }
}

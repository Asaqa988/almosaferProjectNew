package a;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import dev.failsafe.internal.util.Assert;

public class MyTestcase {

	WebDriver driver = new ChromeDriver();

	String Url = "https://global.almosafer.com/en";
	String ExpectedEnglishLanaguge = "en";
	String ExpectedArabicLanage = "ar";
	String ExpectedArabicLanaguge = "ar";

	String ExpectedCurrency = "SAR";
	String ExpectedContactNumber = "+966554400000";

	boolean QitafLogoIsThere = true;

	Random rand = new Random();

	@BeforeTest
	public void mySetup() {

		driver.get(Url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		driver.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary")).click();
		;

	}

	@Test(enabled = false)
	public void CheckTheLanage() {

		WebElement HtmlTag = driver.findElement(By.tagName("html"));

		String ActualLangagueOnTheWebsite = HtmlTag.getAttribute("lang");

		AssertJUnit.assertEquals(ActualLangagueOnTheWebsite, ExpectedEnglishLanaguge);

	}

	@Test(enabled = false)
	public void TestTheCurrencyIsSAR() {
		String ActualCurrency = driver.findElement(By.xpath("//button[@data-testid = 'Header__CurrencySelector']"))
				.getText();

		AssertJUnit.assertEquals(ActualCurrency, ExpectedCurrency);

	}

	@Test(enabled = false)
	public void TestContactNumber() {
		String ActualContactNumber = driver.findElement(By.tagName("strong")).getText();

		AssertJUnit.assertEquals(ActualContactNumber, ExpectedContactNumber);

	}

	@Test(enabled = false)
	public void VerifyQitafLogo() {
		boolean expectedResults = true;
		WebElement TheFoooter = driver.findElement(By.tagName("footer"));

		boolean Actualresult = TheFoooter.findElement(By.cssSelector(".sc-fihHvN.eYrDjb"))
				.findElement(By.tagName("svg")).isDisplayed();

		AssertJUnit.assertEquals(Actualresult, expectedResults);
	}

	@Test(enabled = false)
	public void CheckHotelTabIsNotSelected() {
		String ExpectedValue = "false";
		String ActualValue = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"))
				.getAttribute("aria-selected");

		AssertJUnit.assertEquals(ActualValue, ExpectedValue);

	}

	@Test(enabled = false)
	public void CheckDepatureAndReturnDate() {

		LocalDate today = LocalDate.now();

		int ExpectedDepatureDate = today.plusDays(1).getDayOfMonth();

		int ExpectedReturneDate = today.plusDays(2).getDayOfMonth();

		String ActualDepatureDate = driver
				.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-kqlzXE blwiEW'] span[class='sc-cPuPxo LiroG']"))
				.getText();

		String ActualReturnDate = driver
				.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-OxbzP edzUwL'] span[class='sc-cPuPxo LiroG']"))
				.getText();

		int ActualDepatureDateASInt = Integer.parseInt(ActualDepatureDate);
		int ActualReturnDateASInt = Integer.parseInt(ActualReturnDate);

		AssertJUnit.assertEquals(ActualDepatureDateASInt, ExpectedDepatureDate);
		AssertJUnit.assertEquals(ActualReturnDateASInt, ExpectedReturneDate);

	}

	@Test(priority = 1)

	public void ChangeTheLanguageOfTheWebSiteRandomly() {

		String[] websites = { "https://global.almosafer.com/en", "https://global.almosafer.com/ar" };

		int randomIndex = rand.nextInt(websites.length);
		driver.get(websites[randomIndex]);

		if (driver.getCurrentUrl().contains("en")) {

			WebElement HtmlTag = driver.findElement(By.tagName("html"));

			String ActualLangagueOnTheWebsite = HtmlTag.getAttribute("lang");

			AssertJUnit.assertEquals(ActualLangagueOnTheWebsite, ExpectedEnglishLanaguge);
		} else if (driver.getCurrentUrl().contains("ar")) {
			WebElement HtmlTag = driver.findElement(By.tagName("html"));

			String ActualLangagueOnTheWebsite = HtmlTag.getAttribute("lang");

			AssertJUnit.assertEquals(ActualLangagueOnTheWebsite, ExpectedArabicLanage);

		}

	}

	@Test(priority = 2)
	public void HotelSelection() {
		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		HotelTab.click();
		WebElement SearchHotel = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input"));

		if (driver.getCurrentUrl().contains("en")) {
			String[] EnglishCities = { "dubai", "jeddah", "riyadh" };
			int randomIndex = rand.nextInt(EnglishCities.length);
			SearchHotel.sendKeys(EnglishCities[randomIndex]);

		} else if (driver.getCurrentUrl().contains("ar")) {
			String[] ArbicCities = { "دبي", "جدة" };
			int randomIndex = rand.nextInt(ArbicCities.length);
			SearchHotel.sendKeys(ArbicCities[randomIndex]);

		}
	}

	@Test(priority = 3)

	public void SelectNumberOfPeople() {
		WebElement MyElement = driver
				.findElement(By.xpath("//select[@data-testid='HotelSearchBox__ReservationSelect_Select']"));
		Select mySelector = new Select(MyElement);

		int RandomIndex = rand.nextInt(2);
		mySelector.selectByIndex(RandomIndex);

		driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']")).click();
	}

	@Test(priority = 4)

	public void CheckThePageIsFullyLoaded() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));

		WebElement resultsTab = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//span[@data-testid='HotelSearchResult__resultsFoundCount']")));

		org.testng.Assert.assertEquals(resultsTab.getText().contains("found") || resultsTab.getText().contains("وجدنا"),
				true);

	}

	@Test(priority = 5)
	public void sortItems() throws InterruptedException {

		WebElement LowestPriceButton = driver
				.findElement(By.xpath("//button[@data-testid='HotelSearchResult__sort__LOWEST_PRICE']"));
		LowestPriceButton.click();

		Thread.sleep(2000);

		WebElement PricesContainer = driver.findElement(By.cssSelector(".sc-htpNat.KtFsv.col-9"));
		List<WebElement> prices = PricesContainer.findElements(By.className("Price__Value"));

		String firstPRice = prices.get(0).getText();

		String lastPrice = prices.get(prices.size() - 1).getText();

		int firstPriceAsInt = Integer.parseInt(firstPRice);

		int lastPriceAsInt = Integer.parseInt(lastPrice);
		org.testng.Assert.assertEquals(firstPriceAsInt < lastPriceAsInt, true);

	}
}

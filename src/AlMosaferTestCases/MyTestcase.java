package AlMosaferTestCases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class MyTestcase extends TestData {
    MainPageTestCases TC = new MainPageTestCases();
    HotelPageTestCases HTC = new HotelPageTestCases();

    @BeforeTest
    public void mySetup() {
        TheDefaultConfiguration();
    }

    @Test(description = "MainPageTest", priority = 1)
    public void CheckTheLanguage() {
        TC.CheckTheLanguageTest();
    }

    @Test(description = "MainPageTest", priority = 2)
    public void TestTheCurrencyIsSAR() {
        TC.CheckTheCurrencyTest();
    }

    @Test(description = "MainPageTest", priority = 3)
    public void TestContactNumber() {
        TC.CheckContactNumberTest();
    }

    @Test(description = "MainPageTest", priority = 4)
    public void VerifyQitafLogo() {
        TC.CheckQitafLogoTest();
    }

    @Test(description = "MainPageTest", priority = 5)
    public void CheckHotelTabIsNotSelected() {
        TC.CheckHotelTabIsNotSelectedTest();
    }

    @Test(description = "MainPageTest", priority = 6)
    public void CheckDepartureAndReturnDate() {
        TC.CheckDepartureAndReturnDateTest();
    }

    @Test(description = "MainPageTest", priority = 7)
    public void ChangeTheLanguageOfTheWebsiteRandomly() {
        TC.CheckTheLanguageHasChangedRandomlyTest();
    }

    @Test(description = "HotelPageTest", priority = 8)
    public void HotelSelection() {
        HTC.HotelSelectionTest();
    }

    @Test(description = "HotelPageTest", priority = 9)
    public void SelectNumberOfPeople() {
        HTC.HotelNumberOfVisitorTest();
    }

    @Test(description = "HotelPageTest", priority = 10)
    public void CheckThePageIsFullyLoaded() {
        HTC.HotelPageIsFullyLoadedTest();
    }

    @Test(description = "HotelPageTest", priority = 11)
    public void sortItems() throws InterruptedException {
        HTC.HotelSortingTest();
    }
}

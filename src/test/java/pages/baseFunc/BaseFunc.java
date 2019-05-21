package pages.baseFunc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class BaseFunc {
    private WebDriver driver;
    private WebDriverWait wait;
    private FileWriter writer;

    public BaseFunc() {
        String chromeWebDriver = "webdriver.chrome.driver";
        String propertyMac = "user.home";
        String chromeDriverMac = "chromedriver";
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            System.setProperty(chromeWebDriver, "C://chromedriver.exe");
        } else if (os.contains("mac")) {
            System.setProperty(chromeWebDriver, new File(System.getProperty(propertyMac), chromeDriverMac).getAbsolutePath());
        } else Assertions.assertTrue(!(os.equals("mac")) && !(os.equals("win")), "Incorrect webDriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
    }

    public void openPage(String url) {
        if (!url.contains("https://") && !url.contains("http://")) {
            url = "http://" + url;
        }
        driver.get(url);
    }

    public WebElement getelement(By locator) {
        Assertions.assertFalse(driver.findElements(locator).isEmpty(), "Element is not found");
        return driver.findElement(locator);
    }

    public List<WebElement> getAllElements(By locator) {
        Assertions.assertFalse(driver.findElements(locator).isEmpty(), "Elements are not found");
        return driver.findElements(locator);
    }

    public void waitForElement(By locator) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public boolean isElementPresent(By locator) {
        waitForElement(locator);
        return getAllElements(locator).isEmpty();
    }

    public void listToFile(String name, List<String> list) throws IOException {
        writer = new FileWriter(name + ".txt");
        for (String str : list) {
            writer.write(str + System.getProperty("line.separator"));
        }
        writer.close();
    }

    public void arrayToJsonFile(String name, HashMap<String, List<String>> articles) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        writer = new FileWriter(name + ".txt");
        writer.write(mapper.writeValueAsString(articles));
        writer.close();
    }

    public void quit(){
        driver.close();
    }
}
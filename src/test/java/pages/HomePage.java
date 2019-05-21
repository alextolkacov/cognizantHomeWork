package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.baseFunc.BaseFunc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class HomePage {
    private BaseFunc baseFunc;
    private final By REGISTRATION_CONTAINER = By.xpath(".//div[@class = '-container grid jc-space-between ai-center']");
    private final By SIDE_BAR = By.xpath(".//div[@id = 'sidebar']");
    private final By NAVIGATION_LINKS = By.xpath(".//ol[@class = 'nav-links']");
    private final By FOOTER = By.xpath(".//div[@class = 'site-footer--container']");
    private final By ARTICLES = By.xpath(".//a[@class = 'question-hyperlink']");
    private final By COOKIES = By.xpath(".//a[@aria-label = 'notice-dismiss']");

    public HomePage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
        Assertions.assertFalse(baseFunc.isElementPresent(REGISTRATION_CONTAINER), "Registration container is not present");
        Assertions.assertFalse(baseFunc.isElementPresent(SIDE_BAR), "Remote jobs container is not present");
        Assertions.assertFalse(baseFunc.isElementPresent(NAVIGATION_LINKS), "Navigation links are not present");
        Assertions.assertFalse(baseFunc.isElementPresent(FOOTER), "Footer is not present");
    }

    public void acceptCookies() {
        baseFunc.waitForElement(COOKIES);
        baseFunc.getelement(COOKIES).click();
    }

    public void outputArticles(List<String> wordsToSearch) throws IOException {
        List<WebElement> articles = baseFunc.getAllElements(ARTICLES);
        HashMap<String, List<String>> foundArticles = new HashMap<>();
        List<String> allArticlesFound = new ArrayList<>();
        for (String word : wordsToSearch) {
            List<String> foundList = new ArrayList<>();
            for (WebElement article : articles) {
                if (article.getText().contains(word)) {
                    allArticlesFound.add(article.getText());
                    foundList.add(article.getText());
                }
                foundArticles.put(word, foundList);
            }
            baseFunc.listToFile("output", allArticlesFound);
        }
        baseFunc.arrayToJsonFile("json", foundArticles);
    }
}
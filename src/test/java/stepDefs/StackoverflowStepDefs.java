package stepDefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.HomePage;
import pages.baseFunc.BaseFunc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class StackoverflowStepDefs {
    private final String STACKOVERFLOW_HOME_PAGE = "www.stackoverflow.com";
    private BaseFunc baseFunc = new BaseFunc();
    private HomePage homePage;
    private List<String> listOfArticles;
    private final String user = "root";
    private final String password = "password";

    @Given("Stackoverflow home page")
    public void open_home_page() {
        baseFunc.openPage(STACKOVERFLOW_HOME_PAGE);
        homePage = new HomePage(baseFunc);
    }

    @When("Accept cookies")
    public void accept_cookies() {
        homePage.acceptCookies();
    }

    @Then("Search and output articles by:")
    public void search_articles_by(List<String> words) throws IOException {
        listOfArticles = homePage.outputArticles(words);
    }

    @Then("Store list of found articles in DB with path: {word}, driver: {word}")
    public void store_in_db(String pathToDb, String driver) throws SQLException, ClassNotFoundException {
        homePage.storeInDb(listOfArticles, pathToDb, driver, user, password);
    }

    @Then("Close browser")
    public void close_browser() {
        baseFunc.quit();
    }
}

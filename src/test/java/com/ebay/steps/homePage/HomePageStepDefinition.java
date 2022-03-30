package com.ebay.steps.homePage;

import com.ebay.components.InputComponent;
import com.ebay.components.SearchComponent;
import com.ebay.enums.ScenarioContextKey;
import com.ebay.enums.Users;
import com.ebay.steps.Steps;
import com.ebay.utils.data.scenarioContext.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.util.Locale;

public class HomePageStepDefinition extends Steps {

    @Given("I am a {string} customer")
    public void getUserLoginInfo(String customerName) {
        if (customerName.contains("-"))
            customerName = customerName.replace("-", "_");

        //save user name for future login
        Users userName = Users.valueOf(customerName.toUpperCase(Locale.ROOT));
        ScenarioContext.setValue(ScenarioContextKey.USER_NAME, userName);
    }

    @Given("I navigate to {string}")
    public void navigateToUrl(String url) {
        site.loadPage(url);

//        here write some code for login user or should create new step definition
//            loginByUser();
    }

    @When("I search for an item {string}")
    public void searchItem(String itemName) {
        SearchComponent searchComponent = site.homePage().searchComponent();

        fillSearchField(searchComponent, itemName);
        searchComponent.searchButton().click();
    }

    @When("I enter a search term {string} and select a specific Category {string}")
    public void searchItemAndSelectCategory(String itemName, String categoryName) {
        SearchComponent searchComponent = site.homePage().searchComponent();

        fillSearchField(searchComponent, itemName);
        searchComponent.selectCategory().selectByVisibleText(categoryName);
        searchComponent.searchButton().click();
        ScenarioContext.setValue(ScenarioContextKey.SELECTED_CATEGORY, categoryName);
    }

    private void fillSearchField(SearchComponent searchComponent, String text) {
        InputComponent searchInput = searchComponent.searchInput();

        searchInput.clear();
        searchInput.append(text);
    }
}

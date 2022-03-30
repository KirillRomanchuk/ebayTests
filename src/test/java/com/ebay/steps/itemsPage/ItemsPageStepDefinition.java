package com.ebay.steps.itemsPage;

import com.ebay.components.SelectComponent;
import com.ebay.enums.ScenarioContextKey;
import com.ebay.pages.searchPage.ItemsPage;
import com.ebay.steps.Steps;
import com.ebay.utils.data.scenarioContext.ScenarioContext;
import io.cucumber.java.en.When;

import java.util.Map;

public class ItemsPageStepDefinition extends Steps {
    @When("I can sort the results by {string}")
    public void sortResultList(String sortType) {
        ItemsPage itemsPage = site.itemsPage();
        itemsPage.sortButton().click();

        Map<String, SelectComponent> sortMap = itemsPage.sortVariantsMap();
        SelectComponent selectComponent = sortMap.get(sortType);
        selectComponent.click();
        ScenarioContext.setValue(ScenarioContextKey.SORT_TYPE, sortType);
    }

    @When("I can filter the results by {string}")
    public void addFilter(String filterName) {
        ItemsPage itemsPage = site.itemsPage();

        Map<String, SelectComponent> filterMap = itemsPage.filterMap();
        SelectComponent selectComponent = filterMap.get(filterName);
        selectComponent.click();
    }
}

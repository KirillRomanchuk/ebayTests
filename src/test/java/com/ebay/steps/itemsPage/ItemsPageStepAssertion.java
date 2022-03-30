package com.ebay.steps.itemsPage;

import com.ebay.components.BaseComponent;
import com.ebay.components.PaginationComponent;
import com.ebay.enums.ScenarioContextKey;
import com.ebay.pages.searchPage.ItemCard;
import com.ebay.steps.Steps;
import com.ebay.utils.data.scenarioContext.ScenarioContext;
import io.cucumber.java.en.Then;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ItemsPageStepAssertion extends Steps {
    @Then("I get a list of matching results")
    public void getListMatchingResults() {
        List<ItemCard> itemResultList = site.itemsPage().itemResultList();
        assert !itemResultList.isEmpty() : "I didn't see a list with matching results";
    }

    @Then("the resulting items cards show: price, postage price, No of bids or show Buy it now")
    public void resultingItemsCardsShow() {
        List<ItemCard> itemResultList = site.itemsPage().itemResultList();
        List<BaseComponent> itemResultListPrices = site.itemsPage().itemResultListPrices();
        List<BaseComponent> itemResultListPostage = site.itemsPage().itemResultListPostage();
        List<BaseComponent> itemResultListBuyNow = site.itemsPage().itemResultListBuyNow();
        List<BaseComponent> itemResultListBids = site.itemsPage().itemResultListBids();

        // this is a long variant with personal check for every item
//        boolean sawAllDetails = true;

//        for (ItemCard item : itemResultList) {
//            if (!sawAllDetails)
//                break;
//
//            sawAllDetails = checkFieldAvailability(item);
//        }
//        assert sawAllDetails : "not all resulting items show needed properties";

        int tableSize = itemResultList.size();

        assert tableSize == itemResultListPrices.size()
                && tableSize == itemResultListPostage.size()
                // should use more or equal because some items have buy it now tag and number of bids
                && tableSize <= (itemResultListBuyNow.size() + itemResultListBids.size())
                : "not all resulting items show needed properties";
    }

    @Then("the results are listed in the page in the correct order")
    public void resultsListedInCorrectOrder() {
        List<BaseComponent> itemResultListPrices = site.itemsPage().itemResultListPrices();
        Double[] intPrices = convertToDoublePrice(itemResultListPrices);
        Double[] sortInteger = intPrices.clone();
        String sortType = (String) ScenarioContext.getValueByKey(ScenarioContextKey.SORT_TYPE);

        if (sortType.equals("Lowest price")) {
            Arrays.sort(sortInteger);
        } else if (sortType.equals("Highest price")) {
            Arrays.sort(sortInteger, Collections.reverseOrder());
        }

        assert Arrays.equals(intPrices, sortInteger) : "the order is not correct";
    }


    @Then("all the results shown in the page have the \"Buy it now\" tag")
    public void resultsShownHaveTag() {
        List<ItemCard> itemResultList = site.itemsPage().itemResultList();
        List<BaseComponent> itemResultListBuyNow = site.itemsPage().itemResultListBuyNow();

        assert itemResultList.size() == itemResultListBuyNow.size() : "not all items have  the \"Buy it now\" tag";
    }

    @Then("I can verify that the results shown as per the selected category")
    public void resultsShownHaveSelectedCategory() {
//        i don't found some property for check some item as per selected category
        String category = (String) ScenarioContext.getValueByKey(ScenarioContextKey.SELECTED_CATEGORY);
        String categoryOnPage = site.itemsPage().selectedCategory();
        categoryOnPage = categoryOnPage.split("\n")[1];

        List<ItemCard> itemResultList = site.itemsPage().itemResultList();

        assert category.equals(categoryOnPage) && itemResultList.size() != 0 : "shown results is not as per the selected category";
    }

    @Then("the results show more than one page")
    public void resultsShownMoreThanOnePage() {
        PaginationComponent paginationComponent = site.itemsPage().paginationComponent();
        assert paginationComponent.isDisplayed(1) : "the results did not show more than one page";
    }

    @Then("the user can navigate through the pages to continue looking at the items")
    public void userCanNavigateThroughThePages() {
        PaginationComponent paginationComponent = site.itemsPage().paginationComponent();
        String currentPage = paginationComponent.activePage().getText();

        List<BaseComponent> pageList = paginationComponent.pageList();
        pageList.get(2).click();

        List<ItemCard> itemResultList = site.itemsPage().itemResultList();

        paginationComponent = site.itemsPage().paginationComponent();
        BaseComponent newPage2 = paginationComponent.activePage();
        String newPage = newPage2.getText();

        assert itemResultList.size() != 0 && !currentPage.equals(newPage) : "The user still saw first page";
    }

    private boolean checkFieldAvailability(ItemCard item) {
        BaseComponent price = item.getPrice();
        BaseComponent bids = item.getBids();
        BaseComponent buyNow = item.getBuyNow();
        BaseComponent postage = item.getPostage();

        return price.isDisplayed(1)
                && postage.isDisplayed(1)
                && (buyNow.isDisplayed(1) || bids.isDisplayed(1));
    }

    private Double[] convertToDoublePrice(List<BaseComponent> itemResultListPrices) {
        Double[] intPrices = new Double[itemResultListPrices.size()];

        for (int i = 0; i < itemResultListPrices.size(); i++) {
            String text = itemResultListPrices.get(i).getText();

            // remove the currency sign and other bad symbols
            text = text.substring(1);
            text = text.replace(",", "");

            intPrices[i] = Double.parseDouble(text);
        }

        return intPrices;
    }
}

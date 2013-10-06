package com.globallogic.simple.tests.orders;

import com.globallogic.simple.pages.LoginPage;
import com.globallogic.simple.pages.PizzaPage;
import com.globallogic.simple.tests.BaseTest;
import com.globallogic.simple.utils.NavigationUtils;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 06.10.13
 * Time: 0:30
 * To change this template use File | Settings | File Templates.
 */
public class OrderValidation extends BaseTest{

    private LoginPage loginPage;
    private PizzaPage pizzaPage;

    public OrderValidation() throws Exception {
        super("data/pizzas/PizzaValidation.csv", "Pizza Validation");
    }

    @Override
    protected void onExecute() {
        startPizzaPage();
        chooseType();
        chooseIngredients();
        chooseSize();
        chooseCheeze();
        clickMakeOrder();
        verifyOrderState();
    }

    private void startPizzaPage(){
        loginPage = NavigationUtils.startWebApplication(browser);
        loginPage.setLogin("simple");
        loginPage.setPassword("$1mple");
        loginPage.clickLoginButton();
        pizzaPage = new PizzaPage(browser);
    }

    private void chooseType() {
        pizzaPage.setPizzaType(data.get("Type"));
    }

    private void chooseIngredients() {
        if(data.get("Type").equalsIgnoreCase("Create your own")){
            pizzaPage.setIngredients(data.get("Ingredients"));
        }
    }

    private void chooseSize() {
        pizzaPage.setPizzaSize(data.get("Size"));
    }

    private void chooseCheeze() {
        pizzaPage.setCheeze(data.get("Cheeze"));
    }

    private void clickMakeOrder() {
        pizzaPage.clickMakeOrderButton();
    }

    private void verifyOrderState() {
        boolean expectedState = Boolean.parseBoolean(data.get("IsResultDisplayed"));
        pizzaPage.verifyResultDisplayed(expectedState);

    }
}

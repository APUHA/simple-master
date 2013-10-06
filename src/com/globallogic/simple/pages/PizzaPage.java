package com.globallogic.simple.pages;

import com.globallogic.simple.report.ErrorsHolder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 06.10.13
 * Time: 14:35
 * To change this template use File | Settings | File Templates.
 */
public class PizzaPage extends BasePage {

    private WebElement selectPredefined;
    private WebElement btnMakeOrder;
    private WebElement selectSize;
    private WebElement chbxCheeze;
    private WebElement chbxIngredient;
    private WebElement spanSuccess;


    public PizzaPage(WebDriver browser) {
        super(browser);
    }

    public void setPizzaType(String pizzaType) {
        selectPredefined = browser.findElement(By.id("predefined"));
        selectPredefined.sendKeys(pizzaType);
    }

    public void setIngredients (String ingredients) {
        if(ingredients.length()>0) {
            String[] ingredientList = getIngredientList(ingredients);
            for(int i = 0; i < ingredientList.length; i++) {
                chbxIngredient = browser.findElement(
                                By.xpath("//input[@class='ingredient' and @title='"+ingredientList[i]+"']"));
                chbxIngredient.click();
            }
        }
    }

    public void setPizzaSize (String size) {
        selectSize = browser.findElement(By.id("size"));
        selectSize.sendKeys(size);
    }

    public void setCheeze (String cheeze) {
        chbxCheeze = browser.findElement(By.xpath("//input[@name='cheeze' and @value='" + cheeze + "']"));
        chbxCheeze.click();
    }

    public void clickMakeOrderButton() {
        btnMakeOrder = browser.findElement(By.id("btn-order"));
        btnMakeOrder.click();
    }

    public void verifyResultDisplayed(boolean expectedState) {
        spanSuccess = browser.findElement(By.xpath("//div[@id='result']"));
        String message = spanSuccess.getText();
        boolean actualState = message.equals("");
        if (actualState != expectedState) {
            ErrorsHolder.failIteration("Verification of message for order failed. Message is not displayed");
        }
    }

    private String[] getIngredientList(String ingredients) {
        return ingredients.split(";");
    }

}

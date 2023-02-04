package org.demoWebShop.testscripts;


import com.aventstack.extentreports.ExtentTest;
import org.demoWebShop.automationcore.Base;
import org.demoWebShop.listeners.TestListener;
import org.demoWebShop.pages.HomePage;
import org.demoWebShop.pages.ProductListPage;
import org.demoWebShop.pages.ProductPage;
import org.demoWebShop.pages.ShoppingCartPage;
import org.demoWebShop.utilities.ExcelUtility;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ShoppingCartTest extends Base {
    HomePage home;
    ExcelUtility excel = new ExcelUtility();
    ProductListPage productList;
    ProductPage product;
    ShoppingCartPage shoppingCart;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

    @Test(priority = 1,groups = {"smoke"})
    public void verifyProductAddedInShoppingCart() {
        extentTest.get().assignCategory("Smoke");
        home = new HomePage(driver);
        List<List<String>> data = excel.excelDataReader("JewelleryPage");
        String option = data.get(1).get(0);
        productList = home.clickOnProductMenu(option);
        String value = data.get(1).get(1);
        productList.clickOnSortBy(value);
        String prdt=data.get(1).get(2);
        product=productList.clickOnProduct(prdt);
        product.clickOnShoppingCart();
        shoppingCart=product.clickOnShoppingCartMenu();
        String actualProductInCart = shoppingCart.getProductName();
        String expectedProductInCart ="Black & White Diamond Heart";
        Assert.assertEquals(actualProductInCart, expectedProductInCart, "ERROR::Cart Missmatch");
    }
}

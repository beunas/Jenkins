package org.demoWebShop.dataprovider;

import org.demoWebShop.utilities.ExcelUtility;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.List;

public class DataProviders {
    ExcelUtility excel = new ExcelUtility();

    @DataProvider(name="InvalidUserCredentials")
    public Object[][] invalidUserCredentialsToLogin() throws IOException {
        //List<List<String>> testData = excel.excelDataReader("LoginPageDataProvider");
       // Object[][] data = testData.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
        Object[][] data = excel.dataProviderRead("LoginPageDataProvider");
       return data;
    }
}

package org.TestCases;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import junit.framework.TestCase;
import jxl.write.WriteException;
import org.TestCases.BaseClass;
import org.Utility.XLUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import jxl.write.Label;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OpenWeatherMapAPI extends BaseClass {

    @Test(dataProvider = "getResponse")
    public void TC_01_temp(String temp,String temp_min,String temp_max,String pressure, String sea_level) throws WriteException {
        RestAssured.baseURI = getWeatherMapURL();
        RequestSpecification request = RestAssured.given();
        request.param("q", "London,UK");
        request.param("appid", "b1b15e\n" +
                "88fa797225412429c1c50c122a1");
        Response response = request.get("/city");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        List<String> wtemp = Collections.singletonList(response.jsonPath().getString("list.main.temp"));
        if (Arrays.asList(wtemp).contains(temp)) {
            Assert.assertNull(temp);
            Assert.assertNull(wtemp);
            Testcase="PASS";
        }else{
            Testcase="FAIL";
        }
        Label l1=new Label(0,1,temp);
        Label l2=new Label(1,1,wtemp.get(0));
        writablesh.addCell(l1);
        writablesh.addCell(l2);
    }

    @Test(dataProvider = "getResponse")
    public void TC_02_temp_min(String temp,String temp_min,String temp_max,String pressure, String sea_level) throws WriteException {
        RestAssured.baseURI = getWeatherMapURL();
        RequestSpecification request = RestAssured.given();
        request.param("q", "London,UK");
        request.param("appid", "b1b15e\n" +
                "88fa797225412429c1c50c122a1");
        Response response = request.get("/city");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        List<String> wtemp = Collections.singletonList(response.jsonPath().getString("list.main.temp_min"));
        if (Arrays.asList(wtemp).contains(temp_min)) {
            Assert.assertNull(temp_min);
            Assert.assertNull(wtemp);
            Testcase="PASS";
        }else{
            Testcase="FAIL";
        }
        Label l1=new Label(0,2,temp_min);
        Label l2=new Label(1,2,wtemp.get(0));
        writablesh.addCell(l1);
        writablesh.addCell(l2);
    }

    @Test(dataProvider = "getResponse")
    public void TC_03_temp_max(String temp,String temp_min,String temp_max,String pressure, String sea_level) throws WriteException {
        RestAssured.baseURI = getWeatherMapURL();
        RequestSpecification request = RestAssured.given();
        request.param("q", "London,UK");
        request.param("appid", "b1b15e\n" +
                "88fa797225412429c1c50c122a1");
        Response response = request.get("/city");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        List<String> wtemp = Collections.singletonList(response.jsonPath().getString("list.main.temp_max"));
        if (Arrays.asList(wtemp).contains(temp_max)) {
            Assert.assertNull(temp_max);
            Assert.assertNull(wtemp);
            Testcase="PASS";
        }else{
            Testcase="FAIL";
        }
        Label l1=new Label(0,3,temp_max);
        Label l2=new Label(1,3,wtemp.get(0));
        writablesh.addCell(l1);
        writablesh.addCell(l2);
    }
    @Test(dataProvider = "getResponse")
    public void TC_05_pressure(String temp,String temp_min,String temp_max,String pressure, String sea_level) throws WriteException {
        RestAssured.baseURI = getWeatherMapURL();
        RequestSpecification request = RestAssured.given();
        request.param("q", "London,UK");
        request.param("appid", "b1b15e\n" +
                "88fa797225412429c1c50c122a1");
        Response response = request.get("/city");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        List<String> wtemp = Collections.singletonList(response.jsonPath().getString("list.main.pressure"));
        if (Arrays.asList(wtemp).contains(pressure)) {
            Assert.assertNull(pressure);
            Assert.assertNull(wtemp);
            Testcase="PASS";
        }else{
            Testcase="FAIL";
        }
        Label l1=new Label(0,4,pressure);
        Label l2=new Label(1,4,wtemp.get(0));
        writablesh.addCell(l1);
        writablesh.addCell(l2);
    }

    @Test(dataProvider = "getResponse")
    public void TC_06_sea_level(String temp,String temp_min,String temp_max,String pressure, String sea_level) throws WriteException {
        RestAssured.baseURI = getWeatherMapURL();
        RequestSpecification request = RestAssured.given();
        request.param("q", "London,UK");
        request.param("appid", "b1b15e\n" +
                "88fa797225412429c1c50c122a1");
        Response response = request.get("/city");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        List<String> wtemp = Collections.singletonList(response.jsonPath().getString("list.main.sea_level"));
        if (Arrays.asList(wtemp).contains(sea_level)) {
            Assert.assertNull(sea_level);
            Assert.assertNull(wtemp);
            Testcase="PASS";
        }else{
            Testcase="FAIL";
        }
        Label l1=new Label(0,5,sea_level);
        Label l2=new Label(1,5,wtemp.get(0));
        writablesh.addCell(l1);
        writablesh.addCell(l2);
    }
    @DataProvider(name = "getResponse")
    Object[][] getResponse() throws IOException {
        String path = System.getProperty("user.dir") + "/TestSuitData/openweathermap.xlsx";
        int rownum = XLUtils.getRowCount(path, "data");
        int colcount = XLUtils.getCellCount(path, "data", 1);
        String getData[][] = new String[rownum][colcount];
        for (int k = 1; k <= rownum; k++) {
            for (int m = 0; m < colcount; m++) {
                getData[k - 1][m] = XLUtils.getCellData(path, "data", k, m);
            }
        }
        return getData;
    }
}

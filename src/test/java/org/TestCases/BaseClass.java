package org.TestCases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.Utility.ReadConfig;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.w3c.dom.Document;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class BaseClass extends ReadConfig {
    public static Logger logger;
    public String Testcase;
    public String  ExpectedResults;
    public String ActualResults;
    public WritableSheet writablesh;
    public WritableWorkbook workbookcopy;



    @BeforeClass
    public void setup() throws InterruptedException {
        logger = Logger.getLogger(this.getClass().getName());
        PropertyConfigurator.configure(".//Log4j.xml");
        logger = Logger.getLogger("devpinoyLogger");
    }

    @AfterClass
    public static void tearDown() {
    }

    @BeforeTest

    public void queryPrarametrization() throws IOException, BiffException, WriteException {
        FileInputStream testfile = new FileInputStream("./Reports/TestResults.xls");
        Workbook wbook = Workbook.getWorkbook(testfile);
        Sheet sheets = wbook.getSheet("Query_data");
        int Norows = sheets.getRows();
        String inputdata[][] = new String[sheets.getRows()][sheets.getColumns()];
        System.out.println("Number of rows present in the testdata xls file  is -" + Norows);

        FileOutputStream testoutput = new FileOutputStream(("./Reports/TestResults.xls"));


        workbookcopy = Workbook.createWorkbook(testoutput);
        writablesh = workbookcopy.createSheet("Query_data", 0);

        for (int i = 0; i < sheets.getRows(); i++) {
            for (int k = 0; k < sheets.getColumns(); k++) {
                inputdata[i][k] = sheets.getCell(k, i).getContents();
                Label l = new Label(k, i, inputdata[i][k]);
                Label l1 = new Label(0, 0, "EXPECTED RESULTS");
                Label l2 = new Label(1, 0, "ACTUAL RESULTS");
                writablesh.addCell(l1);
                writablesh.addCell(l2);
            }

        }
    }
    @AfterTest
    public void writeexcels(){
        try{
            workbookcopy.write();
        }catch (IOException e){
            e.printStackTrace();
        }
        try{
            workbookcopy.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }


    }
}



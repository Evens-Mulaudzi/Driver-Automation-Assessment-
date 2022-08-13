package com.AssessmentILAB.webTests;


import com.AssessmentILAB.Data.Data;
import com.AssessmentILAB.PageObjects.WebFunctions;
import com.AssessmentILAB.WebUtilites.Utilities;
import com.AssessmentILAB.extentReports.Reporting;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.*;


import java.sql.ResultSet;


public class TestILAB {
    public Utilities web = new Utilities();
    public Data data =new Data();
    public WebFunctions iLABSite = new WebFunctions();
    public Reporting repo = new Reporting();

    String sUrl,sBrowser;
    ExtentReports reports;


    @Parameters({"ILABURL","Browser"})
    @BeforeTest
    public void init(String sAdactinUrl,String Browser){
        sUrl = sAdactinUrl;
        sBrowser = Browser;

        web.setWebDriver(web.initializeWebDriver(sBrowser));
       reports = repo.initializeExtentReports("reports/report.html");
    }

    @Test
    public void Apply () throws InterruptedException {

        ExtentTest test= reports.createTest("Apply at iLAB");
        test.assignAuthor("Mulaudzi Thompho Evens");
        ExtentTest node;
        ResultSet rs;


        try {
              //connecting to the database and retrieve data
            rs= data.ConnectAndQuerySQL("jdbc:mysql://localhost:3306/ilabassessment",
                    "root", "Mulaudzi@11", "Select * from ilabdata");
            int iRow= data.rowCount(rs);
            web.navigate(sUrl);
            for (int i=1;i<=iRow;i++) {
                if (rs.next()) {

                   node = test.createNode("iLAB South Africa Application Unsuccessful  For Applicant  :"+rs.getString("FirstName" )+ "\t"+"using " +"\t" +sBrowser +"\t" +"Browser");
                    iLABSite.careers(web.getWebDriver(), node);
                    iLABSite.country(web.getWebDriver(), node);
                    iLABSite.openings(web.getWebDriver(), node);
                    iLABSite.Apply(web.getWebDriver(),rs, node);
                    iLABSite.validate(web.getWebDriver(),node);
                }
            }
            rs.close();


        }catch (Exception e){
            System.out.println("Error : " + e.getMessage());
        }

    }


        @AfterTest
    public void tearDown() throws InterruptedException {

        Thread.sleep(3000);
        web.getWebDriver().quit();
        reports.flush();
    }
}

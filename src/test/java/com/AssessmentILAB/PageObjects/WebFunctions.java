package com.AssessmentILAB.PageObjects;

import com.AssessmentILAB.WebPageObjects.iLABCareersPAge;
import com.AssessmentILAB.WebPageObjects.iLABLandingPage;
import com.AssessmentILAB.WebPageObjects.iLABOpeningsPage;
import com.AssessmentILAB.WebPageObjects.iLabApplyPage;
import com.AssessmentILAB.WebUtilites.Actions;
import com.AssessmentILAB.extentReports.Reporting;
import com.AssessmentILAB.phoneNumber.phoneNumber;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import java.io.IOException;
import java.sql.ResultSet;
import java.time.Duration;

public class WebFunctions extends Actions {

    Reporting repo = new Reporting();
    String filename;


    public void careers(WebDriver driver, ExtentTest node) throws IOException {
        iLABLandingPage careersObj = new iLABLandingPage(driver);
        try {
            clickObject(careersObj.careers, driver);
            filename = repo.CaptureScreenShot(driver);

              //validation
            if (driver.getCurrentUrl().contains("careers/")) {
                node.pass("Careers link Successfully clicked", MediaEntityBuilder.createScreenCaptureFromBase64String(filename).build());
            } else {
                node.fail("unable  to click careers page", MediaEntityBuilder.createScreenCaptureFromBase64String(filename).build());
                Assert.fail();
            }

        } catch (Exception e) {
            System.out.print("unable to click careers page : " + e.getMessage());
            Assert.fail();
        }

    }


    public void country(WebDriver driver, ExtentTest node) throws IOException {
        iLABCareersPAge countryObj = new iLABCareersPAge(driver);
        try {
            clickObject(countryObj.country, driver);
            filename = repo.CaptureScreenShot(driver);

         //validation
            if (driver.getCurrentUrl().contains("south-africa/")) {
                node.pass("Successfully clicked south africa openings link", MediaEntityBuilder.createScreenCaptureFromBase64String(filename).build());
            } else {
                node.fail("unable to click south africa link", MediaEntityBuilder.createScreenCaptureFromBase64String(filename).build());
                Assert.fail();
            }
        } catch (Exception e) {
            System.out.print("error " + e.getMessage());
            Assert.fail();
        }
    }

    public void openings(WebDriver driver, ExtentTest node) throws IOException {
        iLABOpeningsPage openingObj = new iLABOpeningsPage(driver);
        iLabApplyPage applyObj = new iLabApplyPage(driver);

        try {
            clickObject(openingObj.openings, driver);
            filename = repo.CaptureScreenShot(driver);

            //validation
            if (applyObj.apply.isDisplayed()) {
                System.out.println("successfully clicked intern openings link");
                node.pass("successfully clicked intern openings link", MediaEntityBuilder.createScreenCaptureFromBase64String(filename).build());
            } else {
                node.pass("unable to click interns link", MediaEntityBuilder.createScreenCaptureFromBase64String(filename).build());
                Assert.fail();
            }

        } catch (Exception e) {
            System.out.print("unable to click interns link: " + e.getMessage());
            Assert.fail();
        }
    }

    public void Apply(WebDriver driver, ResultSet rs, ExtentTest node) {
        iLabApplyPage applyObj = new iLabApplyPage(driver);
        phoneNumber phone = new phoneNumber();


        try {
            filename = repo.CaptureScreenShot(driver);
            clickObject(applyObj.apply, driver);

            passData(applyObj.firstname, driver, rs.getString("FirstName"));
            passData(applyObj.email, driver, rs.getString("Email"));
            passData(applyObj.phone, driver, phone.number());

            clickObject(applyObj.submit, driver);


            //validation
            if(applyObj.validate.isDisplayed()){

                node.pass("You need to upload at least one file Displayed", MediaEntityBuilder.createScreenCaptureFromBase64String(filename).build());
            } else {
                node.pass("You need to upload at least one file not Displayed", MediaEntityBuilder.createScreenCaptureFromBase64String(filename).build());
                Assert.fail();
            }

        } catch (Exception e) {
            System.out.print("Application successfully submitted : " + e.getMessage());
            Assert.fail();
        }
    }

    public void validate(WebDriver driver, ExtentTest node) {

        iLabApplyPage applyObj = new iLabApplyPage(driver);


        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(1000))
                .ignoring(WebDriverException.class);
        wait.until(ExpectedConditions.visibilityOf(applyObj.error));

        try {


            filename = repo.CaptureScreenShot(driver);

            //validation

            if (applyObj.error.isDisplayed()) {

                node.pass("Error message displayed", MediaEntityBuilder.createScreenCaptureFromBase64String(filename).build());

            } else {

                node.fail("Error message not displayed", MediaEntityBuilder.createScreenCaptureFromBase64String(filename).build());
                Assert.fail("Error message not displayed");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


package com.AssessmentILAB.WebPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class iLabApplyPage {
    protected WebDriver driver;

    public iLabApplyPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }


    @FindBy(xpath = " //*[@id=\"wpjb-scroll\"]/div[1]/a")
    public WebElement apply;

    @FindBy(xpath = "//*[@id=\"applicant_name\"]")
    public WebElement firstname;

    @FindBy(xpath = " //*[@id=\"email\"]")
    public WebElement email;

    @FindBy(xpath = "//*[@id=\"phone\"]")
    public WebElement phone;

    @FindBy(xpath = " //*[@id=\"wpjb_submit\"]")
    public WebElement submit;

    @FindBy(xpath = "//li[contains(text(),'You need to upload at least one file.')]")
    public WebElement validate;

    @FindBy(xpath = " //*[@id=\"wpjb-apply-form\"]/fieldset[1]/div[5]/div/ul/li")
    public WebElement error;
}
package com.phptravel.qa.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.phptravel.qa.baseclass.BaseClass;
import com.phptravel.qa.util.TestUtil;

public class Homepage extends BaseClass {

	// PageFactory
	@FindBy(xpath = "//a[contains(text(),'Flights')]")
	WebElement buttonFlightButton;

	@FindBy(xpath = "//label[contains(text(),'Round Trip')]")
	WebElement radioButtonRoundTrip;

	@FindBy(xpath = "//a[@class='chosen-single']/span[text()='Economy']")
	WebElement dropdownEco;

	@FindBy(xpath = "//input[@id='FlightsDateStart']")
	WebElement calenderDeparture;

	@FindBy(xpath = "//input[@id='FlightsDateEnd']")
	WebElement calenderReturn;

//	@FindBy(xpath = "//div[@class='row no-gutters']//descendant::label[text()='Adults ']//following-sibling::div//child::button[@class='btn btn-white bootstrap-touchspin-up ']")
//	WebElement adults;
//
//	@FindBy(xpath = "//div[@class='row no-gutters']//descendant::label[text()='Child ']//following-sibling::div//child::button[@class='btn btn-white bootstrap-touchspin-up ']")
//	WebElement child;
//
//	@FindBy(xpath = "//div[@class='row no-gutters']//descendant::label[text()='infant ']//following-sibling::div//child::button[@class='btn btn-white bootstrap-touchspin-up ']")
//	WebElement infant;

	@FindBy(xpath = "(//button[@class='btn-primary btn btn-block'])[1]")
	WebElement buttonSearch;

	@FindBy(xpath = "//ul[@id='LIST']/li")
	WebElement list;

	// PageFactory
	public Homepage() {
		PageFactory.initElements(driver, this);
	}

	// Actions
	public String getTitle() {
		return driver.getTitle();
	}

	public void selectFlightButtonAndRoundTrip() {
		buttonFlightButton.click();
		radioButtonRoundTrip.click();
	}

	public void selectBusinessClass() {
		dropdownEco.click();
		driver.findElement(By.xpath("//li[text()='" + prop.getProperty("class") + "']")).click();
	}

	public void selectFromAndToValue() {
		By fromValue = By.xpath("(//span[contains(text(),'" + prop.getProperty("from") + "')])[1]");
		By toValue = By.xpath("(//span[contains(text(),'" + prop.getProperty("to") + "')])[1]");
		By fromTextBox = By.xpath("(//a[@class='select2-choice'])[2]");
		By toTextBox = By.xpath("(//a[@class='select2-choice'])[3]");

		driver.findElement(fromTextBox).click();
		WebElement b = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(fromTextBox));
		b.sendKeys(prop.getProperty("from"));
		driver.findElement(fromValue).click();

		driver.findElement(toTextBox).click();
		WebElement g = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(toTextBox));
		g.sendKeys(prop.getProperty("to"));
		driver.findElement(toValue).click();
	}

	public void setDepatureAndReutrnDate() {
		String[] date = TestUtil.date();
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].setAttribute('value','" + date[0] + "');", calenderDeparture);
		js.executeScript("arguments[0].setAttribute('value','" + date[1] + "');", calenderReturn);

	}

	public void numberOfPassengerAndClickOnSave() {
		String[] name=new String[]{"Adults ","Child ","infant "};
		for(int i=0;i<name.length;i++){
			driver.findElement(By.xpath("//div[@class='row no-gutters']//descendant::label[text()='"+name[i]+"']//following-sibling::div//child::button[@class='btn btn-white bootstrap-touchspin-up ']")).click();
		}
//		adults.click();s
//		child.click();
//		infant.click();
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", buttonSearch);
	}

	public boolean list() {
		List<WebElement> list = driver.findElements(By.xpath("//ul[@id='LIST']/li"));
		boolean flag = false;
		if (list.size()>= 0) {
			flag = true;
		}
		return flag;
	}
}

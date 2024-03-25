

import java.io.File;
import java.util.*;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ApplyLeave {
	WebDriver driver;
	@Given("User is on Login Page")
	public void user_is_on_login_page() {
		System.out.println("Given Executed...");
		  System.setProperty("webdriver.chrome.driver","C:/Users/Keerthi Vardhani/Downloads/chromedriver-win64/chromedriver-win64/chromedriver.exe");
		  driver= new ChromeDriver();					
		  driver.manage().window().maximize();			
		  driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}

	@When("User enters valid username and password")
	public void user_enters_valid_username_and_password() throws IOException {
		String username = "";
	    String password = "";

	    try {
	        FileInputStream fStream = new FileInputStream(new File("utils/test_data.xlsx"));
	        XSSFWorkbook workbook = new XSSFWorkbook(fStream);
	        XSSFSheet sheet = workbook.getSheetAt(0); // assuming data is in the first sheet
	        XSSFRow row = sheet.getRow(1); // assuming data starts from the second row (1-indexed)

	        username = row.getCell(0).getStringCellValue();
	        password = row.getCell(1).getStringCellValue();

	        fStream.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    // Find username and password fields on the webpage and enter the retrieved values
	    WebElement usernameField = driver.findElement(By.name("username"));
	    WebElement passwordField = driver.findElement(By.name("password"));

	    usernameField.sendKeys(username);
	    passwordField.sendKeys(password);
	}


	@When("User clicks on Login button")
	public void user_clicks_on_login_button() {
		WebElement loginButton = driver.findElement(By.cssSelector(".oxd-button.oxd-button--medium.oxd-button--main.orangehrm-login-button"));
	    loginButton.click();
	   String expectedURL="https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
	   String actualURL=driver.getCurrentUrl();
	   System.out.println("ActuaURL: "+actualURL);
	   Assert.assertEquals(expectedURL,actualURL);
	   
	}

	@Then("User should be logged in successfully")
	public void user_should_be_logged_in_successfully() {
	    System.out.println("User has loged in successfully");
	}
	

}

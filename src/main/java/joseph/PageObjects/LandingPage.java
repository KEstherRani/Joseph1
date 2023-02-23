package joseph.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import joseph.Abstractcomp1.Abstractcomponents1;

public class LandingPage extends Abstractcomponents1 {
	
	public WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		//Initialization code in constructor
		this.driver=driver;
		//Initialize page factory
		PageFactory.initElements(driver, this);
		
	}
	
	//WebElement Usermail=driver1.findElement(By.id("userEmail"));
	//Page Factory constructs the above line from below syntax
	
	@FindBy(id="userEmail")
	WebElement Usermail;
	
	@FindBy(id="userPassword")
	WebElement Userpswrd;

	@FindBy(id="login")
	WebElement lsubmit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement Errortoast;
	//classname=div[aria-label='Incorrect email or password.']
	
	public ProductCatalogue loginapp(String Email, String Pswrd)
	{
		System.out.println("*****************");
		Usermail.sendKeys(Email);
		Userpswrd.sendKeys(Pswrd);
		lsubmit.click();
		ProductCatalogue PC=new ProductCatalogue(driver);
		return PC;
	}
	public String getErrorMsg(){
		waitforWebElementTOAppear(Errortoast);
	return	Errortoast.getText();
	}
	
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client/");

	}
	
}

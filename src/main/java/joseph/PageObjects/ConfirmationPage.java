package joseph.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import joseph.Abstractcomp1.Abstractcomponents1;

public class ConfirmationPage extends Abstractcomponents1 {
	
	public WebDriver driver1;

	public ConfirmationPage(WebDriver driver)
	{
		super(driver);
		this.driver1=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className="hero-primary")
	WebElement TKmsg;
	
	public String getConfrimationMSG()
	{
		return TKmsg.getText();
	}
	

}

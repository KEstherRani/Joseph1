package joseph.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import joseph.Abstractcomp1.Abstractcomponents1;

public class CheckOutPage extends Abstractcomponents1 {
	
	public WebDriver driver1;
	
	public CheckOutPage(WebDriver driver)
	{
		super(driver);
		this.driver1=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement SelectCountry;
	
	@FindBy(css="section button")
	List<WebElement> DrpdwnOptions;
	
	@FindBy(xpath="//div[@class='actions']/a")
	WebElement submitorderBtn;
	
	
	By DrpdownMenu=By.className("ta-results");
	
	public void fillTheForm(String CountryName)
	{
		SelectCountry.sendKeys("ind");
		waitforElementTOAppear(DrpdownMenu);
		WebElement Indoption=DrpdwnOptions.stream().filter(s->s.getText().equalsIgnoreCase(CountryName)).findFirst().orElse(null);
		Indoption.click();
		
	}
	
	public ConfirmationPage PlaceOrderSubmit()
	{
		submitorderBtn.click();
		ConfirmationPage ConfPage=new ConfirmationPage(driver1);
		return ConfPage;
	}

}

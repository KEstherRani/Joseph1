package joseph.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import joseph.Abstractcomp1.Abstractcomponents1;

public class OrdersPage extends Abstractcomponents1 {

public WebDriver driver1;
	
	public OrdersPage(WebDriver driver)
	{
		super(driver);
		//Initialization code in constructor
		this.driver1=driver;
		//Initialize page factory
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(id="userEmail")
	WebElement Usermail;
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> Orderedele;
	
	public boolean verifyOrderDisplay(String ProductName)
	{
		Boolean b=Orderedele.stream().anyMatch(s->s.getText().equalsIgnoreCase(ProductName));
		return b;
	}
	
	
}

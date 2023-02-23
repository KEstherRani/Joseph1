package joseph.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import joseph.Abstractcomp1.Abstractcomponents1;

public class CartPage extends Abstractcomponents1 {
	
	public WebDriver driver1;
	
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver1=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="li h3")
	private List<WebElement> cartitems;
	
	@FindBy(css=".totalRow button")
	private WebElement checkOutEle;
	
	public boolean verifyIfProductIsinCart(String ProductName){
		
		Boolean b=cartitems.stream().anyMatch(s->s.getText().equalsIgnoreCase(ProductName));
		return b;
	}
	
	public CheckOutPage GotoCheckOut()
	{
		checkOutEle.click();
		CheckOutPage COP=new CheckOutPage(driver1);
		return COP;
	}

}

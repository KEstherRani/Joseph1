package joseph.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import joseph.Abstractcomp1.Abstractcomponents1;

public class ProductCatalogue extends Abstractcomponents1 {
	
	public WebDriver driver1;
	
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		//Initialization code in constructor
		this.driver1=driver;
		//Initialize page factory
		PageFactory.initElements(driver, this);
		
	}
	
	//WebElement Usermail=driver1.findElement(By.id("userEmail"));
	//Page Factory constructs the above line from below syntax
	
	//List<WebElement> Cards=driver1.findElements(By.cssSelector(".card-body"));
	@FindBy(css=".card-body")
	List<WebElement> products;
	
	@FindBy(className="ng-animating")
	WebElement spinner;
	
	By productsBy= By.cssSelector(".card-body");
	By addtoCart= By.cssSelector(".fa-shopping-cart");
	By toastMesage=By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList(){
		waitforElementTOAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String ProductName){
		
		WebElement prod=getProductList().stream().filter(s->s.findElement(By.cssSelector("h5 b")).getText().contains(ProductName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addToCart(String productName) throws InterruptedException{
		
		WebElement prod=getProductByName(productName);
		prod.findElement(addtoCart).click();
		waitforElementTOAppear(toastMesage);
		waitforElementToDisappear(spinner);
	}

	
}

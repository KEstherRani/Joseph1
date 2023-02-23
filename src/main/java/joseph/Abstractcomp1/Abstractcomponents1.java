package joseph.Abstractcomp1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import joseph.PageObjects.CartPage;
import joseph.PageObjects.OrdersPage;

public class Abstractcomponents1 {

	public WebDriver driver1;
	
	public Abstractcomponents1(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver1=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="[routerlink*='cart']")
	WebElement CartBtn;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement OrdersBtn;
	
	public CartPage goToCart(){
		
		waitforWebElementTOAppear(CartBtn);
		CartBtn.click();
		CartPage CP=new CartPage(driver1);
		return CP;
		
	}
	public OrdersPage goToOrders(){
		
		OrdersBtn.click();
		OrdersPage OP=new OrdersPage(driver1);
		return OP;
		
	}
	

	public void waitforElementTOAppear(By FindBy) {
		WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
	}
	
	public void waitforWebElementTOAppear(WebElement FindBy) {
		WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(FindBy));
	}
	
	public void waitforElementToDisappear(WebElement Ele) throws InterruptedException{
		
		Thread.sleep(1000);
//		WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.invisibilityOf(Ele));
	}

}

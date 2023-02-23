package joseph.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import joseph.PageObjects.LandingPage;

public class Script1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

String ProductName="ZARA COAT 3";
		
		WebDriver driver1=new ChromeDriver();
		driver1.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver1.manage().window().maximize();
		
		driver1.get("https://rahulshettyacademy.com/client/");

		LandingPage LP=new LandingPage(driver1);
		
		
		driver1.findElement(By.id("userEmail")).sendKeys("jsunnyjoseph@gmail.com");
		driver1.findElement(By.cssSelector("#userPassword")).sendKeys("J8o5s5e3$");
		driver1.findElement(By.id("login")).click();
		WebDriverWait wait=new WebDriverWait(driver1, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".card-body")));
		
		List<WebElement> Cards=driver1.findElements(By.cssSelector(".card-bodyqwe"));
//		for(WebElement card: Cards)
//		{
//			if(card.findElement(By.cssSelector("h5 b")).getText().contains("ZARA"))
//			{
//			card.findElement(By.cssSelector(".fa-shopping-cart")).click();
//			}
//		}
		
		//Using Streams API
		WebElement els=Cards.stream().filter(s->s.findElement(By.cssSelector("h5 b")).getText().contains(ProductName)).findFirst().orElse(null);
		els.findElement(By.cssSelector(".fa-shopping-cart")).click();
		
		//wait until toast is seen
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//wait until anim,ation disappears
		wait.until(ExpectedConditions.invisibilityOf(driver1.findElement(By.className("ng-animating"))));
		//Thread.sleep(2000);
		driver1.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li h3")));

		List<WebElement> cartitems=driver1.findElements(By.cssSelector("li h3"));

		Boolean b=cartitems.stream().anyMatch(s->s.getText().equalsIgnoreCase(ProductName));
		Assert.assertTrue(b);
		
		driver1.findElement(By.cssSelector(".totalRow button")).click();
		
		driver1.findElement(By.cssSelector(" [placeholder='Select Country']")).sendKeys("ind");
		//auto suggestive dropdown

		//Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(driver1.findElement(By.className("ta-results"))));

		List<WebElement> drpdwnoptions=driver1.findElements(By.cssSelector("section button"));
		
		WebElement Indoption=drpdwnoptions.stream().filter(s->s.getText().equalsIgnoreCase("India")).findFirst().orElse(null);
		Indoption.click();
		
		driver1.findElement(By.xpath("//div[@class='actions']/a")).click();
		
		String str1=driver1.findElement(By.className("hero-primary")).getText();
		Assert.assertEquals(str1, "THANKYOU FOR THE ORDER.");
		System.out.println(driver1.findElement(By.className("hero-primary")).getText());
		
		List<WebElement> orderIds=driver1.findElements(By.xpath("(//td[@class='em-spacer-1'])[4]/label"));
		
		
		System.out.println("*************"); 
			driver1.close();
		
	}

}

package joseph.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import joseph.PageObjects.CartPage;
import joseph.PageObjects.CheckOutPage;
import joseph.PageObjects.ConfirmationPage;
import joseph.PageObjects.LandingPage;
import joseph.PageObjects.OrdersPage;
import joseph.PageObjects.ProductCatalogue;
import joseph.TestComponents.BaseTest;

public class submit1 extends BaseTest {

	String ProductName = "ZARA COAT 3";

	@Test(dataProvider="getData", groups={"purchaseOrder"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalogue PC = LP.loginapp(input.get("email"), input.get("password"));
		List<WebElement> Cards = PC.getProductList();
		PC.addToCart(input.get("product"));
		CartPage CP = PC.goToCart();
		boolean b = CP.verifyIfProductIsinCart(input.get("product"));
		Assert.assertTrue(b);
		
		CheckOutPage COP = CP.GotoCheckOut();
		COP.fillTheForm("india");
		ConfirmationPage ConfirmPage = COP.PlaceOrderSubmit();
		Assert.assertEquals(ConfirmPage.getConfrimationMSG(), "THANKYOU FOR THE ORDER.");
		System.out.println(ConfirmPage.getConfrimationMSG());
		System.out.println("*************");
	}
	
	@Test(dependsOnMethods={"submitOrder"})
	public void OrederHistorytest(){
		
		ProductCatalogue PC = LP.loginapp("jsunnyjoseph@gmail.com", "J8o5s5e3$");
		OrdersPage OP=PC.goToOrders();
		Assert.assertTrue(OP.verifyOrderDisplay(ProductName));
		
	}
	

	
	@DataProvider
	public Object[][] getData() throws IOException{
		
//		HashMap<String, String> map=new HashMap<String, String>();
//		map.put("email", "jsunnyjoseph@gmail.com");
//		map.put("password", "J8o5s5e3$");
//		map.put("product", "ZARA COAT 3");
//		
//		HashMap<String, String> map1=new HashMap<String, String>();
//		map1.put("email", "jsunnyjoseph1@gmail.com");
//		map1.put("password", "J8o5s5e3$1");
//		map1.put("product", "ADIDAS ORIGINAL");
		
		List<HashMap<String, String>> data=	getJsonDatatoHashmap(System.getProperty("user.dir")+"\\src\\test\\java\\joseph1\\Data\\PurchaseOrder.json");
	return	new Object[][] {{data.get(0)},{data.get(1)}};
		
	}

}

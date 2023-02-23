package joseph.Tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import joseph.PageObjects.CartPage;
import joseph.PageObjects.ProductCatalogue;
import joseph.TestComponents.BaseTest;
import joseph.TestComponents.RetryFM;

public class ErrorValidation extends BaseTest{
	
	@Test(groups={"ErrorHandling"}, retryAnalyzer=RetryFM.class)
	public void errorMsg(){
		
		LP.loginapp("jsunnyjoseph@gmail.com", "J8o5e3$");
		Assert.assertEquals("Incorrect email  password.", LP.getErrorMsg());
	}
	
	@Test
	public void ProductErrorValidation() throws InterruptedException{
		
		String ProductName = "ZARA COAT 3";
		ProductCatalogue PC = LP.loginapp("jsunnyjoseph1@gmail.com", "J8o5s5e3$1");
		List<WebElement> Cards = PC.getProductList();
		PC.addToCart(ProductName);
		CartPage CP = PC.goToCart();
		boolean b = CP.verifyIfProductIsinCart(ProductName);
		Assert.assertTrue(b);
	}

}

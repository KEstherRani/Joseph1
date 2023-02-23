package joseph.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import joseph.PageObjects.CartPage;
import joseph.PageObjects.CheckOutPage;
import joseph.PageObjects.ConfirmationPage;
import joseph.PageObjects.LandingPage;
import joseph.PageObjects.ProductCatalogue;
import joseph.TestComponents.BaseTest;


public class StepDefinitionImpl extends BaseTest {
	
	public LandingPage LP;
	public ProductCatalogue PC;

	public ConfirmationPage ConfirmPage;
	
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException
	{
		
		LP=	launchApplication();
	}
	
	@Given("^Login with (.+) and (.+)$")
	public void Login_with_username_and_password(String Username, String Password)
	{
	      PC = LP.loginapp(Username, Password);
	}
	
   @When("^Add (.+) to cart$")
   public void Add_to_Cart(String Productname) throws InterruptedException
   {
	   List<WebElement> Cards = PC.getProductList();
	   PC.addToCart(Productname);
   }
   
   @When("^Checkout (.+) and submit order$")
   public void Checkout_Product_and_submit_order(String Productname)
   {
	    CartPage CP = PC.goToCart();
		boolean b = CP.verifyIfProductIsinCart(Productname);
		Assert.assertTrue(b);
		
		CheckOutPage COP = CP.GotoCheckOut();
		COP.fillTheForm("india");
		 ConfirmPage = COP.PlaceOrderSubmit();
   }
   
   @Then("Verify the Error message {string}")
   public void Verify_the_Error_message(String expectedMSG)
   {
	   Assert.assertEquals(ConfirmPage.getConfrimationMSG(), expectedMSG);
	   driver.close();
   }

   @Then("Verify Error message {string}")
   public void Verify_Error_message(String Errormsg)
   {
		Assert.assertEquals(Errormsg, LP.getErrorMsg());
   }
	
	
	

}


@tag
Feature: Purchase the order from Ecommerce website
  				Description
	
	Background: 
		Given I landed on Ecommerce page

  @Regression
  Scenario Outline: Positive test of submitting order
    Given Login with <name> and <Password>
    When Add <ProductName> to cart
    And Checkout <ProductName> and submit order 
    Then Verify the Error message "THANKYOU FOR THE ORDER."

    Examples: 
      | name   								 |Password    | ProductName  |
      | jsunnyjoseph@gmail.com |  J8o5s5e3$ | ZARA COAT 3  |



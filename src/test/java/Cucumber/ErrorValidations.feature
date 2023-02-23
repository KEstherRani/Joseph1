
@tag
Feature: Error validation of login page
  I want to use this template for my feature file


  @Error
  Scenario Outline: Login Negative scenario
    Given I landed on Ecommerce page
    Given Login with <name> and <Password>
    Then Verify Error message "Incorrect email or password."

    Examples: 
      | name   								 |Password    | 
      | jsunnyjoeph@gmail.com  |  J8o5s5e3$ | 
      
      
      
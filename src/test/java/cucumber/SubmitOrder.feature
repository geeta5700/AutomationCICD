
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

 Background:
 Given I landed on Ecommerce Page
  
  @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THe ORDER." message is displayed on Confirmation

    Examples: 
      | name               | password        | productName  |
      | geeta123@gmail.com |  dgTesting1*    | ZARA COAT 3  |
      

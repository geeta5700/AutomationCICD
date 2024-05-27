@tag
Feature: Error Validation
  I want to use this template for my feature file


  @ErrorValidation
  Scenario Outline: Checking negative scenarios for login(Invalid credentials)
    Given I landed on Ecommerce Page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed

   Examples: 
      | name               | password        |
      | geeta123@gmail.com |  dgTesting    |
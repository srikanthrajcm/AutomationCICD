
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

Background:
Given I landed on Ecommerce page

  @tag2
  Scenario Outline: Positive test of purchasing the order
    Given Logged in with username <username> and <password>
    When I add product <productname> to cart
    And Checkout <productname> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page

    Examples: 
      | username  					| password 		|productname|
      | rajkumar1@gmail.com | Srinivasam77 |ZARA COAT 3|

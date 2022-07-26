Feature: SauceDemo Cart feature
  As a User
  I want to see a Product to Cart
  So I can order it

  Scenario: Check cart
    Given I am in Cart page
    When I check my Cart for the product I added
    Then I am able to start the checkout
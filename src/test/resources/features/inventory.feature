Feature: SauceDemo Inventory feature
  As a User,
  I want to see products in Inventory
  So I can add them to Cart

  Scenario: Add to Cart
    Given I am in Inventory page
    When I add a Product to Cart
    Then a Product is added to Cart


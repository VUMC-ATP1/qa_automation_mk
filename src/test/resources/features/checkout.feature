Feature: SauceDemo checkout feature
  As a User
  I want to be able to add my details
  So I can continue checkout

  Scenario: Proceeding to checkout second step - Success
    Given I am in Checkout first step page
    When I input complete details for all three fields
    Then I am able to proceed to Checkout overview and finish checkout

  Scenario: Check whether the form fields in checkout step one are required fields
    Given I am in Checkout first step page
    When I check for required attribute for the form input fields
    Then I see which are required fields

  Scenario Outline: Check checkout form empty field error
    Given I am in Checkout first step page
    When I input incomplete details for '<first_name>', '<last_name>' and '<postal_code>'
    Then I see error message
    Examples:
      | first_name  | last_name | postal_code|
      |             | Kruklis   | 0000       |
      | Martins     |           | 0000       |
      | Martins     | Kruklis   |            |
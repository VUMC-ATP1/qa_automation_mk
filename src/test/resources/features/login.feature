Feature: Login feature
  As a user,
  I want to be able to log in
  So I can purchase products

  Scenario: Success login
    Given I have navigated to login page
    When I login with 'standard_user' and 'secret_sauce'
    Then I am successfully logged in

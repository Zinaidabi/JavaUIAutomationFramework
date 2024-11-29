Feature: The register Flow test suite

  @run
  Scenario: The system redirects the user to Account page after successful registration with valid data
    Given The home Page is displayed
    And Register Page is accessed from the Home Page button
    And  the privacy toggle bar is enabled
    And the register form is populated with data
    When the continueButton is clicked
    Then the URL contains the following keyword "succes"

  @run
  Scenario: The system keeps the user on register page when registering using valid data without accepting the privacy rules
    Given The home Page is displayed
    And Register Page is accessed from the Home Page button
   # And  the privacy toggle bar is enabled
    And the register form is populated with data
    When the continueButton is clicked
    Then the URL contains the following keyword "register"
Feature: Signup member

  Scenario: As a person I want to sign up for the application
    Given I enter my name "Domenique Tilleuil" and email "domenique.tilleuil@gmail.com"
    When I enter the registration information into the system
    Then The user can be found in the repository
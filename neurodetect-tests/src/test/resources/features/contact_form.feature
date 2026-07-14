
Feature: Contact Page and Form
  As a visitor to NeuroDetect
  I want to be able to read contact information and send a query
  So that I can get in touch with the project team

  Background:
    Given I am on the contact page

  Scenario: Contact info card and form are both displayed
    Then the contact information card should be visible
    And the contact form should be visible

  Scenario: Department information is shown in the info card
    Then the department information should be displayed in the info card

  Scenario: GitHub repository link is present
    Then the GitHub repository link should be present

  Scenario: Form fields are marked as required
    Then the name field should be required
    And the email field should be required
    And the message field should be required

  Scenario: Successfully submitting the form shows a confirmation
    When I fill in the contact form with name "Test User" email "test@test.com" and message "Hello team"
    And I submit the contact form
    Then a success confirmation message should be displayed

  Scenario: Clicking Send Another resets the form after submission
    Given I have successfully submitted the contact form
    When I click "Send another message"
    Then the contact form should be visible again
    And the success message should no longer be displayed

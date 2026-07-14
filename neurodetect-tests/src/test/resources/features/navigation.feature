# ============================================================
# Feature: Navigation
# Tests all navbar links, routing, and active-state behaviour
# ============================================================

Feature: Application Navigation
  As a user of NeuroDetect
  I want to be able to navigate between all pages using the navbar
  So that I can access any part of the application easily

  Background:
    Given I am on the home page

  Scenario: Home page loads successfully
    Then the navbar should be visible
    And the page title should contain "NeuroDetect"

  Scenario: Navigate to About Project page
    When I click the "About Project" nav link
    Then I should be on the "/about" page
    And the "About Project" nav link should be active

  Scenario: Navigate to Predict page
    When I click the "Predict" nav link
    Then I should be on the "/analyze" page
    And the "Predict" nav link should be active

  Scenario: Navigate to Supported Disorders page
    When I click the "Supported Disorders" nav link
    Then I should be on the "/disorders" page
    And the "Supported Disorders" nav link should be active

  Scenario: Navigate to Team page
    When I click the "Team" nav link
    Then I should be on the "/team" page
    And the "Team" nav link should be active

  Scenario: Navigate to Contact page
    When I click the "Contact" nav link
    Then I should be on the "/contact" page
    And the "Contact" nav link should be active

  Scenario: Logo click navigates back to home from another page
    Given I have navigated to the "/team" page
    When I click the NeuroDetect logo
    Then I should be on the home page

  Scenario: Home nav link is active on the home page
    Then the "Home" nav link should be active

  Scenario: Home nav link is not active when on another page
    When I click the "About Project" nav link
    Then the "Home" nav link should not be active

  Scenario: Navigating directly to /result without analysis redirects to /analyze
    When I navigate directly to "/result"
    Then I should be on the "/analyze" page

  Scenario Outline: All nav links navigate to their correct routes
    When I click the "<linkName>" nav link
    Then I should be on the "<expectedRoute>" page

    Examples:
      | linkName             | expectedRoute |
      | About Project        | /about        |
      | Predict              | /analyze      |
      | Supported Disorders  | /disorders    |
      | Team                 | /team         |
      | Contact              | /contact      |

# ============================================================
# Feature: Dashboard / Home Page
# ============================================================

Feature: Dashboard Home Page
  As a user visiting NeuroDetect for the first time
  I want to see a clear and informative home page
  So that I understand the platform's purpose and capabilities

  Background:
    Given I am on the home page

  # ---- Hero Section ----

  Scenario: Hero title contains the project name
    Then the hero title should contain "NeuroDetect"

  Scenario: Start Prediction button is visible
    Then the "Start Prediction" button should be visible on the home page

  Scenario: Start Prediction button navigates to the predict page
    When I click the "Start Prediction" button on the home page
    Then I should be on the "/analyze" page

  Scenario: Learn How It Works button navigates to the about page
    When I click the "Learn How It Works" button
    Then I should be on the "/about" page

  # ---- Stat Cards ----

  Scenario: Three stat cards are displayed
    Then exactly 3 stat cards should be displayed

  Scenario: First stat card shows 4 supported disorders
    Then the first stat card value should be "4"

  Scenario: Second stat card shows 3 AI techniques
    Then the second stat card value should be "3"

  # ---- Workflow Section ----

  Scenario: Workflow section heading is visible
    Then the workflow section should be displayed

  Scenario: Five workflow steps are displayed
    Then exactly 5 workflow steps should be displayed

  # ---- Disorder Cards ----

  Scenario: Four disorder cards are displayed on the home page
    Then exactly 4 disorder cards should be displayed on the home page

  Scenario Outline: Each disorder card is displayed
    Then the "<disorder>" disorder card should be visible on the home page

    Examples:
      | disorder      |
      | Epilepsy      |
      | Autism        |
      | Alzheimer     |
      | Schizophrenia |

  Scenario: View Detailed Disorder Profiles link navigates to /disorders
    When I click the "View Detailed Disorder Profiles" link
    Then I should be on the "/disorders" page

  # ---- Why EEG Section ----

  Scenario: Why EEG section is visible
    Then the "Why EEG?" section should be displayed

  Scenario: Four Why EEG feature cards are displayed
    Then exactly 4 "Why EEG" cards should be displayed

# ============================================================
# Feature: Supported Disorders Page
# ============================================================

Feature: Supported Disorders Page
  As a user of NeuroDetect
  I want to read detailed information about each supported disorder
  So that I understand the clinical significance of the predictions

  Background:
    Given I am on the supported disorders page

  Scenario: Page title is displayed
    Then the disorders page title should be visible

  Scenario: Exactly 4 disorder profile cards are shown
    Then exactly 4 disorder profile cards should be displayed

  Scenario Outline: Each disorder has a dedicated profile card
    Then the "<disorder>" profile card should be displayed

    Examples:
      | disorder                  |
      | Epilepsy                  |
      | Autism Spectrum Disorder  |
      | Alzheimer's Disease       |
      | Schizophrenia             |

  Scenario: Every disorder card has a Symptoms section
    Then all disorder cards should have a Symptoms section

  Scenario: Every disorder card has an Early Detection section
    Then all disorder cards should have an Early Detection section

  Scenario: Every disorder card has a Role of EEG section
    Then all disorder cards should have a Role of EEG section

# ============================================================
# Feature: EEG Prediction Page
# Tests all frontend behaviour on the Predict / upload page
# (No backend required — tests only frontend-side logic)
# ============================================================

Feature: EEG Prediction Page - Frontend Behaviour
  As a clinician using NeuroDetect
  I want the prediction page to validate my inputs clearly
  So that I upload the right data in the right format

  Background:
    Given I am on the predict page

  # ---- Layout ----

  Scenario: Both cards are displayed on the predict page
    Then the patient information card should be visible
    And the EEG file upload card should be visible
    And the status banner should be visible

  # ---- Form Validation ----

  Scenario: Upload is blocked when all patient fields are empty
    When I try to upload a file without entering any patient details
    Then a frontend error message should be displayed

  Scenario: Upload is blocked when only Name is missing
    Given I enter age "35" and select gender "Male"
    When I try to upload a valid EEG file
    Then a frontend error message should be displayed

  Scenario: Upload is blocked when only Age is missing
    Given I enter patient name "John Doe" and select gender "Male"
    When I try to upload a valid EEG file
    Then a frontend error message should be displayed

  Scenario: Upload is blocked when only Gender is missing
    Given I enter patient name "John Doe" and age "35"
    When I try to upload a valid EEG file
    Then a frontend error message should be displayed

  Scenario: Error message is dismissed when Clear Error is clicked
    Given I try to upload a file without entering any patient details
    And a frontend error message is displayed
    When I click the "Clear Error" button
    Then the error message should not be visible

  # ---- File Type Validation ----

  Scenario: Uploading a .pdf file is rejected
    Given I have filled in all required patient details
    When I upload a file with extension ".pdf"

  Scenario: Uploading a .txt file is rejected
    Given I have filled in all required patient details
    When I upload a file with extension ".txt"

  Scenario: Uploading a .png file is rejected
    Given I have filled in all required patient details
    When I upload a file with extension ".png"

  # ---- Valid File Types ----

  Scenario Outline: Valid EEG file types are accepted and show file info
    Given I have filled in all required patient details
    When I upload a file with extension "<extension>"

    Examples:
      | extension |
      | .csv      |
      | .edf      |
      | .set      |

  # ---- File Info Card ----

  Scenario: File info card shows file name, size, and upload time
    Given I have filled in all required patient details
    When I upload a valid .csv EEG file

  # ---- Optional Fields ----

  Scenario: Optional phone and remarks fields are present
    Then the optional phone field should be present on the form
    And the optional remarks textarea should be present on the form

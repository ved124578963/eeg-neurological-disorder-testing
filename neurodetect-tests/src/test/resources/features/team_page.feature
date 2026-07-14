# ============================================================
# Feature: Team Page
# ============================================================

Feature: Team Page
  As a visitor to NeuroDetect
  I want to see who built the platform
  So that I can understand the team behind the project

  Background:
    Given I am on the team page

  Scenario: Team page title is displayed
    Then the team page title should be visible

  Scenario: Team page subtitle is displayed
    Then the team page subtitle should be visible

  Scenario: Exactly 4 team member cards are displayed
    Then exactly 4 team member cards should be displayed

  Scenario: Each team card has a member name
    Then each team card should display a member name

  Scenario: Each team card has a GitHub link
    Then each team card should have a GitHub link

  Scenario: Each team card has a LinkedIn link
    Then each team card should have a LinkedIn link

  Scenario: Avatar placeholders are shown on all cards
    Then all team cards should display an avatar placeholder

Feature: Widget Smoke Test
  As an HR admin
  I want to smoke test Widgets
  So I can confirm that they work in the new builds

  Scenario: Create a Widget
    Given I am logged into ESS server: "automation" as "tanishaj"
    When I click the settings link
    And I create a new widget
    Then the widget should be created

  Scenario: Enable a Widget
    Given I am logged into ESS server: "automation" as "tanishaj"
    When I am on the Home Page
    And I enable the widget
    Then the widget should be enabled

  Scenario: Disable a Widget
    Given I am logged into ESS server: "automation" as "tanishaj"
    When I am on the Home Page
    And I disable the widget
    Then the widget should be disabled

  Scenario: Delete a Widget
    Given I am logged into ESS server: "automation" as "tanishaj"
    When I click the settings link
    And I delete the widget "Test"
    Then the "Test" widget should be deleted
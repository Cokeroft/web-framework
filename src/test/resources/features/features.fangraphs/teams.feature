@Teams
Feature: Teams Tests on Fangraphs.com

  @Teams-1
  Scenario: Get the top 5 teams by war
    Given User is on Fantrax Home Page
    When User searches for player "Matt Olson"
    Then User should be on the correct Player Page
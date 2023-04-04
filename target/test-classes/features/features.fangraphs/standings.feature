@Standings
Feature: Standings Tests on Fangraphs.com

  @Standings-1
  Scenario: Get the top 5 teams from 2021
    Given User is on Fantrax Home Page
    When User searches for player "Matt Olson"
    Then User should be on the correct Player Page
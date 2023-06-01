@PlayerSearch
Feature: Player Search Tests for Fangraphs.com

  @PlayerSearch-1
  Scenario: Search for a specific player
    Given User is on Fangraphs Home Page
    When User searches for player "Matt Olson"
    Then User should be on the correct Player Page

  @PlayerSearch-2
  Scenario: Search for a specific player
    Given User is on Fangraphs Home Page
    When User searches for player "Max Kepler"
    Then User should be on the correct Player Page

  @PlayerSearch-3
  Scenario: Search for a specific player
    Given User is on Fangraphs Home Page
    When User searches for player "Yordan Alvarez"
    Then User should be on the correct Player Page

  @PlayerSearch-4
  Scenario Outline: Search for a specific player
    Given User is on Fangraphs Home Page
    When User searches for player "<player>"
    Then User should be on the correct Player Page
    Then Player should have their stats table shown
    Examples:
      | player            |
      | Shohei Ohtani     |
      | Mike Trout        |
      | Hunter Renfroe    |
      | Taylor Ward       |

  @PlayerSearch-Test
  Scenario: Testing
  Given Testing

  # TODO - add more verifications?
  # TODO - maybe split up scenarios and look for the mvp of each league.
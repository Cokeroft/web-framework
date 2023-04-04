@Addition
Feature: First Basic Test

  @A-1
  Scenario: User completes basic addition problem 1
    When the user adds 2 plus 2 together
    Then the total 4 is correct

  @A-2
  Scenario: User completes basic addition problem 2
    When the user adds 12 plus 2 together
    Then the total 14 is correct

    # TODO - Need to figure out how to run the tests now
Feature: Login Tests for Baseball Prospectus

  @ValidCredentials
  Scenario: Login with valid credentials
    Given User is on Home page
    When User enters username as "vegetto712"
    And User enters password as "harben72"
    Then User should be able to login successfully

  # DO NOT RUN THIS, WILL BLOCK YOU
  @InvalidCredentials
  Scenario Outline: Login with invalid credentials
    Given User is on Home page
    When User enters username as '<username>'
    And User enters password as '<password>'
    Then User should be able to see error message '<errorMessage>'

    Examples:
      |username  |password  |errorMessage                    |
      |vegetto712|admin     |is incorrect                    |
      |          |admin123  |username field is empty         |
      |Admin     |          |password field is empty         |
      |          |          |username field is empty         |

  # WHO KNOWS IF THIS WORKS, DON'T RUN EITHER
  @ForgetPassword
  Scenario: Verify Forget Password Functionality
    Given User is on Home page
    When User clicks on Forgot your password link
    Then User should be able to see new page which contains Reset Password button
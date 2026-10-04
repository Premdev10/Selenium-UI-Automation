Feature: Login with Excel Data

  Scenario Outline: Login successfully using credentials from Excel
    Given User is on the login page
    When User fills the login form from given sheetname "<SheetName>" and rownumber <RowNumber>
    And user disables the captcha
    And User clicks on login button from given sheetname "<SheetName>" and rownumber <RowNumber>
    Then User should be navigated to the dashboard

    Examples: 
      | SheetName | RowNumber |
      | LoginData | 0         |

    Scenario Outline: Verify the error message after providing the invalid credentials
      Given User is on the login page
      When User fills the login form from given sheetname "<SheetName>" and rownumber <RowNumber>
      And user disables the captcha
      Then verify the error message after clicking on the login button

      Examples:
        | SheetName | RowNumber |
        | LoginData | 1         |



Feature: Verify the home page functionality

#  Scenario Outline: Verify the transfer functionality under the banking section
#    Given User is on the login page
#    When User fills the login form from given sheetname "<SheetName>" and rownumber <RowNumber>
#    And user disables the captcha
#    And User clicks on login button from given sheetname "<SheetName>" and rownumber <RowNumber>
#    Then User should be navigated to the dashboard
#    And user clicks on the banking section
#    Then validate user completes the transfer request with amount <amount>
#
#    Examples:
#      | SheetName | RowNumber | amount |
#      | LoginData | 0         | 100    |
#
#  Scenario Outline: Verify the native alert functionality
#    Given User is on the login page
#    When User fills the login form from given sheetname "<SheetName>" and rownumber <RowNumber>
#    And user disables the captcha
#    And User clicks on login button from given sheetname "<SheetName>" and rownumber <RowNumber>
#    Then User should be navigated to the dashboard
#    And user clicks on the alert "<alerttype>" button
#
#
#    Examples:
#      | SheetName | RowNumber |alerttype|
#      | LoginData | 0         |confirm   |
#      | LoginData | 0         |native   |
#      | LoginData | 0         |prompt   |

  Scenario Outline: Verify the beneficiary added successfully under the banking section
    Given User is on the login page
    When User fills the login form from given sheetname "<SheetName>" and rownumber <RowNumber>
    And user disables the captcha
    And User clicks on login button from given sheetname "<SheetName>" and rownumber <RowNumber>
    Then User should be navigated to the dashboard
    And user clicks on the banking section
    And user clicks on the beneficiary tab
    Then verify beneficiary added successfully

    Examples:
      | SheetName | RowNumber |
      | LoginData | 0         |



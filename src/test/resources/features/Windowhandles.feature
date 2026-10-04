Feature: Validate the multiple window functionality

  @url_window
  Scenario Outline: Verify the content in the new tab opened
    Given User is on the multiple windows page
    When user clicks on the "<link>"
    And user navigates to the new tab
    Then verify the content in the new tab

    Examples:
      |link|
      |New Browser Tab|

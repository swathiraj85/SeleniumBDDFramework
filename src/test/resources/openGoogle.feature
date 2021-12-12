#Author: Swathiraj Agaram Venkatavaradan

@google_home
Feature: Test the functionality of Google home page

  @search_google
  Scenario: Search for an keyword on chrome,edge browser
    Given I open the google application on "chrome" browser
    When I enter the below search text
    |text|
    |hello|
    And I click the submit button
    Then I verify that search results for "" is displayed
    
 
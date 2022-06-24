#Author: Swathiraj Agaram Venkatavaradan

######################################################################################################
#                                       User Story  
# As a Product Owner,I want to confirm that the news from https://www.theguardian.com/tone/news 
# is valid using the google search so that I can build a news validation site to prevent fake news.
# 
######################################################################################################

@guardian_news_validation
Feature: Validate the news articles published on https://www.theguardian.com/tone/news website against google search results

 @guardian_first_news_validinput
  Scenario: Validate the first news article published on https://www.theguardian.com/tone/news against google search results
     Given I open the "guardian" application on "chrome" browser
     And I accept the cookies on guardian home page
     When I fetch the content of "1st" news article   
     And I close the browser
     Given I open the "google" application on "chrome" browser
     And I accept the cookies on google home page
     When I search other news article for the content fetched from "guardian"
     And I click the submit button    
     Then I verify that matching search results are displayed
     And I validate that atleast "2" words from the search results matches the search string
     And I close the browser
     
  @guardian_news_validation_invalidsearch
  Scenario: Validate the first news article published on https://www.theguardian.com/tone/news against an invalid input on google search results
     Given I open the "guardian" application on "chrome" browser
     And I accept the cookies on guardian home page
     When I fetch the content of "1st" news article     
     And I close the browser
     Given I open the "google" application on "chrome" browser
     And I accept the cookies on google home page     
     When I enter the below search text
    	|search_text|
    	|#$%#$|    
     And I click the submit button       
     And I verify that matching search results are displayed
     And I validate that atleast "0" words from the search results matches the search string
     And I close the browser
   
   @guardian_news_validation_blanksearch
  Scenario: Validate the first news article published on https://www.theguardian.com/tone/news against a blank input on google search results
     Given I open the "guardian" application on "chrome" browser
     And I accept the cookies on guardian home page
     When I fetch the content of "1st" news article     
     And I close the browser
     Given I open the "google" application on "chrome" browser
     And I accept the cookies on google home page     
     When I enter the below search text
    	|search_text|
    	||    
     And I click the submit button    
     Then I verify that no search results are displayed             
     And I close the browser
   
   @guardian_page_2_second_news_validinput
  Scenario: Validate the second news article published on page 2 of https://www.theguardian.com/tone/news against google search results
     Given I open the "guardian" application on "chrome" browser  
     And I accept the cookies on guardian home page   
     When I click on the next page
     And I fetch the content of "2nd" news article   
     And I close the browser
     Given I open the "google" application on "chrome" browser
     And I accept the cookies on google home page
     When I search other news article for the content fetched from "guardian"
     And I click the submit button    
     Then I verify that matching search results are displayed
     And I validate that atleast "2" words from the search results matches the search string
     And I close the browser
     
   @guardian_standard_ui_validation
  Scenario: Validate the standard UI elements of https://www.theguardian.com/tone/news website
  Given I open the "guardian" application on "chrome" browser  
     And I accept the cookies on guardian home page   
     Then I verify that "News" tab is displayed
     Then I verify that "Opinion" tab is displayed
     Then I verify that "Sport" tab is displayed
     Then I verify that "Culture" tab is displayed
     Then I verify that "Lifestyle" tab is displayed
     And I close the browser
    
            
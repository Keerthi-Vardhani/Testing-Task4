Feature: Login in the page

Scenario Outline: Login with valid credentials
    Given User is on Login Page
    When User enters valid username and password
    And User clicks on Login button
    Then User should be logged in successfully
    

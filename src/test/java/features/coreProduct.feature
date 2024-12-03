Feature: Core Product

  Background:
    Given User is on Core Product Page
  @regression
  Scenario: Verify jacket and store in file
    When User click on Mens submenu
    Then User redirect to Golden State Warriors Men page
    When User select Jackets from all department section
    Then User is able to see all Jackets on page

  @regression
  Scenario: Verify total number of video feeds
    When User click on New & Features under metaBallsMenu
    Then User is able to see news and videos on page
    Then User count total number of video on page

 
 
 
 
 
 
 
 
 
 
 
















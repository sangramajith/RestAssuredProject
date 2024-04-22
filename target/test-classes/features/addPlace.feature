#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Vaidating Place api functionalities
 

  @AddPlace @regression
  Scenario Outline: Verify if place is successfuly added using add place api
    Given Add Place playload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceApi" api with "post" call
    Then verify status code 200
    And verify "status" in response body is "OK"
    And verify "scope" in response body is "APP"
    And verify place_Id created maps to "<name>" using "getPlaceApi"
    
    Examples:
    |name|language|address|
    |testTN|tamil|TamilNadu|
    |testKA|kannada|Karnataka|
    
  @DeletePlace @regression
  Scenario: Delete place created using delete call
    Given delete place api with payload
    When user calls "deletePlaceApi" api with "post" call
    Then verify status code 200
    And verify "status" in response body is "OK"
  

  #@tag2
  #Scenario Outline: Title of your scenario outline
    #Given I want to write a step with <name>
    #When I check for the <value> in step
    #Then I verify the <status> in step
#
    #Examples: 
      #| name  | value | status  |
      #| name1 |     5 | success |
      #| name2 |     7 | Fail    |

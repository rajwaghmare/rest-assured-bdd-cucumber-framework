Feature: Validate using get user api user able to received user information successfully

  Scenario: Verify user getting user details using GET API
    Given User set the base URI for GET request
    When User sets the request header Connection for GET request
    Then User sends GET request to collect user details
    And User should receive status code should be 200
    Then User verify the response body contains user details
Feature: Validate using update user api user able to update user details successfully

  Scenario: Verify user able to update using PUT API request
    Given User sets the base URI for PUT request
    And User sets the request header Content-Type for PUT request
    And User sets the request body for updating user details
    When User sends PUT request
    Then User should receive updated status code 200
    And Update user response message should be "User updated successfully"
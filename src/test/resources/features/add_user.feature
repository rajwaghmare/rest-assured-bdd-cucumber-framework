Feature: Validate using create user api user able to create user id successfully

  Scenario: Verify user creation using POST API
    Given User sets the base URI
    And User sets the request header Content-Type
    And User sets the request body for creating user
    When User sends POST request to "/api/user/addUser/"
    Then User should receive status code 201
    And Response message should be "User created successfully"
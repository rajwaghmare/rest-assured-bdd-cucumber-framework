Feature: API Chaining

  Scenario: Create, Get, Update and Delete User
    Given User creates a new user
    When  User retrieves the created user
    And   User updates the user details
    Then  User deletes the user
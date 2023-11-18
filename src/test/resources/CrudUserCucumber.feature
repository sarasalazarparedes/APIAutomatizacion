Feature: Project Clean
  Scenario: As user I want to enter to the application
    Given I don't need authentication
    When I send POST request "/api/user.json" with body
    """
    {
      "Email": "sara4@gmail.got",
      "Password": "12345",
      "FullName": "Sara Salazar"
    }
    """
    Then the response code should be 200
    And the attribute "Email" should be "sara4@gmail.got"
    And save "Email" in the variable "Email"
  Scenario: como usuario quiero verificar el crud del project por api
    Given using token in todo.ly
    When I send POST request "/api/projects.json" with body
    """
    {
      "Content":"Cucumber3",
      "Icon":1
    }
    """
    Then the response code should be 200
    And the attribute "Content" should be "Cucumber3"
    And save "Id" in the variable "$ID_PROJECT"
    When I send PUT request "/api/projects/$ID_PROJECT.json" with body
    """
    {
      "Content":"Cucumber2"
    }
    """
    Then the response code should be 200
    And the attribute "Content" should be "Cucumber2"
    When I send DELETE request "/api/projects/$ID_PROJECT.json" with body
    """
    {
      "Content":"Cucumber2"
    }
    """
    Then the response code should be 200
    And the attribute "Content" should be "Cucumber2"
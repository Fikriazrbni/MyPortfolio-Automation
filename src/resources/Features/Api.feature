Feature: ApiTesting

  Scenario: petStore not PStore
    Given post to create new pet
    Then  I get a response 200 and the pet ID
    And a get request with pet ID
    Then I get a response 200 and the pet name
    And post to create an order from store
    Then get detail of order
    And delete an order
    And create a new user
    Then login with new user
    Then logout
    Then delete user
    And login with deleted user
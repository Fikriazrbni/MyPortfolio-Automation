Feature: Web Deal Test

  Scenario: Login With Invalid Credential
    Given open url
    When  login with invalid credential
    Then failed to login with error message

    Scenario: Login With Valid Credential
      Given open url
      When login with valid credential
      Then login success with valid information
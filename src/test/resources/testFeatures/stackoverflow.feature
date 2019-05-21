Feature: Stackoverflow test

  Scenario: Check home page for articles
    Given Stackoverflow home page
    When Accept cookies
    Then Search and output articles by:
      | Java    |
      | Python  |
      | Api     |
      | Android |
      | 2013    |
      | json    |
      | Error   |
    And Close browser
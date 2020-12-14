Feature: Success message "You have successfully subscribed to this newsletter." appears after fill in email field

  Scenario:
    Given I am on the Main page of application
    When I fill subscription field with already registered address "ttt@t"
    Then I see success message "You have successfully subscribed to this newsletter." appears
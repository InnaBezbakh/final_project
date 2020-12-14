Feature: Check that 44 languages exists in drop down list including ukrainian

  Scenario:
    Given I am on the Main page of application
    When I open localization drop down list
    Then I see that 44 languages exists in top menu
    And I see that "Українська" exist in list of languages
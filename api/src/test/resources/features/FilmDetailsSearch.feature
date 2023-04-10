Feature: Film Details Search

  @api
  Scenario: Verify Film Details Search
    Given User sends "New Search Request" request to search film with a title
      | search | A New Hope |
    Then API Response "New Search Request" status code is 200
    When User searches person with name "Biggs Darklighter" among the characters for response "New Search Request"
    And User searches starship for character "Biggs Darklighter" and save data in context with reference "Biggs Darklighter's Starship"
    Then Starship "Biggs Darklighter's Starship" class is "Starfighter"
    And Starship "Biggs Darklighter's Starship" pilots list contains
      | Luke Skywalker |

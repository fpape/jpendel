Feature: Create a person

  Scenario: Create a person
    When A person is created
      | FirstName | LastName | BirthDate  | Phone     |
      | Guido     | Dechamps | 01-01-1995 | 059279078 |
    Then The person is listed in the person overview
      | FirstName | LastName | BirthDate  | Phone     |
      | Guido     | Dechamps | 01-01-1995 | 059279078 |




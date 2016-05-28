Feature: Update a person

  Scenario Outline: Update a person
    Given the system contains the persons
      | personUuid | FirstName | LastName | BirthDate  | Phone     |
      | 1          | Guido     | Dechamps | 01-01-1995 | 059279078 |
      | 2          | Mark      | Dechamps | 01-01-1985 | 059279078 |
      | 3          | Pietje    | Puk      | 01-01-1965 | 059279078 |
    When a person with <personUuid> information is updated to
      | FirstName   | LastName   | BirthDate   | Phone   |
      | <FirstName> | <LastName> | <BirthDate> | <Phone> |
    Then the person with <personUuid> has the following infromation
      | FirstName   | LastName   | BirthDate   | Phone   |
      | <FirstName> | <LastName> | <BirthDate> | <Phone> |
    Examples:
      | personUuid | FirstName | LastName | BirthDate  | Phone    |
      | 1          | Pietje    | Puk      | 01-01-2000 | 66678999 |
      | 2          | Guido     | Dechamps | 01-01-2000 | 42667889 |
      | 3          | Mark      | Dechamps | 01-01-2000 | 10101010 |




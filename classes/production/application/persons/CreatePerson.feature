Feature: Create a person

  Scenario: Create a person
    Given no persons are present in the system
    When a person is created
      | FirstName | LastName | BirthDate  | Phone     |
      | Guido     | Dechamps | 01-01-1995 | 059279078 |
    Then the person is listed in the person overview
      | FirstName | LastName | BirthDate  | Phone     |
      | Guido     | Dechamps | 01-01-1995 | 059279078 |

  Scenario: Create same person twice
    Given no persons are present in the system
    When the persons are created
      | FirstName | LastName | BirthDate  | Phone     |
      | Guido     | Dechamps | 01-01-1995 | 059279078 |
      | Guido     | Dechamps | 01-01-1995 | 059279078 |
    Then the persons are listed in the person overview
      | FirstName | LastName | BirthDate  | Phone     |
      | Guido     | Dechamps | 01-01-1995 | 059279078 |
      | Guido     | Dechamps | 01-01-1995 | 059279078 |

  Scenario: Create multiple persons
    Given no persons are present in the system
    When the persons are created
      | FirstName | LastName | BirthDate  | Phone     |
      | Guido     | Dechamps | 01-01-1995 | 059279078 |
      | Mark      | Dechamps | 01-01-1985 | 059279078 |
      | Pietje    | Puk      | 01-01-1965 | 059279078 |
    Then the persons are listed in the person overview
      | FirstName | LastName | BirthDate  | Phone     |
      | Guido     | Dechamps | 01-01-1995 | 059279078 |
      | Mark      | Dechamps | 01-01-1985 | 059279078 |
      | Pietje    | Puk      | 01-01-1965 | 059279078 |


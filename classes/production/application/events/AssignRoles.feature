Feature: Assign roles to an event

  Scenario Outline: Assign a role to an event
    Given an event with uuid "<eventUuid>"
    And a person with uuid "<personUuid>"
    When the user assigns the role <"role"> in the event "<eventUuid>" to the person "<personUuid>"
    Then the event "<eventUuid>" contains the person "<personUuid>" with the role <"role">

    Examples:
      | eventUuid | personUuid    | role      |
      | 1         | tante sidonia | driver    |
      | 2         | suske         | passenger |
      | 3         | wiske         | passenger |
      | 4         | 10-8-2016     | Lokeren   |
      | 5         | 10-12-2016    | Brugge    |

  Scenario: Assign multiple role to an event
    Given an event with uuid "<eventUuid>"
    And a person with uuid "<personUuid>"
    When the user assigns the roles
      | personUuid    | role      |
      | tante sidonia | driver    |
      | suske         | passenger |
      | wiske         | passenger |
      | 10-8-2016     | Lokeren   |
      | 10-12-2016    | Brugge    |
    Then the event "<eventUuid>" has the roles assigned
      | personUuid    | role      |
      | tante sidonia | driver    |
      | suske         | passenger |
      | wiske         | passenger |
      | 10-8-2016     | Lokeren   |
      | 10-12-2016    | Brugge    |


Feature: Create an event

  Scenario Outline: Create an event
    When An event is created with name "<Event name>"
    Then The event with name "<Event name>" is listed in the event overview

    Examples:
      | Event name       |
      |  Pukkelpop       |
      |  Werchter        |
      |  schaaktoernooi  |


  Scenario: Create multiple events
    When Events are created with following names
      |  Pukkelpop        |
      |  Werchter         |
      |  schaaktoernooi   |
    Then Below events are listed in the event overview
      |  Pukkelpop        |
      |  Werchter         |
      |  schaaktoernooi   |
Feature: Create an event

  Scenario Outline: Create an event
    When An event is created with name "<Event name>" for date "<Event date>" in location "<Event location>"
    Then The event with name "<Event name>" is listed in the event overview

    Examples:
      | Event name     | Event date | Event location |
      | Pukkelpop      | 10-12-2016 | Hasselt        |
      | Rock Werchter  | 20-06-2016 | Werchter       |
      | Guido Barbecue | 10-8-2016  | Lokeren        |
      | schaaktoernooi | 10-12-2016 | Brugge         |

#  Scenario Outline: Create double an event
#    Given An event exists with name "<First event name>"
#    When An event is created with name "<Second event name>"
#    Then The event with name "<Event name>" is listed 2 times in the event overview
#    Examples:
#      | First event name | Event name     |
#      | Pukkelpop        | Pukkelpop      |
#      | Rock Werchter    | Rock Werchter  |
#      | Guido Barbecue   | Guido Barbecue |
#      | schaaktoernooi   | schaaktoernooi |


  Scenario: Create multiple events
    When Events are created with following names
      | Pukkelpop      |
      | Werchter       |
      | schaaktoernooi |
    Then Below events are listed in the event overview
      | Pukkelpop      |
      | Werchter       |
      | schaaktoernooi |
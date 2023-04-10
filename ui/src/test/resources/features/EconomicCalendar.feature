Feature: Economic Calendar

  @ui
  Scenario Outline: Verify Economic Calendar Page Elements
    Given User updates browser with browser’s screen resolution "<resolutionSettings>" settings
    When User navigates Home page
    And User accepts cookies settings
    Then Home Page is loaded
    When User clicks the "Research & Education" link at the top menu
    Then The top menu "Research & Education" block is displayed
    When User clicks "Economic Calendar" link in the "Research" section
    Then Economic Calendar page is loaded
    When User selects "Today" on Economic Calendar slider and
    Then Economic Calendar displays "Today" selected date
    When User selects "Tomorrow" on Economic Calendar slider and
    Then Economic Calendar displays "Tomorrow" selected date
    When User selects "Next Week" on Economic Calendar slider and
    Then Economic Calendar displays "Next Week" selected date
    When User selects "Next Month" on Economic Calendar slider and
    Then Economic Calendar displays "Next Month" selected date
    When User clicks "here" link in the "Disclaimer" text block at the Economic Calendar bottom
    Then Risk Warning page is loaded
    When User clicks "here" link in the "Risk Warning" text block at the Risk Warning bottom
    Then "Risk-Disclosures" document was opened in 2 tab
    Examples:
      | resolutionSettings |
      | Maximum            |
      | 1024x768           |
      | 800x600            |
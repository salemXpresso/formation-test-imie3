# language: en
Feature: Currency Calculator form

  Scenario: In order to calculate a currency conversion, the user fill the form and retrieve a correct result

    Given A running platform

    When the user opens the page 'je voyage'
    Then the url on the browser contains 'je-voyage'

    When the user clicks on 'mes outils pratiques'
    And the user clicks on 'convertisseur de devises'
    Then the currency form is displayed

    When the user selects <EUR> for his card
    And the user selects <GBP> for his transaction
    And the user submits the form
    Then the result page is displayed
    And the conversion result is <111.62>

    # Post requisites
    And The user closes his Web browser

Feature: Adding the product to cart

  Scenario: I login with password and add product to the card
    Given I will open a application in "chrome" browser
    Then I login to the application with password "idgad"
    Then I search the product "Round Neck Shirt"
    Then I select the product "Round Neck Shirt" from the search
    Then I click on Add to cart
    Then I click on view cart
    Then I change quantity to "3" in cart
    Then I verify total amount of product is changed as per quantity



  Scenario: I login with password and add multiple product with different size and color to the card
    Given I will open a application in "chrome" browser
    Then I login to the application with password "idgad"
    Then I goto Featured Collection and add first item
    Then I click on Add to cart
    Then I select size as "S"
    Then I click on Add to cart
    Then I select color as "Silver"
    Then I click on Add to cart
    Then I click on view cart
    Then I verify subtotal amount is sum of individual total amount


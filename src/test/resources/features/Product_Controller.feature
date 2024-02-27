
Feature: Product Controller Functionality
@Sanity
Scenario Outline: Verify Product Controller Functionalities

Given I generate  test random value
When  I add  Product Controller <id> and "<Name>" and "<Sku>" and <unitPrice> and <unitsInstock>
Then  I Verify with categoryName,Id and unitPrice of product
Then  I Update product with "<UpdateName>" and "<updateDescription>" and <updateUnitsInstock> and <updateUnitPrice>
Then  I verify get  after update product
Then  I delete product
Then  I get after delete product and verify statusCode 404

Examples:
| id    |Name         |Sku      |unitPrice |unitsInstock |  UpdateName                  | updateDescription   |updateUnitsInstock | updateUnitPrice|
|  1    |Beef_Buffolo |DF0098   |15.00     |   80        |  Purina ONE Beef & Barley    |AB_1092              |       90          |  50.00            |            
|  2    |Milk         |DE1033   | 6.99     |   60        |  Fancy Feast Savory          |DC_4887              |       57          |   98.00           |
|  3    |Dog_Chips    |DF3098   |15.00     |   80        |  Purina ONE Chicken & Brown  |HD_5679              |       34          |   23.00           |
|  4    |Fry_Fish     |DE4033   | 6.99     |   60        |  Beef & Crab Flavor          |HK_8790              |       98          |   45.00           |
|  5    |Beef_Stick   |DF5098   |15.00     |   80        |  Turkey & Venison            |HJ_6749              |       23          |    45.00          |
|  6    |Chicken_Bone |DE6033   | 6.99     |   60        |  Tuna Flavor Cat             |HD_7894              |       34          |    34.00          |








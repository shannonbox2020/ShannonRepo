Feature: Paylocity Benefits Challenge QA Test


  Scenario: Add Employee -- no Discount -- zero Dependents
    Given an Employer
    And I am on the Benefits Dashboard page
    When I select Add Employee
    Then I should be able to enter employee details and save successfully:
      | FName0 | LName0 | 0 |
    And I should see the employee in the table with correct cost benefit calculations:
      | FName0 | LName0 | 0 | 52000.00 | 2000 | 38.46 | 1961.54 |

 Scenario: Add Employee -- no Discount -- one Dependents
    Given an Employer
    And I am on the Benefits Dashboard page
    When I select Add Employee
    Then I should be able to enter employee details and save successfully:
      | FName1 | LName1 | 1 |
    And I should see the employee in the table with correct cost benefit calculations:
      | FName1 | LName1 | 1 | 52000.00 | 2000 | 57.69 | 1942.31 |

 Scenario: Add Employee -- no Discount -- two Dependents
    Given an Employer
    And I am on the Benefits Dashboard page
    When I select Add Employee
    Then I should be able to enter employee details and save successfully:
      | FName2 | LName2 | 2 |
    And I should see the employee in the table with correct cost benefit calculations:
      | FName2 | LName2 | 2 | 52000.00 | 2000 | 76.92 | 1923.08 |
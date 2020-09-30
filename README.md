#Steps to compile
-	Import the project as maven project using IDE like IntelliJ.

#Steps to execute
-	Run the test cases from IntelliJ in files:
	OrderHandlerTest.java

#Questions and assumptions made:
Question1:
Can you confirm if "Live Order Board" summary is per coinType as not clear from sample input/output or problem statement?
Question 2:
Can you provide sample input/output for summary when multiple sell or buy orders for different coin types are received as not clear from sample input/ouput or problem statement?
Question 3:
Can you confirm sample input/ouput for summary if multiple buy and sell orders received as not clear from sample input/output or problem statement?
Note: Based on these initial questions, below assumptions have been made and solution can be refined after discussion with the team.

#Assumptions
- Order quantity and price values are greater than 0.
- Price is in single currency GBP.
- "Live Order Board" summary is assumed to be per coinType.

#Design decisions
-  Create buy and sell as separate strategy using Strategy pattern which can evolve and be tested in isolation.
-  Custom runtime exception could be thrown and documented as part of API specifiation.
-
    //Approach 2 for data structures that may be used- this will depend whether final sums needs to be calculated by getSummary() method on demand.
    //With current implemented approach, as per my understanding of requirements, their does not seem to need to use approach-2 which can be validated with the team and refactoring jiras created(as per feedback).
    //Few thoughts around options for data structures:
    //Declare map for buy side  [price ->  buyOrderSet]
    //private final Map<BigDecimal, Set<Order>> priceToBuyOrdersMap;
    //Declare map for buy side  [price ->  sellOrderSet]
    //private final Map<BigDecimal, Set<Order>> priceToSellOrdersMap;

#Next Steps
-	More input validation checks for file reading/writing can be added. (file empty, containing invalid characters, more than 2 lines per robot etc)
-	Demo and discuss if this meet the MVP functional requirements and create jiras for any non- functional requirements, on currency aspects(if any) that needs to be addressed (if any).
-	Create jiras for more enhancements after demo.
-   Create more unit and integration tests.
-	Create and enhance the test harness for regression testing.


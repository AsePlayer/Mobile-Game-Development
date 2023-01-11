Code can be easily ran online at: https://www.tutorialspoint.com/online_kotlin_compiler.php
----
# Shipping Calculator

You are working on a eCommerce website and need to make a shipping cost calculator based on the order amount.
The store uses the following cost structure:
For orders in the US:
- $75+ orders have free shipping
- orders less than $75 have a shipping fee of 10% of the total order amount.

For international orders, there is a 15% shipping fee, with a maximum of $50. This means that the maximum shipping fee for an international order is $50.

You need to complete the given shippingCost() function, which takes the order amount and a Boolean indicating whether the order is international or not, and returns the shipping cost for that order.
The return amount should be a Double.

Sample Input:
140.0
true

Sample Output:
21.0
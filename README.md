# BrilloConnectzJavaTest
Console application solutions to BrilloConnectz Java Developer Test 

Observations for Pancake shop application:

In the non-concurrent version, the pancake orders for all three users are determined one by one in a sequential manner. The calculations are performed sequentially for each 30-second slot.
In the concurrent version, the pancake orders for all three users are determined simultaneously using a loop. This allows for a more concurrent execution, although not utilizing multithreading or executor services.
Both versions calculate the total number of wasted pancakes and the number of orders not met. The results are printed at the end.
The concurrent version has a slightly more compact code structure as it uses an array to store user orders instead of individual variables.
Both versions should produce the same output and provide the desired functionality, but the concurrent version allows for a more efficient calculation by determining the pancake orders concurrently.

###### Project description.

This is simple application that help solve problems with expensive requests to remote server for find distance between 2 points.
Project include 2 package:
1) Simulator of remote server that include method that find distance (in real project method would be more complex with many additional conditions)
2) The local server that makes requests to remote server

Also project include simple tests.

###### Solution for solve problems with expensive requests.

There is a Spring Cache is used in the project. It helps skip request to remote server and take information from a local cache. But if remote server updated information about points we need to delete our local cache and make request to remote server to take new data.
There is one method that once a week at 10 o'clock checks last time updates and delete local cache if time of remote update later than local updates time (information about last local updates store in special variable).

###### Answer for questions from task#2.

1) How would you test this class?

To test class contains method with expensive requests we need to call them. After that call it one more time and check if this method make request to remote server or get it from a cache. We need to create positive and negative scenarios.

2) After the method above is on production, how would you monitor the performance?

We need to use different tools for monitor the performance. It would be Graphana, Zabbix, Jmeter. Also, we can create for example Benchmark tests.

3) In some cases, we would like to change the provider, for example for tests or different cluster. How can we achieve this?

There are many reasons to change provider. Because of this it is necessary to prepare solutions that will be independent of provider. For example, we can use interfaces for this.


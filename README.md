# Installation

This guide assumes a machine with Java 8 installed. If java 8 is not installed, please visit the [official oracle website](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) and make sure that Java 8 is installed before continuing.

External dependencies are managed via gradle wrapper. Therefore, to compile the project, install dependencies, run all tests and run the project:

* Navigate to the bidder directory (e.g. `~/projects/bidder`)
* Execute the following gradle command: `./gradlew clean test`
* Run the project by executing `./gradlew bootRun`

# Gradle commands

Also, please find below a list with a few gradle commands that might be needed:

* `./gradlew clean`: Clean the project 
* `./gradlew build`: Compile the project
* `./gradlew test`: Compile and test the project
* `./gradlew bootRun`: Run the project

Extensive documentation on gradle wrapper may be found in the [official documentation](https://docs.gradle.org/current/userguide/gradle_wrapper.html)

# Test cases

The end-to-end test cases are included in the test sources, under `com.bluebanana.bidder.acceptance.integration` package.

* Suite `NotBidFeature` covers the cases in which the bidder decides to make no bid:
  * No available campaigns are retrieved from the campaign pool.
  * Campaigns are retrieved from the campaign pool, but none matched the bidding criteria.

* Suite `BidFeature` covers the main case, in which the bidder retrieves the available campaigns from the campaign pool and places a bid for the highest paying campaign that matches the bidding criteria.

* Suite `ErrorHandlingFeature` covers the error handling cases. Specifically, the case in which an error occurs during the campaign retrieval.
Wongnai Backend Assignment
===

Requirements

```
1. Java 8 (JDK 1.8) (either OracleJDK or OpenJDK)
2. Maven 3.5 or up
3. Recommended IDE: IntelliJ 2018.3+ Community Edition or above
4. mvn and java command should work on command prompt or terminal
```

There are 2 parts to the assignment.
1. Warm-up has one short problem.
2. Movie Search System has four problems.

## PART 1 WARM UP
This assignment is designed to test basic java and algorithm and only has 1 point. Let's get started! 

### Problem 1

Open `DigitalRoot.java` and implement a function that calculates the digital root of a positive number.

Run class DigitalRootTest.java to test whether your code is working correctly. All unit tests in this file must pass. You can add more tests to improve coverage.

Digital root definition:
```
The digital root of a non-negative integer is the single-digit value obtained by an iterative process of summing digits, 
on each iteration using the result from the previous iteration to compute the digit sum. 
The process continues until a single-digit number is reached.
// Example:
//   Input : 12345
//   Output : 6 (Because 1 + 2 + 3 + 4 + 5 equals 15 and then 1 + 5 equals 6)
```

## PART 2 Movie Search System

There are 4 problems in this part. Please do the parts sequentially to avoid misunderstandings.

Your work is to complete the business logic and make all unit tests pass. You are allowed to modify any code except the unit tests. We only give you tests for easy cases and you can add more tests to improve coverage.

You are free to deal with unknown cases however you like.

### Problem 1

This problem asks you to implement a movie search system to serve a website where users can query movie information in JSON format via RESTful API.


We have already provided the necessary dependencies, controllers, models, databases, and tests. You have to complete the missing business logic.

First, make sure the server starts and can serve simple requests.
- Open a terminal
- Change directory to the project directory
- Run the following command.
```
mvn spring-boot:run
```
    
After the server started, go to URL http://localhost:8080/movies. You should see the text "Hello World!"

You are ready to begin.

We want a service that searches movies by text and using data from a movie database on the internet. The database provides an api in JSON format. Your task is to write a piece of code that fetches that JSON data and converts it to java object. Let’s not worry about the search function yet.

Note that the online movie data is updated occasionally, so you want to be able to update your stored data from time to time.

Open `MovieDataServiceImpl.java` and complete the `fetchAll` method which downloads data from `MOVIE_DATA_URL`. Then, convert it into the `MoviesResponse` class.  

When you complete the logic, run `MovieDataServiceImplIntegrationTest`. All test cases in this file must pass.


### Problem 2

We have a method to fetch movie data from the previous problem. We will now create a simple web service that allows users to search the movie data by name.

Go to `SimpleMovieSearchService` class then implement a straightforward logic to search movie data that comes from `MovieDataService`.

#### Search Example

Suppose a user specifies "Glorious" as a query text. The result should be:
```
The Glorious Lady
The Glorious Fool
One Glorious Day
One Glorious Night
Glorious Betsy
His Glorious Night
Borat! Cultural Learnings of America for Make Benefit Glorious Nation of Kazakhstan
```

If a user searches for `Glorios XXXYYY`, the api should respond with empty result.

Run `SimpleMovieSearchServiceIntegrationTest` and `MoviesControllerIntegrationTest`. Then, start the server and make sure that API http://localhost:8080/movies/search?q=Glorious works as expected.

### Problem 3
After launching the previous simple search feature, we notice the search is too slow and would benefit from optimization.

We launch an investigation and notice we always download data when we search and the data is too big.

The proposed solution is to download the data only when the server starts and store it in a local database. Then, we search from the stored database. We expect the speed-up to be noticeable since we now don’t have to download a large amount of data every time a user makes a request. Although this might make our data outdated with respect to our source, it is a great trade-off.

Read and edit the following files as you see fit:
1. DatabaseMovieSearchService
2. MovieDataSynchronizer
3. Movie
4. MovieRepository

All tests in `DatabaseMovieSearchServiceIntegrationTest` must pass.

### Problem 4
The time complexity for searching from the database in practice 3 is O(N*M) where N is the total characters of movie titles in the database and M is the total characters of the search query.

We want to make the search faster to support more requests.

One way we can achieve this is to implement an inverted index. The explanation of how an inverted index works is located in `InvertedIndexMovieSearchService`.

This task asks you to implement an in-memory inverted index or some other optimization techniques using java data structures.

Run `InvertedIndexMovieSearchServiceIntegrationTest`. All test must be passed.

If the test passed, please go to `MoviesController` and modify bean from `simpleMovieSearchService` to `invertedIndexMovieSearchService`. This tells our service to use the new search service instead of the old one. Make sure the tests in MoviesControllerIntegrationTest still passes.

Start the server and then perform the search. The search should work as expected.

### Submit Assignment
Make sure all tests pass by running
```
mvn clean install
```
Then start the server and test all the APIs again. Make sure everything works correctly.

Remove all the build files and git files
```
mvn clean
rm -rf .git
```
Compress the file in zip format and submit using the link provided to you in the assignment email.



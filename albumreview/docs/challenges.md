# Challenges in our project
The "restserver" package in the SpringBoot module has the wrong name (RESTserver) in the master branch in GitLab. Rename the package folder to "restserver" to fix this. We tried to fix this, but either Git or GitLab is handelig folder-names weird, or some team memeber had an old version and merged it to master. 

## Challenge 1

### Description of challenges

The first issue was that the REST API was finished late in the project, because the team members responsible for writing the API had a hard time understanding how it should be structured in the project. 

The second issue was that we should have made sure more team-members worked on the API so that a larger part of the team had an understanding of the API. 

The third issue was poor planning of how the REST API could affect other parts of the code-base. 

### Strategies

We tried to get an understanding of REST API, but the sources we used (like the todo-list example) and the SpringBoot documentation was confusing at first. In the end we understood how to make a simple REST API using basic SpringBoot tools.

We have tried to do good planning during the entire project, but because we understood the REST API technology late it was hard to plan for how this would affect the codebase. 

### Reflection

We believe we would have been able to finish the app with the use of the API if the API had been finished a week earlier. So this challenge comes down to poor planning and the fact that more team members should have been working on the API to get familiar with the technology. 

## Challenge 2

### Description
We had big issues with our tests, mainly because of our late implementation of the REST-API. The reason for this becoming a big problem was that while implementing the REST api we had to change a lot of the UI controllers, and add helping methods for both controllers and domain logic. This resulted in bad test coverage for multiple reasons (Our test coverage before these reasons was +80% for both domainLogic and UI (see merge #84)). The UI tests were not working, causing us to comment out all the old tests to help build the maven project. The domain logic tests also dropped significantly in coverage. Mainly because new methods were implemented to help the API.

### Strategies and solutions
Since the application was running fine, we knew that it had to do with the tests. Therefore we tried looking at errors when running the tests. We found out that many of the errors were connected to not being able to add an album (When testing) and that the filepath for writing and loading did not change to testFile.json. We think that fixing this could have helped the tests work.

### Reflection
As in the first challenge, we think the main cause of the sudden test issues is bad planning and time management. We started developing and understanding the REST-API way too late. When this is what we should have started with. When the REST API was finally finished, the changes to be made were simply too many. 

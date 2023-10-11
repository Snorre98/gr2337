# Release 1

## What has been done
### The app
In release 1 we have begun programming a simple app for reviewing music albums. This app consists of domain logic and fxui which are different modules, and has persistance by using a txt-file for saving data. 

At this point the UI for our app looks like this:

![UI project1](<./assets/UI-R1.png>)

The app is configured to use Maven when building the project, and can be run using Maven commands.

###  Project management
Our project is found in GitLab, and we are activly using Git for version controll. 

In GitLab we made one milestone for this project, which is reached when project one is done. We only made one milestone for this project because the app has low complexity and could be covered by a single simple milstone. We decided that this would be a clean way of managing project 1, rather than making multiple milstones. 

We started good work-habits by weekly workmeetings, good use of GitLab project management and descriptive Git use. 
We used Issue boards and tried to make sure that every issue was well defined with a descriptive title. 
We also required every issue to have its own branch, unless this was a major inconvinience. When commiting changes to branch we have marked every commit message with the issue-number the changes was related to and a co-author of the code (if there were multiple). Every commit message also was supposed to be as descriptive as possible. We tried to make small changes for each commit. 
To merge code we required that a team member other than the code author in the merge-request would have to approve the merge-request.




### Testing
We have created some jUnit-tests, which are used to test our domain logic. Test coverage is monitored by Jacoco, and the test report for the core module can be foundt under the core/target/site. The test report for the ui-moduel can be found at ui/target/site. 


## What we would do with more time
### The app
Even though it is a pretty simple project, we didnt have time for everything. We wanted to implement input validation, file validation and add some more exceptions to make the app more complete.


### Project management
On the next assignment we want to work even better together. We will try to make a better plan and make better issues that are easy to understand. This will make us able to avoid making issues that cover the same areas. Furthermore, we would like to improve our group communicatian and take it to the next level.


### Testing 
We could have tried to have larger test coverage. Specificly we wanted to test UI with TestFX, but we did not have time to set this up and write tests. 

The fact that there is not a collective report for core and ui modules is not optimal. We wanted to make an aggreagte test coverage report, but did not have time for this.

## What will change
Setting up the Maven project took alot of time, and was the reason we started writing code the same week as the project deadline. Even though we have learned alot from setting up this project we will make sure to set up the code-project for project 2 in less time. 

For the next assignment, here are some of the things that we are going to change:
- Refine and improve layer architechture
- State persistans with JSON, which requires a JSON parser.
- Implement Eclips Che
- Implement Code-Checker and SpotBug to improve code quality
- Implement a pipeline for checking code pre-merge before beeing able to merge
-Improve test coverage to 60% and have test coverage for all modules/layers
- Update documentation to cover new pluggins, dependencies and other technical solutions needed to run the project.
- Improve use of GitLab tools and Git use. 



# Release 3

For release 3 we have focused on further developing the app. This development includes building and implementing a REST-API, but also expanding our JavaFX-app with more functionality. We also wanted the UI to be a bit more pleasant to the eye.

## New app functionality

For this release, we have expanded the app. We developed the functionality based on the user story that we made the first day of working on this release. We also set up a REST-API.

These are the UML diagrams that we made, showcasing the project.

- ![Package diagram](<./assets/PackageDiagramR3.png>)
- ![Sequence Diagram](<./assets/SequenceDiagramR3.png>)
- ![Class Diagram](<./assets/ClassDiagramR3.png>)

### Domain Logic

We have made 5 “new” classes. Many of them have a lot of the old code from the last release, but we needed to tweak them to better fit the user story. The names of the classes are also better and easier to understand, we now have AlbumList, Album and Review. AlbumList contains a list of Albums, and each Album contains a list of Reviews.

The two sorting classes were also updated to now work with sorting album names and artists.

### State persistence

In the state persistence we replaced the two pairs of original deserializers and serializers, with 3 new pairs. This includes deserializers and serializers for AlbumList, Album and Review. Like in the domain logic, they share a lot of similarities with the original code. The changes include changes in names and data types, as well as implementing a new pair of serializers and deserializers.
We also made changes to WriteToFile and LoadToFile to take in and return an AlbumList instead of an AlbumReviewList, which is no longer used.

### UI

Firstly, we have made a login page where you can enter a username. Then the user enters the album page where the different albums and their respective artist. You can also add a new album, but only if it is a new one. You can also sort the list by artist or album name. If you select an album and then press “Open album”, the app will take you to a page where you can see all the reviews for the selected album. Here you see a list of ratings and the username of the reviewer. You can also add a new review and delete a review, if you have the same username. All of this is connected to the new domain logic.

We decided to tyle the UI with css, making it much nicer to look at and interact with.

## REST-API

We used spring-boot to implement REST-API. The server is set up using spring in the new rest server module. This module is dependent on core and handles requests from a new client module. This makes ui and core independent, and the data between them are handled by the client module (Data access layer) and the restserver (Service layer).
We have chosen to use Spring because Spring is widely used by other java developers and therefore it is well documented. Spring Boot made it easier to set up the Spring server.

## Code Quality

Maintaining a high level of code quality is essential to our development. We have used several tools to ensure this, more on these tools below. Furthermore, by creating thorough tests with high coverage, we can provide a safe and secure code.

### Testing and Jacoco
We were not able to achive the testcoverage we wanted. When we implemented the REST-API in the UI code, all tests that previously ran, were now malfunctioning. This is why we chose to comment out the UI-tests to help the project be run in maven. Despite this, all our functionality has been proven to work, from just running the app.
Maintaining a test coverage of over 80% is important for ensuring the reliability and stability of our codebase. Test coverage quantifies the percentage of code that is exercised by automated tests, offering a measure of the thoroughness of our testing strategy. A high test coverage provides confidence that most paths through the code have been validated, reducing the likelihood of undetected bugs and regressions. It serves as a safety net during development, catching issues early in the development process and allowing for timely corrections. In essence, a test coverage of 80 % not only enhances the overall quality of our software but also improves the development process by fostering a more reliable and maintainable codebase.

####
Issues reagarding testing. Because of implementing the REST API most of the test in ui broke. The only way we could hae fixed this would be by implementing the API alot earlier. 
### Spotbugs

In the last release we had trouble using spotbugs, but in this release we have used it throughout the release. Read release2.md for more information about what we use spotbugs for. We did however exclude "EI_EXPOSE_REP2". This is because it is warning us that one of the controllers could change the state of something in the core. We decided to exclude it because we felt that we had other more pressing issues, and we saw that resolving this bug would take a lot of time.
As of 15.11.2023 we did not have any errors regarding SpotBugs, but because of the changes in the API we got some errors we did not have time to solve, and therefor used the SpotBugs filter to be able to run the app.
### Checkstyle

We have continued to use checkstyle in this release as in the last. Read release2.md for more information about checkstyle.
However, we have improved our usages of javadocs by using parameters and better descriptions. We also had to remove one rule in the configuration because it was not working. It gave an error message about needing line spacing on the parameters, even though it was correct. This is not a major problem so we decided to remove it.
Because of time constraints there are alot of checkstyle issues we were not able to solve. Most of these are related to indentation. This could also be caused by different settings in VScode for the team members. 

## Shippable product

We added code to support a shippable product, which can be opened and run on your computer. This was done by implementing the tools jlink and jpackage.

### jlink

Jlink is used to assemble and optimise the set of modules and their dependencies into a custom runtime image. We implemented jlink in the ui/pom.xml, as a configuration under the javafx-maven-plugin.

### jpackage

The jpackage tool takes a Java application and a Java run-time image as input, and produces a Java application image including all necessary dependencies. We implemented jpackage in the ui/pom.xml as a maven plugin.

## Work routines

We had much more regular meetings, and we still held them, even though not everyone could attend. By doing this, we were able to work evenly and smoothly through the weeks, instead of having very long days now and then. We also improved on our teamwork. With the members focusing on different areas of the project, while at the same time keeping everyone up to date with their progress, and asking for help from each other when we needed it.

Next time, we have to focus even more on doing things in the correct order. We created the rest-api last, but it took way longer than we anticipated so we did not have a lot of time in the end.

## Reflection

We managed to improve how our controller works, fixing one of our problems from R2 reflection. The finished product is also in line with how our user story is, and we completed all the UI and domain logic according to plan.

The group worked well together, focusing on sharing progress, knowledge and information. This made it easier to help each other when needed.

Using spotbugs and checkstyle, we are able to deliver a product that is easier to read and understand. Something which has improved our own understanding of other members' code in the project.

Same as last time, we underestimated how much time this project would take. We spent a lot of time on the UI and domain logic, so when we got to the REST-API, we did not have a lot left.
Because of this the REST API is only partially used, and there is a lot of unused services, as well as commented code found in ui controllers and springboot controllers.

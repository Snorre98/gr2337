# Release 2
For release 2 we have focused on improving code quality, writing good and relevant tests. 
We also have tried to create a better project architecture which separates ui (with app-control), domain logic and data management (for state persistence in the app). 
We wanted to add more functionality to the app, but prioritized raising code quality and project architecture so that it will be easier to add new features in the future. 

## Eclipse Che and virtual environments
In the root README there is a link to open a new Eclipse Che workspace for our Java project. 
Under the Eclipse Che heading there is a description of how to manage the project in Eclipse Che. 
We wanted to run the tests with the test-manager in VScode, but were not able to. 
There might be some configuration in the .vscode for this.
It is possible to run the tests with maven. See the root README for instructions on this.
The dev-file which configurates the Eclipse Che workspace is simple, but might be extended if we need more functionality in Che for Project 3.
We view Eclipse Che as a tool that can help the TAs view our project in a controlled environment. For further use of virtual environments we are planning on running the project in Docker during work on Project 3 and make the same dev-container and dev-file available in Eclipse Che to make sure these environments are the same. This will also be a large advantage for the group, since docker will run in the same dev environment for all team members. The advantage of Docker over Eclipse Che is that it can be run without an internet connection, but there is a larger learning curve to Docker compared to Eclipse Che.

## Modularization
We have divided the project into one parent module (albumreview) and two sub-moduels albumreview.core and albumreview.ui. The parent module is found in root(gr2337). 
If one opens the project in the InteliJ IDE root will be registered as a InteliJ-module due to config in the .idea folder, but this is not an actual java module.
The core module contains the packages `core.domainlogic` and `core.statepersistence`. 
This separates domainlogic and data handling (with serialization) from each other and from the app controller logic.
The ui module contains package `ui.controller`, which contains controller logic. There are also fxml resources for the user interface viewed by the user.
We believe this separates the different scopes (model, view and controller) of the project, but there are some improvements we can implement which will be discussed further down.
The different modules are configured by separate POM files, 
and only implement the plugins and dependencies needed to avoid conflict and inefficiencies regarding this and to 
make the project more readable for people not working on it.

### Architecture
We wanted to separate the project into three different modules; “core”, “controller” and “ui” to keep with a model-view-controller approach. 
Because of the current code we got some dependency complicit and had to move on without implementing this in P2. We will work further on this in P3.
Even though we were not able to reach this goal the project is still separated well into the modules of core and ui (which are further separated into packages), 
and the parent module. 

#### Domain Logic, ui and controller
The main focus on for domain logic was to be able to open up a new JavaFX scene so that the user could be able to see more information about each album. 
However, we decided to focus more on getting the project architecture, but have created an “open” button that opens a new Scene. Pressing the open button will s+p.
`viewutil.openButton` function in the controller that opens a new fxml file that is very simple for now. 
This fxml file only contains a label, but it refers to another controller class than the original one, making it easy to implement more in the future. 
We also want to be able to only be able to open a new scene if an album is actually selected. However, now you can press the button without anything selected. 
This whole functionality will be developed further.
We decided to invest more time on other parts of the project resulting in only a small update to the domain logic. 
We chose to do this as we figured that when we have a good framework on our  project, we can expand the domain logic later in a more correct way.

This is the updated UI:
![UI project2](<./assets/UI-R2.png>)

This is a project diagram for the core logic of the project:

![Project dynamics](<./assets/Project-dynamics.png>)

#### State persistence
We are now using the JSON data format as supposed to a .txt file. The advantage of using JSON is that it is a flexible dataformat that easily can be used for simple
'CRUD' (create, read, update and delete). Java does not have good support for JSON manipulation out of the box. Because of this we are using the Jackson dependencie, which fills in this missing JSON support in Java.
We are now able to Jackson to serialize Java objects into JSON and then deserialize from JSON data into Java objects again. This is essential for dealing with JSON, as the data has to be 'translated' into a readable format.
JSON makes it easier to represent the data in a structured way, making it easier to organize data. 
Furthermore, Jackson provides a structured way to handle errors and exceptions. 
When parsing JSON data, one can easily catch and handle exceptions if the data doesn't conform to the expected structure.

## Code quality
To ensure that our code is at a certain quality, we have used several tools. 
Spotbugs and checkstyle are two of these and will be discussed further in the Spotbug and Checkstyle section. 
Testing is of course a crucial part of ensuring a high quality code without loopholes and other problems. 
We used Jacoco to report to what degree of test coverage and have made sure to write tests that cover every module of the project architecture. 
This includes the user interface (with user interaction simulation), domain logic and state persistence. This ensures high quality code where we have made sure all aspects of the project work as envisioned.

### Spotbugs and Checkstyle
SpotBugs is a code checker (linter) that analyzes Java code for possible bugs, inefficient code, 
and in general code that does not follow best practices. Using such a tool helps in writing better code. 
SpotBugs is also configured in the same way as Checkstyle, with a separate configuration file.

In the project, we are use 'Checkstyle,' which checks code format. It checks whether the code follows a certain style.
The style is configured in an XML configuration file, and there are ready-made configurations available for such code formatters
(including one created by Google which we are using). To make it easier to adhere to the configured style we are using the IDE extension 'Checkstyle-IDEA' for 
InteliJ and 'Checkstyle for Java' in VScode. Both the .IDEA and the .vscode folder contains config for these extensions. 
The extensions can be integrated with both IDEs 'format on save' functionality to make it simple to format the code right.

Both Checkstyle and SpotBugs are tools that can be run in the terminal using Maven and 
provide feedback on the code in the form of 'error' or 'warning,' 
along with some explanatory text on why the code does not adhere to the configured standard.

### Testing and Jacoco
For the second release we added tests classes for the UI. We are currently testing both the app launcher and our controller in our UI. 
When testing the controller we noticed a problem where creating an instance of the controller-class loads the file we use when launching the actual app. 
This makes testing all of the functions of the app difficult. Right now AlbumReview test-objects are written directly in the main file.

We also added test classes for state persistence, where we’re testing both the jackson module with serialization and deserialization, 
as well as our classes for writing to and loading from file. 
Our classes concerning file writing and -saving contains some
try-and-catch-statements where we found it difficult to test that all exceptions were caught.

We also found some of the catches a bit redundant for the functionality of the methods.
With Jacoco we have achieved a coverage of 82% for the UI, and for the core, which includes domain logic and state persistence we reached 77%. 
The lack of complete coverage is due to the issues mentioned above. Despite this, we feel that we have tested all of the most crucial aspects of the project. 
All though, we would like to achieve a higher coverage for the next assignment, but for this to happen we have to make some changes in our app controller code.

## Work routines
After the last release we wanted to improve communication and to create better issues. We feel like we have improved upon this. 
We have met more often throughout this release than the last and when members have been sick or late, the member sent a message to tell the others.
All our work has had the issues as a focus-point. When we needed something, we would look at the issue board and pick an issue in the “Open” section. 
This made working as a group structured and clear.
We do however need to work on a few things. We made three larger milestones. We followed these for the most part, but towards the end of the project we fell behind on deadlines.
This created a problem where we started on issues and tasks where we needed other issues to have been finished beforehand. 
It wasn't a huge problem, but we will try to follow our plan and milestones better on the next release.

# Reflection
We wanted to change more of our domain logic, controller, architecture and how our app functioned as a whole during this release. 
But we underestimated the time it took to implement, json with jackson, checkstyle and spotbugs as well as setting up project architecture.
Therefore, most of our time went to implementing these dependencies rather than working on the more direct effects of functionality of the app. 
For the next time we would like to continue developing the functionality of our app, and make it more advanced.

One of the things we would like to improve is how the controller works. As mentioned in the paragraph concerning testing, 
we can’t choose which file we want to write to and load from in a good way.
Because of this, test-albums are written to the main file, and we have to manually remove these albums, when testing user interaction.

For our next release we also want to focus on writing better javaDoc and being more descriptive while writing our functions. 
With this our main agenda is to be able to see what each function takes in, 
returns and what it does more specifically while our release will still explain the overall context.

Other than that we have kept our weekly meetings, and communication within the group has stayed good. 
We have stayed committed to our good and descriptive use of git for milestones and issues, 
but still need to work on our order of doing the different issues to the next release. 

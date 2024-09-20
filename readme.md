# Project context (added september 2024)

This project was part of the course [IT1901 - Informatics, Project I ](https://www.ntnu.edu/studies/courses/IT1901#tab=omEmnet) at NTNU. It was completed by Snorre Sæther, Jacob Alveberg, Sebastian Kemp and Brendan Kennedy. The project was completed in the automn of 2023.

IT1901 is a course which introduces many students to developing software with a client-server architecture. Because of this I (Snorre) think there is a lot of bad code in this project. I belive most students taking this course feel that they have to spend way to much time setting up a Java project, with POM file configurations for all modules of the project.

This takes away from time that could be spent learning about how to write code in the context of client-server architecture. If I could do the project again I would focus on learning Spring Boot and how to write REST APIs.

REST was an important part of the IT1901 course, but there was almost no time learning what it is and how to develope with REST in mind. Luckily I have learned a lot since this project was completed.


The course gives knowledge and skills in agile application development in teams. The application will use a client server architecture, structured in modules and configured with a build system. The groups will use a system for issue tracking, source code management and code review. The focus is more on code quality and testing, than on functionality.


We created a simple Java app, with testing, logging, code checking, a user interface and basic API.


We also set up the project to run remote on an Eclipse Che service. As of September 2024 it is not running, so any reference to this can be ignored.


AlbumReview is an app where you can log in with your username. From here you can either add a new album to the list of albums, or open an already existing album. When you have opened an album you can give it a review on a scale from 1-10. You can also see the previous reviews of different users.

# How to Navigate and Run our App
[Our Eclipse Che workspace](https://che.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2337/gr2337?new)
Our app 'Album-Review lets useres, rate and save music. 
This Readme shows our folder structure, and where to finde the different classes. How to run the app. And what versions and dependecys we use.

## Building and Running the App
How to run the app with maven:
1. clone the GitLab rep root from master branch
2. cd to the folder 'albumreview' 
3. run `mvn install`
4. run `mvn clean compile`
5. To open the app run: `mvn javafx:run -f ui/pom.xml`

## Server and app
How to start the server (from /albumreview/):
**When running the app the server must be running as well!!**
1. cd springboot
2. run `mvn spring-boot:run`

### Issues with the REST API and the app:
Because of time-constraints we where not able to implement the REST API for the whole app. 
The first screen after inputing the username is using the REST API, but the rest of the app is still using the controller logic in ui. 
This is of course not what we wanted to accomplish, but to be able to deliver a product on time this is what we had to do.

### Tests and checks
To run tests use: `mvn test`, this has to be done after compile. 
Jacoco test-coverage report is generated in: <modulename>/target/site/index.html.

When you run `mvn test` it will run both tests and checks.

If you want to run Checkstyle and SpotBugs without running tests use `mvn verify`
### About test coverage: 
When we converte to using the REST API most of the tests in ui broke, because of time constraints we were not able to fix all of this. 

## Eclipse Che:
After opening the link above for Eclipse Che it will create a workspace git our project. 
Here you can follow these steps to run the app:

1. cd to the folder 'albumreview'
2. run `mvn install`
3. run `mvn clean install`
4. To open the app run: `mvn javafx:run -f ui/pom.xml`

The app will open on the 6080 endpoint. To reach it you can either copy the url in the sidemenu 
under Endpoints in the Eclipse Che VScode editor or you can edit and go to this link in your browser when
the workspace is up:

`http://<ntnuUser>-stud-ntnu-no-<workspaceName>-6080-tcp-desktop-ui.che.stud.ntnu.no/`

## JLink to greate shippable product
To generate shippable product run these commands (if you are in folder /gr2337/):
1. `cd albumreview`
2. `mvn javafx:jlink -f ui/pom.xml`
3. `mvn jpackage:jpackage -f ui/pom.xml`

If the app does not run automaticly, try this from /albumreview/: (in Windows):
`.\target\image\bin\albumreviewfx.exe`

## Versions, Dependencies and Pluggins
The versions we are using:
1. Java version: 17.0.8
2. Maven version: 3.9.4 locally.
   - maven compiler plugin version 3.11.0
3. Junit Jupiter version: 5.10.0
    - For testing
4. Jacoco version: 0.8.10
    - For checking test coverage 
5. Jackson version 2.14.0-rc2:
   - For parsing JSON in Java
6. Surefire version: 3.1.2
7. Spotbugs version: 4.7.2.0
8. Checkstyle version: 10.12.3



# Project Architecture
The root folder is 'gr2337'. The maven project is found in `albumreview`.  

Here you will find this structure:

gr2337
- .devcontainer `#containe setup for virtual enviroment (e.g. Eclipse Che, Docker)`
- .idea
- .vscode
- albumreview(ALBUM-REVIEW)
  - config `# config for spotbug and checkstyl`
  - core (albumreview.core)
  - docs
  - ui (albumreview.ui)
  - pom.xml(for ALBUM-REVIEW)
- .devfile.yaml `# devfile for Eclipse Che (or Docker?)`
- .gitignore
- readme.md

## Core Folder
Core contains domain logic and data handling logic to implement persistent state. 

- core (albumreview.core)
  - src
    - main
      - java
        - domainlogic (core.domianlogic) `#contains domainlogic`
        - statepersistence(core.statepersistence) `#contains data handeling `
        - module-info.java
      - resources `#empty for now`
    - test
      - java
          - domainlogic `#contains tests for domainlogic`
          - statepersistence `#contains tests for serializing, write-load to/from file. `
      - resources `#contains resources used in `
  - pom.xml (albumreview.core)
  

## ui folder
The ui folder contains logic for controlling the app (viewutil) and the fxml files for the user interface.

- ui (albumreview.ui)
  - ui
    - src
        - main
            - java
                - viewutil(ui.viewutil) `#contains controll logic for the app`
                - module-info.java
            - resources
              - fxml `#contains fxml files for ui`
        - test
            - java
                - viewutil `#contains tests for app controll logic written in TestFX`
            - resources `#contains resources used for testing `
    - pom.xml (albumreview.ui)

## springboot folder
- springboot (albumreview.springboot)
        - src
            - main
                - java
                    - restserver(springboot.restserver) `#contains REST API and server controllers and services`
                    - module-info.java
                - resources
                    - fxml `#contains fxml files for ui`


## Docs
In the Docs folder we have all the release notes and documentation for the different releases. 
- docs
    - release1
    - release2
    - release3

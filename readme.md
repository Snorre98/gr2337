[Our Eclipse Che workspace](https://che.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2337/gr2337?new)
# How to Navigate and Run our App
Our app 'Album-Review lets useres, rate and save music. 
This Readme shows our folder structure, and where to finde the different classes. How to run the app. And what versions and dependecys we use.

## Building and Running the App
How to run the app with maven:
1. clone the GitLab rep root from master branch
2. cd to the folder 'albumreview' 
3. run `mvn install`
4. run `mvn clean install`
5. To open the app run: `mvn javafx:run -f ui/pom.xml`



### Tests and checks
To run tests use: `mvn test`, this has to be done after compile. 
Jacoco test-coverage report is generated in: <modulename>/target/site/index.html.

When you run `mvn test` it will run both tests and checks.

If you want to run Checkstyle and SpotBugs without running tests use `mvn verify`

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

## Docs
In the Docs folder we have all the release notes and documentation for the different releases. 
- docs
    - release1
    - release2
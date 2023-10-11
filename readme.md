[Our Eclipse Che workspace](https://che.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2337/gr2337?new)
# How to Navigate and Run our App
Our app 'Album-Review lets useres, rate and save music. This Readme shows our folder structure, and where to finde the different classes. How to run the app. And what versions and dependecys we use.

## Building and Running the App
How to run the app with maven:
1. mvn install
2. mvn javafx:run -f ui/pom.xml

## Java, Maven and other Dependecys
The versions we are using:
1. Java version: 17.0.8
2. Maven version: 3.9.4.
3. Junit Jupiter version: 5.10.0
4. Jacoco version: 0.8.10
    - For checking test coverage 

## Core Folder
Inside the core folder we have core\main\java\core. Within the last core folder is where our domain logic code is writen. Here we have our main object class, a list of the objects class, a filehandler class and the comparator classes.
- core
  - main
    - java
      - core
        - (Domain logic Classes)

## UI Folder
Within the UI folder we have our visual app. Inside ui\src\main\java\ui we have the App and the Controller. We also have our fxml folder under UI ui\src\main\resources\ui.

Folder Structure for App and Controller:
- ui
    - src
        - main
            - java
                - ui
                    - (App and Controller)

Folder Structure for fxml:
- ui
    - src
        - main
            - resources 
                - ui
                    - (fxml)

## Docs
In the Docs folder we have all the release notes and documentation for the different releases. 
- docs
    - (Different releas notes)
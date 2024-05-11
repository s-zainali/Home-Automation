# Home Automation Project

## Overview 
This Java-based Home Automation project aims to provide a comprehensive solution for *controlling* and *monitoring* various smart devices within a house / room. It is meant for individuals or companies that want to increase efficiency by automating tedious chores.

Since it is a requirement of this project not to make network calls, the different devices and rooms will be simulated using classes and objects.
## Features:
- **Device Control:** Control lights, thermostats, fans, doors, and other devices through a centralized platform.

- **Scheduling:** Set up schedules for devices to automate routines, enhancing energy savings and convenience.

- **Sensor Integration:** Integrate sensors to monitor environmental conditions, such as temperatures and light, enabling automated responses based on predefined rules.

## My motivation
This project interests me because I like to focus my energy on expressing my creativity and innovating (like this project !) rather than expend it on boring, tedious tasks which could be executed by a computer!

## User Stories
- [x] As a user I want to have a house which has the capability to manage a list of devices and a list of rooms
- [x] As a user I want to be able to add different rooms in my house to a list.
- [x] As a user I want to be able to view each of the rooms that I have added.
- [x] As a user I want to be able to select a room to control devices within.
- [x] As a user I want to be able to remove a room and all devices within.
- [x] As a user I want to be able to add different devices to a list of all my devices for the selected room.
- [x] As a user I want to be able to view each of the devices that I have added for a room.
- [x] As a user I want to select a particular device in a selected room.
- [x] As a user, when I select the quit option from the main menu, I want to be prompted to save my house's status to file and have the option to do so or not.
- [x] As a user, when I start the application, I want to be given the option to load my house lists from file.

# Instructions for Grader

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by clicking manage devices on the main screen followed by clicking add device and entering the device type and device name.![sdf.png](data%2FDocs%2Fsdf.png)

- You can generate the second required action related to the user story "adding multiple Xs to a Y" by clicking manage devices on the main screen followed by clicking add room and entering the room name ![Screenshot from 2024-03-sd 16-45-10.png](data%2FDocs%2FScreenshot%20from%202024-03-sd%2016-45-10.png)

- To select a specific room click on the room button shown ![sdfsdfsd.png](data%2FDocs%2Fsdfsdfsd.png)

- You can locate my visual component when the application starts up, in the middle of the UI window.![Screenshot from 2024-03-29 16-38-44.png](data%2FDocs%2FScreenshot%20from%202024-03-29%2016-38-44.png)

- You can save the state of my application by clicking the close button on the window, this will prompt you with an option to save the house state or not. Click yes to save the house to file ![Screenshot from 2024-03-29 16-38-51.png](data%2FDocs%2FScreenshot%20from%202024-03-29%2016-38-51.png)

- You can reload the state of my application by running the app. On startup you will be prompted if you want to load a saved state of the program or not. Select yes to reload the state of the saved application.![Screenshot from 2024-03-29 16-38-07.png](data%2FDocs%2FScreenshot%20from%202024-03-29%2016-38-07.png)![Screenshot from 2024-03-29 16-38-16.png](data%2FDocs%2FScreenshot%20from%202024-03-29%2016-38-16.png)

- If you choose not to restore the application you will be asked to enter the name for your new house. ![Screenshot from 2024-03-29 16-38-51.png](data%2FDocs%2FScreenshot%20from%202024-03-29%2016-38-51.png)

# Phase 4: Task 2


```/home/zain/.jdks/corretto-11.0.21/bin/java-javaagent:/snap/intellij-idea-community/493/lib/idea_rt.jar=32973:/snap/intellij-idea-community/493/bin -Dfile.encoding=UTF-8-classpath /home/zain/University/CPSC-210/project_k9y0h/out/production/Project-Starter:/home/zain/University/CPSC-210/project_k9y0h/lib/spec/json-20210307.jar ui.gui.GraphicalUI
Thu Apr 04 23:32:23 PDT 2024
Device: Main house door added to list of devices.
Thu Apr 04 23:32:36 PDT 2024
Room: Bedroom added to list of rooms.
Thu Apr 04 23:32:46 PDT 2024
Device: SmartLight added to list of devices.
Thu Apr 04 23:32:50 PDT 2024
Room: Kitchen added to list of rooms.
Thu Apr 04 23:32:57 PDT 2024
Device: Door added to list of devices.
Thu Apr 04 23:33:04 PDT 2024
Room: Living Room added to list of rooms.
Thu Apr 04 23:33:12 PDT 2024
Device: Light added to list of devices.
Thu Apr 04 23:33:21 PDT 2024
Device: Vacuum added to list of devices.
Thu Apr 04 23:33:23 PDT 2024
Room: Living Room removed from list of rooms.

Process finished with exit code 0
```

# Phase 4: Task 3

In order to improve my design, I would focus more on the new design principles we learnt that involve cohesion and coupling. An example would be my `DeviceManager` and `RoomManager` classes. They have almost the same functionality, so I could improve on that. Finally, I could improve on my GUI implementation. It includes a lot of redundant code which can be simplified to optimise the code.
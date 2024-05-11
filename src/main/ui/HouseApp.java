package ui;

import model.House;
import model.room.*;
import model.device.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

/**
 * Home automation application
 */
public class HouseApp {
    private static final String JSON_PATH =  "./data/house.json";
    public final ColorfulTerminal ct = new ColorfulTerminal();
    private House house;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    /**
     * <b>EFFECTS  :</b> runs the home automation application
     */
    public HouseApp() {
        house = new House();
        jsonWriter = new JsonWriter(JSON_PATH);
        jsonReader = new JsonReader(JSON_PATH);
        init();
        runApp();
    }

    /**
     * <b>MODIFIES :</b> this
     * <p>
     * <b>EFFECTS  :</b>  handles user interaction for rooms and devices
     */
    public void runApp() {
        while (true) {
            handleRooms();
            handleDevices(house);
            mainMenu();
        }
    }

    /**
     * <b>MODIFIES :</b> this
     * <br>
     * <b>EFFECTS : </b> Greets the User <br>
     * Asks the user if they want to load a saved house or create a new one from scratch
     * <br>
     * If user chooses to load an existing house:<br>
     * * Load saved house<br>
     * Otherwise:<br>
     * * Ask the user for the name for their new house and set it
     */
    public void init() {
        System.out.println(ct.ansiGreen
                + "|House Automation|"
                + ct.ansiReset);
        System.out.println(ct.ansiGreen + "Welcome to the App\n\n" + ct.ansiReset);

        Scanner scan = new Scanner(System.in);

        System.out.println("Would you like to load a saved house ? (y/N)");
        String userInput = scan.next().toLowerCase();

        if (userInput.equals("y")) {
            loadHouse();
        } else {
            scan = new Scanner(System.in);

            System.out.println("Enter a name for your new house : ");
            userInput = scan.nextLine();
            house.setName(userInput);
        }
    }


    /**
     * <b>REQUIRES :</b> User input matches prompts given exactly
     * <p>
     * <b>MODIFIES :</b> this
     * <p>
     * <b>EFFECTS  :</b> handles selected/ unselected rooms <br>
     * if no rooms are selected, tells the user that no rooms are selected <br>
     * if a room is selected, handles the interactions for that selected room
     */
    public void handleRooms() {
        Room selectedRoom = house.roomManager().getSelectedRoom();
        if (selectedRoom == null) {
            System.out.println(ct.ansiYellow + "No rooms selected" + ct.ansiReset);
        } else {
            while (true) {
                handleSelectedRoom(selectedRoom);
            }
        }
    }

    /**
     * <b>REQUIRES :</b> User input matches prompts given exactly
     * <p>
     * <b>MODIFIES :</b> this
     * <p>
     * <b>EFFECTS  :</b> handles selected/ unselected devices <br>
     * if no devices are selected, tells the user that no devices are selected <br>
     * if a device is selected, handles the interactions for that selected device
     */
    public void handleDevices(Room room) {
        Device selectedDevice = room.deviceManager().getSelectedDevice();
        if (selectedDevice == null) {
            System.out.println(ct.ansiYellow + "No devices selected" + ct.ansiReset);
        } else {
            while (room.deviceManager().getSelectedDevice() != null) {
                handleSelectedDevice(room);
            }
        }
    }

    /**
     * <b>MODIFIES :</b> this
     * <p>
     * <b>EFFECTS  :</b> handles the main menu
     */
    public void mainMenu() {
        Scanner scan = new Scanner(System.in);
        displayMainMenu();
        int userOption = scan.nextInt();
        processMainMenuOption(userOption);
    }

    /**
     * <b>EFFECTS  :</b> prints out the main menu to the console
     */
    public void displayMainMenu() {
        System.out.println("MAIN MENU\n\nChoose an option:\n"
                + "\t[1] Manage rooms\n"
                + "\t[2] Manage devices\n"
                + "\t[3] Exit");
    }

    /**
     * <b>REQUIRES :</b> Option is from the ones specified.
     * <p>
     * <b>MODIFIES :</b> this
     * <p>
     * <b>EFFECTS  :</b> processes the input the user has given
     * <br>
     * if the user input is 1 it will display the room menu
     * <br>
     * if the user input is 2, it will display the device menu for house
     * <br>
     * if the user input is 3, the program will halt and exit
     */
    public void processMainMenuOption(int option) {

        if (option == 1) {
            roomMenu();
        } else if (option == 2) {
            deviceMenu(house);
        } else if (option == 3) {
            exitApp();
        } else {
            System.out.println("Invalid option entered, try again.");
            runApp();
        }
    }

    /**
     * <b>MODIFIES :</b> this
     * <p>
     * <b>EFFECTS  :</b> Handles the room menu
     */
    public void roomMenu() {
        Scanner scan = new Scanner(System.in);
        displayRoomMenu();
        int userOption = scan.nextInt();
        processRoomMenuOption(userOption);
    }

    /**
     * <b>EFFECTS  :</b> prints out the room menu to the console
     */
    public void displayRoomMenu() {
        System.out.println("Choose an option:\n"
                + "\t[1] List rooms\n"
                + "\t[2] Select room\n"
                + "\t[3] Create new room\n"
                + "\t[4] Return to main menu");
    }

    /**
     * <b>EFFECTS  :</b> Prints all the rooms to the console if rooms exist in the room manager
     * <br>
     * print No rooms to select from if no room exist in the room manager
     */
    public boolean displayRooms() {
        String listRoomOutput = house.roomManager().listRooms(house);
        System.out.println(listRoomOutput);
        return !listRoomOutput.equals("No rooms to display");
    }

    /**
     * <b>REQUIRES :</b> option must be from ones specified
     * <p>
     * <b>MODIFIES :</b> this
     * <p>
     * <b>EFFECTS  :</b> processes room menu option input by the user.
     * <br>
     * if user input is 1, move to room selection
     * <br>
     * if user input is 2, create a new room,
     * <br>
     * if user input is 3, return to the main menu
     */
    public void processRoomMenuOption(int option) {
        Scanner scan = new Scanner(System.in);
        if (option == 1) {
            displayRooms();
            System.out.println("Press Enter to continue...");
            scan.nextLine();
            roomMenu();
        } else if (option == 2) {
            selectRoom();
        } else if (option == 3) {
            processRoomMenuOptionThree(scan);
        } else if (option == 4) {
            house.roomManager().setSelectedRoom(null);
            runApp();
        } else {
            System.out.println("Invalid option entered, try again");
            roomMenu();
        }
    }

    /**
     * <b>MODIFIES : this</b>
     * <br>
     * <b>EFFECTS  : </b> Handles the creation process of a new room
     */
    private void processRoomMenuOptionThree(Scanner scan) {
        Room newRoom = createRoom();
        house.roomManager().addRoom(newRoom);
        System.out.print("Do you want to select the room you have created? (y/N): ");
        String userDecision = scan.next();

        if (userDecision.equals("y")) {
            selectRoom(newRoom);
        } else {
            roomMenu();
        }
    }

    /**
     * <b>MODIFIES :</b> this
     * <p>
     * <b>EFFECTS  :</b> handles the device menu
     */
    public void deviceMenu(Room room) {
        Scanner scan = new Scanner(System.in);
        displayDeviceMenu();
        int userOption = scan.nextInt();
        processDeviceMenuOption(userOption, room);
    }

    /**
     * <b>EFFECTS  :</b> prints out the device menu to the console
     */
    public void displayDeviceMenu() {
        System.out.println("Choose an option:\n"
                + "\t[1] List devices\n"
                + "\t[2] Select device\n"
                + "\t[3] Create new device\n"
                + "\t[4] Return to Main Menu");
    }

    /**
     * <b>EFFECTS  :</b> Prints all the devices to the console if rooms exist in the device manager
     * <br>
     * print No devices to select from if no device exist in the device manager
     */
    public boolean displayDevices(Room room) {
        String listDevicesOutput = room.deviceManager().listDevices(room);
        System.out.println(listDevicesOutput);
        return !listDevicesOutput.equals("No devices to display");
    }

    /**
     * <b>EFFECTS  :</b> Prints out all available types of devices to the console
     * <br>
     * if no types of devices are available, print No devices types available to add yet
     */
    public void displayAvailableDeviceTypes() {
        if (house.deviceManager().getAvailableDeviceTypes().isEmpty()) {
            System.out.println("No devices types available to add yet");
        } else {
            System.out.println("There are "
                    + house.deviceManager().getNumAvailableDeviceTypes()
                    + " types of devices available");

            System.out.println("Device types: ");
            for (int i = 0; i < house.deviceManager().getNumAvailableDeviceTypes(); i++) {
                System.out.println("\t[" + (i + 1) + "] " + house.deviceManager().getAvailableDeviceTypes().get(i));
            }
        }
    }

    /**
     * <b>REQUIRES :</b> option must be exactly from ones specified
     * <p>
     * <b>MODIFIES :</b> this
     * <p>
     * <b>EFFECTS  :</b> processes device menu option entered by the user
     * <br>
     * if option is 1, move to device selection
     * <br>
     * if option is 2, create a new device
     * <br>
     * if option is 3, return to the main menu
     */
    public void processDeviceMenuOption(int option, Room room) {
        Scanner scan = new Scanner(System.in);
        if (option == 1) {
            displayDevices(room);
            System.out.println("Press Enter to continue...");
            scan.nextLine();
            deviceMenu(room);
        } else if (option == 2) {
            selectDevice(room);
        } else if (option == 3) {
            processDeviceMenuOptionThree(room, scan);
        } else if (option == 4) {
            house.roomManager().setSelectedRoom(null);
            runApp();
        } else {
            System.out.println("Invalid option entered, try again");
            deviceMenu(room);
        }
    }

    /**
     * <b>MODIFIES : this</b>
     * <br>
     * <b>EFFECTS  : </b> Handles the creation process of a new device
     */
    private void processDeviceMenuOptionThree(Room room, Scanner scan) {
        Device newDevice = createDevice(room);
        room.deviceManager().addDevice(newDevice);
        System.out.print("Do you want to select the device you have created? (y/N): ");
        String userDecision = scan.next();

        if (userDecision.equals("y")) {
            selectDevice(newDevice, room);
        } else {
            deviceMenu(room);
        }
    }

    /**
     * <b>MODIFIES :</b> this
     * <p>
     * <b>EFFECTS  :</b> handles the controls menu (controls for a device)
     */
    public void controlsMenu(Device device, Room room) {
        Scanner scan = new Scanner(System.in);
        displayControlsMenu();
        int userOption = scan.nextInt();
        processControlsMenuOption(userOption, device, room);
    }

    /**
     * <b>EFFECTS  :</b> prints out the controls for a device as a menu to the console
     */
    public void displayControlsMenu() {
        System.out.println("Choose an option:\n"
                + "\t[1] Deselect this device and return to devices menu");
    }

    /**
     * <b>REQUIRES :</b> option must be exactly from ones specified
     * <p>
     * <b>MODIFIES :</b> this
     * <p>
     * <b>EFFECTS  :</b> processes device menu option entered by the user
     * <br>
     * if option is 1, move to device selection
     * <br>
     * if option is 2, create a new device
     * <br>
     * if option is 3, return to the main menu
     */
    public void processControlsMenuOption(int option, Device device, Room room) {

        if (option == 1) {
            selectDevice(null, room);
            deviceMenu(room);
        }
    }

    /**
     * <b>EFFECTS  :</b> creates and returns a new room instance with title given by the user
     */
    public Room createRoom() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Give your new room a title: ");

        String roomTitle = scan.nextLine();

        while (house.roomManager().checkRoomExists(roomTitle)) {
            System.out.println("There already exists a room with this title");
            scan = new Scanner(System.in);
            System.out.print("Enter a name for your new room: ");
            roomTitle = scan.nextLine();
        }

        return new Room(roomTitle);
    }

    /**
     * <b>REQUIRES :</b> availableDeviceTypes has at least one type
     * <p>
     * <b>EFFECTS  :</b> creates and returns a new device instance with name and type specified by the user
     */
    public Device createDevice(Room room) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Select from one of the device types listed below");
        displayAvailableDeviceTypes();
        int userOption = scan.nextInt();
        String selectedType = house.deviceManager().getAvailableDeviceTypes().get(userOption - 1);
        scan = new Scanner(System.in);
        System.out.print("Enter a name for your new device: ");
        String deviceName = scan.nextLine();

        while (room.deviceManager().checkDeviceExists(deviceName)) {
            System.out.println("There already exists a device with this title");
            scan = new Scanner(System.in);
            System.out.print("Enter a name for your new device: ");
            deviceName = scan.nextLine();
        }

        return createAppropriateDeviceInstance(selectedType, deviceName);
    }

    /**
     * <b>EFFECTS: </b>
     * @return an appropriate object based on the device type passed
     */
    public static Device createAppropriateDeviceInstance(String type, String title) {
        switch (type) {
            case "SmartDoor":
                return new SmartDoor(title);
            case "LightBulb":
                return new LightBulb(title);
            case "AirFreshener":
                return new AirFreshener(title);
            case "AutomaticVacuum":
                return new AutomaticVacuum(title);
            case "AirConditioner":
                return new AirConditioner(title);
            default:
                return null;
        }
    }

    /**
     * <b>MODIFIES :</b> this
     * <p>
     * <b>EFFECTS  :</b> handles a selected room
     */
    public void handleSelectedRoom(Room room) {
        Device selectedDevice = room.deviceManager().getSelectedDevice();
        if (selectedDevice != null) {
            handleSelectedDevice(room);
        } else {
            System.out.println("Selected room:  " + ct.ansiGreen + room.getTitle() + ct.ansiReset);
            deviceMenu(room);
        }
    }

    /**
     * <b>MODIFIES :</b> this
     * <p>
     * <b>EFFECTS  :</b> handles a selected device
     */
    public void handleSelectedDevice(Room room) {
        Device device = room.deviceManager().getSelectedDevice();
        System.out.println("Selected device:  " + ct.ansiGreen + device.getTitle() + ct.ansiReset);
        System.out.println("There are no control options implemented at this stage for the device.");
        controlsMenu(device, room);
    }

    /**
     * <b>MODIFIES :</b> this
     * <p>
     * <b>EFFECTS  :</b> uses the room manager to set the current selected room to the one passed
     */
    public void selectRoom() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Choose from one of the rooms below: ");
        if (displayRooms()) {
            int userChoice = scan.nextInt();
            selectRoom(house.roomManager().getRooms().get(userChoice - 1));
        } else {
            roomMenu();
        }
    }

    /**
     * <b>MODIFIES :</b> this
     * <p>
     * <b>EFFECTS  :</b> uses the room manager to set the current selected room to the one passed
     */
    public void selectRoom(Room room) {
        house.roomManager().setSelectedRoom(room);
    }

    /**
     * <b>REQUIRES :</b> userChoice must be from options specified
     * <p>
     * <b>MODIFIES :</b> this
     * <p>
     * <b>EFFECTS  :</b> calls overloaded function to select a device
     */
    public void selectDevice(Room room) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Choose from one of the devices below: ");
        if (displayDevices(room)) {
            int userChoice = scan.nextInt();
            selectDevice(room.deviceManager().getDevices().get(userChoice - 1), room);
        } else {
            deviceMenu(room);
        }
    }

    /**
     * <b>MODIFIES :</b> this
     * <p>
     * <b>EFFECTS  :</b> uses the device manager of the room passed to set the current selected device to the one passed
     */
    public void selectDevice(Device device, Room room) {
        room.deviceManager().setSelectedDevice(device);
    }

    /**
     * <b>MODIFIES :</b> this
     * <br>
     * <b>EFFECTS : </b> Runs the exit program sequence. Asks user if they want to save program state and then exits
     * gracefully
     */
    private void exitApp() {
        Scanner scan = new Scanner(System.in);
        System.out.println("You are about to exit the program, do you want to save the program state ? (y/n)");
        String userChoice = scan.next();

        if (userChoice.equalsIgnoreCase("y")) {
            saveHouse();
            System.out.println("House state saved at " + JSON_PATH);
        } else {
            System.out.println("You have chosen not to save the current house state");
        }

        System.out.println(ct.ansiGreen + "Goodbye..." + ct.ansiReset);
        System.exit(0);
    }

    /**
     * <b>EFFECTS : </b> Writes our the current house state in JSON format and saves it to a file.
     */
    public void saveHouse() {
        try {
            jsonWriter.open();
            jsonWriter.write(house);
            jsonWriter.close();

            System.out.println("Successfully saved to " + JSON_PATH);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to access file " + JSON_PATH);
        }
    }

    /**<b>MODIFIES: </b> this
     * <b>EFFECTS: </b> Loads house from the saved file
     * */
    public void loadHouse() {
        try {
            house = jsonReader.read();
            System.out.println("Loaded " + house.getTitle() + " from " + JSON_PATH);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_PATH);
        }
    }

}
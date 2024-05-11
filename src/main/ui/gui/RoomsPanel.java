package ui.gui;

import model.House;
import model.device.*;
import model.room.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.HouseApp;

import static ui.gui.GraphicalUI.*;

public class RoomsPanel extends JPanel implements ActionListener {

    public static final int HEADING_SIZE = 32;

    private Room room;

    private ImageIcon houseImage;
    private JButton addRoomButton;
    private JButton addDeviceButton;
    private JButton returnToMainMenuButton;
    private JLabel title;

    private GraphicalUI controller;

    protected JPanel panel;
    protected JPanel optionsPanel;
    protected JPanel buttonsPanel;
    protected JPanel roomsPanel;

    private JScrollPane devicesContainer;
    private JPanel buttonsContainer;
    private JPanel buttonsContainer1;

    JScrollPane roomsContainer;

    public RoomsPanel(GraphicalUI controller, Room room) {
        this.controller = controller;

        this.room = room;

        optionsPanel = new JPanel();

        buttonsPanel = new JPanel();

        panel = new JPanel();
        panel.setSize(WIDTH, HEIGHT);

        roomsPanel = new JPanel();
        buttonsPanel  = new JPanel();
        roomsContainer = new JScrollPane();
        devicesContainer = new JScrollPane();

        addTitle();
        buttonsPanel.setLayout(new GridLayout(1, 2));
        addDevices(room, buttonsPanel, true);
        addRooms(controller.getHouse(), buttonsPanel, true);

        panel.add(buttonsPanel);

        addButtons(0);

        panel.setLayout(new GridLayout(3, 1, 0, 50));
        panel.setBackground(DARK_BACKGROUND);

    }

    public JPanel getPanel() {
        return panel;
    }

    private void addTitle() {
        title = new JLabel("Rooms and devices");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Sans-serif", Font.PLAIN, HEADING_SIZE));
        title.setForeground(Color.WHITE);

        panel.add(title);
    }

    private void addButtons(int n) {

        if (n == 0) {
            returnToMainMenuButton = new JButton("Return to main menu");
        } else {
            returnToMainMenuButton = new JButton("Return to previous menu");
        }

        createButtons();

        optionsPanel.setBackground(DARK_BACKGROUND);
        optionsPanel.add(addDeviceButton);
        optionsPanel.add(returnToMainMenuButton);
        optionsPanel.add(addRoomButton);
        optionsPanel.setLayout(new GridLayout(1, 3));
        panel.add(optionsPanel);
    }

    private void createButtons() {
        addDeviceButton = new JButton("Add Device");
        addDeviceButton.setBackground(LIGHT);
        addDeviceButton.setFocusPainted(false);
        addDeviceButton.setFont(new Font("Sans-serif", Font.PLAIN, 24));
        addDeviceButton.setForeground(DARK_BACKGROUND);
        addDeviceButton.addActionListener(this);

        addRoomButton = new JButton("Add Room");
        addRoomButton.setBackground(LIGHT);
        addRoomButton.setFocusPainted(false);
        addRoomButton.setFont(new Font("Sans-serif", Font.PLAIN, 24));
        addRoomButton.setForeground(DARK_BACKGROUND);
        addRoomButton.addActionListener(this);

        returnToMainMenuButton.setBackground(LIGHT);
        returnToMainMenuButton.setFocusPainted(false);
        returnToMainMenuButton.setFont(new Font("Sans-serif", Font.PLAIN, 24));
        returnToMainMenuButton.setForeground(DARK_BACKGROUND);
        returnToMainMenuButton.addActionListener(this);
    }

    private void addDevices(Room room, JPanel panel, boolean newPanel) {


        JList<JButton> buttonList = new JList<JButton>();
        buttonsContainer = new JPanel();
        buttonsContainer.setBackground(DARK_BACKGROUND);

        addDevicesTitle(buttonsContainer);

//        if (room.deviceManager().getDevices().isEmpty()) {
//            JLabel roomsEmpty = new JLabel("No rooms to display");
//            roomsEmpty.setForeground(Color.RED);
//            roomsEmpty.setHorizontalAlignment(SwingConstants.CENTER);
//            buttonsContainer.add(roomsEmpty);
//        } else {

        for (Device device : room.deviceManager().getDevices()) {
            addDevice(device, buttonsContainer);
        }
//        }

        buttonList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        buttonsContainer.setLayout(new GridLayout(25, 1));


        devicesContainer.setBackground(DARK_BACKGROUND);
        devicesContainer.setViewportView(buttonsContainer);

        if (newPanel) {
            panel.add(devicesContainer);
        }
    }

    private void addDevicesTitle(JPanel parent) {
        JLabel title = new JLabel("Devices");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Sans-serif", Font.PLAIN, 24));
        title.setForeground(LIGHT);

        parent.add(title);
    }

    private void addDevice(Device device, JPanel buttonsContainer) {
        JButton myNewButton = new JButton(returnDeviceAppropriateIcon(device));
        myNewButton.setText(device.getTitle());
        myNewButton.setBackground(GREEN);
        myNewButton.setForeground(LIGHT);
        myNewButton.setSize(10, 40);
        buttonsContainer.add(myNewButton);
    }

    private ImageIcon returnDeviceAppropriateIcon(Device device) {
        ImageIcon deviceIcon = new ImageIcon("./data/" + device.getType() + ".png");
        return deviceIcon;
    }

    public void addRooms(House house, JPanel panel, boolean newPanel) {

        JList<JButton> buttonList = new JList<JButton>();
        buttonsContainer1 = new JPanel();
        buttonsContainer1.setBackground(DARK_BACKGROUND);

        addRoomsTitle(buttonsContainer1);

//        if (house.roomManager().getRooms().isEmpty()) {
//            JLabel roomsEmpty = new JLabel("No rooms to display");
//            roomsEmpty.setForeground(Color.RED);
//            roomsEmpty.setHorizontalAlignment(SwingConstants.CENTER);
//            buttonsContainer1.add(roomsEmpty);
//        } else {
        for (Room room : house.roomManager().getRooms()) {
            addRoom(room, buttonsContainer1);
        }
//        }
        buttonList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        buttonsContainer1.setLayout(new GridLayout(25, 1));

        roomsContainer.setBackground(DARK_BACKGROUND);
        roomsContainer.setViewportView(buttonsContainer1);

        panel.setBackground(DARK_BACKGROUND);

        if (newPanel) {
            panel.add(roomsContainer);
        }
    }

    private void addRoomsTitle(JPanel buttonsContainer) {
        JLabel title = new JLabel("Rooms");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Sans-serif", Font.PLAIN, 24));
        title.setForeground(LIGHT);
        buttonsContainer.add(title);
    }

    private void addRoom(Room room, JPanel buttonsContainer) {
        JButton myNewButton = new JButton(room.getTitle());
        myNewButton.addActionListener(this);
        myNewButton.setBackground(AQUA);
        myNewButton.setForeground(LIGHT);
        myNewButton.setSize(10, 40);
        buttonsContainer.add(myNewButton);
    }

    private void addAddRoomButton() {
        addRoomButton = new JButton("Add Room");
        addRoomButton.setBackground(LIGHT);
        addRoomButton.setFocusPainted(false);
        addRoomButton.setFont(new Font("Sans-serif", Font.PLAIN, 24));
        addRoomButton.setForeground(DARK_BACKGROUND);
        addRoomButton.addActionListener(this);
        optionsPanel.add(addRoomButton);
    }

    private Room getSelectedRoom(String roomTitle) {

        for (Room room : controller.getHouse().roomManager().getRooms()) {
            if (room.getTitle().equals(roomTitle)) {
                return room;
            }
        }

        return null;
    }

    public void refresh() {
        buttonsContainer.removeAll();
        addDevices(controller.getHouse(), buttonsPanel, true);
        buttonsContainer1.removeAll();
        addRooms(controller.getHouse(), buttonsPanel, true);
    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Return to main menu":
                controller.moveToPane("homePanel");
                break;
            case "Add Device":
                String[] options = room.deviceManager().getAvailableDeviceTypes().toArray(new String[0]);
                int deviceType = JOptionPane.showOptionDialog(controller,
                        "Select a Device type:", "Choose Device Type",
                        0, 2, null, options, options[0]);
                String deviceName = JOptionPane.showInputDialog(controller,
                        "Give your new device a name: ");
                Device newDevice = HouseApp.createAppropriateDeviceInstance(options[deviceType], deviceName);
                room.deviceManager().addDevice(newDevice);
                addDevice(newDevice, buttonsContainer);
                buttonsContainer.revalidate();
                break;
            case "Add Room":
                String roomName = JOptionPane.showInputDialog(controller,
                        "Give your new room a name: ");
                Room newRoom = new Room(roomName);
                controller.getHouse().roomManager().addRoom(newRoom);
                addRoom(newRoom, buttonsContainer1);
                buttonsContainer1.revalidate();
                break;
            case "Return to previous menu":
                controller.getHouse().roomManager().setSelectedRoom(controller.getHouse());
                controller.moveToPane("roomsPanel");
                break;
            default:
                Room selectedRoom = getSelectedRoom(e.getActionCommand());
                controller.getHouse().roomManager().setSelectedRoom(selectedRoom);
                controller.remove(controller.roomPanel);
                JPanel roomPanel = new RoomPanel(controller, selectedRoom, true).getPanel();
                controller.add(roomPanel, "roomPanel");
                controller.moveToPane("roomPanel");
        }
    }
}

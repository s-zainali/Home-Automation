package ui.gui;

import model.device.*;
import model.room.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.HouseApp;

import static ui.gui.GraphicalUI.*;

public class RoomPanel implements ActionListener {

    public static final int HEADING_SIZE = 32;

    protected Room room;

    private ImageIcon houseImage;
    private JButton removeRoomButton;
    private JButton addDeviceButton;
    private JButton returnToMainMenuButton;
    private JLabel title;

    private GraphicalUI controller;

    protected JPanel panel;
    protected JPanel optionsPanel;
    protected JPanel buttonsPanel;

    private JScrollPane devicesContainer;
    private JPanel buttonsContainer;

    private JScrollPane roomsContainer;

    public RoomPanel(GraphicalUI controller, Room room, boolean createPanels) {
        this.controller = controller;

        this.room = room;

        optionsPanel = new JPanel();

        buttonsPanel = new JPanel();

        devicesContainer = new JScrollPane();

        panel = new JPanel();
        panel.setSize(GraphicalUI.WIDTH, GraphicalUI.HEIGHT);

        addTitle();
        buttonsPanel.setLayout(new GridLayout(1, 2));
        panel.add(buttonsPanel);
        addDevices(room, buttonsPanel, true);
        addButtons(0);
        panel.setLayout(new GridLayout(3, 1, 0, 50));
        panel.setBackground(DARK_BACKGROUND);
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public JPanel getPanel() {
        return panel;
    }

    private void addTitle() {
        title = new JLabel("Devices in " + room.getTitle());
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Sans-serif", Font.PLAIN, HEADING_SIZE));
        title.setForeground(Color.WHITE);

        panel.add(title);
    }

    private void addButtons(int n) {
        addDeviceButton = new JButton("Add Device");
        addDeviceButton.setBackground(LIGHT);
        addDeviceButton.setFocusPainted(false);
        addDeviceButton.setFont(new Font("Sans-serif", Font.PLAIN, 24));
        addDeviceButton.setForeground(DARK_BACKGROUND);
        addDeviceButton.addActionListener(this);
        returnToMainMenuButton = new JButton("Return to previous menu");
        returnToMainMenuButton.setBackground(LIGHT);
        returnToMainMenuButton.setFocusPainted(false);
        returnToMainMenuButton.setFont(new Font("Sans-serif", Font.PLAIN, 24));
        returnToMainMenuButton.setForeground(DARK_BACKGROUND);
        returnToMainMenuButton.addActionListener(this);
        addRemoveRoomButton();

        optionsPanel.setBackground(DARK_BACKGROUND);
        optionsPanel.add(addDeviceButton);
        optionsPanel.add(returnToMainMenuButton);
        optionsPanel.add(removeRoomButton);
        optionsPanel.setLayout(new GridLayout(1, 3));
        panel.add(optionsPanel);
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

    private void addRemoveRoomButton() {
        removeRoomButton = new JButton("Remove Room");
        removeRoomButton.setBackground(LIGHT);
        removeRoomButton.setFocusPainted(false);
        removeRoomButton.setFont(new Font("Sans-serif", Font.PLAIN, 24));
        removeRoomButton.setForeground(DARK_BACKGROUND);
        removeRoomButton.addActionListener(this);
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

    public void refresh() {
        buttonsContainer.removeAll();
        addDevices(controller.getHouse(), buttonsPanel, true);
    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
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
            case "Remove Room":
                if (JOptionPane.showConfirmDialog(controller,
                         "This action will remove this room and all devices it contains\n"
                                 + "Are you sure you want to continue ?") == 0) {
                    controller.getHouse().roomManager().removeRoom(room);
                    controller.refresh();
                    controller.moveToPane("roomsPanel");
                }
                break;
            case "Return to previous menu":
                controller.moveToPane("roomsPanel");
                break;
        }
    }
}

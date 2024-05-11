package ui.gui;

import model.House;

import model.device.Device;
import model.logging.Event;
import model.logging.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

//"https://www.flaticon.com/free-icons/home" Home icons created by Freepik - Flaticon
public class GraphicalUI extends JFrame implements ActionListener {
    public static final int HOME_TAB_INDEX = 0;
    public static final int ROOMS_TAB_INDEX = 1;

    public static final int WIDTH = 1080;
    public static final int HEIGHT = 720;

    public static final Color DARK_BACKGROUND = new Color(22, 22, 22);
    public static final Color GREEN = new Color(98, 163, 136);
    public static final Color AQUA = new Color(5, 94, 104);
    public static final Color LIGHT = new Color(185, 210, 210);

    private JPanel homePanel;
    private JPanel roomsPanel;
    private RoomsPanel rsp;
    protected JPanel roomPanel;
    protected RoomPanel rp;

    CardLayout card;

    private static final String JSON_PATH =  "./data/house.json";

    private House house;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    public static void main(String[] args) {
        new GraphicalUI();
    }

    //MODIFIES: this
    //EFFECTS: creates SmartHomeUI, loads SmartHome appliances, displays sidebar and tabs
    private GraphicalUI() {
        super("Smart House Controller");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        jsonReader = new JsonReader(JSON_PATH);
        jsonWriter = new JsonWriter(JSON_PATH);

        house = new House();

        handleLoadPopup();

        managePanels();

        card = new CardLayout();
        setLayout(card);

        add(homePanel, "homePanel");
        add(roomsPanel, "roomsPanel");
        add(roomPanel, "roomPanel");

        this.getContentPane().setBackground(DARK_BACKGROUND);
        setVisible(true);

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                exitApp();
            }
        });

    }

    private void managePanels() {
        homePanel = new HomePanel(this).getRootPane();
        rsp = new RoomsPanel(this, this.getHouse());
        roomsPanel = rsp.getPanel();
        rp = new RoomPanel(this, this.getHouse(), false);
        roomPanel = rp.getPanel();


        roomsPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent evt) {
                roomsPanel.revalidate();
            }
        });

        roomPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent evt) {
                roomPanel.revalidate();
            }
        });
    }

    public void setRoomPanel(JPanel roomPanel) {
        this.roomPanel = roomPanel;
    }

    public void handleLoadPopup() {
        int loadHouse  = JOptionPane.showConfirmDialog(this,
                "Do you wish to load a saved house form file ?");
        if (loadHouse == 1) {
            String houseName = JOptionPane.showInputDialog(this, "Enter New House name");
            house.setName(houseName);
        } else if (loadHouse == 0) {
            loadHouse();
        } else {
            System.exit(0);
        }
    }

    private void loadHouse() {
        try {
            house = jsonReader.read();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Failed to read File");
        }
    }

    public void saveHouse() {
        try {
            jsonWriter.open();
            jsonWriter.write(house);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this, "Successfully saved file to " + JSON_PATH);

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Unable to access " + JSON_PATH);
        }
    }

    public void refresh() {
        rp.refresh();
        rsp.refresh();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //
    }


    //EFFECTS: returns SmartHome object controlled by this UI
    public House getHouse() {
        return house;
    }

    public void moveToPane(String viewName) {
        card.show(this.getContentPane(), viewName);
    }

    public void exitApp() {
        if (JOptionPane.showConfirmDialog(this,
                "Do you want to save the house to file?") == 0) {
            saveHouse();
        }
        for (Event e : EventLog.getInstance()) {
            System.out.println(e.toString());
        }
        System.exit(0);
    }

}

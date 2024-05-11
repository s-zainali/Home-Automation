package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.gui.GraphicalUI.*;

public class HomePanel implements ActionListener {

    public static final int HEADING_SIZE = 32;

    private ImageIcon houseImage;
    private JLabel houseLabel;
    private JLabel houseName;
    private JButton manageButton;
    private JLabel title;

    private GraphicalUI controller;

    private JPanel panel;

    public HomePanel(GraphicalUI controller) {
        this.controller = controller;

        title = new JLabel("Welcome to the Home Automation Dashboard");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Sans-serif", Font.PLAIN, HEADING_SIZE));
        title.setMaximumSize(new Dimension(WIDTH, HEADING_SIZE + 5));
        title.setForeground(LIGHT);

        houseImage = new ImageIcon("./data/home.png");
        houseLabel = new JLabel(houseImage);

        houseName = new JLabel("Selected house: " + controller.getHouse().getTitle());
        houseName.setHorizontalAlignment(SwingConstants.CENTER);
        houseName.setForeground(LIGHT);
        houseName.setFont(new Font("sans-serif", Font.PLAIN, 18));

        manageButton = new JButton("Manage Devices");
        manageButton.setBackground(LIGHT);
        manageButton.setFocusPainted(false);
        manageButton.setFont(new Font("Sans-serif", Font.PLAIN, 24));
        manageButton.setForeground(DARK_BACKGROUND);
        manageButton.addActionListener(this);

        panel = new JPanel();
        panel.setSize(GraphicalUI.WIDTH, GraphicalUI.HEIGHT);

        addItemsToPanel();

        panel.setLayout(new GridLayout(4, 1, 0, 0));
        panel.setBackground(DARK_BACKGROUND);
    }

    private void addItemsToPanel() {
        panel.add(title);
        panel.add(houseLabel);
        panel.add(houseName);
        panel.add(manageButton);
    }

    public JPanel getRootPane() {
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.moveToPane("roomsPanel");
    }
}

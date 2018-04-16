package clueGame;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class DetectiveNotes
{
    private JPanel topPanel, centerPanel, bottomPanel;
    private JPanel peoplePanel, RoomPanel, WeaponPanel;
    private int hgap;
    private int vgap;
    private JComboBox cbox1, cbox2, cbox3;
    private String[] people = {"Miss Scarlett", "Colonel Mustard", "Mr. Green", "Mrs. White", "Mrs. Peacock", "Professor Plum", "Unsure"};
    private String[] rooms = {"Hotbox", "Dungeon", "Bar", "Study", "Computer Room", "Gameroom", "Kitchen", "Living Room", "Art Room", "Theater", "Unsure"};
    private String[] weapons = {"Revolver", "Knife", "Pipe", "Wrench", "Rope", "Candlestick", "Unsure"};
    public DetectiveNotes()
    {
        hgap = 5;
        vgap = 5;
    }

    public void displayGUI()
    {
    	JCheckBox revolver = new JCheckBox("Revolver");
    	JCheckBox knife = new JCheckBox("Knife");
    	JCheckBox pipe = new JCheckBox("Pipe");
    	JCheckBox wrench = new JCheckBox("Wrench");
    	JCheckBox rope = new JCheckBox("Rope");
    	JCheckBox candlestick = new JCheckBox("Candlestick");
    	
    	JCheckBox hotbox = new JCheckBox("Hotbox");
    	JCheckBox dungeon = new JCheckBox("Dungeon");
    	JCheckBox bar = new JCheckBox("Bar");
    	JCheckBox study = new JCheckBox("Study");
    	JCheckBox cpuRoom = new JCheckBox("Computer Room");
    	JCheckBox gameRoom = new JCheckBox("GameRoom");
    	JCheckBox kitchen = new JCheckBox("Kitchen"); 
    	JCheckBox livingRoom = new JCheckBox("Living Room");
    	JCheckBox artRoom = new JCheckBox("Art Room");
    	JCheckBox theater = new JCheckBox("Theater");
    	
    	JCheckBox scarlett = new JCheckBox("Miss Scarlett");
    	JCheckBox mustard = new JCheckBox("Colonel Mustard");
    	JCheckBox greene = new JCheckBox("Mr. Green");
    	JCheckBox mrsWhite = new JCheckBox("Mrs. White");
    	JCheckBox peacock = new JCheckBox("Mrs. Peacock");
    	JCheckBox plumPHD = new JCheckBox("Professor Plum");
        JFrame mainFrame = new JFrame("Detective Notepad");

        JPanel contentPane = new JPanel();
        contentPane.setOpaque(true);
        contentPane.setBorder(
            BorderFactory.createEmptyBorder(hgap, hgap, hgap, hgap));
        contentPane.setLayout(new BorderLayout(2, 3));

        peoplePanel = new JPanel();
        peoplePanel.setOpaque(true);
        peoplePanel.setBorder(
            BorderFactory.createTitledBorder("People"));
        topPanel = new JPanel();
        topPanel.setOpaque(true);          
        cbox1 = new JComboBox(people);
        cbox1.setBorder(
            BorderFactory.createTitledBorder("Person Guess")); 
        peoplePanel.add(scarlett);
        peoplePanel.add(mustard);
        peoplePanel.add(greene);
        peoplePanel.add(mrsWhite);
        peoplePanel.add(peacock);
        peoplePanel.add(plumPHD);
        topPanel.add(peoplePanel);
        topPanel.add(cbox1);


        RoomPanel = new JPanel(); 
        RoomPanel.setOpaque(true);
        RoomPanel.setBorder(
            BorderFactory.createTitledBorder("Rooms"));
        centerPanel = new JPanel(); 
        centerPanel.setOpaque(true);
        cbox2 = new JComboBox(rooms);
        cbox2.setBorder(
            BorderFactory.createTitledBorder("Room Guess"));   
        RoomPanel.add(hotbox);
        RoomPanel.add(dungeon);
        RoomPanel.add(bar);
        RoomPanel.add(study);
        RoomPanel.add(cpuRoom);
        RoomPanel.add(gameRoom);
        RoomPanel.add(kitchen);
        RoomPanel.add(livingRoom);
        RoomPanel.add(artRoom);
        RoomPanel.add(theater);
        centerPanel.add(RoomPanel);
        centerPanel.add(cbox2);


        WeaponPanel = new JPanel(); 
        WeaponPanel.setOpaque(true);
        WeaponPanel.setBorder(
            BorderFactory.createTitledBorder("Weapons"));
        bottomPanel = new JPanel(); 
        bottomPanel.setOpaque(true);
        cbox3 = new JComboBox(weapons);
        cbox3.setBorder(
            BorderFactory.createTitledBorder("Weapon Guess"));  
        WeaponPanel.add(revolver);
        WeaponPanel.add(knife);
        WeaponPanel.add(pipe);
        WeaponPanel.add(wrench);
        WeaponPanel.add(rope);
        WeaponPanel.add(candlestick);
        bottomPanel.add(WeaponPanel);
        bottomPanel.add(cbox3);
        contentPane.add(topPanel, BorderLayout.PAGE_START);
        contentPane.add(centerPanel, BorderLayout.CENTER);
        contentPane.add(bottomPanel, BorderLayout.PAGE_END);

        mainFrame.setContentPane(contentPane);
        mainFrame.pack();
        mainFrame.setLocationByPlatform(true);
        mainFrame.setVisible(true);
    }

}

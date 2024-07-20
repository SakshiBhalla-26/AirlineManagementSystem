package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class BoardingPass extends JFrame implements ActionListener{
    
    JTextField tfpnr;
    JLabel tfname, tfnationality, tfsrc, tfdest, tffname, tffcode, labeldate,tfseat,tfgate,tfboardtime;
    JButton fetchButton;
    
    public BoardingPass() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading = new JLabel("AIR INDIA");
        heading.setBounds(380, 10, 450, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        add(heading);
        
        JLabel subheading = new JLabel("Boarding Pass");
        subheading.setBounds(360, 50, 300, 30);
        subheading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        subheading.setForeground(Color.BLUE);
        add(subheading);
        
        JLabel lblaadhar = new JLabel("PNR DETAILS");
        lblaadhar.setBounds(60, 100, 150, 25);
        lblaadhar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblaadhar);
        
        tfpnr = new JTextField();
        tfpnr.setBounds(220, 100, 150, 25);
        add(tfpnr);
        
        fetchButton = new JButton("Enter");
        fetchButton.setBackground(Color.BLACK);
        fetchButton.setForeground(Color.WHITE);
        fetchButton.setBounds(380, 100, 120, 25);
        fetchButton.addActionListener(this);
        add(fetchButton);
        
        JLabel lblname = new JLabel("NAME");
        lblname.setBounds(60, 140, 150, 25);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblname);
        
        tfname = new JLabel();
        tfname.setBounds(220, 140, 150, 25);
        add(tfname);
        
        JLabel lblsource = new JLabel("FROM:");
        lblsource.setBounds(60, 180, 150, 25);
        lblsource.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblsource);
        
        tfsrc = new JLabel();
        tfsrc.setBounds(220, 180, 150, 25);
        add(tfsrc);
        
        JLabel lbldest = new JLabel("TO:");
        lbldest.setBounds(380, 180, 150, 25);
        lbldest.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbldest);
        
        tfdest = new JLabel();
        tfdest.setBounds(540, 180, 150, 25);
        add(tfdest);
        
        JLabel lblfname = new JLabel("Flight Name");
        lblfname.setBounds(60, 220, 150, 25);
        lblfname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblfname);
        
        tffname = new JLabel();
        tffname.setBounds(220, 220, 150, 25);
        add(tffname);
        
        JLabel lblfcode = new JLabel("Flight Code");
        lblfcode.setBounds(380, 220, 150, 25);
        lblfcode.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblfcode);
        
        tffcode = new JLabel();
        tffcode.setBounds(540, 220, 150, 25);
        add(tffcode);
        
         JLabel lbldate = new JLabel("Date");
        lbldate.setBounds(60, 260, 150, 25);
        lbldate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbldate);
        
        labeldate = new JLabel();
        labeldate.setBounds(220, 260, 150, 25);
        add(labeldate);
        
        JLabel lblseat = new JLabel("SEAT");
        lblseat.setBounds(380, 260, 150, 25);
        lblseat.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblseat);
        
        tfseat = new JLabel();
        tfseat.setBounds(540, 260, 150, 25);
        add(tfseat);
        
        JLabel lblgate = new JLabel("GATE");
        lblgate.setBounds(60, 300, 150, 30);
        lblgate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblgate.setForeground(Color.red);
        add(lblgate);
        
        tfgate = new JLabel();
        tfgate.setBounds(220, 300, 150, 25);
        tfgate.setForeground(Color.red);
        add(tfgate);
        
        JLabel lblboardtime = new JLabel("BOARDING TIME");
        lblboardtime.setBounds(380, 300, 150, 30);
        lblboardtime.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblboardtime.setForeground(Color.red);
        add(lblboardtime);
        
        tfboardtime = new JLabel();
        tfboardtime.setBounds(540, 300, 150, 25);
        tfboardtime.setForeground(Color.red);
        add(tfboardtime);
        
        
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/airindia.png"));
        Image i2 = i1.getImage().getScaledInstance(300, 230, Image.SCALE_DEFAULT);
        ImageIcon image = new ImageIcon(i2);
        JLabel lblimage = new JLabel(image);
        lblimage.setBounds(600, 0, 300, 300);
        add(lblimage);
        
        setSize(1000, 450);
        setLocation(300, 150);
        setVisible(true);
    }
    
    private String generateRandomSeat() {
        Random rand = new Random();
        int maxRows = 30; // Define the number of rows in your flight
        char[] seatLetters = {'A', 'B', 'C', 'D', 'E', 'F'};

        int rowNumber = rand.nextInt(maxRows) + 1; // Generate random row number between 1 and maxRows
        char seatLetter = seatLetters[rand.nextInt(seatLetters.length)]; // Generate random seat letter from A to F

        return rowNumber + String.valueOf(seatLetter);
    }
    
    public void actionPerformed(ActionEvent ae) {
        Random random=new Random();
        String pnr = tfpnr.getText();

        try {
            Conn conn = new Conn();

            String query = "SELECT * FROM reservation WHERE PNR = '" + pnr + "'";
            ResultSet rs = conn.s.executeQuery(query);

            if (rs.next()) {
                tfname.setText(rs.getString("name"));
                tfsrc.setText(rs.getString("src"));
                tfdest.setText(rs.getString("des"));
                tffname.setText(rs.getString("flightname"));
                tffcode.setText(rs.getString("flightcode"));
                labeldate.setText(rs.getString("ddate"));

                String fcode = rs.getString("flightcode"); // Fetch flight code from reservation details

                System.out.println("Reservation details fetched successfully. Flight code: " + fcode);

                // Execute the second query to get flight details
                String flightQuery = "SELECT gate_no, DATE_FORMAT(departure_time, '%H:%i:%s') AS departure_time FROM flight WHERE f_code = '" + fcode + "'";
                ResultSet flightRs = conn.s.executeQuery(flightQuery);

                if (flightRs.next()) {
                    tfgate.setText(flightRs.getString("gate_no"));

                    // Get departure time
                    String departureTimeStr = flightRs.getString("departure_time");
                    System.out.println("Departure Time: " + departureTimeStr);

                    LocalTime departureTime = LocalTime.parse(departureTimeStr);

                    // Calculate boarding time (30 minutes earlier)
                    LocalTime boardingTime = departureTime.minus(30, ChronoUnit.MINUTES);

                    // Format boarding time to 12-hour format with AM/PM
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
                    String formattedBoardingTime = boardingTime.format(formatter);

                    // Set the formatted boarding time
                    tfboardtime.setText(formattedBoardingTime);

                    System.out.println("Flight details fetched successfully.");
                    
                    String seatNumber = generateRandomSeat();
                    tfseat.setText(seatNumber);
                    System.out.println("Assigned Seat Number: " + seatNumber);
                    
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Flight details not found");
                    System.out.println("Flight details not found for flight code: " + fcode);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please enter correct PNR");
                System.out.println("Reservation details not found for PNR: " + pnr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    public static void main(String[] args) {
        new BoardingPass();
    }
}

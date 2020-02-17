/*
Class: UWEAccommodationSystem
Description: Handles the system login.
Created: 27/01/2020
Updated: 27/01/2020
Authors: Michael Tonkin (Michael2.Tonkin@live.uwe.ac.uk)
 */
package uweaccommodationsystem;

import java.util.ArrayList;
import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import uweaccommodationsystem.guicontrollers.Property;

public class UWEAccommodationSystem {

    private static UWEAccommodationSystem uweAccommodationSystem = null;
    private static ArrayList<Hall> halls = new ArrayList<Hall>(); //create a list of halls 
    private ArrayList<Lease> lease = new ArrayList<Lease>();
    private ArrayList<Student> student = new ArrayList<Student>();
    private Room[] room = new Room[12];
    private Random rand = new Random();
    private static char accountType;
    private static ArrayList<Property> property = new ArrayList<Property>();
    private int leaseNum = 1;
    private int studentID = 18034567;
    private String[] names = {"James", "Wei", "Bas", "Raj", "Chris", "Mr Sparkle", "Cat", "Bird", "Dog", "Ryan the Tiger", "Jon Snow", "Rakib",
         "James", "Wei", "Bas", "Raj", "Chris", "Mr Sparkle", "Cat", "Bird", "Dog", "Ryan the Tiger", "Jon Snow", "Rakib",
         "James", "Wei", "Bas", "Raj", "Chris", "Mr Sparkle", "Cat", "Bird", "Dog", "Ryan the Tiger", "Jon Snow", "Rakib"};

    //TODO UPDATE COMMENTS!!!
    private void addRoomsAndLeases(Hall hall) {

        int random = rand.nextInt(2);
        // Add rooms
        for (int x = 0; x < room.length; x++) {
            room[x] = new Room(x);

            //Create lease if random number is 1, else do nothing
            random = rand.nextInt(2);
            if (random == 1) {
                //leaseNum = leaseNum + x;
                lease.add(new Lease(12, leaseNum, Integer.toString(x) + " Frenchay Campus, Bristol, BS16 1ZG")); //TODO add hall name
                //if room has a lease set cleaningstatus and occupancy to Clean and Occupied
                random = rand.nextInt(2);
                //set the cleaning status randomly
                switch (random) {
                    case 1:
                        room[x].setCleaningStatus("Clean");
                        break;
                    default:
                        room[x].setCleaningStatus("Dirty");
                }
                student.add(new Student(studentID + random, names[leaseNum - 1]));
                lease.get(leaseNum - 1).setStudent(student.get(leaseNum - 1));

                room[x].setRoomStatus("Occupied");
                

            } else {
                lease.add(new Lease(0, 0, "")); //TODO 0 = empty
                
                student.add(new Student(0 + random, " "));
                lease.get(leaseNum - 1).setStudent(student.get(leaseNum - 1));
                //if room has a lease set cleaningstatus and occupancy to Offline and Unccupied
                room[x].setCleaningStatus("Offline");
                room[x].setRoomStatus("Unoccupied");
            }
            random = rand.nextInt(1000);

            room[x].setLease(lease.get(leaseNum - 1));

            leaseNum++;

            //}
            hall.addRoom(room[x]);
        }
    }

    /*
    Constructor: UWEAccommodationSystem
    Description: Intialises halls, adds rooms to halls and has a chance to create a new lease for a room based on a random number.
    Created: 12/02/2020
    Updated: 12/02/2020
     */
    private UWEAccommodationSystem() {
        // Create halls
        Hall hall = new Hall("H1", 1, "James Town, Frenchay Campus, Bristol, BS16 1ZG", "0123 177777");
        Hall hall2 = new Hall("H2", 2, "Kun Town, Frenchay Campus, Bristol, BS16 1ZG", "0123 188888");
        Hall hall3 = new Hall("H3", 3, "Jim Town, Frenchay Campus, Bristol, BS16 1ZG", "0123 199999");

        addRoomsAndLeases(hall);
        addRoomsAndLeases(hall2);
        addRoomsAndLeases(hall3);

        this.addHall(hall);
        this.addHall(hall2);
        this.addHall(hall3);

        for (int i = 0; i < halls.size(); i++) {
            //loop through each hall to get the rooms
            for (int j = 0; j < halls.get(i).getRooms().size(); j++) {
                property.add(new Property(
                        halls.get(i).getHallName(),
                        halls.get(i).getHallNum(),
                        halls.get(i).getHallAddress(),
                        halls.get(i).getHallTel(),
                        halls.get(i).getRooms().get(j).getRoomNumber(),
                        halls.get(i).getRooms().get(j).getLease().getLeaseID(),
                        halls.get(i).getRooms().get(j).getLease().getDuration(),
                        halls.get(i).getRooms().get(j).getLease().getRentRate(),
                        halls.get(i).getRooms().get(j).getLease().getStudent().getStudentName(),
                        halls.get(i).getRooms().get(j).getLease().getStudent().getStudentID(),
                        halls.get(i).getRooms().get(j).getRoomStatus(),
                        halls.get(i).getRooms().get(j).getCleaningStatus()
                ));
            }
        }
    }

    public static UWEAccommodationSystem getInstance() {

        if (uweAccommodationSystem == null) {
            uweAccommodationSystem = new UWEAccommodationSystem();
        }

        return uweAccommodationSystem;
    }

    //Function: userLogin
    //Description: called when the user logs in to determine which account they are using.
    //Parameters: String username, String password. Both correspond to a different account.
    public static void userLogin(String username, String password) {
        if (username.equals("warden") && password.equals("pass")) {
            accountType = 'w';
        } else if (username.equals("hallmanager") && password.equals("pass")) {
            accountType = 'h';
        } else if (username.equals("root") && password.equals("pass")) {
            accountType = 'r';
        } else {
            accountType = 'n';
        }
    }

    //Function getAccType
    //Description: returns the type of account that the user has logged in as.
    public static char getAccType() {
        return accountType;
    }

    //Function: getHalls
    //Description: gets halls to be displayed on screen later
    //Warnings: update description when we figures out where this goes
    public static ArrayList getHalls() {
        return halls;
    }

    public static void addHall(Hall hall) {
        halls.add(hall);
    }

    public ArrayList getProperty() {
        return this.property;
    }
}

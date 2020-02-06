/*
Class: UWEAccommodationSystem
Description: Handles the system login.
Created: 27/01/2020
Updated: 27/01/2020
Authors: Michael Tonkin (Michael2.Tonkin@live.uwe.ac.uk)
*/
package uweaccommodationsystem;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class UWEAccommodationSystem{

private static ArrayList<Hall> halls = new ArrayList<Hall>(); //create a list of halls 
private static char accountType;   
    

    //Function: userLogin
    //Description: called when the user logs in to determine which account they are using.
    //Parameters: String username, String password. Both correspond to a different account.
    public static void userLogin(String username, String password)
    {
        if(username.equals("warden") && password.equals("pass"))
            accountType = 'w';
        else if (username.equals("hallmanager") && password.equals("pass"))
            accountType = 'h';
        else if (username.equals("root") && password.equals("pass"))
            accountType = 'r';
        else
            accountType = 'n';
    }
    
    //Function getAccType
    //Description: returns the type of account that the user has logged in as.
    public static char getAccType()
    {
        return accountType;
    }
    
    //Function: getHalls
    //Description: gets halls to be displayed on screen later
    //Warnings: update description when we figures out where this goes
    public static ArrayList getHalls()
    {
	return halls;
    }
    
    public static void addHall(Hall hall)
    {
	halls.add(hall);
    }
}

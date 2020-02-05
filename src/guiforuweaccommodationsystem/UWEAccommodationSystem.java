/*
Class: UWEAccommodationSystem
Description: constitutes the driver class. Allows for access to the rest of the
    program.
Created: 27/01/2020
Updated: 27/01/2020
Authors: Michael Tonkin (Michael2.Tonkin@live.uwe.ac.uk)
*/
package guiforuweaccommodationsystem;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class UWEAccommodationSystem extends Application {

private ArrayList<Hall> halls = new ArrayList<Hall>(); //create a list of halls 
private char accountType;   

    @Override
    public void start(Stage primaryStage) {
        
        StackPane root = new StackPane();
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("UWE Accommodation System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    //Function: userLogin
    //Description: called when the user logs in to determine which account they are using.
    //Parameters: String username, String password. Both correspond to a different account.
    public void userLogin(String username, String password)
    {
        if(username == "warden" && password == "pass")
            accountType = 'w';
        else if (username == "hallmanager" && password == "pass")
            accountType = 'h';
        else if (username == "root" && password == "pass")
            accountType = 'r';
    }
    
    //Function getAccType
    //Description: returns the type of account that the user has logged in as.
    public char getAccType()
    {
        return accountType;
    }
    
    //Function: getHalls
    //Description: gets halls to be displayed on screen later
    //Warnings: update description when we figures out where this goes
    public ArrayList getHalls()
    {
	return halls;
    }
}

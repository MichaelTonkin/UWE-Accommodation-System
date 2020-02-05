/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uweaccommodationsystem.guicontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import uweaccommodationsystem.UWEAccommodationSystem;
 
public class FXMLLoginViewController {
    
    private char account;
    
    
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    
 //method: changeScreenButtonClicked
 //parameters: ActionEvent event - parsed by javafx.
 //Description: Activates when the login button is clicked, evaluating if the account details are correct before
 //taking the user to the table view interface.
 public void changeScreenButtonClicked(ActionEvent event) throws Exception {
        String usr = usernameField.getText();
        String pas = passwordField.getText();
        
        System.out.println(usr + pas);
        
        UWEAccommodationSystem.userLogin(usr, pas); //allow the program to process the user's login details.
        account = UWEAccommodationSystem.getAccType();
        
        System.out.println(account);
        
        //check to see if the login was valid (n is an invalid login code).
        if (account != 'n')
        {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/FXMLTableView.fxml"));
        Scene scene = new Scene(root);
        //set stage to window and get stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("UWEAccommodationSystem");
        window.setScene(scene);
        window.show();
        }
    }
}
   
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
    
    @FXML
    private Text actiontarget;
    private TextField usernameField;
    private PasswordField passwordField;
    
    @FXML 
 protected void handleSubmitButtonAction(ActionEvent event) {
        actiontarget.setText("Sign in button pressed");
    }
 public void ChangeScreenButtonClicked(ActionEvent event) throws Exception {
     
        UWEAccommodationSystem.userLogin(usernameField.toString(), passwordField.toString()); //allow the program to process the user's login details.
        account = UWEAccommodationSystem.getAccType();
        
        //check to see if the login was valid (n is an invalid login code).
        if (account == 'n')
        {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLTableView.fxml"));
        Scene scene = new Scene(root);
        //set stage to window and get stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("UWEAccommodationSystem");
        window.setScene(scene);
        window.show();
        }
    }
}
   
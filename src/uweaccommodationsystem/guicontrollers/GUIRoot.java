/*
Class: GUIRoot.
Description: The launching point for this application. Launches the login window.
Created: 30/01/2020 (Benyadilok).
Updated: 05/02/2020 (Tonkin).
Authors: Asia Benyadilok, Michael Tonkin.
*/
package uweaccommodationsystem.guicontrollers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class GUIRoot extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("fxml/FXMLLoginView.fxml"));
        
        Scene scene = new Scene(root, 400, 275);
    
        stage.setTitle("UWEAccommodationSystem");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uweaccommodationsystem.guicontrollers;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import uweaccommodationsystem.TestProperty;

/**
 * FXML Controller class
 *
 * @author asia
 */
public class FXMLTableViewController implements Initializable {
    public TableView<TestProperty> tableView;
    public TableColumn<TestProperty,Integer> ColLeaseNum;
    public TableColumn<TestProperty,String> ColHallName;
    public TableColumn<TestProperty,Integer> ColHallNum;
    public TableColumn<TestProperty,Integer> ColRoomNum;
    public TableColumn<TestProperty,String> ColStudentName;
    public TableColumn<TestProperty,String> ColOccupancy;
    public TableColumn<TestProperty,String> ColCleaningStatus;
    public TextField TextFieldLeaseNum;
    public TextField TextFieldHallName;
    public TextField TextFieldHallNum;
    public TextField TextFieldOccupancy;
    public TextField TextFieldRoomNum;
    public TextField TextFieldStudentName;
    private TestProperty TestProperty;

    
    

    @FXML
    public void ChangeScreenButtonClicked(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLLoginView.fxml"));
        
        Scene scene = new Scene(root);
        //set stage to window and get stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("UWEAccommodationSystem");
        window.setScene(scene);
        window.show();
    }
    
    /**
     * Called when the user clicks save.
     */
    @FXML
    private void handleSaveTestproperty() {
        if (isInputValid()){
        TestProperty selectedTestproperty = tableView.getSelectionModel().getSelectedItem();
        selectedTestproperty.setLeaseNum(Integer.parseInt(TextFieldLeaseNum.getText()));
        selectedTestproperty.setHallName(TextFieldHallName.getText());
        selectedTestproperty.setHallNum(Integer.parseInt(TextFieldHallNum.getText()));
        selectedTestproperty.setRoomNum(Integer.parseInt(TextFieldRoomNum.getText()));
        selectedTestproperty.setStudentName(TextFieldStudentName.getText());
        selectedTestproperty.setOccupancy(TextFieldOccupancy.getText());
        
        //get index from row selected
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        //update tableList
        observableList.set(selectedIndex, selectedTestproperty);
        //show new updated
        showTestPropertyDetails(selectedTestproperty);
        }
        }
   
     @FXML
    //actions for delete property
    private void handleDeleteTestProperty() {
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
           tableView.getItems().remove(selectedIndex);
        }      
        else {
        // Nothing selected.
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("No Selection");
        alert.setHeaderText("No Data Selected");
        alert.setContentText("Please select a data in the table.");

        alert.showAndWait();
        }
    }
    
     /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (TextFieldHallName.getText() == null || TextFieldHallName.getText().length() == 0) {
            errorMessage += "No valid hall name!\n"; 
        }
        if (TextFieldOccupancy.getText() == null || TextFieldOccupancy.getText().length() == 0) {
            errorMessage += "No valid occupancy!\n"; 
        }
        if (TextFieldStudentName.getText() == null || TextFieldStudentName.getText().length() == 0) {
            errorMessage += "No valid student name!\n"; 
        }

        if (TextFieldLeaseNum.getText() == null || TextFieldLeaseNum.getText().length() == 0) {
            errorMessage += "No lease number !\n"; 
        } else {
            // try to parse the lease num into an int.
            try {
                Integer.parseInt(TextFieldLeaseNum.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid lease number (must be an integer)!\n"; 
            }
        }
         
        if (TextFieldHallNum.getText() == null || TextFieldHallNum.getText().length() == 0) {
            errorMessage += "No hall number!\n"; 
        }  else {
            // try to parse the hall num into an int.
            try {
                Integer.parseInt(TextFieldHallNum.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid hall number (must be an integer)!\n"; 
            }
        }
        
        if (TextFieldRoomNum.getText() == null || TextFieldRoomNum.getText().length() == 0) {
            errorMessage += "No valid room number!\n"; 
        }  else {
            // try to parse the room num into an int.
            try {
                Integer.parseInt(TextFieldRoomNum.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid room number (must be an integer)!\n"; 
            }
        }
         

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
        }
    }
    
    /**
 * Fills all text fields to show details about the person.
 * If the specified person is null, all text fields are cleared.
 * 
 * @param person the person or null
 */
    private void showTestPropertyDetails(TestProperty TestProperty) {
        if (TestProperty != null) {
            // Fill the labels with info from the person object.
            TextFieldLeaseNum.setText(Integer.toString(TestProperty.getLeaseNum()));
            TextFieldHallName.setText(TestProperty.getHallName());
            TextFieldHallNum.setText(Integer.toString(TestProperty.getHallNum()));
            TextFieldRoomNum.setText(Integer.toString(TestProperty.getRoomNum()));
            TextFieldStudentName.setText(TestProperty.getStudentName());
            TextFieldOccupancy.setText(TestProperty.getOccupancy());
       //     ColCleaningStatus.setText(TestProperty.getCleaningStatus());
            

            // TODO: We need a way to convert the birthday into a String! 
            // birthdayLabel.setText(...);
        } else {
            // Person is null, remove all the text.
            TextFieldLeaseNum.setText("");
            TextFieldHallName.setText("");
            TextFieldHallNum.setText("");
            TextFieldRoomNum.setText("");
            TextFieldStudentName.setText("");
            TextFieldOccupancy.setText("");
        //    ColCleaningStatus.setText("");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ColLeaseNum.setCellValueFactory(new PropertyValueFactory<>("LeaseNum"));
        ColHallName.setCellValueFactory(new PropertyValueFactory<>("HallName"));
        ColHallNum.setCellValueFactory(new PropertyValueFactory<>("HallNum"));
        ColRoomNum.setCellValueFactory(new PropertyValueFactory<>("RoomNum"));
        ColStudentName.setCellValueFactory(new PropertyValueFactory<>("StudentName"));
        ColOccupancy.setCellValueFactory(new PropertyValueFactory<>("Occupancy"));
        ColCleaningStatus.setCellValueFactory(new PropertyValueFactory<>("CleaningStatus"));
        //show items on tableView
        tableView.setItems(observableList);
        
        // Clear Testproperty details.
        showTestPropertyDetails(null);
        // Listen for selection changes and show the person details when changed.
        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showTestPropertyDetails(newValue));
    }
        ObservableList<TestProperty> observableList = FXCollections.observableArrayList(
                new TestProperty(564,"Michael",16,69,"Christy","occupied","dirty")
                ,new TestProperty(123,"Michael1",156,679,"Christy1","occupied","clean")
        );
               
        
      
    
}

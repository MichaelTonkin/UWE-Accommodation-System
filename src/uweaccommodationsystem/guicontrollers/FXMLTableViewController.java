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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import uweaccommodationsystem.Hall;
import uweaccommodationsystem.UWEAccommodationSystem;

/**
 * FXML Controller class
 *
 * @author asia
 */
public class FXMLTableViewController implements Initializable {
    
    public TableView<Property> tableView;
    
    public TableColumn<Property,Integer> ColLeaseNum;
    public TableColumn<Property,String> ColHallName;
    public TableColumn<Property,Integer> ColHallNum;
    public TableColumn<Property,Integer> ColRoomNum;
    public TableColumn<Property,String> ColStudentName;
    public TableColumn<Property,String> ColOccupancy;
    public TableColumn<Property,String> ColCleaningStatus;
    
    public TextField TextFieldLeaseNum;
    public TextField TextFieldLeaseDuration;
    public TextField TextFieldStudentName;
    public TextField TextFieldStudentID;
    public TextField TextFieldMonthlyRentRate;
    public ComboBox comboBoxCleaningStatus;
    public ComboBox comboBoxOccupancy;
    
    public Label labelHallName;
    public Label labelHallNum;
    public Label labelHallAddress;
    public Label labelHallTel;
    public Label labelRoomNum;
    public Label labelLeaseNum;
    public Label labelLeaseDuration;
    public Label labelStudentName;
    public Label labelStudentID;
    public Label labelMonthlyRentRate;
    public Label labelOccupancy;
    public Label labelCleaningStatus;
    
    public Button DeleteButton;
    public Button SaveButton;

  


    @FXML
    public void ChangeScreenButtonClicked(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/FXMLLoginView.fxml"));
        
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
    private void handleSaveProperty() {
        if(isInputValid()){
            Property selectedProperty = tableView.getSelectionModel().getSelectedItem();
            selectedProperty.setLeaseNum(Integer.parseInt(TextFieldLeaseNum.getText()));
            selectedProperty.setStudentName(TextFieldStudentName.getText());
            selectedProperty.setStudentID(Integer.parseInt(TextFieldStudentID.getText()));
            selectedProperty.setMonthlyRentRate(Double.parseDouble(TextFieldMonthlyRentRate.getText()));
            selectedProperty.setOccupancy((String) comboBoxOccupancy.getValue());
            selectedProperty.setCleaningStatus((String) comboBoxCleaningStatus.getValue());

            //get index from row selected
            int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
            //update tableList
            observableList.set(selectedIndex, selectedProperty);
            //show new updated
            showPropertyDetails(selectedProperty);
        }
    }
   
     @FXML
    //actions for delete property
    private void handleDeleteProperty() {
        Property selectedProperty = tableView.getSelectionModel().getSelectedItem();
        selectedProperty.setLeaseNum(0);
        selectedProperty.setLeaseDuration(0);
        selectedProperty.setStudentName("");
        selectedProperty.setStudentID(0);
        selectedProperty.setMonthlyRentRate(0);
        selectedProperty.setOccupancy("Unoccupied");
        selectedProperty.setCleaningStatus("Offline");
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            observableList.set(selectedIndex, selectedProperty);
            showPropertyDetails(selectedProperty);
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


       if (comboBoxOccupancy.getValue() == "Occupied")
       {
                    
        if (TextFieldStudentName.getText() == null || TextFieldStudentName.getText().length() == 0) {
            errorMessage += "No valid student name!\n"; 
        }
        
        if (TextFieldStudentID.getText() == null || TextFieldLeaseNum.getText().length() == 0) {
            errorMessage += "No valid Student ID !\n"; 
        } else {
            // try to parse the Student ID into an int.
            try {
                Integer.parseInt(TextFieldStudentID.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid Student ID (must be an integer)!\n"; 
            }
        }

        if (TextFieldLeaseNum.getText() == null || TextFieldLeaseNum.getText().length() == 0) {
            errorMessage += "No valid lease number !\n"; 
        } else {
            // try to parse the lease num into an int.
            try {
                Integer.parseInt(TextFieldLeaseNum.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid lease number (must be an integer)!\n"; 
            }
        }
        
         if (TextFieldLeaseDuration.getText() == null || TextFieldLeaseDuration.getText().length() == 0) {
            errorMessage += "No valid lease Duration !\n"; 
        } else {
            // try to parse the lease Duration into an int.
            try {
                Integer.parseInt(TextFieldLeaseDuration.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid lease Duration (must be an integer)!\n"; 
            }
        }
         
         if (TextFieldMonthlyRentRate.getText() == null || TextFieldMonthlyRentRate.getText().length() == 0) {
            errorMessage += "No valid Monthly Rent Rate !\n"; 
        } else {
            // try to parse the Monthly Rent Rate into an int.
            try {
                Double.parseDouble(TextFieldMonthlyRentRate.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid Monthly Rent Rate (must be a double)!\n"; 
            }
        }
         if (comboBoxCleaningStatus.getValue()=="Offline")
         {
             errorMessage += "This room is Occupied cannot change to Offline\n";
         }
         
       } 
       
       if(comboBoxOccupancy.getValue() == "Unoccupied")
       {
           if(TextFieldLeaseNum.getText()!= ""
           || TextFieldLeaseDuration.getText() != ""
           || TextFieldStudentName.getText() != ""
           || TextFieldStudentID.getText() !=  ""
           || TextFieldMonthlyRentRate.getText() != "")
           {
                errorMessage += "This room is Unoccupied cannot have Lease\n";
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
 * Fills all text fields to show details about the property.
 * If the specified property is null, all text fields are cleared.
 * 
 * @param property the person or null
 */
    private void showPropertyDetails(Property TestProperty) {
        if (TestProperty != null) {
            // Fill the labels with info from the property object.
            TextFieldLeaseNum.setText(Integer.toString(TestProperty.getLeaseNum()));
            TextFieldLeaseDuration.setText(Integer.toString(TestProperty.getLeaseDuration()));
            TextFieldStudentName.setText(TestProperty.getStudentName());
            TextFieldStudentID.setText(Integer.toString(TestProperty.getStudentID()));
            TextFieldMonthlyRentRate.setText(Double.toString(TestProperty.getMonthlyRentRate()));
            comboBoxOccupancy.setValue(TestProperty.getOccupancy());
            comboBoxCleaningStatus.setValue(TestProperty.getCleaningStatus());
       
            labelHallName.setText(TestProperty.getHallName());
            labelHallNum.setText(Integer.toString(TestProperty.getHallNum()));
            labelHallAddress.setText(TestProperty.getHallAddress());
            labelHallTel.setText(TestProperty.getHallTel());
            labelRoomNum.setText(Integer.toString(TestProperty.getRoomNum()));
            labelLeaseNum.setText(Integer.toString(TestProperty.getLeaseNum()));
            labelLeaseDuration.setText(Integer.toString(TestProperty.getLeaseDuration()));
            labelStudentName.setText(TestProperty.getStudentName());
            labelStudentID.setText(Integer.toString(TestProperty.getStudentID()));
            labelMonthlyRentRate.setText(Double.toString(TestProperty.getMonthlyRentRate()));
            labelOccupancy.setText(TestProperty.getOccupancy());
            labelCleaningStatus.setText(TestProperty.getCleaningStatus());
            
            if (comboBoxOccupancy.getValue() == "Unoccupied")
            {
             labelLeaseNum.setText("");
             labelLeaseDuration.setText("");
             labelStudentID.setText("");
             labelMonthlyRentRate.setText("");
             TextFieldLeaseNum.setText("");
             TextFieldLeaseDuration.setText("");
             TextFieldStudentName.setText("");
             TextFieldStudentID.setText("");
             TextFieldMonthlyRentRate.setText("");
            }
            
        } else {
            // Person is null, remove all the text.
            TextFieldLeaseNum.setText("");
            TextFieldLeaseDuration.setText("");
            TextFieldStudentName.setText("");
            TextFieldStudentID.setText("");
            TextFieldMonthlyRentRate.setText("");
            comboBoxOccupancy.setValue("");
            comboBoxCleaningStatus.setValue("");
           
        }
    }

    @FXML
    private void handleClickProperty() {
         // Property selectedProperty = tableView.getSelectionModel().getSelectedItem();
          if(comboBoxOccupancy.getValue() == "Unoccupied")
        {

                TextFieldStudentID.setDisable(true);
                TextFieldMonthlyRentRate.setDisable(true);
                TextFieldLeaseNum.setDisable(true);
                TextFieldLeaseDuration.setDisable(true);
                TextFieldStudentName.setDisable(true);
                SaveButton.setDisable(true);

        }
        if  (comboBoxOccupancy.getValue() == "Occupied"){
            TextFieldStudentID.setDisable(false);
            TextFieldMonthlyRentRate.setDisable(false);
            TextFieldLeaseNum.setDisable(false);
            TextFieldLeaseDuration.setDisable(false);
            TextFieldStudentName.setDisable(false);  
            SaveButton.setDisable(false);
            }
       
    }
     @FXML
     private void handleClickStatusBar() {
          if(comboBoxOccupancy.getValue() == "Unoccupied")
        {

                TextFieldStudentID.setDisable(true);
                TextFieldMonthlyRentRate.setDisable(true);
                TextFieldLeaseNum.setDisable(true);
                TextFieldLeaseDuration.setDisable(true);
                TextFieldStudentName.setDisable(true);
                comboBoxCleaningStatus.setValue("Offline");
                SaveButton.setDisable(true);

        }
        if  (comboBoxOccupancy.getValue() == "Occupied"){
            TextFieldStudentID.setDisable(false);
            TextFieldMonthlyRentRate.setDisable(false);
            TextFieldLeaseNum.setDisable(false);
            TextFieldLeaseDuration.setDisable(false);
            TextFieldStudentName.setDisable(false);      
            comboBoxCleaningStatus.setValue("Clean");
              SaveButton.setDisable(false);
            
            }
        
          
       
    }
    
    /*
    Method: intialize
    Description: populates the screen with details from system data and relevant editting buttons.
                 Account authentication is partly done here.
    Parameters: Handled by javafx, do not change.
    Warning: Do not attempt to call this method anywhere else in the program.
    */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        ColLeaseNum.setCellValueFactory(new PropertyValueFactory<>("LeaseNum"));
        ColStudentName.setCellValueFactory(new PropertyValueFactory<>("StudentName"));
        ColHallName.setCellValueFactory(new PropertyValueFactory<>("HallName"));
        ColHallNum.setCellValueFactory(new PropertyValueFactory<>("HallNum"));
        ColRoomNum.setCellValueFactory(new PropertyValueFactory<>("RoomNum"));        
        ColOccupancy.setCellValueFactory(new PropertyValueFactory<>("Occupancy"));
        ColCleaningStatus.setCellValueFactory(new PropertyValueFactory<>("CleaningStatus"));
                
        //Here we are checking that the user has the privillages to view Lease and Student details
        //if the account is warden then they will be unable to view leasenumber and student name.
        if(UWEAccommodationSystem.getAccType() == 'w')
        {
            ColLeaseNum.setVisible(false);
            ColStudentName.setVisible(false);
            
            TextFieldStudentID.setDisable(true);
            TextFieldMonthlyRentRate.setDisable(true);
            TextFieldLeaseNum.setDisable(true);
            TextFieldLeaseDuration.setDisable(true);
            TextFieldStudentName.setDisable(true);
            comboBoxOccupancy.setDisable(true);
            DeleteButton.setVisible(false);

            
        }
        
        if(UWEAccommodationSystem.getAccType() == 'h')
        {
            //Disable Cleaning Status if logged in as hallmanager
            comboBoxCleaningStatus.setDisable(true);
        }
     
        //show items on tableView
        tableView.setItems(observableList);
        
        //set cleaning status option
        comboBoxCleaningStatus.setItems(cleaningStatusList);
        
        //set occupancy options
        comboBoxOccupancy.setItems(occupancyList);
        
        
        // Clear Testproperty details.
        showPropertyDetails(null);
        // Listen for selection changes and show the person details when changed.
        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showPropertyDetails(newValue));

        
        

    }
        ObservableList<Property> observableList = FXCollections.observableArrayList(
                UWEAccommodationSystem.getInstance().getProperty()
        );
        
        ObservableList<String> cleaningStatusList = FXCollections.observableArrayList("Clean","Dirty","Offline");
        ObservableList<String> occupancyList = FXCollections.observableArrayList("Occupied","Unoccupied");
     
  
}

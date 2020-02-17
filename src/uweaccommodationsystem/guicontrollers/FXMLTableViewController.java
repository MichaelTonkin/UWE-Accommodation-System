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
    
    public TableColumn<Property,Integer> colLeaseNum;
    public TableColumn<Property,String> colHallName;
    public TableColumn<Property,Integer> colHallNum;
    public TableColumn<Property,Integer> colRoomNum;
    public TableColumn<Property,String> colStudentName;
    public TableColumn<Property,String> colOccupancy;
    public TableColumn<Property,String> colCleaningStatus;
    
    public TextField textFieldLeaseNum;
    public TextField textFieldLeaseDuration;
    public TextField textFieldStudentName;
    public TextField textFieldStudentID;
    public TextField textFieldMonthlyRentRate;
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
    
    public Button deleteButton;
    public Button saveButton;

  


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
            selectedProperty.setLeaseNum(Integer.parseInt(textFieldLeaseNum.getText()));
            selectedProperty.setStudentName(textFieldStudentName.getText());
            selectedProperty.setStudentID(Integer.parseInt(textFieldStudentID.getText()));
            selectedProperty.setMonthlyRentRate(Double.parseDouble(textFieldMonthlyRentRate.getText()));
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

        /*
        if (TextFieldOccupancy.getText() == null || TextFieldOccupancy.getText().length() == 0) {
            errorMessage += "No valid occupancy!\n"; 
        }
        */
        if (textFieldStudentName.getText() == null || textFieldStudentName.getText().length() == 0) {
            errorMessage += "No valid student name!\n"; 
        }

        if (textFieldLeaseNum.getText() == null || textFieldLeaseNum.getText().length() == 0) {
            errorMessage += "No lease number !\n"; 
        } else {
            // try to parse the lease num into an int.
            try {
                Integer.parseInt(textFieldLeaseNum.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid lease number (must be an integer)!\n"; 
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
    private void showPropertyDetails(Property TestProperty) {
        if (TestProperty != null) {
            // Fill the labels with info from the person object.
            textFieldLeaseNum.setText(Integer.toString(TestProperty.getLeaseNum()));
            /*
            TextFieldHallName.setText(TestProperty.getHallName());
            TextFieldHallNum.setText(Integer.toString(TestProperty.getHallNum()));
            TextFieldRoomNum.setText(Integer.toString(TestProperty.getRoomNum()));
            */
            
            textFieldStudentName.setText(TestProperty.getStudentName());
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
            

        } else {
            // Person is null, remove all the text.
            textFieldLeaseNum.setText("");
            textFieldStudentName.setText("");
            comboBoxOccupancy.setValue("");
            comboBoxCleaningStatus.setValue("");

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
        
        
        colLeaseNum.setCellValueFactory(new PropertyValueFactory<>("LeaseNum"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("StudentName"));
        colHallName.setCellValueFactory(new PropertyValueFactory<>("HallName"));
        colHallNum.setCellValueFactory(new PropertyValueFactory<>("HallNum"));
        colRoomNum.setCellValueFactory(new PropertyValueFactory<>("RoomNum"));        
        colOccupancy.setCellValueFactory(new PropertyValueFactory<>("Occupancy"));
        colCleaningStatus.setCellValueFactory(new PropertyValueFactory<>("CleaningStatus"));
                
        //Here we are checking that the user has the privillages to view Lease and Student details
        //if the account is warden then they will be unable to view leasenumber and student name.
       /*
        if(UWEAccommodationSystem.getAccType() == 'w')
        {
            colLeaseNum.setVisible(false);
            colStudentName.setVisible(false);
            
            textFieldLeaseNum.setDisable(true);
            textFieldLeaseDuration.setDisable(true);
            textFieldStudentName.setDisable(true);
            comboBoxOccupancy.setDisable(true);
            deleteButton.setVisible(false);

            
        }
        */
        
        //show items on tableView
        tableView.setItems(observableList);
        
        //set initial cleaning status to clean
        comboBoxCleaningStatus.setValue("clean");
        comboBoxCleaningStatus.setItems(cleaningStatusList);
        
        //set initial occupancy to unoccupied
        comboBoxOccupancy.setValue("Unoccupied");
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

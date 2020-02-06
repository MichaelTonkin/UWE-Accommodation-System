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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import uweaccommodationsystem.Hall;
import uweaccommodationsystem.Property;
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
    public TextField TextFieldHallName;
    public TextField TextFieldHallNum;
    public TextField TextFieldHallAddress;
    public TextField TextFieldHallTel;
    public TextField TextFieldOccupancy;
    public TextField TextFieldRoomNum;
    public TextField TextFieldStudentName;
    
    public Button DeleteButton;
    public Button SaveButton;
    public Button AddButton;
    public Button handleAddButton;
    
    private Property TestProperty;


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
    
    /*
    Method: handleAddButton
    Description: evalutes user input and creates necessary objects.
    */
    @FXML
    private void handleAddButton()
    {
               
        //Create a new hall if we have all the necessary details
        if(!TextFieldHallName.getText().equals("") 
                && !TextFieldHallNum.getText().equals("") 
                && !TextFieldHallAddress.getText().equals("")
                && !TextFieldHallTel.getText().equals(""))
        {
            //Create new hall object
            Hall hall = new Hall(TextFieldHallName.getText(), Integer.parseInt(TextFieldHallNum.getText()), TextFieldHallAddress.getText(), TextFieldHallTel.getText());
            UWEAccommodationSystem.addHall(hall);    
            observableList.add(new Property(0, hall.getHallName(), hall.getHallNum(), 0, "", "Empty", "Offline"));
        }
        
    }
    
    /**
     * Called when the user clicks save.
     */
    @FXML
    private void handleSaveProperty() {

            Property selectedProperty = tableView.getSelectionModel().getSelectedItem();
            selectedProperty.setLeaseNum(Integer.parseInt(TextFieldLeaseNum.getText()));
            selectedProperty.setRoomNum(Integer.parseInt(TextFieldRoomNum.getText()));
            selectedProperty.setStudentName(TextFieldStudentName.getText());
            selectedProperty.setOccupancy(TextFieldOccupancy.getText());

            if(TextFieldHallName.getText() == null)
            {
                selectedProperty.setHallName(TextFieldHallName.getText());
                selectedProperty.setHallNum(Integer.parseInt(TextFieldHallNum.getText()));
            }
            
            //get index from row selected
            int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
            //update tableList
            observableList.set(selectedIndex, selectedProperty);
            //show new updated
            showPropertyDetails(selectedProperty);
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
    private void showPropertyDetails(Property TestProperty) {
        if (TestProperty != null) {
            // Fill the labels with info from the person object.
            TextFieldLeaseNum.setText(Integer.toString(TestProperty.getLeaseNum()));
            TextFieldHallName.setText(TestProperty.getHallName());
            TextFieldHallNum.setText(Integer.toString(TestProperty.getHallNum()));
            TextFieldRoomNum.setText(Integer.toString(TestProperty.getRoomNum()));
            TextFieldStudentName.setText(TestProperty.getStudentName());
            TextFieldOccupancy.setText(TestProperty.getOccupancy());
       
            //we disable the hall category because we do not want to create a new hall every time we edit.
            TextFieldHallName.setEditable(false);
            TextFieldHallNum.setEditable(false);            
            TextFieldHallAddress.setEditable(false);
            

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
            
            TextFieldLeaseNum.setVisible(false);
            TextFieldHallName.setVisible(false);
            TextFieldOccupancy.setVisible(false);
            TextFieldRoomNum.setVisible(false);
            TextFieldStudentName.setVisible(false);
            TextFieldHallNum.setVisible(false);
            
            DeleteButton.setVisible(false);
            AddButton.setVisible(false);
            
        }
        
        
        //show items on tableView
        tableView.setItems(observableList);
        
        // Clear Testproperty details.
        showPropertyDetails(null);
        // Listen for selection changes and show the person details when changed.
        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showPropertyDetails(newValue));
    }
        ObservableList<Property> observableList = FXCollections.observableArrayList(
                //new Property(Hall test)
               // ,new Property(123,"Michael1",156,679,"Christy1","occupied","clean")
        );
                   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uweaccommodationsystem;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author asia
 */
public class Property {
    
    private SimpleIntegerProperty leaseNum;
    private SimpleStringProperty hallName;
    private SimpleIntegerProperty hallNum;
    private SimpleIntegerProperty roomNum;
    private SimpleStringProperty studentName;
    private SimpleStringProperty occupancy;
    private SimpleStringProperty cleaningStatus;
    
    public Property(int leaseNum, String hallName, int hallNum, int roomNum, String studentName, String occupancy, String cleaningStatus ){
        
     this.leaseNum = new SimpleIntegerProperty(leaseNum);
     this.hallName = new SimpleStringProperty(hallName);
     this.hallNum = new SimpleIntegerProperty(hallNum);
     this.roomNum = new SimpleIntegerProperty(roomNum);
     this.studentName = new SimpleStringProperty(studentName);
     this.occupancy = new SimpleStringProperty(occupancy);
     this.cleaningStatus = new SimpleStringProperty(cleaningStatus);
  
    }

    Property() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setLeaseNum(int leaseNum) {
        this.leaseNum = new SimpleIntegerProperty(leaseNum);
    }

    public void setHallName(String hallName) {
        this.hallName = new SimpleStringProperty(hallName);
    }

    public void setHallNum(int hallNum) {
        this.hallNum = new SimpleIntegerProperty(hallNum);
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = new SimpleIntegerProperty(roomNum);
    }

    public void setStudentName(String studentName) {
        this.studentName = new SimpleStringProperty(studentName);
    }

    public void setOccupancy(String occupancy) {
        this.occupancy = new SimpleStringProperty(occupancy);
    }

    public void setCleaningStatus(String cleaningStatus) {
        this.cleaningStatus = new SimpleStringProperty(cleaningStatus);
    }

    public int getLeaseNum() {
        return leaseNum.get();
    }

    public String getHallName() {
        return hallName.get();
    }

    public int getHallNum() {
        return hallNum.get();
    }

    public int getRoomNum() {
        return roomNum.get();
    }

    public String getStudentName() {
        return studentName.get();
    }

    public String getOccupancy() {
        return occupancy.get();
    }

    public String getCleaningStatus() {
        return cleaningStatus.get();
    }
    
    
    
}

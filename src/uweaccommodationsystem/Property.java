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
    
    private SimpleIntegerProperty LeaseNum;
    private SimpleStringProperty HallName;
    private SimpleIntegerProperty HallNum;
    private SimpleIntegerProperty RoomNum;
    private SimpleStringProperty StudentName;
    private SimpleStringProperty Occupancy;
    private SimpleStringProperty CleaningStatus;
    
    public Property(int LeaseNum,String HallName,int HallNum,int RoomNum,String StudentName,String Occupancy,String CleaningStatus ){
        
     this.LeaseNum = new SimpleIntegerProperty(LeaseNum);
     this.HallName = new SimpleStringProperty(HallName);
     this.HallNum = new SimpleIntegerProperty(HallNum);
     this.RoomNum = new SimpleIntegerProperty(RoomNum);
     this.StudentName = new SimpleStringProperty(StudentName);
     this.Occupancy = new SimpleStringProperty(Occupancy);
     this.CleaningStatus = new SimpleStringProperty(CleaningStatus);
  
    }

    Property() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setLeaseNum(int LeaseNum) {
        this.LeaseNum = new SimpleIntegerProperty(LeaseNum);
    }

    public void setHallName(String HallName) {
        this.HallName = new SimpleStringProperty(HallName);
    }

    public void setHallNum(int HallNum) {
        this.HallNum = new SimpleIntegerProperty(HallNum);
    }

    public void setRoomNum(int RoomNum) {
        this.RoomNum = new SimpleIntegerProperty(RoomNum);
    }

    public void setStudentName(String StudentName) {
        this.StudentName = new SimpleStringProperty(StudentName);
    }

    public void setOccupancy(String Occupancy) {
        this.Occupancy = new SimpleStringProperty(Occupancy);
    }

    public void setCleaningStatus(String CleaningStatus) {
        this.CleaningStatus = new SimpleStringProperty(CleaningStatus);
    }

    public int getLeaseNum() {
        return LeaseNum.get();
    }

    public String getHallName() {
        return HallName.get();
    }

    public int getHallNum() {
        return HallNum.get();
    }

    public int getRoomNum() {
        return RoomNum.get();
    }

    public String getStudentName() {
        return StudentName.get();
    }

    public String getOccupancy() {
        return Occupancy.get();
    }

    public String getCleaningStatus() {
        return CleaningStatus.get();
    }
    
    
    
}

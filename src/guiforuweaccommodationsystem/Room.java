package guiforuweaccommodationsystem;

public class Room{
    private int roomNumber;
    private boolean roomStatus; //false = unoccupied, true = occupied
    private String cleaningStatus;
    private Lease lease;
    
    public Room(int roomNumber)
    {
        this.roomNumber = roomNumber;
    }
    
    public int getRoomNumber()
    {
        return this.roomNumber; 
    }
    
    public boolean getRoomstatus()
    {
        return this.roomStatus;
    }
    
    public String getCleaningStatus()
    {
        return this.cleaningStatus;
    }
    
    public void setCleaningStatus(String cleaningStatus)
    {
        this.cleaningStatus = cleaningStatus;
    }
  
    public void setRoomStatus(boolean roomStatus)
    {
        this.roomStatus = roomStatus;
    }
    
    public void setLease(Lease lease)
    {
        this.lease = lease;
    }

}

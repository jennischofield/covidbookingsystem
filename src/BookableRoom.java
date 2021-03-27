
/**
 * A BookableRoom represents a timeslot for a Room to be used to administer a COVID test
 * @author Jenni Schofield
 * @version 24/02/2021
 */
public class BookableRoom
{
    private String roomCode;
    private String timeSlot;
    private int capacity;
    private int occupancy =0;
    private String status = "EMPTY";
    /**
     * Constructor for objects of class AssistantOnShift
     * @param c code of the room
     * @param t timeslot of the room
     * @param cap capacity of the room
     */
    public BookableRoom(String c, String t, int cap )
    {
        // initialise instance variables
        roomCode = c;
        timeSlot =  t;
        capacity = cap;
    }
    /**
     * Increases the occupancy of the BookableRoom
     */
    public void increaseOccupancy(){
        System.out.println(occupancy + " " + capacity);
        if(status.equals("FULL")){
            throw new RuntimeException("Occupancy can never be bigger than the capacity");
        
        }
        occupancy++;
        if(occupancy == capacity){
            status = "FULL";
        }else{
            status = "AVAILABLE";
        }
    }
    /**
     * Decreases the occupancy of the BookableRoom
     */
    public void decreaseOccupancy(){
        if(status.equals("EMPTY")){
            throw new RuntimeException("Cannot decrease anymore, room is already empty.");
        }
        occupancy--;
        if(occupancy ==0){
            status = "EMPTY";
        }else{
            status = "AVAILABLE";
        }
    }  
    /**
     * Gets the status of the BookableRoom
     * @return String status of the BookableRoom
     */
    public String getStatus(){
        return status;
    }
    /**
     * Gets the occupancy of the BookableRoom
     * @return int occupancy of the BookableRoom
     */
    public int getOccupancy(){
        return occupancy;
    }
    /**
     * Gets the timeslot of the BookableRoom
     * @return String timeslot of the BookableRoom
     */
    public String getTimeSlot(){
        return timeSlot;
    }
    /**
     * Gets the code of the BookableRoom
     * @return String code of the BookableRoom
     */
    public String getRoomCode(){
        return roomCode;
    }
    /**
     * Returns a String formatted to include timeslot, status, roomCode, and occupancy
     * @return String formatted String
     */
    public String toString(){
        return "| " + timeSlot + " | " + status + " | " + roomCode + " | occupancy: " + occupancy;
    }
}

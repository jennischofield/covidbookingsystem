import java.util.ArrayList;
/**
 *Booking represents an appointment for a COVID test
 *
 * @author Jenni Schofield
 * @version 24/02/2021
 */
public class Booking
{
    /** Represents the room the test will take place in
     */
    private BookableRoom room;
    /**
     * Represents the assistant who will be administering the test
     */
    private AssistantOnShift assistant;
    private String status;
    private String testedEmail;
    private String timeSlot;
    /**
     * Constructor for Booking
     * @param r The room at which the test will take place
     * @param a The assistant who will administer the test
     * @param t The time at which this booking will occur
     * @param e The email of the person getting tested.
     */
    public Booking(BookableRoom r, AssistantOnShift a, String t, String e ){
        if(r.getStatus().equals("FULL") || a.getStatus().equals("BUSY")){
            throw new RuntimeException("Room and Assistant cannot be in use");
        }
        if(e.endsWith("@uok.ac.uk") == false){
            throw new IllegalArgumentException("Emails must end in the @uok.ac.uk domain");
        }
        room = r;
        room.increaseOccupancy();
        assistant = a;
        assistant.assignBooking();
        status = "SCHEDULED";
        testedEmail = e;
        timeSlot = t;
    }
    /**
     * Gets the status of the Booking
     * @return a String representing the status of the booking
     */
    public String getStatus(){
        return status;
    }
    /**
     * Updates the status of the Booking to completed
     */
    public void completeTest(){
        status = "COMPLETED";
    }
    /**
     * Cancels the test and frees the room and assistant from this timeslot
     */
    public void cancelTest(){
        room.decreaseOccupancy();
        assistant.removeBooking();
    }
    /**
     * Returns a formatted String for the Booking
     * @return A string formatted to print the timeslot, status, assistant's email, room code
     * and the person who is getting tested's email
     */
    public String toString(){
        return "| " + timeSlot + " | " + status + " | " + assistant.getEmail() + " | " + room.getRoomCode() + " | " + testedEmail + " |";
    }
}

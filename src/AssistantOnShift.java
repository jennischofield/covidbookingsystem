
/**
 * An AssistantOnShift represents a timeslot for an assistant to administer a test
 *
 * @author Jenni Schofield
 * @version 24/02/2021
 */
public class AssistantOnShift
{
    private String email;
    private String date;
    private String time;
    private String status = "FREE";
    /**
     * Constructor for AssistantOnShift
     * @param e email of the Assistant
     * @param d date of the test, formatted in dd/mm/yyyy
     * @param t time of the test, formatted in HH:MM
     */
    public AssistantOnShift(String e, String d, String t){
        email = e;
        date = d;
        time = t;
    }
    /**
     * Sets the status of the AssistantOnShift to BUSY
     */
    public void assignBooking(){
        status = "BUSY";
    }
    /**
     * Sets the status of the AssistantOnShift to FREE
     */
    public void removeBooking(){
        status = "FREE";
    }
    /**
     * Gets the status of the AssistantOnShift
     * @return String status of AssistantOnShift
     */
    public String getStatus(){
        return status;
    }
    /**
     * Gets the timeslot of the AssistantOnShift
     * @return String timeslot of test
     */
    public String getTimeSlot(){
        return date + " " + time;
    }
    /**
     * Gets the email of the AssistantOnShift
     * @return String email of AssistantOnShift
     */
    public String getEmail(){
        return email;
    }
    /**
     * Returns String formatted to include timeslot, status, and email
     * @return String of timeslot, status, email
     */
    public String toString(){
        return "| " + getTimeSlot() + " | " + status + " | " + email + " |";
    }
}

import java.util.ArrayList;
/**
 * A Room represents a room within the University that will have COVID tests being 
 * administered in
 *
 * @author Jenni Schofield
 * @version 24/02/2021
 */
public class Room
{
    private String code = "ICM";
    private ArrayList<BookableRoom> timeslots = new ArrayList<BookableRoom>();
    private int capacity;
    private static int roomCounter = 210;
    /**
     * Constructor for Room
     * @param c Represents the capacity of a Room
     * @throws IllegalArgumentException In the case that c is less than or equal to 0
     */
    public Room(int c){
        if(c <= 0){
            throw new IllegalArgumentException("Capacity cannot be less than 0");
        }else{
            capacity = c;
        }
        code += roomCounter;
        roomCounter++;
    }
    /**
     * Adds a BookableRoom to the schedule of the Room
     * @param b BookableRoom to be added to the schedule
     */
    public void addSchedule(BookableRoom b){
        timeslots.add(b);
    }
    /**
     * Removes a BookableRoom from the schedule of the Room
     * @param b BookableRoom to be removed to the schedule
     */
    public void removeSchedule(BookableRoom b){
        timeslots.remove(b);
    }
    /**
     * Gets the schedule of a Room
     * @return ArrayList<BookableRoom> the schedule of the Room
     */
    public ArrayList<BookableRoom> getSchedule(){
        return timeslots;
    }
    /**
     * Gets the capacity of a Room
     * @return int capacity of Room
     */
    public int getCapacity(){
        return capacity;
    }
    /**
     * Gets the code of the Room
     * @return String code of the Room
     */
    public String getCode(){
        return code;
    }
    /**
     * Returns String with code and capacity of Room
     * @returns String formatted to display code and capacity
     */
    public String toString(){
        return "| "+ code + " | capacity: " + capacity + " |";
    }
}

import java.util.ArrayList;
/**
 * A University holds the list of Assistants and Rooms
 *
 * @author Jenni Schofield
 * @version 24/02/2021
 */
public class University
{
    private ArrayList<Room> rooms = new ArrayList<Room>();
    private ArrayList<Assistant> assistants = new ArrayList<Assistant>();
    /**
     * Adds Room to the list of Rooms in the University
     * @param r Room to be added
     */
    public void addRoom(Room r){
        rooms.add(r);
    }
    /**
     * Adds Assistant to the list of Assistants in the University
     * @param a Room to be added
     */
    public void addAssistant(Assistant a){
        assistants.add(a);
    }
    /**
     * Returns list of Rooms in the University
     * @return ArrayList<Room> Rooms in University
     */
    public ArrayList<Room> getRooms(){
        return rooms;
    }
    /**
     * Returns list of Assistants in the University
     * @return ArrayList<Assistant> Assistants in University
     */
    public ArrayList<Assistant> getAssistants(){
        return assistants;
    }
}

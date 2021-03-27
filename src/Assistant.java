import java.util.ArrayList;
/**
 * An Assistant represents a volunteer at the University that will administer a COVID test
 *
 * @author Jenni Schofield
 * @version 24/02/2021
 */
public class Assistant
{
        private String name;
        private String email;
        private ArrayList<AssistantOnShift> schedule = new ArrayList<AssistantOnShift>();
        /**
         * Constructor for objects of class Assistant
         */
        public Assistant(String n, String e)
        {
            if(n != "")
                name = n;
            else
                throw new IllegalArgumentException("Name cannot be empty");
            if(e.endsWith("@uok.ac.uk") == true)
                email = e;
            else
                throw new IllegalArgumentException("Email must be of @uok.ac.uk domain");
        }
        /**
         * Returns the name of the Assistant
         * @return name String representing the name
         */  
        public String getName(){
            return name;
        }
        /**
         * Returns the email of the Assistant
         * @return name String representing the email
         */
        public String getEmail(){
            return email;
        }
        /**
         * Adds AssistantOnShifts for the whole day to the list of AssistantOnShifts
         * @param date String representing the date in dd/mm/yyyy format
         * @return ArrayList<AssistantOnShift> of all the entries
         */
        public ArrayList<AssistantOnShift> addSchedule(String date){
            ArrayList<AssistantOnShift> retList = new ArrayList<AssistantOnShift>();
            schedule.add(new AssistantOnShift(email, date, "07:00"));
            schedule.add(new AssistantOnShift(email, date, "08:00"));
            schedule.add(new AssistantOnShift(email, date, "09:00"));
            retList.add(new AssistantOnShift(email, date, "07:00"));
            retList.add(new AssistantOnShift(email, date, "08:00"));
            retList.add(new AssistantOnShift(email, date, "09:00"));
            return retList;
        }
        /**
         * Returns the Assistant's schedule
         * @return ArrayList<AssistantOnShift> of all the appointments 
         */
        public ArrayList<AssistantOnShift> getSchedule(){
            return schedule;
        }
        /**
         * Deletes an appointment from the schedule
         * @param a AssistantOnShift to remove
         */
        public void removeSchedule(AssistantOnShift a){
            schedule.remove(a);
        }
        /**
         * Returns String with name and email
         * @return String formatted String
         */
        public String toString(){
            return "| " + name + " | " + email + " |";
        }
        
}


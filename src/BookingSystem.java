import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.time.format.DateTimeParseException;
/**
 * BookingSystem is the main section of the BookingApp, managing the Bookings and attributes 
 * associated with them
 *
 * @author Jenni Schofield
 * @version 24/02/2021
 */
public class BookingSystem
{
    private ArrayList<BookableRoom> roomTimeSlots = new ArrayList<BookableRoom>();
    private ArrayList<AssistantOnShift> assistantSchedules = new ArrayList<AssistantOnShift>();
    private ArrayList<Booking> bookings = new ArrayList<Booking>();
    private University uok = new University();
    private final int DURATION = 60;
    int input = 0;
    Scanner reader = new Scanner(System.in);
    /**
     * Main menu options for the Booking System. Allows user to select what option they want
     */
    public void mainMenu(){
        
        boolean running = true;
        while(running){
            if(input ==0){
                clearConsole();
                printMainMenu();
                input = reader.nextInt();
            }else if(input ==-1){
                running = false;
                clearConsole();
                System.out.println("Thank you for using UOK's booking system, goodbye!");
            }else if(input == 1){//list bookable rooms
                clearConsole();
                listBookableRooms();
            }else if(input ==2){//add bookable rooms
                clearConsole();
                addBookableRoom();
            }else if(input ==3){//remove bookable room
                clearConsole();
                removeBookableRoom();
            }else if(input ==4){//list assistants on shifts
                clearConsole();
                listAssistant();
            }else if(input ==5){//add assistants on shifts
                clearConsole();
                addAssistant();
            }else if(input ==6){//remove assistant
                clearConsole();
                removeAssistant();
            }else if(input ==7){//list bookings
                clearConsole();
                listBooking();
            }else if(input ==8){//add booking
                clearConsole();
                addBooking();
            }else if(input ==9){//remove booking
                clearConsole();
                removeBooking();
            }else if(input ==10){//conclude booking
                clearConsole();
                concludeBooking();
            }
        }
    }
    /**
     * Lists all the Bookable Rooms in the Booking System
     */
    public void listBookableRooms(){
        System.out.println("University of Knowledge - COVID Test\n");
        for(int i = 0; i < roomTimeSlots.size();i++){
            System.out.println(roomTimeSlots.get(i));
        }
        while(true){
            System.out.println("0. Back to main menu.\n-1. Quit application.\n");
            int choice = reader.nextInt();
            if(choice == 0){
                input = 0;
                break;
            }else if(choice == -1){
                input = -1;
                break;
            }
        }
    }
    /**
     * Checks to see if a String fits into the format dd/mm/yyyy HH:MM
     * @param date String representing the date and time of an appointment
     * @return boolean representing if the date is of the correct format
     * @throws DateTimeParseException (is caught)
     */
    public boolean isDateTime(String date){
        try{
                LocalDateTime lt = LocalDateTime.parse(date.trim(), DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm").withResolverStyle(ResolverStyle.STRICT));
        }catch(DateTimeParseException e){
               return false;
        }
        System.out.println(date.substring(date.indexOf(" ")+1, date.length()));
             if(!((date.substring(date.indexOf(" "), date.length())).trim().equals("07:00")||(date.substring(date.indexOf(" "), date.length())).trim().equals("08:00")||(date.substring(date.indexOf(" "), date.length())).trim().equals("09:00")))
           return false;
           return true;
    }
    /**
     * Checks to see if a String fits into the format dd/mm/yyyy
     * @param date String representing the date and time of an appointment
     * @return boolean representing if the date is of the correct format
     * @throws DateTimeParseException (is caught)
     */
    public boolean isDate(String date){
       
        try{
           LocalDate lt = LocalDate.parse(date.trim(), DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT));
        }catch(DateTimeParseException e){
            return false;
       }
       return true;
    }
    /**
     * Checks to see if a String is numerical or not
     * @param num String to check 
     * @return boolean whether or not it's numerical
     * @throws NumberFormatException (is caught)
     */
    public boolean isNumeric(String num){
        try{
            Integer.parseInt(num);
        }catch(NumberFormatException e){
            return false;
        }
        return true;
    }
    /**
     * Checks to see if a BookableRoom already exists in the list
     * @param b BookableRoom to check
     * @return boolean whether or not it's in the list already
     */
    public boolean isInList(BookableRoom b){
        for(int i = 0; i< roomTimeSlots.size();i++){
            if(b.getRoomCode().equals(roomTimeSlots.get(i).getRoomCode()) && b.getTimeSlot().equals(roomTimeSlots.get(i).getTimeSlot()))
            return true;
        }
        return false;
    }
    /**
     * Adds a BookableRoom to the list and to the respective Room's list of BookableRooms
     * 
     */
    public void addBookableRoom(){
            reader.nextLine();
            System.out.println("University of Knowledge - COVID test\n");
            System.out.println("Adding bookable room\n");
            for(int i = 0; i < uok.getRooms().size();i++){
                System.out.println((i+11) + ". " + uok.getRooms().get(i));
            }
            while(true){
                System.out.println("Please, enter one of the following:\n\n");
                System.out.println("The sequential ID listed to a room, a date (dd/mm/yyyy), and a time (HH:MM),separated by a white space.");
                System.out.println("0. Back to main menu.\n-1. Quit application.\n");
                String choice = reader.nextLine();
                
                if(choice.equals("-1")){
                    input = -1;
                    break;
                }else if(choice.equals("0")){
                    input = 0;
                    break;
                }else{
                    if(choice.indexOf(" ") != -1){
                        String idStr = choice.substring(0,choice.indexOf(" "));
                        String date = choice.substring(choice.indexOf(" ")+1, choice.length());
                        if(isDateTime(date)){
                           if(isNumeric(idStr) && Integer.parseInt(idStr)-11 <uok.getRooms().size()){
                               int id = Integer.parseInt(idStr);
                               Room r = uok.getRooms().get(id-11);
                               
                               BookableRoom b = new BookableRoom(r.getCode(), date, r.getCapacity());
                                
                               if(!isInList(b)){
                                   r.addSchedule(b);
                                   roomTimeSlots.add(b);
                                   System.out.println("Bookable Room added successfully:\n" + b);
                                }else{
                                    System.out.println("Error!\nDuplicate Bookable Room");
                                }
                            } else{
                                System.out.println("Error!\nID invalid or not in range");
                            }
                               
                        }else{
                            System.out.println("Error!\nDate format incorrect, must be on the hour");
                        }
                    }else{
                        System.out.println("Error! Requires both ID and date");
                    }
                }
            }
        
    }
    /**
     * Removes a BookableRoom from the list and from the Room it's assigned to
     * @param b A BookableRoom to be removed from the list 
     */
    public void removingRoom(BookableRoom b){
        for(int i = 0; i < roomTimeSlots.size(); i++){
            if(b.getRoomCode().equals(roomTimeSlots.get(i).getRoomCode()) && b.getTimeSlot().equals(roomTimeSlots.get(i).getTimeSlot())){
                roomTimeSlots.remove(i);
                break;
            }
        }
        for(int i = 0; i <uok.getRooms().size();i++){
            for(int j = 0; j <uok.getRooms().get(i).getSchedule().size(); j++){
               if(b.getRoomCode().equals(uok.getRooms().get(i).getSchedule().get(i).getRoomCode()) && b.getTimeSlot().equals(uok.getRooms().get(i).getSchedule().get(i).getTimeSlot())){
                   uok.getRooms().get(i).removeSchedule(b);
                   break;
                }
            }   
        }
    }
    /**
     * Displays all removable BookableRooms, then takes the user input and removes that 
     * room
     * 
     */
    public void removeBookableRoom(){
            reader.nextLine();
            System.out.println("University of Knowledge - COVID test\n");
            ArrayList<BookableRoom> empties = new ArrayList<BookableRoom>();
            for(int i = 0; i<roomTimeSlots.size(); i++){
                if(roomTimeSlots.get(i).getStatus().equals("EMPTY")){
                    empties.add(roomTimeSlots.get(i));
                }
            }
            for(int i = 0; i<empties.size();i++){
                System.out.println((i+11) + ". " + empties.get(i));
            }
            System.out.println("Removing bookable room\n");
            while(true){
                System.out.println("Please, enter one of the following:\n");
                System.out.println("The sequential ID to select the bookable room to be removed.\n0. Back to main menu.\n-1. Quit application.\n");
                 String choice = reader.nextLine();
                if(choice.equals("-1")){
                   input = -1;
                   break;
                }else if(choice.equals("0")){
                   input = 0;
                   break;
                }else{
                    if(isNumeric(choice) && Integer.parseInt(choice)-11 <empties.size()){
                        BookableRoom removing = empties.get(Integer.parseInt(choice)-11);
                        removingRoom(removing);
                        System.out.println("Bookable Room removed successfully:\n" + removing);
                    }else{
                        System.out.println("Error!\nID invalid or not in range");
                    }
    
                 }
            }
    }
    /**
     * Lists all AssistantsOnShift in the system
     * 
     */
    public void listAssistant(){
        System.out.println("University of Knowledge - COVID test\n");
        for(int i = 0; i < assistantSchedules.size();i++){
            System.out.println(assistantSchedules.get(i));
        }
        while(true){
            System.out.println("0. Back to main menu.\n-1. Quit application.\n");
            int choice = reader.nextInt();
            if(choice == 0){
                input = 0;
                break;
            }else if(choice == -1){
                input = -1;
                break;
            }
        }
    }
    /**
     * Checks to see if an assistant is already scheduled for the timeslot
     * @param a AssistantOnShift to be checked if they're already scheduled
     * @return boolean, whether or not the AssistantOnShift is already scheduled
     */
    public boolean isAlreadyScheduled(AssistantOnShift a){
        for(int i = 0; i<assistantSchedules.size();i++){
            if(assistantSchedules.get(i).getTimeSlot().equals(a.getTimeSlot())){
                if(assistantSchedules.get(i).getEmail().equals(a.getEmail())){
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Adds an AssistantOnShift to the system, given user input
     */
    public void addAssistant(){
        reader.nextLine();
            System.out.println("University of Knowledge - COVID test\n");
            System.out.println("Adding assistant on shift\n");
            for(int i = 0; i < uok.getAssistants().size();i++){
                System.out.println((i+11) + ". " + uok.getAssistants().get(i));
            }
            while(true){
                System.out.println("Please, enter one of the following:\n");
                System.out.println("The sequential ID listed to an assistant and date (dd/mm/yyyy), separated by a white space.");
                System.out.println("0. Back to main menu.\n-1. Quit application.\n");
                String choice = reader.nextLine();
                if(choice.equals("-1")){
                    input = -1;
                    break;
                }else if(choice.equals("0")){
                    input = 0;
                    break;
                }else{
                    if(choice.indexOf(" ") != -1){
                        String idStr = choice.substring(0,choice.indexOf(" "));
                        String date = choice.substring(choice.indexOf(" ")+1, choice.length());
                        if(isDate(date)){
                           if(isNumeric(idStr) && Integer.parseInt(idStr)-11 <uok.getAssistants().size()){
                               int id = Integer.parseInt(idStr);
                               Assistant a = uok.getAssistants().get(id-11);
                               ArrayList<AssistantOnShift> adding = a.addSchedule(date);
                               if(!isAlreadyScheduled(adding.get(0))){
                                   assistantSchedules.addAll(adding);
                                   System.out.println("Assistant on Shift added successfully:");
                                   for(int i = 0; i<adding.size();i++){
                                       System.out.println(adding.get(i));
                                    }
                                   System.out.print("\n"); 
                                }else{
                                    System.out.println("Error!\nAssistant already scheduled to that day");
                                }
                            } else{
                                System.out.println("Error!\nID invalid or not in range");
                            }
                               
                        }else{
                            System.out.println("Error!\nDate format incorrect");
                        }
                    }else{
                        System.out.println("Error!\nID and date required");
                    }
                }
            }
    }
    /**
     * Removes an AssistantOnShift from the system and its respective Assistant
     * @param a AssistantOnShift to be removed
     */
    public void removingAssistant(AssistantOnShift a){
            for(int i = 0; i < assistantSchedules.size(); i++){
                if(a.getEmail().equals(assistantSchedules.get(i).getEmail()) && a.getTimeSlot().equals(assistantSchedules.get(i).getTimeSlot())){
                    assistantSchedules.remove(i);
                    break;
                }
            }
            for(int i = 0; i <uok.getAssistants().size();i++){
                for(int j = 0; j <uok.getAssistants().get(i).getSchedule().size(); j++){
                   if(a.getEmail().equals(uok.getAssistants().get(i).getSchedule().get(i).getEmail()) && a.getTimeSlot().equals(uok.getAssistants().get(i).getSchedule().get(i).getTimeSlot())){
                       uok.getAssistants().get(i).removeSchedule(a);
                       break;
                    }
                }   
            }
    }
    /**
     * Removes AssistantOnShift from the system and the assistant it's assigned to, by 
     * getting user choice
     */
    public void removeAssistant(){
            reader.nextLine();  
            System.out.println("University of Knowledge - COVID test\n");
            ArrayList<AssistantOnShift> frees = new ArrayList<AssistantOnShift>();
            for(int i = 0; i<assistantSchedules.size(); i++){
                if(assistantSchedules.get(i).getStatus().equals("FREE")){
                    frees.add(assistantSchedules.get(i));
                }
            }
            for(int i = 0; i<frees.size();i++){
                System.out.println((i+11) + ". " + frees.get(i));
            }
            System.out.println("Removing assistant on shift\n");
            while(true){
                System.out.println("Please, enter one of the following:\n");
                System.out.println("The sequential ID to select the assistant on shift to be removed.\n0. Back to main menu.\n-1. Quit application.\n");
                 String choice = reader.nextLine();
                if(choice.equals("-1")){
                   input = -1;
                   break;
                }else if(choice.equals("0")){
                   input = 0;
                   break;
                }else{
                    if(isNumeric(choice) && Integer.parseInt(choice)-11 <frees.size()){
                        AssistantOnShift removing = frees.get(Integer.parseInt(choice)-11);
                        removingAssistant(removing);
                        System.out.println("Assistant removed successfully:\n" + removing);
                    }else{
                        System.out.println("Error!\nID invalid or not in range");
                    }
    
                 }
            }
    }
    /**
     * Lists all the Bookings registered in the system, with no duplicates
     */
    public void listBooking(){
            reader.nextLine();  //clears the reader
            System.out.println("University of Knowledge - COVID test\n");
            System.out.println("Select which bookings to list:\n1. All\n2. Only bookings status: SCHEDULED\n3. Only bookings status:COMPLETED\n0. Back to the main menu.\n-1. Quit application \n");
            String choice = reader.nextLine();
            if(choice.equals("2")){
                ArrayList<Booking> scheduleds = new ArrayList<Booking>();
                for(int i = 0; i<bookings.size(); i++){
                    if(bookings.get(i).getStatus().equals("SCHEDULED")){
                        scheduleds.add(bookings.get(i));
                    }
                }
                for(int i = 0; i<scheduleds.size(); i++){
                    System.out.println(scheduleds.get(i));
                }
                while(true){
                   System.out.println("0. Back to main menu.\n-1. Quit application.\n");
                    String option = reader.nextLine();
                   if(option.equals("0")){
                       input = 0;
                       break;
                    }else if(option.equals("-1")){
                        input = -1;
                        break;
                    }
                }
            }else if(choice.equals("3")){
                ArrayList<Booking> completeds = new ArrayList<Booking>();
                for(int i = 0; i<bookings.size(); i++){
                    if(bookings.get(i).getStatus().equals("COMPLETED")){
                        completeds.add(bookings.get(i));
                    }
                }
                for(int i = 0; i<completeds.size(); i++){
                    System.out.println(completeds.get(i));
                }
                
                while(true){
                   System.out.println("0. Back to main menu.\n-1. Quit application.\n");
                   String option = reader.nextLine();
                   if(option.equals("0")){
                       input = 0;
                       break;
                    }else if(option.equals("-1")){
                        input = -1;
                        break;
                    }
                }
            }else if(choice.equals("0")){
                input = 0;
            }else if(choice.equals("-1")){
                input = -1;
            }else{
                for(int i = 0; i<bookings.size();i++){
                    System.out.println(bookings.get(i));
                }
                while(true){
                    System.out.println("0. Back to main menu.\n-1. Quit application.\n");
                   String option = reader.nextLine();
                   if(option.equals("0")){
                       input = 0;
                       break;
                    }else if(option.equals("-1")){
                        input = -1;
                        break;
                    }
                }
            }
    }
    /**
     * Adds new Booking to the system based upon user input
     */
    public void addBooking(){
         reader.nextLine();  //clears the reader
        System.out.println("University of Knowledge - COVID test\n");
        System.out.println("Adding booking (appointment for a COVID test) to the system\n");
       
        
        while(true){
                System.out.println("List of available time-slots:");
                ArrayList<String> timeslotsList = getTimeSlots();
                for(int i = 0; i<timeslotsList.size(); i++){
                    System.out.println((i+11) + ". " + timeslotsList.get(i));
                }
                System.out.println("Please, enter one of the following:\n\n");
                System.out.println("The sequential ID of an available time-slot and the student email, separated by a white space.");
                System.out.println("0. Back to main menu.\n-1. Quit application.\n");
                String choice = reader.nextLine();
                if(choice.equals("-1")){
                    input = -1;
                    break;
                }else if(choice.equals("0")){
                    input = 0;
                    break;
                }else{
                    if(choice.indexOf(" ") != -1){
                        String idStr = choice.substring(0,choice.indexOf(" "));
                        String email = choice.substring(choice.indexOf(" ")+1, choice.length());
                        if(isNumeric(idStr) && Integer.parseInt(idStr)-11 <timeslotsList.size()){
                            if(email.endsWith("@uok.ac.uk")){
                                String date = timeslotsList.get(Integer.parseInt(idStr)-11);
                                
                                Booking added = createBooking(date,email);
                                System.out.println("Booking added successfully:\n" + added + "\n");
                            }else{
                                System.out.println("Error!\nEmail must be of @uok.ac.uk domain");
                            }
                        } else{
                                System.out.println("Error!\nID invalid or not in range");
                        }
                           
                    }else{
                         System.out.println("Error!\nID and student email required");
                    }
                }
            }
    }
    /**
     * Removes booking from system based upon user input
     */
    public void removeBooking(){
        reader.nextLine();  //clears the reader
        System.out.println("University of Knowledge - COVID test\n");
        System.out.println("Removing booking from the system\n");

        ArrayList<Booking> scheduleds = new ArrayList<Booking>();
        for(int i = 0; i<bookings.size(); i++){
          if(bookings.get(i).getStatus().equals("SCHEDULED")){
              scheduleds.add(bookings.get(i));
                }
            }
        for(int i = 0; i<scheduleds.size(); i++){
            System.out.println((i+11) + ". " + scheduleds.get(i));
            }
        while(true){
                
            System.out.println("Please, enter one of the following:\n");
            System.out.println("The sequential ID to select the booking to be removed from the listed bookings above.");
            System.out.println("0. Back to main menu.\n-1. Quit application.\n");
            String idStr = reader.nextLine();
            if(idStr.equals("0")){
                input =0;
                break;
            }else if(idStr.equals("-1")){
                input = -1;
                break;
            }else{
                if(isNumeric(idStr) && Integer.parseInt(idStr)-11 <scheduleds.size()){
                    scheduleds.get(Integer.parseInt(idStr)-11).cancelTest();
                    bookings.remove(scheduleds.get(Integer.parseInt(idStr)-11));
                    System.out.println("Booking removed successfully:\n" + scheduleds.get(Integer.parseInt(idStr)-11));
                    }else{
                        System.out.println("Error!\nID invalid or out of range");
                }
            }
        }
    }
    /**
     * Concludes a booking, chosen by the user, by setting the status of the booking to
     * COMPLETED
     * 
     */
    public void concludeBooking(){
        reader.nextLine();  //clears the reader
        System.out.println("University of Knowledge - COVID test\n");
        

        ArrayList<Booking> scheduleds = new ArrayList<Booking>();
        for(int i = 0; i<bookings.size(); i++){
          if(bookings.get(i).getStatus().equals("SCHEDULED")){
              scheduleds.add(bookings.get(i));
                }
            }
            System.out.println("Conclude Booking\n");
        for(int i = 0; i<scheduleds.size(); i++){
            System.out.println((i+11) + ". " + scheduleds.get(i));
            }
        while(true){
                
            System.out.println("Please, enter one of the following:\n");
            System.out.println("The sequential ID to select the booking to be completed");
            System.out.println("0. Back to main menu.\n-1. Quit application.\n");
            String idStr = reader.nextLine();
            if(idStr.equals("0")){
                input =0;
                break;
            }else if(idStr.equals("-1")){
                input = -1;
                break;
            }else{
                if(isNumeric(idStr) && Integer.parseInt(idStr)-11 <scheduleds.size()){
                    scheduleds.get(Integer.parseInt(idStr)-11).completeTest();
                    System.out.println("Booking completed successfully:\n" + scheduleds.get(Integer.parseInt(idStr)-11));

                }else{
                    System.out.println("Error!\nID invalid or out of range");
                }
            }
        }
    }
    /**
     * Gets the list of all valid timeslots with no duplicates
     * @return ArrayList<String> of strings of time 
     */
    public ArrayList<String> getTimeSlots(){
        ArrayList<String> retList = new ArrayList<String>();
        for(int i = 0; i < roomTimeSlots.size(); i++){
            if(!(roomTimeSlots.get(i).getStatus().equals("FULL"))){
                String time = roomTimeSlots.get(i).getTimeSlot();
                for(int j = 0; j < assistantSchedules.size();j++){
                    if(!(assistantSchedules.get(j).getStatus().equals("BUSY")) && time.equals(assistantSchedules.get(j).getTimeSlot())){
                        if(!retList.contains(time))
                            retList.add(time);
                    }
                }
            }
        }
        Collections.sort(retList);
        return retList;
    }
    /**
     * Creates a booking by finding a free room and free assistant
     * @param time String representing the time of the appointment
     * @param email String representing the email of the person getting tested
     * @return Booking for that person
     */
    public Booking createBooking(String time, String email){
        BookableRoom r = null;
        AssistantOnShift a = null;
        if(getTimeSlots().size() != 0){
            for(int i = 0; i<roomTimeSlots.size();i++){
                if(roomTimeSlots.get(i).getTimeSlot().equals(time) && (!(roomTimeSlots.get(i).getStatus().equals("FULL")))){
                    r = roomTimeSlots.get(i);
                    break;
                }
            }
            for(int i = 0; i<assistantSchedules.size(); i++){
                if(assistantSchedules.get(i).getTimeSlot().equals(time) && assistantSchedules.get(i).getStatus().equals("FREE")){
                    a = assistantSchedules.get(i);
                    break;
                }
            }
            Booking b = new Booking(r,a,time, email);
            bookings.add(b);
            return b;
        }else{
            throw new RuntimeException("No Bookable Rooms Free");   
        }
    }
    /**
     * Clears the console for between screens
     */
    public static void clearConsole(){
        try{
            final String os = System.getProperty("os.name");
            if(os.contains("Windows")){
                Runtime.getRuntime().exec("cls");
            }else{
                Runtime.getRuntime().exec("clear");
                System.out.println('\u000C');
                System.out.print("\033\143");

            }
        }catch (final Exception e){
        }
    }
    /**
     * Prints the main menu screen
     */
    public void printMainMenu(){
        String retString = "";
        retString += "University of Knowledge - COVID test\n\n";
        retString += "Manage Bookings\n\n";
        retString += "Please, enter the number to select your option:\n\n";
        retString += "To manage Bookable Rooms:\n";
        retString += "\t1. List\n";
        retString += "\t2. Add\n";
        retString += "\t3. Remove\n";
        retString += "To manage Assistants on Shift:\n";
        retString += "\t4. List\n";
        retString += "\t5. Add\n";
        retString += "\t6. Remove\n";
        retString += "To manage Bookings:\n";
        retString += "\t7. List\n";
        retString += "\t8. Add\n";
        retString += "\t9. Remove\n";
        retString += "\t10. Conclude\n";
        retString += "After selecting one of the options above, you will be presented with other screens\n";
        retString += "If you press 0, you will be able to return to this main menu.\n";
        retString += "Press -1 (or ctrl+c) to quit this application.\n";
        System.out.println(retString);
    } 
    /**
     * Initialises data for the system to test, with 3 Assistants, 3 Rooms, 9 BookableRooms,
     * 9 AssistantOnShift, and 6 Bookings
     */
    public void initialiseData(){
        //Initialising bookable rooms
        //Intialise University resources
        uok.addAssistant(new Assistant("Jenni", "jns205@uok.ac.uk"));
        uok.addAssistant(new Assistant("Priya", "ps772@uok.ac.uk"));
        uok.addAssistant(new Assistant("Maya", "ms1125@uok.ac.uk"));
        uok.addRoom(new Room(3));
        uok.addRoom(new Room(3));
        uok.addRoom(new Room(3));
        
        BookableRoom br1 = new BookableRoom(uok.getRooms().get(0).getCode(), "13/06/2021 07:00", 3);
        uok.getRooms().get(0).addSchedule(br1);
        roomTimeSlots.add(br1);
        BookableRoom br2 = new BookableRoom(uok.getRooms().get(0).getCode(), "13/06/2021 08:00", 3);
        uok.getRooms().get(0).addSchedule(br2);
        roomTimeSlots.add(br2);
        BookableRoom br3 = new BookableRoom(uok.getRooms().get(0).getCode(), "13/06/2021 09:00", 3);
        uok.getRooms().get(0).addSchedule(br3);
        roomTimeSlots.add(br3);
        BookableRoom br4 = new BookableRoom(uok.getRooms().get(1).getCode(), "13/06/2021 07:00", 3);
        uok.getRooms().get(1).addSchedule(br4);
        roomTimeSlots.add(br4);
        BookableRoom br5 = new BookableRoom(uok.getRooms().get(1).getCode(), "13/06/2021 08:00", 3);
        uok.getRooms().get(1).addSchedule(br5);
        roomTimeSlots.add(br5);
        BookableRoom br6 = new BookableRoom(uok.getRooms().get(1).getCode(), "13/06/2021 09:00", 3);
        uok.getRooms().get(1).addSchedule(br6);
        roomTimeSlots.add(br6);
        BookableRoom br7 = new BookableRoom(uok.getRooms().get(2).getCode(), "13/06/2021 07:00", 3);
        uok.getRooms().get(2).addSchedule(br7);
        roomTimeSlots.add(br7);
        BookableRoom br8 = new BookableRoom(uok.getRooms().get(2).getCode(), "13/06/2021 08:00", 3);
        uok.getRooms().get(2).addSchedule(br8);
        roomTimeSlots.add(br8);
        BookableRoom br9 = new BookableRoom(uok.getRooms().get(2).getCode(), "13/06/2021 09:00", 3);
        uok.getRooms().get(2).addSchedule(br9);
        roomTimeSlots.add(br9);
        ArrayList<AssistantOnShift> adding = uok.getAssistants().get(0).addSchedule("13/06/2021");
        assistantSchedules.addAll(adding);
        ArrayList<AssistantOnShift> adding2 = uok.getAssistants().get(1).addSchedule("13/06/2021");
        assistantSchedules.addAll(adding2);
        ArrayList<AssistantOnShift> adding3 = uok.getAssistants().get(2).addSchedule("13/06/2021");
        assistantSchedules.addAll(adding3);
        ArrayList<String> timeslotList = getTimeSlots();
        //busy room
        createBooking(timeslotList.get(0),"js544@uok.ac.uk");
        createBooking(timeslotList.get(0),"ky982@uok.ac.uk");
        createBooking(timeslotList.get(0),"um883@uok.ac.uk");
        //available room
        createBooking(timeslotList.get(1),"df234@uok.ac.uk");
        createBooking(timeslotList.get(1),"tl334@uok.ac.uk");
        //booking to complete
        createBooking(timeslotList.get(2), "xp234@uok.ac.uk");
        bookings.get(bookings.size()-1).completeTest();
            }
    
}

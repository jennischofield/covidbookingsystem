
/**
 * Initialises and runs the Booking System.
 *
 * @author Jenni Schofield
 * @version 24/02/2021
 */
public class BookingApp
{
    /**
     * Initialises the data, then runs the main menu
     */
    public static void main(String [] args){
        BookingSystem b = new BookingSystem();
        b.initialiseData();
        b.clearConsole();
        b.mainMenu();
    }
}

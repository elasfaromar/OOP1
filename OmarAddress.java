// Import JOptionPane
import javax.swing.JOptionPane;
// Import full io class
import java.io.*;

/**
 * An object that stores an address based on the instiance variables: street and postal.
 * Includes methods: toString, enterAddress
 *
 * @author (Omar Elasfar)
 * @version (Version 1)
 */
public class OmarAddress
{
    // instance variables
    String street; // Allow the user to go as deep in detail as he would like
    String postal;

    /**
     * Default constructor for objects of class OmarPerson - takes in nothing
     */
    public OmarAddress()
    {
        // initialise instance variables
        this.street = "N/A";
        this.postal = "A1A A1A";
    }

    // Overriding toString method
    public String toString()
    {
        // Use String.format to avoid concatenation
        return String.format("Address: %s\nPostal Code: %s", this.street, this.postal.toUpperCase()); // Postal codes are written with capital letters so use toUpperCase()
    }

    // Method that fills in each instance variable of an Address through panes (not file)
    public void enterAddress(String name)
    {
        // Ask user for street address using the JOptionPane to store in the object
        // Do-while loop to ensure that the entry was valid ie. no typos, misspelling, etc.
        do 
        {
            do
            {
                this.street = JOptionPane.showInputDialog("Please enter the ADDRESS of " +  name  + ". Go as detailed as you'd like.\nDo not include postal code.");
            }
            // Since JOptionPane.showInputDialog allows a user to return a"null" by cancelling, as well as the user being able to submit the input pop-up
            // without entering anything, therefore invalidString method is called to check if that is the case
            while (OmarPerson.invalidString(this.street)); 
        }
        while (OmarPerson.satisfied(this.street));

        // Ask user for postal code using the JOptionPane to store in the object
        // Do-while loop to ensure that the entry was valid ie. no typos, misspelling, etc.
        do 
        {
            do
            {
                this.postal = JOptionPane.showInputDialog("For " + name + ":\nPlease enter the POSTAL code of " + this.street + ". (ex: \"K2J 5N7\")");
            }
            // Since JOptionPane.showInputDialog allows a user to return a"null" by cancelling, as well as the user being able to submit the input pop-up
            // without entering anything, therefore invalidString method is called to check if that is the case
            while (OmarPerson.invalidString(this.postal));  
            // Postal codes are written with capital letters so use toUpperCase()
            // This is done on a seperate line because if the entered value is null, then the program will crash because of toUpperCase, therefore it is
            // done after the string is confired to not being null.
            this.postal = this.postal.toUpperCase(); 
        }
        while (OmarPerson.satisfied(this.postal));
    }
}
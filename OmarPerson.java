// Import JOptionPane
import javax.swing.JOptionPane;
// Import full io class
import java.io.*;

/**
 * A person object that stores a person's info: firstName, lastName, age, address
 * Includes methods: toString, printFirstLast, enterPerson, inputPerson, satisfied, inputSatisied, invalidNum, invalidString, compareTo
 *
 * @author (Omar Elasfar)
 * @version (Version 1)
 */
public class OmarPerson
{
    // instance variables
    String firstName;
    String lastName;
    byte age;
    OmarAddress address;

    // Declare constant variables for the number of instance variables in a person for the for-loops in the inputEmployee and inputManager
    public static final byte INSTANCE_VARIABLES_OMARPERSON_AND_OMARADDRESS = 5; 

    // Declare static boolean for try-catch loops for all methods in this class (subclasses too) and others
    public static boolean LOOP = true;

    /**
     * Default constructor for objects of class OmarPerson - takes in nothing
     */
    public OmarPerson()
    {
        // initialise instance variables
        this.firstName = "N/A";
        this.lastName = "N/A";
        this.age = 0;
        this.address = new OmarAddress();
    }

    // Overriding toString method
    public String toString()
    {
        // Use String.format to avoid concatenation
        return String.format("%s, %s (Age: %d)\n%s", this.lastName, this.firstName, this.age, this.address);
    }

    // Printing method that prints a Person's object with "first name last name" format
    public String printFirstLast()
    {
        // Use String.format to avoid concatenation
        return String.format("%s %s (Age: %d)\n%s", this.firstName, this.lastName, this.age, this.address);
    }

    // Method that fills in each instance variable of a Person through the panes (not file)
    public void enterPerson()
    {
        // Ask user for first name using the JOptionPane to store in the object
        // Do-while loop to ensure that the entry was valid ie. no typos, misspelling, etc.
        do 
        {
            do
            {
                this.firstName = JOptionPane.showInputDialog("Please enter the FIRST name. (ex: \"Omar\")");
            }
            // Since JOptionPane.showInputDialog allows a user to return a "null" by cancelling, as well as the user being able to submit the input pop-up
            // without entering anything, therefore invalidString method is called to check if that is the case
            while (invalidString(this.firstName)); 
        }
        while (satisfied(this.firstName));

        // Ask user for last name using the JOptionPane to store in the object
        // Do-while loop to ensure that the entry was valid ie. no typos, misspelling, etc.
        do 
        {
            do
            {
                this.lastName = JOptionPane.showInputDialog("Please enter the LAST name of " + this.firstName + ". (ex: \"Elasfar\")");
            }
            // Since JOptionPane.showInputDialog allows a user to return a"null" by cancelling, as well as the user being able to submit the input pop-up
            // without entering anything, therefore invalidString method is called to check if that is the case
            while (invalidString(this.lastName)); 
        }
        while (satisfied(this.lastName));

        // Ask user for age using the JOptionPane to store in the object
        // Do-while loop to ensure that the entry was valid ie. no typos, misspelling, etc.
        do 
        {
            // Do-while loop to allow user to input a valid answer even if they previously entered invalid ones. This way user gets infinite chances
            do
            {
                // Using a try-catch to catch any code-crashing entries by the user
                try
                {   
                    this.age = Byte.parseByte(JOptionPane.showInputDialog("Please enter the age of " + this.firstName + " " + this.lastName
                            + ". (ex: \"17\")"));
                    // Set loop boolean to false, meaning that there is no need to loop the try-catch since the entry was valid
                    LOOP = false;
                }
                catch (Exception e)
                {
                    // Set loop boolean to true, meaning that there is a need to loop the try-catch since the entry was not valid
                    LOOP = true;

                    // Output error message
                    JOptionPane.showConfirmDialog(null, "Please enter a positive WHOLE NUMBER between (1-127)",
                        "ERROR", JOptionPane.DEFAULT_OPTION);
                }
            }
            // Add a second condition that ensures the number is a valid number by calling the invalidNum method that checks if the number is <= 0
            while (LOOP || invalidNum(this.age));
        }
        while (satisfied(this.age+ "")); // Blank string to fool the program into thinking the num is a string

        // Call method from OmarAddress class, enterAddress(), and give as the parameter the person's name.
        this.address.enterAddress(this.firstName + " " + this.lastName);
    }

    // Method that fills in each instance variable of a person by inputting information from a file
    // Takes in the person's 
    public boolean inputPerson(String fileName)
    {
        // try catch block to prevent crashes based on user entries
        try
        {
            // Create a FileInputStream object
            FileInputStream fis = new FileInputStream(fileName);
            // Create a BufferedReader to read the data from the file
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            // Read the first line and store it in as first name
            this.firstName = br.readLine();
            // Read the second line and store it in as last name
            this.lastName = br.readLine();
            // Read the third line, parse it to a byte and store it as age
            this.age = Byte.parseByte(br.readLine());
            // Read the fourth line and store it in as street
            this.address.street = br.readLine();
            // Read the fifth line and store it in as postal
            this.address.postal = br.readLine().toUpperCase(); // Postal codes are written with all uppercases

            // Close the BufferedReader
            br.close();
            // Close the FileInputStream
            fis.close();
        }
        catch (FileNotFoundException e)
        {
            // Handle the exception that is thrown when the specified file is not found
            // Return false because the input was not complete
            return false;
        }
        catch (IOException e)
        {
            // If an I/O error occurs
            // Return false because the input was not complete
            return false;
        }
        catch (Exception e)
        {
            // Handle any other exception that could be thrown
            // Return false because the input was not complete
            return false;
        }

        // Return true because the input was complete
        return true;
    }

    // Method that asks the user if they are satisfied with their answer
    public static boolean satisfied(String x)
    {
        return JOptionPane.showConfirmDialog(null, "Are you satisfied with \"" + x + "\" as your answer?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION;
    }

    // Method that asks the user if they are satisfied with the retrieved info
    public static boolean inputSatisfied(Object x)
    {
        return JOptionPane.showConfirmDialog(null, "Is this the intended information?\n\n" + x
        , "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    // Method that checks if the number entered is greater or equal to 0
    public static boolean invalidNum(int x)
    {
        // Returns true if the number is invalid (lesser than or equal 0)
        return x <= 0;
    }

    // Method that checks if a String is a null or blank
    public static boolean invalidString(String x)
    {
        // Returns true if the String is invalid (null or a blank string)
        return (x == null || x.equals(""));
    }

    // Method that checks if a person is the same as another
    public byte compareTo(String firstName, String lastName, byte age)
    {
        // check if the current OmarPerson's firstName, lastName, and age is equal to the provided firstName, lastName and age
        if (this.firstName.equals(firstName) && this.lastName.equals(lastName) && this.age == age)
        {
            // return 0 if they are equal
            return 0;
        } 
        // check if the current OmarPerson's lastName is greater than the provided lastName
        else if (this.lastName.compareTo(lastName) > 0)
        {
            // return 1 if it is greater
            return 1;
        } 
        // check if the current OmarPerson's lastName is less than the provided lastName
        else if (this.lastName.compareTo(lastName) < 0)
        {
            // return -1 if it is less
            return -1;
        } 
        // check if the current OmarPerson's firstName is greater than the provided firstName
        else if (this.firstName.compareTo(firstName) > 0)
        {
            // return 1 if it is greater
            return 1;
        } 
        // check if the current OmarPerson's firstName is less than the provided firstName
        else if (this.firstName.compareTo(firstName) < 0)
        {
            // return -1 if it is less
            return -1;
        }
        // check if the current OmarPerson's age is greater than the provided age
        else if (this.age > age)
        {
            // return 1 if it is greater
            return 1;
        } else
        {
            // return -1 if all of the above conditions are false
            return -1;
        }
    }
}
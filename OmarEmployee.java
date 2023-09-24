// Import JOptionPane
import javax.swing.JOptionPane;
// Import full io class
import java.io.*;
/**
 * An employee object that inherits person and adds: position and wage (encapsulated)
 * Includes methods: toString, printFirstLast, getWage, setWage, enterEmployee, inputEmployee, searchForEmployee, sortLastName, addEmployee
 * 
 * @author (Omar Elasfar)
 * @version (Version 1)
 */
public class OmarEmployee extends OmarPerson
{
    // instance variables
    String position;
    private float wage;

    /**
     * Default constructor for objects of class OmarEmployee - takes in nothing
     */
    public OmarEmployee()
    {
        // initialise instance variables
        super();
        this.position = "N/A";
        this.wage = 0;
    }

    // Overriding toString method
    public String toString()
    {
        // Use String.format to avoid concatenation
        return String.format("%s\n%s\t $%.2f/hr", super.toString(), this.position, this.wage);
    }

    // Printing method that prints a Person's object with "first name last name" format
    public String printFirstLast()
    {
        // Use String.format to avoid concatenation
        return String.format("%s\n%s\t $%.2f/hr", super.printFirstLast(), this.position, this.wage);
    }

    // Setter method for wage
    public void setWage(float wage)
    {
        this.wage = wage;
    }

    // Getter method for wage
    public float getWage()
    {
        return this.wage;
    }

    // Method that fills in each instance variable of an employee through panes (not file)
    public void enterEmployee()
    {
        // Call method from OmarPerson class, enterPerson(), and give as the parameter the person's name. The method will directly affect the given 
        // variable (this)
        this.enterPerson();

        // Ask user for employee's position using the JOptionPane to store in the object
        // Do-while loop to ensure that the entry was valid ie. no typos, misspelling, etc.
        do 
        {
            do
            {
                this.position = JOptionPane.showInputDialog("Please enter " + this.firstName + " " + this.lastName + "'s position. (ex: \"Cashier\")");
            }
            // Since JOptionPane.showInputDialog allows a user to return a"null" by cancelling, as well as the user being able to submit the input pop-up
            // without entering anything, therefore invalidString method is called to check if that is the case
            while (invalidString(this.position));  
        }
        while (satisfied(this.position));

        // Ask user for employee's hourly wage using the JOptionPane to store in the object
        // Do-while loop to ensure that the entry was valid ie. no typos, misspelling, etc.
        do 
        {
            // Do-while loop to allow user to input a valid answer even if they previously entered invalid ones. This way user gets infinite chances
            do
            {
                // Using a try-catch to catch any code-crashing entries by the user
                try
                {   
                    this.wage = Float.parseFloat(JOptionPane.showInputDialog("Please enter the hourly wage of " + this.firstName + " " + this.lastName
                            + ". (ex: \"19\" or \"20.76\")"));
                    // Set loop boolean to false, meaning that there is no need to loop the try-catch since the entry was valid
                    LOOP = false;
                }
                catch (Exception e)
                {
                    // Set loop boolean to true, meaning that there is a need to loop the try-catch since the entry was not valid
                    LOOP = true;

                    // Output error message
                    JOptionPane.showConfirmDialog(null, "Please enter a positive NUMBER", "ERROR", JOptionPane.DEFAULT_OPTION);
                }
            }
            // Add a second condition that ensures the number is a valid number by calling the invalidNum method that checks if the number is <= 0
            while (LOOP || invalidNum((int)(Math.ceil(this.wage)))); //Math.ceil to get the next whole number and had to cast as an int because .ceil returns a double
        }
        while (satisfied(this.wage + "")); // Blank string to fool the program into thinking the num is a string
    }

    // Method that fills in each instance variable of an employee by inputted information from a file
    public void inputEmployee(String fileName)
    {
        try
        {
            // Call method inputPerson from class OmarPerson to input the first seven lines
            // If statement because if an error was found in the inputPerson mehtod then immediatly call the enterEmployee method since IO is out the picture
            if (!(this.inputPerson(fileName)))
            {
                // Inform user that due to the input not satisfying their desire, they would have to enter the info manually
                JOptionPane.showMessageDialog(null, "There was an error with your file. Please enter the info manually.", "Error", JOptionPane.ERROR_MESSAGE);
                // Call enterEmployee method to enter the info through the JOptionPane
                this.enterEmployee();
                // End the method
                return;
            }

            // Create a FileInputStream object
            FileInputStream fis = new FileInputStream(fileName);
            // Create a BufferedReader to read the data from the file
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            // For-loop to read the first lines up until the instance variables relevant to this sub class
            for (byte i = 1; i <= OmarPerson.INSTANCE_VARIABLES_OMARPERSON_AND_OMARADDRESS; i++)
            {
                // Read the first lines and do nothing with it
                br.readLine();
            }

            // Read the eighth line and store it in as positon
            this.position = br.readLine();
            // Read the nineth line, parse it to a float and store it as wage
            this.wage = Float.parseFloat(br.readLine());

            // Close the BufferedReader
            br.close();
            // Close the FileInputStream
            fis.close();// Print the values of the variables

            // Ask to see if user is satisfied by the inputted data received from the file proveded
            if (inputSatisfied(this))
            {
                // End method if the user is satisfied with the input
                return;
            }
            else
            {
                // Inform user that due to the input not satisfying their desire, they would have to enter the info manually
                JOptionPane.showMessageDialog(null, "Please enter the info manually.", "Error", JOptionPane.ERROR_MESSAGE);
                // Call enterEmployee from the OmarEmployee class to have the user enter the info manually
                this.enterEmployee();
                // End the method
                return;
            }
        }
        catch (FileNotFoundException e)
        {
            // Handle the exception that is thrown when the specified file is not found
            JOptionPane.showMessageDialog(null, "Error: File not found. Please enter the info manually.", "Error", JOptionPane.ERROR_MESSAGE);
            // Call enterEmployee method to allow the user to anter the information manually
            this.enterEmployee();
            // End the method
            return;
        }
        catch (IOException e)
        {
            // If an I/O error occurs
            JOptionPane.showMessageDialog(null, "Error: IO Exception. Please enter the info manually.", "Error", JOptionPane.ERROR_MESSAGE);
            // Call enterEmployee method to allow the user to anter the information manually
            this.enterEmployee();
            // End the method
            return;
        }
        catch (Exception e)
        {
            // Handle any other exception that could be thrown
            JOptionPane.showMessageDialog(null, "Error: An unknown error occurred!\nPlease enter the info manually.", "Error", JOptionPane.ERROR_MESSAGE);
            // Call enterEmployee method to allow the user to anter the information manually
            this.enterEmployee();
        }
    }
    
    // Method to search for an employee
    public static short searchForEmployee(OmarEmployee[] array, String firstName, String lastName, byte age)
    {
        // Using binary search to search for the target OmarEmployee
        // initialize left pointer to the first index of the array
        short left = 0;
        // initialize right pointer to the last index of the array
        short right = (short)(array.length-1);
        // Start a while loop that runs until left is less than or equal to right
        while (left <= right)
        {
            // calculate the midpoint of the current search range
            short mid = (short)((left+right)/2);
            // call the compareTo method of the current object with the given firstName, lastName and age
            short comparison = array[mid].compareTo(firstName, lastName, age);

            // check if the comparison returns 0, meaning that the target OmarPerson has been found
            if (comparison == 0)
            {
                // return the index of the target object
                return mid;
            } 
            else if (comparison < 0) // if comparison returns a negative value, it means that the target OmarPerson is greater than the object at the midpoint
            {
                // set the left pointer to the midpoint + 1 to search on the right half of the array
                left = (short)(mid+1);
            } 
            else // if comparison returns a positive value, it means that the target OmarPerson is less than the object at the midpoint
            {
                // set the right pointer to the midpoint - 1 to search on the left half of the array
                right = (short)(mid-1);
            }
        }
        // if the loop completes without finding a match, return -1
        return -1;
    }

    // Method that sorts an employee array alphebetically by last name
    public static void sortByLastName(OmarEmployee[] array) // Use the bubble sort algorithm to sort the array
    {
        // Check if the array is null or empty
        if (array == null || array.length == 0) 
        {
            // Outputs error message
            JOptionPane.showMessageDialog(null, "Error: Array is null or empty", "Error", JOptionPane.ERROR_MESSAGE);
            
            // Ends the methods
            return;
        }

        // Declare boolean
        boolean swapped;
        // Start a do-while loop, the loop runs until swapped is false.
        do
        {
            // initialize swapped to false
            swapped = false;
            // iterate over all elements in the array except the last one
            for (short i = 0; i < array.length-1; i++)
            {
                // Compare the lastName of the current element with the lastName of the next element
                if (array[i].lastName.compareTo(array[i+1].lastName) > 0)
                {
                    // If the current lastName is greater than the next lastName, it means that the elements are out of order
                    // so it swaps the two elements
                    OmarEmployee temp = array[i];
                    array[i] = array[i+1];
                    array[i+1] = temp;
                    // set the value of swapped to true
                    swapped = true;
                }
            }
        } 
        while (swapped);
    }
    
    // Method to add an employee to an array
    public OmarEmployee[] addEmployee(OmarEmployee[] array)
    {
        // Intilisializing an array of objects that is one larger than the one given
        OmarEmployee[] newArray = new OmarEmployee[array.length+1];

        // For loop that will set each slot of the new array to the old array but skips the one at the index
        for (short i = 0; i < newArray.length-1; i++)
        {
            // Setting the new array's slot to the old slot
            newArray[i] = array[i];
        }
        
        // Setting the last slot of the array to the OmarEmployee provided
        newArray[newArray.length-1] = this;
        
        // Informing user that the removal was a success
        JOptionPane.showMessageDialog(null, "Addition was successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
        
        // Sorting the array in numerical order once again
        sortByLastName(newArray);
        
        //return the updated array
        return newArray;
    }
}
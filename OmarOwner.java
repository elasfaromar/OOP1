// Import JOptionPane
import javax.swing.JOptionPane;
// Import full io class
import java.io.*;

/**
 * An owner object that extends person, since the owner is a person but the owner has unique instance variablescsuch as an array of stores, totalMans, and totalEmps
 * Methods included: toString, enterOwner, ordinalEnding, getOnesDigit, askForPersonInfo, askForStoreNum, retry, fileOutput, removeStore, removeEmployee, removeManager, choices
 *
 * @author (Omar Elasfar)
 * @version (Version 1)
 */
public class OmarOwner extends OmarPerson
{
    // instance variables
    OmarStore[] stores;

    /**
     * Default constructor for objects of class OmarOwner - takes in nothing
     */
    public OmarOwner()
    {
        // initialise instance variables
        super();
        this.stores = null;
    }

    // Overriding toString method
    public String toString()
    {
        // Use String.format to avoid concatenation
        return String.format("Owner: %s\n\nStores: %s", super.toString(), OmarStore.printArray(stores));
    }

    // Method that fills in each instance variable of an owner through panes (not file)
    public void enterOwner()
    {
        // Declare and initialize variable for number of stores the owner owns
        byte numOfStores = 0;

        // Inform user how to proceed if there is any instance variable they, at the time being, are not aware of
        JOptionPane.showConfirmDialog(null, "You will start the entry process of a store owner, \nif there is any information you don't know, input\na valid" + 
            " entry for the time being. You will get the\nchance to update all information later on.", "Confirm", JOptionPane.DEFAULT_OPTION);

        // Call enterPerson() method from OmarPerson class to starting creating the Owner's profile
        this.enterPerson();

        // Ask user for number of stores currently under their ownership JOptionPane to store in the object
        // Do-while loop to ensure that the entry was valid ie. no typos, misspelling, etc.
        do 
        {
            // Do-while loop to allow user to input a valid answer even if they previously entered invalid ones. This way user gets infinite chances
            do
            {
                // Using a try-catch to catch any code-crashing entries by the user
                try
                {   
                    numOfStores = Byte.parseByte(JOptionPane.showInputDialog("Please enter the number of stores you currently own. (ex: \"4\")"));
                    // Set loop boolean to false, meaning that there is no need to loop the try-catch since the entry was valid
                    LOOP = false;
                }
                catch (Exception e)
                {
                    // Set loop boolean to true, meaning that there is a need to loop the try-catch since the entry was not valid
                    LOOP = true;

                    // Output error message
                    JOptionPane.showConfirmDialog(null, "Please enter a positive WHOLE NUMBER between (1-127)", "ERROR", JOptionPane.DEFAULT_OPTION);
                }
            }
            // Add a second condition that ensures the number is a valid number by calling the invalidNum method that checks if the number is <= 0
            while (LOOP || invalidNum(numOfStores));
        }
        while (satisfied(numOfStores + "")); // Blank string to fool the program into thinking the num is a string

        // Initialzing the array, this.stores, using the information obtained about the number of stores the owner currently owns
        this.stores = new OmarStore[numOfStores];

        // For-loop loops the length of the array to fill each slot of stores
        for (byte i = 0; i < this.stores.length; i++)
        {
            // Tell user what they are entering
            // Using ordinalEnding() method to find out whether to place st, nd, rd, or th after a number (these are known as ordinals)
            JOptionPane.showConfirmDialog(null, "You are now entering information for your " + ordinalEnding((short)(i+1)) + " store.", 
                "Confirm", JOptionPane.DEFAULT_OPTION);

            // Inisitialize the store object at each index in the array first, then call the enterStore method in the OmarStore class 
            this.stores[i] = new OmarStore();
            this.stores[i].enterStore();
        }

        // Sorting the store in numerical order based on storeNum
        OmarStore.sortByStoreNum(this.stores);
    }

    // Method to find out whether to place st, nd, rd, or th after a number (these are known as ordinals)
    public static String ordinalEnding(short i)
    {
        // Initializing a variables to store the ones digit of the given number
        byte onesDigit = getOnesDigit(i);

        // If statement to decide the ordinal that will be returned based on the value of the ones digit
        if (onesDigit == 1)
        {
            return i + "st"; // ex 21st
        }
        else if (onesDigit == 2)
        {
            return i + "nd"; // ex 2nd
        }
        else if (onesDigit == 3)
        {
            return i + "rd"; // ex 53rd
        }   
        else //numbers between 
        {
            return i + "th"; // ex 10th
        }
    }

    // Method to get the ones digit of a number. 
    // Private method because it is only used in this class
    private static byte getOnesDigit(int number)
    {
        // Use modulus operator to find the remainder when the number is divided by 10
        return (byte)(number % 10);
    }

    // Method that asks user for the first name, last name, and the age of a person
    // Private method because it is only used in this class
    private static String[] askForPersonInfo()
    {
        // Declaring a string array that will later be filled.
        String[] output = new String[3];
        // Initialzing an age byte to store the age of the person
        byte age = 0;

        // Ask user for first name using the JOptionPane to store in the output array at index 0
        // Do-while loop to ensure that the entry was valid ie. no typos, misspelling, etc.
        do 
        {
            do
            {
                output[0] = JOptionPane.showInputDialog("Please enter the FIRST name of the person you'd like to search for. (ex: \"Omar\")");
            }
            // Since JOptionPane.showInputDialog allows a user to return a "null" by cancelling, as well as the user being able to submit the input pop-up
            // without entering anything, therefore invalidString method is called to check if that is the case
            while (invalidString(output[0])); 
        }
        while (satisfied(output[0]));

        // Ask user for last name using the JOptionPane to store in the the output array at index 1
        // Do-while loop to ensure that the entry was valid ie. no typos, misspelling, etc.
        do 
        {
            do
            {
                output[1] = JOptionPane.showInputDialog("Please enter the LAST name of the person you'd like to search for. (ex: \"Elasfar\")");
            }
            // Since JOptionPane.showInputDialog allows a user to return a"null" by cancelling, as well as the user being able to submit the input pop-up
            // without entering anything, therefore invalidString method is called to check if that is the case
            while (invalidString(output[1])); 
        }
        while (satisfied(output[1]));

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
                    age = Byte.parseByte(JOptionPane.showInputDialog("Please enter the age of the person you'd like to search for. (ex: \"17\")"));
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
            while (LOOP || invalidNum(age));
        }
        while (satisfied(age+ "")); // Blank string to fool the program into thinking the num is a string

        // Fooling the program into storing age, a byte, 
        output[2] = age+"";
        
        // returning array
        return output;
    }

    // Method that asks user for store number of a store
    // Private method because it is only used in this class
    private static int askForStoreNum()
    {
        // Initializing an integer that will store the user's answer
        int output = 0;

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
                    output = Integer.parseInt(JOptionPane.showInputDialog("Please enter the Store Number. (ex: \"910\")"));
                    // Set loop boolean to false, meaning that there is no need to loop the try-catch since the entry was valid
                    LOOP = false;
                }
                catch (Exception e)
                {
                    // Set loop boolean to true, meaning that there is a need to loop the try-catch since the entry was not valid
                    LOOP = true;

                    // Output error message
                    JOptionPane.showConfirmDialog(null, "Please enter a positive WHOLE NUMBER",
                        "ERROR", JOptionPane.DEFAULT_OPTION);
                }
            }
            // Add a second condition that ensures the number is a valid number by calling the invalidNum method that checks if the number is <= 0
            while (LOOP || invalidNum(output));
        }
        while (satisfied(output+ "")); // Blank string to fool the program into thinking the num is a string

        // Returning the answer after error proofing it
        return output;
    }
    
    // method that asks user if they would like to retry their trial
    // Private method because it is only used in this class
    private boolean retry(String argument)
    {
        // Declare and Initialize a new variable to store the answer of the showOptionDialog
        byte answer = -1;
        // Initialize an array for the showOptionDialog to show options
        String[] options = {"Re-Try", "Back to Main Menu"};

        // Do-while loop to make sure the user is satisified with their answer
        // Do-while loop to ensure that the entry was valid ie. no misclicks
        do 
        {
            // Using a try-catch to catch any code-crashing entries by the user
            try
            {   
                // Ask user what they would like to do know and store their answer in a variable
                answer = (byte)(JOptionPane.showOptionDialog(null, "We could not find the " + argument + " with your specifications. How would you like to proceed?", "Message", 
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]));
                // Set loop boolean to false, meaning that there is no need to loop the try-catch since the entry was valid
                LOOP = false;
            }
            catch (Exception e)
            {
                // Set loop boolean to true, meaning that there is a need to loop the try-catch since the entry was not valid
                LOOP = true;
            }
        }
        while (satisfied(options[answer])); 

        // if statement to manage what is returned
        if (answer == 0)
        {
            return true; // returning true because that means the user chose to retry
        }
        else 
        {
            return false; // returning false because the user does not want to retry
        }
    }

    // Method to output text to a file
    // Private method because it is only used in this class
    private static void fileOutput(String fileName, Object x)
    {
        try
        {
            //initializing variable for output
            PrintWriter out = new PrintWriter(new FileWriter(fileName));

            //starting teh outpttuing text process
            out.println(x);

            //stopping the outputting of sentences
            out.close();

            //outputting that the output was complete and the name of the outputted file
            JOptionPane.showMessageDialog(null, "Output completed to file \"" + fileName + "\".", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (FileNotFoundException e)
        {
            // Handle the exception that is thrown when the specified file is not found
            JOptionPane.showMessageDialog(null, "Error: File not found. Please enter the info manually.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        catch (IOException e)
        {
            // If an I/O error occurs
            JOptionPane.showMessageDialog(null, "Error: IO Exception. Please enter the info manually.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Method to remove a store at an index from an array
    // Private method because it is only used in this class
    private static OmarStore[] removeStore(OmarStore[] array, short index)
    {
        // Intilisializing an array of objects that is one shorter than the one given
        OmarStore[] newArray = new OmarStore[array.length-1];

        // For loop that will set each slot of the new array to the old array but skips the one at the index
        for (short i = 0; i < newArray.length; i++)
        {
            if (i != index) // this way nothing will happen once the indexes match up from the old and the new
            {
                // Setting the new array's slot to the old slot
                newArray[i] = array[i];
            }
        }

        // Informing user that the removal was a success
        JOptionPane.showMessageDialog(null, "Removal was successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
        
        // returning new array
        return newArray;
    }
    
    // Method to remove a store at an index from an array
    public static OmarEmployee[] removeEmployee(OmarEmployee[] array, short index)
    {
        // Intilisializing an array of objects that is one shorter than the one given
        OmarEmployee[] newArray = new OmarEmployee[array.length-1];

        // For loop that will set each slot of the new array to the old array but skips the one at the index
        for (short i = 0; i < newArray.length; i++)
        {
            if (i != index) // this way nothing will happen once the indexes match up from the old and the new
            {
                // Setting the new array's slot to the old slot
                newArray[i] = array[i];
            }
        }

        // Informing user that the removal was a success
        JOptionPane.showMessageDialog(null, "Removal was successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
        
        // returning new array
        return newArray;
    }
    
    // Method to remove a store at an index from an array
    public static OmarManager[] removeManager(OmarManager[] array, short index)
    {
        // Intilisializing an array of objects that is one shorter than the one given
        OmarManager[] newArray = new OmarManager[array.length-1];

        // For loop that will set each slot of the new array to the old array but skips the one at the index
        for (short i = 0; i < newArray.length; i++)
        {
            if (i != index) // this way nothing will happen once the indexes match up from the old and the new
            {
                // Setting the new array's slot to the old slot
                newArray[i] = array[i];
            }
        }

        // Informing user that the removal was a success
        JOptionPane.showMessageDialog(null, "Removal was successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
        
        // returning new array
        return newArray;
    }
    
    // Method that asks the user what they would like to do with the information so far and completes the user's wishes calling the appropriate methods based on the user's responses
    public void choices()
    {
        // Initialize an array for the showOptionDialog to show options
        String[] options = {"Edit/Remove Info", "Add a New Employee/Manager/Store", "Print out a store's info",
                "Exit the program (All progress will be lost)"};
        // Declare and Initialize a new variable to store the answer of the showOptionDialog
        // -1 because it will cause an error if does not get changed
        byte answer = -1;

        // Do-while loop that keeps looping till user is satisfied with their answer
        // Do-while loop to ensure that the entry was valid ie. no misclicks
        do 
        {
            // Using a try-catch to catch any code-crashing entries by the user
            try
            {   
                // Ask user what they would like to do know and store their answer in a variable
                answer = (byte)(JOptionPane.showOptionDialog(null, "What would you like to do now?", "Choices", 
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]));
                // Set loop boolean to false, meaning that there is no need to loop the try-catch since the entry was valid
                LOOP = false;
            }
            catch (Exception e)
            {
                // Set loop boolean to true, meaning that there is a need to loop the try-catch since the entry was not valid
                LOOP = true;
            }
        }
        while (satisfied(options[answer]));

        // If statement to perform the appropriate actions based on the choice received
        if (answer == 0)
        {
            // Initialize an array for the showOptionDialog to show options
            String[] choices = {"1. The Owner", "2. An Employee", "3. A Manager", "4. A Store", "5. Exit the program"};

            // Do-while loop that keeps looping till user is satisfied with their answer
            // Do-while loop to ensure that the entry was valid ie. no misclicks
            do 
            {
                // Using a try-catch to catch any code-crashing entries by the user
                try
                {   
                    // Ask user what they would like to do know and store their answer in a variable
                    answer = (byte)(JOptionPane.showOptionDialog(null, "You would like to edit info about:", "Edit entered info", 
                            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]));
                    // Set loop boolean to false, meaning that there is no need to loop the try-catch since the entry was valid
                    LOOP = false;
                }
                catch (Exception e)
                {
                    // Set loop boolean to true, meaning that there is a need to loop the try-catch since the entry was not valid
                    LOOP = true;
                }
            }
            while (satisfied(choices[answer])); 

            // If statement to perform the appropriate actions based on the choice received
            if (answer == 0) // Edit Owner
            {
                // Have the user re-enter the Owner's profile but just the questions that are from he super class OmarPerson (First and Last Name, age, address)
                this.enterPerson();
            }
            else if (answer == 1 || answer == 2) // Edit employee or manager
            {
                // Loop until user wants to return to main menu
                do
                {
                    // Declaring an int that will store the Store's number. Initializing the variable by calling the askForStoreNum
                    int storeNum = askForStoreNum();

                    // Declaring an int that will store the Store's index within an array. Initializing the variable by calling the searchForStore method in the OmarStore Class
                    int storeIndex = OmarStore.searchForStore(this.stores, storeNum);

                    // First findout if the store was found
                    if (storeIndex == -1)
                    {
                        // See if user wants to retry
                        if (retry("store")) //returns true if user wants to retry, returns false if not
                        {
                            // sets LOOP to true meaning the program will reprompt the user to enter the store number
                            LOOP = true;
                        }
                        else
                        {
                            // User chose not to retry so calling the choices method again (main menu) 
                            choices();
                            // sets LOOP to false meaning the method will end
                            LOOP = false;
                        }
                    }
                    else
                    {
                        // Declaring an OmarStore object that will store the found store
                        OmarStore store = this.stores[storeIndex];

                        // the store was found. Now confirm if the correct store was chosen. 
                        if (inputSatisfied(store))
                        {
                            // Declare an array of Strings and initialise using askForPersonInfo method that fills within the array: first, last names, and age
                            String[] temp = askForPersonInfo();

                            if (answer == 1) // means user wants to edit an employee
                            {
                                // Loop until user is wants to return to main menu
                                do 
                                {
                                    // Declare a short that will hold the index of the employee in the store's array of employees
                                    // Initializing it using searchForEmployee method in OmarEmployee class
                                    short employeeIndex = OmarEmployee.searchForEmployee(store.emps, temp[0], temp[1], Byte.parseByte(temp[2]));

                                    // First findout if the employee was found
                                    if (employeeIndex == -1)
                                    {
                                        // See if user wants to retry
                                        if (retry("employee")) //returns true if user wants to retry, returns false if not
                                        {
                                            // sets LOOP to true meaning the program will reprompt the user to enter the person's info
                                            LOOP = true;
                                        }
                                        else
                                        {
                                            // User chose not to retry so calling the choices method again (main menu) 
                                            choices();
                                        }
                                    }
                                    else
                                    {
                                        // Declaring an OmarEmployee object that will store the found employee
                                        OmarEmployee employee = store.emps[employeeIndex];
                                        // Initialize an array for the showOptionDialog to show options
                                        String[] x = {"Edit info", "Remove"};

                                        // the employee was found. Now confirm if the correct employee was chosen. 
                                        if (inputSatisfied(employee))
                                        {
                                            // Do-while loop to ensure that the entry was valid ie. no misclicks
                                            do 
                                            {
                                                // Using a try-catch to catch any code-crashing entries by the user
                                                try
                                                {   
                                                    // Ask user what they would like to do know and store their answer in a variable
                                                    answer = (byte)(JOptionPane.showOptionDialog(null, "You would like to do to " + employee.firstName + "?", "Edit entered info", 
                                                            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, x, x[0]));
                                                    // Set loop boolean to false, meaning that there is no need to loop the try-catch since the entry was valid
                                                    LOOP = false;
                                                }
                                                catch (Exception e)
                                                {
                                                    // Set loop boolean to true, meaning that there is a need to loop the try-catch since the entry was not valid
                                                    LOOP = true;
                                                }
                                            }
                                            while (satisfied(x[answer])); 

                                            // If statement to perform the appropriate actions based on the choice received
                                            if (answer == 0) // Edit Employee
                                            {
                                                //Call enterEmployee method and have the user re-enter their info
                                                store.emps[employeeIndex].enterEmployee();
                                            }
                                            else // Remove employee
                                            {
                                                // Calling fileOutput method to output the employee
                                                fileOutput(employee.firstName + employee.lastName + ".txt", employee);
                                                // Calling removeEmployee from the OmarPerson class to remove the employee at the index we got previously
                                                store.emps = removeEmployee(store.emps, employeeIndex);
                                            }
                                        }
                                        else
                                        {
                                            // See if user wants to retry
                                            if (retry("employee")) //returns true if user wants to retry, returns false if not
                                            {
                                                // sets LOOP to true meaning the program will reprompt the user to enter the store number
                                                LOOP = true;
                                            }
                                            else
                                            {
                                                // User chose not to retry so calling the choices method again (main menu) 
                                                choices();
                                                // sets LOOP to false meaning the method will end
                                                LOOP = false;
                                            }
                                        }
                                    }  
                                }
                                while (LOOP);
                            }
                            else if (answer == 2) //means user wants to edit a manager
                            {
                                // Loop until user is wants to return to main menu
                                do 
                                {
                                    // Declare a short that will hold the index of the employee in the store's array of employees
                                    // Initializing it using searchForManager method in OmarManager class
                                    short managerIndex = OmarManager.searchForManager(store.mans, temp[0], temp[1], Byte.parseByte(temp[2]));

                                    // First findout if the manager was found
                                    if (managerIndex == -1)
                                    {
                                        // See if user wants to retry
                                        if (retry("manager")) //returns true if user wants to retry, returns false if not
                                        {
                                            // sets LOOP to true meaning the program will reprompt the user to enter the person's info
                                            LOOP = true;
                                        }
                                        else
                                        {
                                            // User chose not to retry so calling the choices method again (main menu) 
                                            choices();
                                            // sets LOOP to false meaning the method will end
                                            LOOP = false;
                                        }
                                    }
                                    else
                                    {
                                        // Declaring an OmarEmployee object that will store the found manager
                                        OmarManager manager = store.mans[managerIndex];
                                        // Initialize an array for the showOptionDialog to show options
                                        String[] x = {"Edit info", "Remove"};

                                        // the manager was found. Now confirm if the correct manager was chosen. 
                                        if (inputSatisfied(manager))
                                        {
                                            // Do-while loop that keeps looping till user is satisfied with their answer ie. no misclicks
                                            do 
                                            {
                                                // Using a try-catch to catch any code-crashing entries by the user
                                                try
                                                {   
                                                    // Ask user what they would like to do know and store their answer in a variable
                                                    answer = (byte)(JOptionPane.showOptionDialog(null, "You would like to do to " + manager.firstName + "?", "Edit entered info", 
                                                            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, x, x[0]));
                                                    // Set loop boolean to false, meaning that there is no need to loop the try-catch since the entry was valid
                                                    LOOP = false;
                                                }
                                                catch (Exception e)
                                                {
                                                    // Set loop boolean to true, meaning that there is a need to loop the try-catch since the entry was not valid
                                                    LOOP = true;
                                                }
                                            }
                                            while (satisfied(choices[answer])); 

                                            // If statement to perform the appropriate actions based on the choice received
                                            if (answer == 0) // Edit manager
                                            {
                                                //Call enterManager method and have the user re-enter their info
                                                store.mans[managerIndex].enterManager();
                                            }
                                            else // Remove manager
                                            {
                                                // Calling fileOutput method to output the manager
                                                fileOutput(manager.firstName + manager.lastName + ".txt", manager);
                                                // Calling removeManager from the OmarPerson class to remove the manager at the index we got previously
                                                store.mans = removeManager(store.mans, managerIndex);
                                            }
                                        }
                                        else
                                        {
                                            // See if user wants to retry
                                            if (retry("manager")) //returns true if user wants to retry, returns false if not
                                            {
                                                // sets LOOP to true meaning the program will reprompt the user to enter the store number
                                                LOOP = true;
                                            }
                                            else
                                            {
                                                // User chose not to retry so calling the choices method again (main menu) 
                                                choices();
                                                // sets LOOP to false meaning the method will end
                                                LOOP = false;
                                            }
                                        }
                                    }  
                                }
                                while (LOOP);
                            }
                        }
                        else
                        {
                            // See if user wants to retry
                            if (retry("store")) //returns true if user wants to retry, returns false if not
                            {
                                // sets LOOP to true meaning the program will reprompt the user to enter the store number
                                LOOP = true;
                            }
                            else
                            {
                                // User chose not to retry so calling the choices method again (main menu) 
                                choices();
                                // sets LOOP to false meaning the method will end
                                LOOP = false;
                            }
                        }
                    }
                }
                while (LOOP);
            }
            else if (answer == 3) // Edit store
            {

                // Loop until user is wants to return to main menu
                do
                {
                    // Declaring an int that will store the Store's number. Initializing the variable by calling the askForStoreNum
                    int storeNum = askForStoreNum();

                    // Declaring an int that will store the Store's index within an array. Initializing the variable by calling the searchForStore method in the OmarStore Class
                    byte storeIndex = OmarStore.searchForStore(this.stores, storeNum);

                    // First findout if the store was found
                    if (storeIndex == -1)
                    {
                        // See if user wants to retry
                        if (retry("store")) //returns true if user wants to retry, returns false if not
                        {
                            // sets LOOP to true meaning the program will reprompt the user to enter the store number
                            LOOP = true;
                        }
                        else
                        {
                            // User chose not to retry so calling the choices method again (main menu) 
                            choices();
                            // sets LOOP to false meaning the method will end
                            LOOP = false;
                        }
                    }
                    else
                    {
                        // Declaring an OmarStore object that will store the found store
                        OmarStore store = this.stores[storeIndex];
                        // Initialize an array for the showOptionDialog to show options
                        String[] x = {"Completely Re-Enter Information", "Remove"};

                        // the store was found. Now confirm if the correct store was chosen. 
                        if (inputSatisfied(store))
                        {
                            // Do-while loop that keeps looping till user is satisfied with their answer ie. no misclicks
                            do 
                            {
                                // Using a try-catch to catch any code-crashing entries by the user
                                try
                                {   
                                    // Ask user what they would like to do know and store their answer in a variable
                                    answer = (byte)(JOptionPane.showOptionDialog(null, "You would like to do to store #" + store.storeNum + "?", "Edit entered info", 
                                            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, x, x[0]));
                                    // Set loop boolean to false, meaning that there is no need to loop the try-catch since the entry was valid
                                    LOOP = false;
                                }
                                catch (Exception e)
                                {
                                    // Set loop boolean to true, meaning that there is a need to loop the try-catch since the entry was not valid
                                    LOOP = true;
                                }
                            }
                            while (satisfied(x[answer])); 

                            // If statement to perform the appropriate actions based on the choice received
                            if (answer == 0) // Edit store
                            {
                                //Call enterStore method and have the user re-enter their info
                                store.enterStore();
                            }
                            else // Remove store
                            {
                                // Calling fileOutput method to output the store
                                fileOutput(store.storeNum + ".txt", store);
                                // Calling removeStore from the OmarPerson class to remove the store at the index we got previously
                                this.stores = removeStore(this.stores, storeIndex);
                            }
                        }
                        else
                        {
                            // See if user wants to retry
                            if (retry("store")) //returns true if user wants to retry, returns false if not
                            {
                                // sets LOOP to true meaning the program will reprompt the user to enter the store number
                                LOOP = true;
                            }
                            else
                            {
                                // User chose not to retry so calling the choices method again (main menu) 
                                choices();
                                // sets LOOP to false meaning the method will end
                                LOOP = false;
                            }
                        }
                    }
                }
                while (LOOP);
            }
            else //Exit program
            {
                // ends method
                return;
            }
            //Calling choices method again. This way the panel keeps popping up till the user selects exit the program
            choices();
        }
        else if (answer == 1) // Add manager/employee/store
        {
            // Initialize an array for the showOptionDialog to show options
            String[] x = {"A store", "An Employee", "A Manager"};

            // Do-while loop that keeps looping till user is satisfied with their answer ie. no misclicks
            do 
            {
                // Using a try-catch to catch any code-crashing entries by the user
                try
                {   
                    // Ask user what they would like to do know and store their answer in a variable
                    answer = (byte)(JOptionPane.showOptionDialog(null, "What would you like to add?", "Add", 
                            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, x, x[0]));
                    // Set loop boolean to false, meaning that there is no need to loop the try-catch since the entry was valid
                    LOOP = false;
                }
                catch (Exception e)
                {
                    // Set loop boolean to true, meaning that there is a need to loop the try-catch since the entry was not valid
                    LOOP = true;
                }
            }
            while (satisfied(x[answer])); 

            // If statement to perform the appropriate actions based on the choice received
            if (answer == 0) // add store
            {
                // Declaring a new store object
                OmarStore newStore = new OmarStore();

                // Do-while loop to ensure the user is satisfied with the entry before saving the entry.
                do
                {
                    // Calling enterStore to have the user enter the new store's details
                    newStore.enterStore();
                }
                while (!inputSatisfied(newStore));

                // Call addStore method and store the new store in the owner's array of stores
                this.stores = newStore.addStore(this.stores);
            }
            else // add employee/manager
            {

                // Loop until user is wants to return to main menu
                do 
                {
                    // Declaring an int that will store the Store's number. Initializing the variable by calling the askForStoreNum
                    int storeNum = askForStoreNum();

                    // Declaring an int that will store the Store's index within an array. Initializing the variable by calling the searchForStore method in the OmarStore Class
                    byte storeIndex = OmarStore.searchForStore(this.stores, storeNum);

                    // First findout if the store was found
                    if (storeIndex == -1)
                    {
                        // See if user wants to retry
                        if (retry("store")) //returns true if user wants to retry, returns false if not
                        {
                            // sets LOOP to true meaning the program will reprompt the user to enter the store number
                            LOOP = true;
                        }
                        else
                        {
                            // User chose not to retry so calling the choices method again (main menu) 
                            choices();
                            // sets LOOP to false meaning the method will end
                            LOOP = false;
                        }
                    }
                    else
                    {
                        // Declaring an OmarStore object that will store the found store
                        OmarStore store = this.stores[storeIndex];

                        // the store was found. Now confirm if the correct store was chosen. 
                        if (inputSatisfied(store))
                        {
                            // if statement depending on if user selected manager or employee to add
                            if (answer == 1) // add employee
                            {
                                // Declaring a new employee object
                                OmarEmployee newEmployee = new OmarEmployee();

                                // Do-while loop to ensure the user is satisfied with the entry before saving the entry.
                                do
                                {
                                    // Calling enterEmployee to have the user enter the new store's details
                                    newEmployee.enterEmployee();
                                }
                                while (!inputSatisfied(newEmployee));

                                // Call addEmployee method and store the new manager in the stores' employees array
                                store.emps = newEmployee.addEmployee(store.emps);
                            }
                            else // add manager
                            {
                                // Declaring a new manager object
                                OmarManager newManager = new OmarManager();

                                // Do-while loop to ensure the user is satisfied with the entry before saving the entry.
                                do
                                {
                                    // Calling enterManager to have the user enter the new store's details
                                    newManager.enterManager();
                                }
                                while (!inputSatisfied(newManager));

                                // Call addManager method and store the new manager in the stores' managers array
                                store.mans = newManager.addManager(store.mans);
                            }
                        }
                        else
                        {
                            // See if user wants to retry
                            if (retry("store")) //returns true if user wants to retry, returns false if not
                            {
                                // sets LOOP to true meaning the program will reprompt the user to enter the store number
                                LOOP = true;
                            }
                            else
                            {
                                // User chose not to retry so calling the choices method again (main menu) 
                                choices();
                                // sets LOOP to false meaning the method will end
                                LOOP = false;
                            }
                        }
                    }
                }
                while (LOOP);
            }
            //Calling choices method again. This way the panel keeps popping up till the user selects exit the program
            choices();
        }
        else if (answer == 2) // Print out store
        {

            // Loop until user is wants to return to main menu
            do 
            {
                // Declaring an int that will store the Store's number. Initializing the variable by calling the askForStoreNum
                int storeNum = askForStoreNum();

                // Declaring an int that will store the Store's index within an array. Initializing the variable by calling the searchForStore method in the OmarStore Class
                byte storeIndex = OmarStore.searchForStore(this.stores, storeNum);

                // First findout if the store was found
                if (storeIndex == -1)
                {
                    // See if user wants to retry
                    if (retry("store")) //returns true if user wants to retry, returns false if not
                    {
                        // sets LOOP to true meaning the program will reprompt the user to enter the store number
                        LOOP = true;
                    }
                    else
                    {
                        // User chose not to retry so calling the choices method again (main menu) 
                        choices();
                        // sets LOOP to false meaning the method will end
                        LOOP = false;
                    }
                }
                else
                {
                    // Declaring an OmarStore object that will store the found store
                    OmarStore store = this.stores[storeIndex];

                    // the store was found. Now confirm if the correct store was chosen. 
                    if (inputSatisfied(store)) // means correct store
                    {
                        // Initialize an array for the showOptionDialog to show options
                        String[] x = {"Terminal Window", "Export File", "Both"};

                        // Do-while loop that keeps looping till user is satisfied with their answer ie. no misclicks
                        do 
                        {
                            // Using a try-catch to catch any code-crashing entries by the user
                            try
                            {   
                                // Ask user what they would like to do know and store their answer in a variable
                                answer = (byte)(JOptionPane.showOptionDialog(null, "How would you like to output the code?", "Output", 
                                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, x, x[0]));
                                // Set loop boolean to false, meaning that there is no need to loop the try-catch since the entry was valid
                                LOOP = false;
                            }
                            catch (Exception e)
                            {
                                // Set loop boolean to true, meaning that there is a need to loop the try-catch since the entry was not valid
                                LOOP = true;
                            }
                        }
                        while (satisfied(x[answer])); 

                        // if statement based on the user's answer
                        if (answer == 0)
                        {
                            // Output store via terminal window
                            System.out.println(store);
                        }
                        else // to file or both
                        {
                            // initialize string to hold fileName
                            String fileName;

                            // Ask user for file name using the JOptionPane
                            /// Do-while loop that keeps looping till user is satisfied with their answer ie. no spelling mistakes, types, missclicks
                            do 
                            {
                                // do-while loop to make sure the string is valid (not null)
                                do
                                {
                                    fileName = JOptionPane.showInputDialog("How would you like to save this file? (Provide a file name) (ex: \"OmarElasfar.txt\")");
                                }
                                // Since JOptionPane.showInputDialog allows a user to return a"null" by cancelling, as well as the user being able to submit the input pop-up
                                // without entering anything, therefore a condition is put the while loop so that if the answer is eqaul to null or an empty string to reloop.
                                while (OmarPerson.invalidString(fileName)); 
                            }
                            while (OmarPerson.satisfied(fileName));    

                            if (answer == 1) // output to file
                            {
                                // call fileOutput method and provide the fileName and the store
                                fileOutput(fileName, store);
                            }
                            else //both
                            {
                                // call fileOutput method and provide the fileName and the store
                                fileOutput(fileName, store);
                                // Output store via terminal window
                                System.out.println(store);
                            }
                        }
                    }
                    else
                    {
                        // See if user wants to retry
                        if (retry("store")) //returns true if user wants to retry, returns false if not
                        {
                            // sets LOOP to true meaning the program will reprompt the user to enter the store number
                            LOOP = true;
                        }
                        else
                        {
                            // User chose not to retry so calling the choices method again (main menu) 
                            choices();
                            // sets LOOP to false meaning the method will end
                            LOOP = false;
                        }
                    }
                }
            }
            while (LOOP);

            //Calling choices method again. This way the panel keeps popping up till the user selects exit the program
            choices();
        }
        else //exit the program
        {
            return;
        }
    }
}
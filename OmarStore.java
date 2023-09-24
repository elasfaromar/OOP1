// Import JOptionPane
import javax.swing.JOptionPane;
/**
 * A store object that his instance variables: address, storeNum, and arrays for managers and employees
 * Methods included: toString, printStoreAndAddress, printArray, enterStore, searchForStore, sortByStoreNum, addStore
 *
 * @author (Omar Elasfar)
 * @version (Version 1)
 */
public class OmarStore
{
    // instance variables
    int storeNum; // Store numbers do NOT have to be in order, but even if they were it is realistic to have over 40,000 stores making store numbers reach
    //over 40,000 (out of short range, therefore an int)
    OmarAddress address;
    OmarManager[] mans;
    OmarEmployee[] emps;

    /**
     * Constructor for objects of class OmarStore - takes in nothing
     */
    public OmarStore()
    {
        // initialise instance variables
        this.storeNum = 0;
        this.address = new OmarAddress();
        this.mans = null;
        this.emps = null;
    }

    // Overriding toString method
    public String toString()
    {
        // Use String.format to avoid concatenation
        return String.format("Store #%d\n%s\n\nList of managers:\n%s\nList of employees:\n%s", this.storeNum, this.address, printArray(mans), printArray(emps));
    }

    // Printing method that only prints a Store object's store number and address without array
    public String printStoreAndAddress()
    {
        // Use String.format to avoid concatenation
        return String.format("Store #%d\n%s\n", this.storeNum, this.address);
    }

    // PrintArray method used to print any array
    // Method type is String and not void so that it can be included in String.format() and printf() printing methods
    public static String printArray(Object[] array)
    {
        // Check if the array is null or empty
        if(array == null || array.length == 0)
        {
            // Outputs error message
            JOptionPane.showMessageDialog(null, "Error: Array is null or empty", "Error", JOptionPane.ERROR_MESSAGE);
            return "";
        }

        // Declare and initialise String variable with a blank String
        String output = "";

        // For loop that runs through each index of an array, stores it along with a "\n" in the output String
        for (int i = 0; i<array.length; i++)
        {
            output += array[i] + "\n";
        }

        // Return String output with "\n" (new line)
        return output + "\n";
    }

    // Method that fills in each instance variable of a store through panes (not file)
    public void enterStore()
    {
        // Declare and initialize variable for number of Employees and Managers
        byte numOfManagers = 0;
        short numOfEmployees = 0;

        // Ask user for store number the JOptionPane to store in the object
        // Do-while loop to ensure that the entry was valid ie. no typos, misspelling, etc.
        do 
        {
            // Do-while loop to allow user to input a valid answer even if they previously entered invalid ones. This way user gets infinite chances
            do
            {
                // Using a try-catch to catch any code-crashing entries by the user
                try
                {   
                    this.storeNum = Integer.parseInt(JOptionPane.showInputDialog("Please enter the store's number. (ex: \"917\")"));
                    // Set loop boolean to false, meaning that there is no need to loop the try-catch since the entry was valid
                    OmarPerson.LOOP = false;
                }
                catch (Exception e)
                {
                    // Set loop boolean to true, meaning that there is a need to loop the try-catch since the entry was not valid
                    OmarPerson.LOOP = true;

                    // Output error message
                    JOptionPane.showConfirmDialog(null, "Please enter a positive WHOLE NUMBER", "ERROR", JOptionPane.DEFAULT_OPTION);
                }
            }
            // Add a second condition that ensures the number is a valid number by calling the invalidNum method that checks if the number is <= 0
            while (OmarPerson.LOOP || OmarPerson.invalidNum(this.storeNum));
        }
        while (OmarPerson.satisfied(this.storeNum + "")); // Blank string to fool the program into thinking the num is a string

        // Call enterAddress method in OmarAddress class to ask user for address of the store
        this.address.enterAddress("Store #" + this.storeNum);

        // Ask user for number of managers working at the store using the JOptionPane to store in the object
        // Do-while loop to ensure that the entry was valid ie. no typos, misspelling, etc.
        do 
        {
            // Do-while loop to allow user to input a valid answer even if they previously entered invalid ones. This way user gets infinite chances
            do
            {
                // Using a try-catch to catch any code-crashing entries by the user
                try
                {   
                    numOfManagers = Byte.parseByte(JOptionPane.showInputDialog("Please enter the number of MANAGERS currently working at store #" +
                            this.storeNum + ". (ex: \"17\")"));
                    // Set loop boolean to false, meaning that there is no need to loop the try-catch since the entry was valid
                    OmarPerson.LOOP = false;
                }
                catch (Exception e)
                {
                    // Set loop boolean to true, meaning that there is a need to loop the try-catch since the entry was not valid
                    OmarPerson.LOOP = true;

                    // Output error message
                    JOptionPane.showConfirmDialog(null, "Please enter a positive WHOLE NUMBER between (1-127)", "ERROR", JOptionPane.DEFAULT_OPTION);
                }
            }
            // Add a second condition that ensures the number is a valid number by calling the invalidNum method that checks if the number is <= 0
            while (OmarPerson.LOOP || OmarPerson.invalidNum(numOfManagers));
        }
        while (OmarPerson.satisfied(numOfManagers + "")); // Blank string to fool the program into thinking the num is a string

        // Ask user for number of employees working at the store using the JOptionPane to store in the object
        //Do-while loop to ensure that the entry was valid ie. no typos, misspelling, etc.
        do 
        {
            // Do-while loop to allow user to input a valid answer even if they previously entered invalid ones. This way user gets infinite chances
            do
            {
                // Using a try-catch to catch any code-crashing entries by the user
                try
                {   
                    numOfEmployees = Short.parseShort(JOptionPane.showInputDialog("Please enter the number of EMPLOYEES (exluding managers) currently working at store #" +
                            this.storeNum + ". (ex: \"65\")"));
                    // Set loop boolean to false, meaning that there is no need to loop the try-catch since the entry was valid
                    OmarPerson.LOOP = false;
                }
                catch (Exception e)
                {
                    // Set loop boolean to true, meaning that there is a need to loop the try-catch since the entry was not valid
                    OmarPerson.LOOP = true;

                    // Output error message
                    JOptionPane.showConfirmDialog(null, "Please enter a positive WHOLE NUMBER between (1-32,000)", "ERROR", JOptionPane.DEFAULT_OPTION);
                }
            }
            // Add a second condition that ensures the number is a valid number by calling the invalidNum method that checks if the number is <= 0
            while (OmarPerson.LOOP || OmarPerson.invalidNum(numOfEmployees));
        }
        while (OmarPerson.satisfied(numOfEmployees + "")); // Blank string to fool the program into thinking the num is a string

        // Initialzing arrays this.mans and this.emps using the information obtained about the number of managers and employees working at the store
        this.mans = new OmarManager[numOfManagers];
        this.emps = new OmarEmployee[numOfEmployees];

        // Inititializing an array for the showOptionDialog in the if statement in the next two for loops
        String[] options = {"Manually", "From a File"};
        // Initializing String variable to store the name of the file in the next two for loops
        String fileName; 

        // For-loop loops the length of the array to fill each slot of managers
        for (byte i = 0; i < this.mans.length; i++)
        {
            // Inisitialize the manager object at each index in the array first
            this.mans[i] = new OmarManager();            

            // Tell user what they are entering
            // Then asking the user whether they want to enter an manager's information manually or from a file
            if (JOptionPane.showOptionDialog(null, "You are now entering information for your " + OmarOwner.ordinalEnding((short)(i+1)) + " manager.\n" + 
                "How would you like to enter the information?", "Enter Information", 
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]) == 0)
            {
                // Call the enterManager method in the OmarManager class since the user chose "Manual" entry 
                this.mans[i].enterManager();
            }
            else
            {
                // Explain the formatting of the files before proceeding
                JOptionPane.showConfirmDialog(null, "To input through files, make sure that the files you input are document files.\n" +
                    "Ex: .txt, .doc, .docx, .pdf, .ppt, etc. \n\n" +
                    "Format your file to look like this (ignore brackets):\n\n" +
                    "Omar (First Name)\nElasfar (Last Name)\n17 (Age: Whole and Positive Number)\n" +
                    "103 Malvern Dr (Address)\nK2J 5N7 (Postal Code)\n" +
                    "Pharmacy (Section)\n100000 (Salary: Whole and Positive Number, NO commas)\n",
                    "Attention!", JOptionPane.DEFAULT_OPTION);

                // Ask user for file name using the JOptionPane
                // Do-while loop that keeps looping till user is satisfied with their answer ie. no misclicks
                do 
                {
                    do
                    {
                        fileName = JOptionPane.showInputDialog("Please enter the file name. (ex: \"OmarElasfar.txt\")");
                    }
                    // Since JOptionPane.showInputDialog allows a user to return a"null" by cancelling, as well as the user being able to submit the input pop-up
                    // without entering anything, therefore a condition is put the while loop so that if the answer is eqaul to null or an empty string to reloop.
                    while (OmarPerson.invalidString(fileName)); 
                }
                while (OmarPerson.satisfied(fileName));    

                // Call the inputManager method in the OmarManager class since the user chose "From a File" entry 
                this.mans[i].inputManager(fileName);
            }

            // Print in the window the manager that was entered so that the Owner can never forget who has entered so far
            System.out.println("Manager #" + (i+1) + " from Store #" + this.storeNum + ":\n" + this.mans[i] + "\n\n");
            // Inform user that the manager that was just created was printed in the terminal window if he wanted to know
            JOptionPane.showConfirmDialog(null, "Manager succesfully added! Check terminal window.\n",
                "Success", JOptionPane.DEFAULT_OPTION); 
        }

        // For-loop loops the length of the array to fill each slot of employee
        for (short i = 0; i < this.emps.length; i++)
        {
            // Inisitialize the employee object at each index in the array first
            this.emps[i] = new OmarEmployee();

            // Tell user what they are entering
            // Then asking the user whether they want to enter an employees's information manually or from a file
            if (JOptionPane.showOptionDialog(null, "You are now entering information for your " + OmarOwner.ordinalEnding((short)(i+1)) + " employee.\n" + 
                "How would you like to enter the information?", "Enter Information", 
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]) == 0)
            {
                // Call the enterEmployee method in the OmarEmployee class since the user chose "Manual" entry
                this.emps[i].enterEmployee();
            }
            else
            {
                // Explain the formatting of the files before proceeding
                JOptionPane.showConfirmDialog(null, "To input through files, make sure that the files you input are document files.\n" +
                    "Ex: .txt, .doc, .docx, .pdf, .ppt, etc. \n\n" +
                    "Format your file to look like this (ignore brackets): \n\n" +
                    "Omar (First Name)\nElasfar (Last Name)\n17 (Age: Whole and Positive Number)\n" +
                    "103 Malvern Dr (Address)\nK2J 5N7 (Postal Code)\n" +
                    "Cashier (Position)\n18.5 (Hourly rate: Positive Number)\n",
                    "Attention!", JOptionPane.DEFAULT_OPTION);   

                // Ask user for first name using the JOptionPane to store in the object
                // Do-while loop to ensure that the entry was valid ie. no typos, misspelling, etc.
                do 
                {
                    do
                    {
                        fileName = JOptionPane.showInputDialog("Please enter the file name. (ex: \"OmarElasfar.txt\")");
                    }
                    // Since JOptionPane.showInputDialog allows a user to return a"null" by cancelling, as well as the user being able to submit the input pop-up
                    // without entering anything, therefore a condition is put the while loop so that if the answer is eqaul to null or an empty string to reloop.
                    while (OmarPerson.invalidString(fileName)); 
                }
                while (OmarPerson.satisfied(fileName));    

                // Call the inputEmployee method in the OmarManager class since the user chose "From a File" entry
                this.emps[i].inputEmployee(fileName);
            }

            // Print in the window the employee that was entered so that the Owner can never forget who has entered so far
            System.out.println("Employee #" + (i+1) + " from store #" + this.storeNum + "\n" + this.emps[i] + "\n");
            // Inform user that the employee that was just created was printed in the terminal window if he wanted to know
            JOptionPane.showConfirmDialog(null, "Employee succesfully added! Check terminal window.\n",
                "Success", JOptionPane.DEFAULT_OPTION); 
        }

        // Sorting both arrays alphebetically by last name
        OmarEmployee.sortByLastName(this.emps);
        OmarManager.sortByLastName(this.mans);
    }

    // Search method for an array for stores that finds a store based on storeNum 
    public static byte searchForStore(OmarStore[] array, int storeNum)
    {
        // check if the array is null or empty
        if(array == null || array.length == 0)
        {
            System.out.println("Error: Array is null or empty");
            return -1;
        }

        // Using binary search to search for the target OmarStore
        byte left = 0;
        byte right = (byte)(array.length-1);
        while (left <= right) 
        {
            byte mid = (byte)((left+right)/2);
            // Compare the storeNum of the mid element with the target storeNum
            if (array[mid].storeNum == storeNum)
            {
                // if storeNum is equal return the index
                return mid;
            }
            else if (array[mid].storeNum < storeNum)
            {
                // if storeNum of the mid element is less than the target storeNum, search the right half of the array
                left = (byte)(mid+1);
            }
            else
            {
                // if storeNum of the mid element is greater than the target storeNum, search the left half of the array
                right = (byte)(mid-1);
            }
        }
        // if the loop completes without finding a match, return -1
        return -1;
    }

    // Sorting method that sorts an array of stores by store number
    public static void sortByStoreNum(OmarStore[] array)
    {
        // Check if the array is null or empty
        if(array == null || array.length == 0)
        {
            // Outputs error message
            JOptionPane.showMessageDialog(null, "Error: Array is null or empty", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Use the bubble sort algorithm to sort the array
        boolean swapped;

        // Start a do-while loop, the loop runs until swapped is false.
        do
        {
            // initialize swapped to false
            swapped = false;
            // iterate over all elements in the array except the last one
            for (int i = 0; i < array.length - 1; i++)
            {
                // Compare the storeNum of the current element with the storeNum of the next element
                if (array[i].storeNum > array[i + 1].storeNum)
                {
                    // If the current storeNum is greater than the next storeNum, it means that the elements are out of order
                    // so it swaps the two elements
                    OmarStore temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    // set the value of swapped to true
                    swapped = true;
                }
            }
        }
        while (swapped);
    }

    // Method to add a Store to an array
    public OmarStore[] addStore(OmarStore[] array)
    {
        // Intilisializing an array of objects that is one larger than the one given
        OmarStore[] newArray = new OmarStore[array.length+1];

        // For loop that will set each slot of the new array to the old array but skips the one at the index
        for (short i = 0; i < newArray.length-1; i++)
        {
            // Setting the new array's slot to the old slot
            newArray[i] = array[i];
        }

        // Setting the last slot of the array to the OmarStore provided
        newArray[newArray.length-1] = this;

        // Informing user that the removal was a success
        JOptionPane.showMessageDialog(null, "Addition was successful.", "Success", JOptionPane.INFORMATION_MESSAGE);

        // Sorting the array in numerical order once again
        sortByStoreNum(newArray);

        //return the updated array
        return newArray;
    }
}
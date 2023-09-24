// Import JOptionPanes and GUIs
import javax.swing.*;
/**
 * This class is an interface for the entire Store Owner's tool. It will use all classes in this project and their constructors.
 * This program will have many features, including: creating, adding, and removing: stores, managers, and employees (via file IO and through the window)
 *
 * @author (Omar Elasfar)
 * @version (Version 1)
 */
public class Interface
{
    public static void main(String[] args)
    {
        // Initialize variables
        OmarOwner owner = new OmarOwner();

        // Explain the program to the user
        JOptionPane.showConfirmDialog(null,
            "This tool offers many features to Store Owners including\n" +
            "but not limited to: adding and removing managers and\n" +
            "employees to a store. Once added to the store, the\n" +
            "owner can then search for a specific manager or employee\n" +
            "to remove, promote, give a raise, or edit their information.\n" +
            "Finally, the owner has the choice of inputting an employee or\n" +
            "manager from a file instead of through the window.\n",
            "Welcome to the Store Owner Tool", JOptionPane.DEFAULT_OPTION);

        // Call enterOwner method to fill in every instance variable of the owner object
        owner.enterOwner();

        // Once the owner object is completed, output options of what they would like to do from now
        owner.choices();

        // Create a new JFrame
        JFrame frame = new JFrame("Goodbye");
        // Set the size of the frame
        frame.setSize(300, 100);
        // Set the default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a new JLabel
        JLabel label = new JLabel("Thank you for using our program!");
        // Add the label to the frame
        frame.add(label);

        // Make the frame visible
        frame.setVisible(true);
    }
}
package osproject; // Package declaration

import java.util.ArrayList;

// Class declaration
public class scan extends sharedvalueforalgos implements disksceduler {
    // Member variable
    private final ArrayList<Integer> input_temp;
    private static int seekdistance;
    // Constructor
    scan(ArrayList<Integer> input_cylinders, int head) {
        super(input_cylinders, head); // Call to superclass constructor
        input_temp = new ArrayList<>(input_cylinders); // Initialize input_temp with a copy of input_cylinders
    }

    // Implementation of schedule method from disksceduler interface
    @Override
    public ArrayList<Integer> schedule() {
        int counter = 0; // Initialize counter to keep track of processed cylinders
        int size = input_temp.size(); // Get the size of the input cylinders list
        int start = head_start; // Initialize start position
        boolean f = true; // Flag to control the loop

        cylinders.add(start); // Add initial position to the list of cylinders

        // Forward scan
        for (int i = start; i >= 0; i--) {
            if (counter == size) { // Check if all cylinders have been processed
                f = false; // Set flag to false to break the loop
                break; // Exit the loop
            }
            if (input_temp.contains(i)) { // If the current cylinder is in the input list
                Total_Movement += (start - i); // Calculate movement and update total movement
                start = i; // Update start position
                cylinders.add(start); // Add current cylinder to the list
                counter++; // Increment counter
                input_temp.remove((Integer) i); // Remove the processed cylinder from the input list
            }}
        Total_Movement += start; // Move the head to the beginning of the disk
        start = 0; // Set start to the beginning of the disk
        cylinders.add(start); // Add current position to the list of cylinders

        // If the flag is true, continue scanning in the opposite direction
        if (f) {
            for (int i = start; i < 200; i++) {
                if (counter == size) { // Check if all cylinders have been processed
                    break; // Exit the loop
                }
                if (input_temp.contains(i)) { // If the current cylinder is in the input list
                    Total_Movement += (i - start); // Calculate movement and update total movement
                    start = i; // Update start position
                    cylinders.add(start); // Add current cylinder to the list
                    counter++; // Increment counter
                    input_temp.remove((Integer) i); // Remove the processed cylinder from the input list
                }}}

        // Print total seek distance
        System.out.println("Scan algorithm Total SEEK distance= " + Total_Movement);
           seekdistance=Total_Movement;
        // Calculate and print total seek time
        double cylinder_movement_time = 0.5;
        System.out.println("SCAN_algorithm Total SEEK time = " + Total_Movement * cylinder_movement_time);

        // Print separator
        System.out.println("---------------------------------------------------------------");

        return cylinders; // Return list of cylinders
    }

	public static int getTotalMovement() {
		// TODO Auto-generated method stub
		return seekdistance;
	}
	public static double getTotalTime() {
		// TODO Auto-generated method stub
		return  seekdistance*0.5;
	}

}

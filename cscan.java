package osproject; // Package declaration

import java.util.ArrayList;

// Class declaration
public class cscan extends sharedvalueforalgos implements disksceduler {
    // Member variable
	 private static int seekditance ;
    private final ArrayList<Integer> input_temp;

    // Constructor
    cscan(ArrayList<Integer> input_cylinders, int head) {
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

        // Main loop to scan the cylinders
        while (true) {
            // Scan cylinders starting from the current position
            for (int i = start; i < 200; i++) {
                if (counter == size) { // Check if all cylinders have been processed
                    f = false; // Set flag to false to break the loop
                    break; // Exit the loop
                }
                if (input_temp.contains(i)) { // If the current cylinder is in the input list
                    Total_Movement += (i - start); // Calculate movement and update total movement
                    start = i; // Update start position
                    cylinders.add(start); // Add current cylinder to the list
                    counter++; // Increment counter
                    input_temp.remove((Integer) i); // Remove the processed cylinder from the input list
                }
            }
            if (!f) break; // If all cylinders have been processed, exit the loop

            // Move the head to the end of the disk and then to the beginning
            Total_Movement += (199 - start); // Calculate movement and update total movement
            start = 199; // Move the head to the end of the disk
            cylinders.add(start); // Add current position to the list of cylinders
            Total_Movement += start; // Calculate movement and update total movement
            start = 0; // Move the head to the beginning of the disk
            cylinders.add(start); // Add current position to the list of cylinders
        }

        // Print total seek distance
        System.out.println("C_Scan algorithm Total SEEK distance= " + Total_Movement);
        seekditance=Total_Movement;
        // Calculate and print total seek time
        double cylinder_movement_time = 0.5;
        System.out.println("CSCAN_algorithm Total SEEK time = " + Total_Movement * cylinder_movement_time);

        // Print separator
        System.out.println("---------------------------------------------------------------");

        return cylinders; // Return list of cylinders
    }

	public static int getTotalMovement() {
		// TODO Auto-generated method stub
		return  seekditance;
	}
	public static double getTotalTime() {
		// TODO Auto-generated method stub
		return  seekditance*0.5;
	}

}

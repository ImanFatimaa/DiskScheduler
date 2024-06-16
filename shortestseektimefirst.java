package osproject; // Package declaration

import java.util.ArrayList;

// Class declaration
public class sstf extends sharedvalueforalgos implements disksceduler {
    // Member variable
	 private static int seekditance;
    private final ArrayList<Integer> input_temp;

    // Constructor
    sstf(ArrayList<Integer> inputCylindrs, int head) {
        super(inputCylindrs, head); // Call to superclass constructor
        input_temp = new ArrayList<>(inputCylindrs); // Initialize input_temp with a copy of inputCylindrs
    }

    // Method to get the closest cylinder to the current head position
    int getClosestCylinder(final ArrayList<Integer> list, int head) {
        // Create a copy of the input list
        ArrayList<Integer> cylinders = new ArrayList<>(list);

        // Calculate the distance of each cylinder from the current head position
        for (int i = 0; i < cylinders.size(); i++) {
            cylinders.set(i, Math.abs(cylinders.get(i) - head));
        }

        // Sort the list based on the distances
        sort(cylinders);

        // Return the closest cylinder
        return cylinders.get(0);
    }

    // Implementation of schedule method from disksceduler interface
    @Override
    public ArrayList<Integer> schedule() {
        int size = input_temp.size(); // Get the size of the input cylinders list
        int currentHead = head_start; // Initialize the current head position
        cylinders.add(head_start); // Add initial head position to the list of cylinders

        // Iterate through all input cylinders
        for (int i = 0; i < size; i++) {
            int closest = getClosestCylinder(input_temp, currentHead); // Get the closest cylinder to the current head

            Total_Movement += closest; // Update the total movement
            if (input_temp.contains(currentHead + closest)) { // If the cylinder is in the positive direction
                currentHead = currentHead + closest; // Move the head to the positive direction
            } else { // If the cylinder is in the negative direction
                currentHead = currentHead - closest; // Move the head to the negative direction
            }
            cylinders.add(currentHead); // Add the new head position to the list of cylinders
            input_temp.remove((Integer) currentHead); // Remove the processed cylinder from the input list
        }

        // Print total seek distance
        System.out.println("SSTF algorithm Total SEEK distance= " + Total_Movement);
        seekditance=Total_Movement;
        // Calculate and print total seek time
        double cylinder_movement_time = 0.5;
        System.out.println("SSTF_algorithm Total SEEK time = " + Total_Movement * cylinder_movement_time);

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

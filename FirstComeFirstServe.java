package osproject; // Package declaration

import java.util.ArrayList;

// Class declaration
public class fcfs extends sharedvalueforalgos implements disksceduler {
    // Constructor
	private static int seekditance;
	
    fcfs(ArrayList<Integer> input_cylinders, int head) {
        super(input_cylinders, head); // Call to superclass constructor
    }

    // Implementation of schedule method from disksceduler interface
    @Override
    public ArrayList<Integer> schedule() {
        int start = head_start; // Initialize start position
        cylinders.add(start); // Add initial position to the list of cylinders
        for (Integer input_cylinder : input_temp) { // Loop through input cylinders
            // Calculate movement and update total movement
            Total_Movement += Math.abs(start - input_cylinder);
            start = input_cylinder; // Update start position
            cylinders.add(input_cylinder); // Add current cylinder to the list
        }

        // Print total movement
        System.out.println("FCFS_algorithm Total SEEK distance = " + Total_Movement);
        seekditance=Total_Movement;
        //Print total seek time if cylinder to cylinder time is 0.2ms
        double cylinder_movement_time = 0.5;
        System.out.println("FCFS_algorithm Total SEEK time = " + Total_Movement*cylinder_movement_time);
        System.out.println("---------------------------------------------------------------");
        return cylinders; // Return list of cylinders
    }

	public static  int getTotalMovement() {
		// TODO Auto-generated method stub
		return seekditance;
	}
	public static double getTotalTime() {
		// TODO Auto-generated method stub
		return  seekditance*0.5;
	}

}
/*239, 244, 255*/

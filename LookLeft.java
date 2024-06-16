package osproject; // Package declaration

import java.util.ArrayList;

// Class declaration
public class look extends sharedvalueforalgos implements disksceduler {
    // Member variable
	 private static int seekditance;
    private final ArrayList<Integer> input_temp;

    // Constructor
    look(ArrayList<Integer> input_cylinders, int head) {
        super(input_cylinders, head); // Call to superclass constructor
        input_temp = new ArrayList<>(input_cylinders); // Initialize input_temp with a copy of input_cylinders
    }

    // Implementation of schedule method from disksceduler interface
    @Override
    public ArrayList<Integer> schedule() {
        // Sort the input cylinders
        sort(input_temp);

        // Find the starting point
        int startpoint = 0;
        cylinders.add(head_start);
        for (int i = 0; i < input_temp.size(); i++) {
            if (input_temp.get(i) > head_start) {
                startpoint = i - 1;
                break;
            }
        }

        // Move head in one direction
        int start = head_start;
        for (int i = startpoint; i >= 0; i--) {
            Total_Movement += Math.abs(start - input_temp.get(i));
            cylinders.add(input_temp.get(i));
            start = input_temp.get(i);
        }

        // Move head in the opposite direction
        for (int i = startpoint + 1; i < input_temp.size(); i++) {
            Total_Movement += Math.abs(start - input_temp.get(i));
            cylinders.add(input_temp.get(i));
            start = input_temp.get(i);
        }

        // Print total seek distance
        System.out.println("LOOK algorithm Total SEEK distance= " + Total_Movement);
        seekditance=Total_Movement;
        // Calculate and print total seek time
        double cylinder_movement_time = 0.5;
        System.out.println("LOOK_algorithm Total SEEK time = " + Total_Movement * cylinder_movement_time);

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

package osproject;

import java.util.ArrayList;
import java.util.Collections;

public class lookright extends sharedvalueforalgos implements disksceduler {
    private static int seekditance;
    private final ArrayList<Integer> input_temp;

    public lookright(ArrayList<Integer> input_cylinders, int head) {
        super(input_cylinders, head);
        input_temp = new ArrayList<>(input_cylinders);
    }

    @Override
    public ArrayList<Integer> schedule() {
        // Sort the input cylinders
        Collections.sort(input_temp);

        // Find the starting point
        int startpoint = 0;
        for (int i = 0; i < input_temp.size(); i++) {
            if (input_temp.get(i) > head_start) {
                startpoint = i;
                break;
            }
        }

        // Start from the initial head position
        int start = head_start;
        cylinders.add(start);

        // Move to the right
        for (int i = startpoint; i < input_temp.size(); i++) {
            int current_cylinder = input_temp.get(i);
            Total_Movement += Math.abs(start - current_cylinder);
            cylinders.add(current_cylinder);
            start = current_cylinder;
        }

        // Move to the left
        for (int i = startpoint - 1; i >= 0; i--) {
            int current_cylinder = input_temp.get(i);
            Total_Movement += Math.abs(start - current_cylinder);
            cylinders.add(current_cylinder);
            start = current_cylinder;
        }

        // Print total seek distance
        System.out.println("RIGHTLOOK algorithm Total SEEK distance = " + Total_Movement);
        seekditance = Total_Movement;

        // Calculate and print total seek time
        double cylinder_movement_time = 0.5;
        System.out.println("RIGHTLOOK algorithm Total SEEK time = " + Total_Movement * cylinder_movement_time);

        // Print separator
        System.out.println("---------------------------------------------------------------");

        return cylinders;
    }

    public static int getTotalMovement() {
        return seekditance;
    }

    public static double getTotalTime() {
        return seekditance * 0.5;
    }
}

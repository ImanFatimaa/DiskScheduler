package osproject;

import java.util.ArrayList;
import java.util.Collections;

public class clook extends sharedvalueforalgos implements disksceduler {
    private static int seekditance;
    private final ArrayList<Integer> input_temp;

    clook(ArrayList<Integer> input_cylinders, int head) {
        super(input_cylinders, head);
        input_temp = new ArrayList<>(input_cylinders);
    }

    @Override
    public ArrayList<Integer> schedule() {
        // Sort the input cylinders
        Collections.sort(input_temp);

        // Find the starting point
        int startpoint = 0;
        cylinders.add(head_start);
        for (int i = 0; i < input_temp.size(); i++) {
            if (input_temp.get(i) > head_start) {
                startpoint = i;
                break;
            }
        }

        // Forward scan
        int start = head_start;
        for (int i = startpoint; i < input_temp.size(); i++) {
            Total_Movement += Math.abs(start - input_temp.get(i));
            cylinders.add(input_temp.get(i));
            start = input_temp.get(i);
        }

        // Jump back to the beginning and continue forward scan
        for (int i = 0; i < startpoint; i++) {
            Total_Movement += Math.abs(start - input_temp.get(i));
            cylinders.add(input_temp.get(i));
            start = input_temp.get(i);
        }

        // Print total seek distance
        System.out.println("C-LOOK algorithm Total SEEK distance = " + Total_Movement);
        seekditance = Total_Movement;
        
        // Calculate and print total seek time
        double cylinder_movement_time = 0.5;
        System.out.println("C-LOOK algorithm Total SEEK time = " + Total_Movement * cylinder_movement_time);

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

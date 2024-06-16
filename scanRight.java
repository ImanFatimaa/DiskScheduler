package osproject;

import java.util.ArrayList;

public class ScanRight extends sharedvalueforalgos implements disksceduler {
    private static int seekdistance;
    private final ArrayList<Integer> input_temp;

    public ScanRight(ArrayList<Integer> input_cylinders, int head) {
        super(input_cylinders, head);
        input_temp = new ArrayList<>(input_cylinders);
    }

    @Override
    public ArrayList<Integer> schedule() {
        int counter = 0;
        int size = input_temp.size();
        int start = head_start;
        boolean f = true;

        cylinders.add(start);

        for (int i = start; i < 200; i++) {
            if (counter == size) {
                f = false;
                break;
            }
            if (input_temp.contains(i)) {
                Total_Movement += (i - start);
                start = i;
                cylinders.add(start);
                counter++;
                input_temp.remove((Integer) i);
            }
        }

        Total_Movement += (200 - 1 - start); // Move the head to the end of the disk
        start = 199; // Set start to the end of the disk
        cylinders.add(start);

        if (f) {
            for (int i = start; i >= 0; i--) {
                if (counter == size) {
                    break;
                }
                if (input_temp.contains(i)) {
                    Total_Movement += (start - i);
                    start = i;
                    cylinders.add(start);
                    counter++;
                    input_temp.remove((Integer) i);
                }
            }
        }

        System.out.println("Scan Right algorithm Total SEEK distance= " + Total_Movement);
        seekdistance = Total_Movement;
        double cylinder_movement_time = 0.5;
        System.out.println("SCAN Right algorithm Total SEEK time = " + Total_Movement * cylinder_movement_time);
        System.out.println("---------------------------------------------------------------");

        return cylinders;
    }

    public static int getTotalMovement() {
        return seekdistance;
    }

    public static double getTotalTime() {
        return seekdistance * 0.5;
    }
}

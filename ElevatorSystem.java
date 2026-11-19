import java.util.ArrayList;
import java.util.List;

// Step 0: everything in one class. It works, and that is the only goal for now.
public class ElevatorSystem {
    private final int totalFloors;
    private int currentFloor = 0;
    private final List<Integer> requests = new ArrayList<>();

    public ElevatorSystem(int totalFloors) {
        this.totalFloors = totalFloors;
    }

    public void requestFloor(int floor) {
        if (floor < 0 || floor >= totalFloors) {
            System.out.println("Invalid floor " + floor);
            return;
        }
        requests.add(floor);
    }

    // we can make a seperate class for floor
    // we can make seperate class
    public void run() {
        int count = 0;
        while (!requests.isEmpty()) {
            int target = requests.remove(0); // first come, first served
            while (currentFloor != target) {
                if (target > currentFloor) {
                    currentFloor++;
                    count++;
                    System.out.println("going up ");
                } else {
                    currentFloor--;
                    count++;
                    System.out.println("going down ");
                }
                System.out.println("[Display] Floor " + currentFloor);
            }
            System.out.println("  Door opens at floor " + currentFloor);
            System.out.println("  no of floor moved till reached to target " + count);
            System.out.println("  Door closes");
        }
    }
}
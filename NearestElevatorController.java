import java.util.ArrayList;
import java.util.List;

// Nearest Floor First Always selects the closest requested floor
public class NearestElevatorController {
    private final int totalFloors;
    private final Elevator elevator;
    private final Display display;
    private final List<Integer> pending = new ArrayList<>();

    public NearestElevatorController(int totalFloors) {
        this(totalFloors, new Elevator(), new Display());
    }

    public NearestElevatorController(int totalFloors, Elevator elevator, Display display) {
        this.totalFloors = totalFloors;
        this.elevator = elevator;
        this.display = display;
    }

    public void requestFloor(int floor) {
        if (floor < 0 || floor >= totalFloors) {
            System.out.println("Invalid floor " + floor);
            return;
        }
        if (!pending.contains(floor)) {
            pending.add(floor);
        }
    }

    // Finds the floor in pending closest to the current floor
    private int getNearestTarget() {
        int currentFloor = elevator.getCurrentFloor();
        int closestFloor = pending.get(0);
        int minDistance = Math.abs(currentFloor - closestFloor);

        for (int floor : pending) {
            int distance = Math.abs(currentFloor - floor);
            if (distance < minDistance) {
                minDistance = distance;
                closestFloor = floor;
            }
        }
        return closestFloor;
    }

    // the elevator moves at most one floor towards the nearest target
    public void step() {
        if (pending.isEmpty()) {
            elevator.becomeIdle();
            return;
        }

        int target = getNearestTarget(); // Nearest Floor First

        if (elevator.getCurrentFloor() != target) {
            elevator.moveOneFloorTowards(target);
            display.show(elevator.getCurrentFloor(), elevator.getDirection());
        }

        if (elevator.getCurrentFloor() == target) {
            elevator.stopAndOpenDoor();
            pending.remove(Integer.valueOf(target));
        }
    }

    public void run() {
        while (!pending.isEmpty()) {
            step();
        }
        elevator.becomeIdle();
    }

    public int getFloorsTravelled() {
        return elevator.getFloorsTravelled();
    }

    public Elevator getElevator() {
        return elevator;
    }

    public Display getDisplay() {
        return display;
    }

    public List<Integer> getPending() {
        return pending;
    }
}

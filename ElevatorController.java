import java.util.ArrayList;
import java.util.List;

// Only decides what the elevator should do next.
public class ElevatorController {
    private final int totalFloors;
    private final Elevator elevator;
    private final Display display;
    private final List<Integer> pending = new ArrayList<>();

    public ElevatorController(int totalFloors) {
        this(totalFloors, new Elevator(), new Display());
    }

    public ElevatorController(int totalFloors, Elevator elevator, Display display) {
        this.totalFloors = totalFloors;
        this.elevator = elevator;
        this.display = display;
    }

    public void requestFloor(int floor) {
        if (floor < 0 || floor >= totalFloors) {
            System.out.println("Invalid floor " + floor);
            return;
        }
        // Ignore request if already at that floor
        if (floor == elevator.getCurrentFloor()) {
            System.out.println("already at floor " + floor);
            return;
        }
        if (!pending.contains(floor)) {
            pending.add(floor);
        }
    }

    // One tick of time: the elevator moves at most one floor.
    public void step() {
        if (pending.isEmpty()) {
            elevator.becomeIdle();
            return;
        }
        int target = pending.get(0); // first come, first served
        if (elevator.getCurrentFloor() != target) {
            elevator.moveOneFloorTowards(target);
            display.show(elevator.getCurrentFloor(), elevator.getDirection());
        }
        if (elevator.getCurrentFloor() == target) {
            elevator.stopAndOpenDoor();
            pending.remove(Integer.valueOf(target)); // remove the value, not the index
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

import java.util.ArrayList;
import java.util.List;

// Elevator moves in one direction, serving floors along the way, then reverses direction
public class ScanElevatorController {
    private final int totalFloors;
    private final Elevator elevator;
    private final Display display;
    private final List<Integer> pending = new ArrayList<>();
    private Direction currentDirection = Direction.UP;

    public ScanElevatorController(int totalFloors) {
        this(totalFloors, new Elevator(), new Display());
    }

    public ScanElevatorController(int totalFloors, int initialFloor) {
        this(totalFloors, new Elevator(initialFloor), new Display());
    }

    public ScanElevatorController(int totalFloors, Elevator elevator, Display display) {
        this.totalFloors = totalFloors;
        this.elevator = elevator;
        this.display = display;
        if (elevator.getDirection() != Direction.IDLE) {
            this.currentDirection = elevator.getDirection();
        }
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

    // selects the next target floor in the current direction.
    // if no more requests exist in that direction, reverses direction
    private int getNextTarget() {
        int currentFloor = elevator.getCurrentFloor();
        if (pending.contains(currentFloor)) {
            return currentFloor;
        }
        if (currentDirection == Direction.UP) {
            int minAbove = Integer.MAX_VALUE;
            for (int floor : pending) {
                if (floor > currentFloor && floor < minAbove) {
                    minAbove = floor;
                }
            }
            if (minAbove != Integer.MAX_VALUE) {
                return minAbove;
            }

            // No more requests above, reverse direction to DOWN
            currentDirection = Direction.DOWN;
            int maxBelow = Integer.MIN_VALUE;
            for (int floor : pending) {
                if (floor < currentFloor && floor > maxBelow) {
                    maxBelow = floor;
                }
            }
            if (maxBelow != Integer.MIN_VALUE) {
                return maxBelow;
            }
        } else {
            // Moving DOWN: find the closest requested floor below current floor
            int maxBelow = Integer.MIN_VALUE;
            for (int floor : pending) {
                if (floor < currentFloor && floor > maxBelow) {
                    maxBelow = floor;
                }
            }
            if (maxBelow != Integer.MIN_VALUE) {
                return maxBelow;
            }

            // no more requests below, reverse direction to UP
            currentDirection = Direction.UP;
            int minAbove = Integer.MAX_VALUE;
            for (int floor : pending) {
                if (floor > currentFloor && floor < minAbove) {
                    minAbove = floor;
                }
            }
            if (minAbove != Integer.MAX_VALUE) {
                return minAbove;
            }
        }

        return pending.get(0);
    }

    // the elevator moves at most one floor towards the target
    public void step() {
        if (pending.isEmpty()) {
            elevator.becomeIdle();
            return;
        }

        int target = getNextTarget();

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

    public Direction getCurrentDirection() {
        return currentDirection;
    }
}

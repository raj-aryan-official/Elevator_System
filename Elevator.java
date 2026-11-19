public class Elevator {
    private int currentFloor = 0;
    private Direction direction = Direction.IDLE;
    private int floorsTravelled = 0;
    private final Door door;

    public Elevator() {
        this.door = new Door();
    }

    public Elevator(int currentFloor) {
        this.currentFloor = currentFloor;
        this.door = new Door();
    }

    public Elevator(Door door) {
        this.door = door;
    }

    public Elevator(int currentFloor, Door door) {
        this.currentFloor = currentFloor;
        this.door = door;
    }

    public void moveOneFloorTowards(int target) {
        if (target > currentFloor) {
            currentFloor++;
            direction = Direction.UP;
            floorsTravelled++;
            sleep(1);
        } else if (target < currentFloor) {
            currentFloor--;
            direction = Direction.DOWN;
            floorsTravelled++;
            sleep(1);
        }
    }

    public void stopAndOpenDoor() {
        door.open(currentFloor);
        sleep(door.getWaitTime());
        door.close();
    }

    private void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void becomeIdle() {
        direction = Direction.IDLE;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getFloorsTravelled() {
        return floorsTravelled;
    }

    public Door getDoor() {
        return door;
    }
}

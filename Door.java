// Only knows how to open and close.
public class Door {
    private boolean open = false;
    private int waitTime = 2; // in secs

    public Door() {
    }

    public Door(int waitTime) {
        this.waitTime = waitTime;
    }

    public void open(int floor) {
        open = true;
        System.out.println("  Door opens at floor " + floor);
    }

    public void close() {
        open = false;
        System.out.println("  Door closes");
    }

    public boolean isOpen() {
        return open;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }
}

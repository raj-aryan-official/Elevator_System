public class Main {
    public static void main(String[] args) {
        System.out.println("first come first serve");
        ElevatorController controller = new ElevatorController(10);
        controller.requestFloor(3);
        controller.requestFloor(1);
        controller.requestFloor(42);
        controller.requestFloor(8);
        controller.requestFloor(1);
        controller.requestFloor(5);
        controller.run();
        System.out.println("Floors travelled: " + controller.getFloorsTravelled());
        System.out.println("Sortest first");
        NearestElevatorController controller2 = new NearestElevatorController(10);
        controller2.requestFloor(3);
        controller2.requestFloor(1);
        controller2.requestFloor(6);
        controller2.requestFloor(0);
        controller2.run();
        System.out.println("Floors travelled: " + controller2.getFloorsTravelled());
        System.out.println("one direction");
        ScanElevatorController controller3 = new ScanElevatorController(10);
        controller3.requestFloor(3);
        controller3.requestFloor(1);
        controller3.requestFloor(6);
        controller3.requestFloor(5);
        controller3.run();
        System.out.println("Floors travelled: " + controller3.getFloorsTravelled());
    }
}

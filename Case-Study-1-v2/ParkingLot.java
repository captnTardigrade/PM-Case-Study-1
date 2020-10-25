import java.util.Scanner;

public class ParkingLot {

    public static void main(String[] args) {
        AssignmentSystem assignmentSystem = new AssignmentSystem();
        ExitSystem exitSystem = new ExitSystem();
        Admin admin = new Admin();
        admin.initializeFloors(assignmentSystem);
        Scanner scanner = new Scanner(System.in);
        assignmentSystem.showPrices();
    }
}

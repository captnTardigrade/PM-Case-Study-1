import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.*;

public class AssignmentSystem {
    private DisplayBoard entranceBoard = new DisplayBoard();
    private double[] carPrices = { 20, 10, 5 };
    private double[] bikePrices = { 10, 5, 5 };
    private double[] evPrices = { 50, 25, 15 };
    // private double[] truckPrices = { 0, 0, 0 }; assuming the trucks don't have to
    // pay for parking because they're used to deliver supplies
    private ParkingFloor[] parkingFloors;
    private int numFloors;

    public void showPrices() {
        entranceBoard.displayMessage("\nCar parking prices");
        entranceBoard.displayMessage("\n₹ " + carPrices[0] + " for the first hour\n" + "₹ " + carPrices[1]
                + " for the second hour\n" + "₹ " + carPrices[2] + " for the third hour\n");
        entranceBoard.displayMessage("Bike Parking prices");
        entranceBoard.displayMessage("\n₹ " + bikePrices[0] + " for the first hour\n" + "₹ " + bikePrices[1]
                + " for the second hour\n" + "₹ " + bikePrices[2] + " for the third hour\n");
        entranceBoard.displayMessage("EV parking prices");
        entranceBoard.displayMessage("\n₹ " + evPrices[0] + " for the first hour\n" + "₹ " + evPrices[1]
                + " for the second hour\n" + "₹ " + evPrices[2] + " for the third hour\n");
    }

    public void recordDetails(String name, String vehicleNumber, long entry, String phone) {
        final String path = "./CustomerData.csv";
        try {
            FileWriter fw = new FileWriter(path, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println(name + "," + vehicleNumber + "," + entry + "," + phone);
            pw.flush();
            pw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initalizeFloors() {
        System.out.println("Enter the number of floors: ");
        Scanner scanner = new Scanner(System.in);
        int floors = scanner.nextInt();
        numFloors = floors;

        parkingFloors = new ParkingFloor[floors];
        for (int i = 0; i < floors; i++) {
            parkingFloors[i] = new ParkingFloor();
            System.out.println("Enter the number of bike spots for floor number " + i + 1);
            int numBikeSpots = scanner.nextInt();
            parkingFloors[i].initializeBikeSpots(numBikeSpots);
            System.out.println("Enter the number of car spots for floor number " + i + 1);
            int numCarSpots = scanner.nextInt();
            parkingFloors[i].initializeCarSpots(numCarSpots);
            System.out.println("Enter the number of spots for the handicapped for floor number " + i + 1);
            int numHandicappedSpots = scanner.nextInt();
            parkingFloors[i].initializeHandicappedSpots(numHandicappedSpots);
            System.out.println("Enter the number of truck spots for the floor number " + i + 1);
            int numTruckSpots = scanner.nextInt();
            parkingFloors[i].initializeTruckSpots(numTruckSpots);
            System.out.println("Enter the number of EV spots");
            int numEVSpots = scanner.nextInt();
            parkingFloors[i].initializeEVSpots(numEVSpots);
        }
    }

    public void assignSpot(String vehicleType) {
        Pattern vehiclePattern = Pattern.compile("^[A-Z]{2}[ -][0-9]{1,2}(?: [A-Z])?(?: [A-Z]*)? [0-9]{4}$");
        Pattern phonePattern = Pattern.compile("\\d\\d\\d([,\\s])?\\d\\d\\d\\d");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.next();
        System.out.print("Enter the vehicle number: ");
        String vehicleNumber = scanner.next();
        while (!vehiclePattern.matcher(vehicleNumber).matches()) {
            System.out.println("Please enter a valid vehicle number: ");
            vehicleNumber = scanner.next();
        }
        long entry = System.currentTimeMillis();
        String phone = scanner.next();
        while (!phonePattern.matcher(phone).matches()) {
            System.out.println("Please enter a valid mobile number: ");
            phone = scanner.next();
        }
        scanner.close();

        for (int floor = 0; floor < numFloors; floor++) {
            for (int j = 0; j < parkingFloors[floor].totalNumSpots(); j++) {
                if (vehicleType.toLowerCase() == "bike")
                    for (int k = 0; k < parkingFloors[floor].numBikeSpots; k++) {
                        if (!parkingFloors[floor].bikeSpots[k].isOccupied) {

                            recordDetails(name, vehicleNumber, entry, phone);
                            parkingFloors[floor].bikeSpots[k].assignSpot(entry);
                            System.out.println(
                                    "You have been assigned " + "Floor Number " + floor + 1 + " Spot number " + k + 1);
                            System.out.println("Your parking ID is: " + entry);
                            return;
                        } else {
                            System.out.println("Sorry, the lot is full!");
                        }
                    }
                if (vehicleType.toLowerCase() == "car")
                    for (int k = 0; k < parkingFloors[floor].numCarSpots; k++) {
                        if (!parkingFloors[floor].carSpots[k].isOccupied) {
                            recordDetails(name, vehicleNumber, entry, phone);
                            parkingFloors[floor].carSpots[k].assignSpot(entry);
                            System.out.println(
                                    "You have been assigned " + "Floor Number " + floor + 1 + " Spot number " + k + 1);
                            System.out.println("Your parking ID is: " + entry);
                            return;
                        } else {
                            System.out.println("Sorry, the lot is full!");
                        }
                    }
                if (vehicleType.toLowerCase() == "truck")
                    for (int k = 0; k < parkingFloors[floor].numTruckSpots; k++) {
                        if (!parkingFloors[floor].truckSpots[k].isOccupied) {
                            recordDetails(name, vehicleNumber, entry, phone);
                            parkingFloors[floor].truckSpots[k].assignSpot(entry);
                            System.out.println(
                                    "You have been assigned " + "Floor Number " + floor + 1 + " Spot number " + k + 1);
                            System.out.println("Your parking ID is: " + entry);
                            return;
                        } else {
                            System.out.println("Sorry, the lot is full!");
                        }
                    }
                if (vehicleType.toLowerCase() == "handicapped")
                    for (int k = 0; k < parkingFloors[floor].numHandicappedSpots; k++) {
                        if (!parkingFloors[floor].handicappedSpots[k].isOccupied) {
                            recordDetails(name, vehicleNumber, entry, phone);
                            parkingFloors[floor].handicappedSpots[k].assignSpot(entry);
                            System.out.println(
                                    "You have been assigned " + "Floor Number " + floor + 1 + " Spot number " + k + 1);
                            System.out.println("Your parking ID is: " + entry);
                            return;
                        } else {
                            System.out.println("Sorry, the lot is full!");
                        }
                    }
            }
        }
    }

    public static void main(String[] args) {
        AssignmentSystem assignmentSystem = new AssignmentSystem();
        // assignmentSystem.showPrices();
        // assignmentSystem.entranceBoard.displayMessage("Do you want to park?");
        // Scanner scanner = new Scanner(System.in);
        // int option = scanner.nextInt();
        // if (option == 1) { // tapping the first option

        // }
        // if (option == 2) { // otherwise
        // assignmentSystem.entranceBoard.displayMessage("Have a great day!");
        // assignmentSystem.entranceBoard.idleMessage();
        // }

        // dummy lines while development
        assignmentSystem.initalizeFloors();
        assignmentSystem.assignSpot("bike");
        System.out.println(assignmentSystem.parkingFloors[0].handicappedSpots[0].isOccupied);
    }
}

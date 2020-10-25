import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.*;

public class AssignmentSystem {
    private String masterKey = "topSecret";
    private DisplayBoard entranceBoard = new DisplayBoard();
    private double[] carPrices = { 20, 10, 5 };
    private double[] bikePrices = { 10, 5, 5 };
    private double[] evPrices = { 50, 25, 15 };
    private double[] truckPrices = { 0, 0, 0 }; // assuming the trucks don't have to pay for parking because they're
                                                // used to deliver supplies
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

    public void dummyBalanceGenerator(long id) {
        final String path = "./Bank.csv";
        try {
            FileWriter fw = new FileWriter(path, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println(id + "," + Math.random() * 1000);
            pw.flush();
            pw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void groundFloor() {
        ParkingFloor parkingFloor = new ParkingFloor();
        for (int i = 0; i < 10; i++) {
            parkingFloor.truckSpots[i] = new ParkingSpot(4, truckPrices);
        }
        for (int i = 0; i < 5; i++) {
            parkingFloor.handicappedSpots[i] = new ParkingSpot(2, carPrices);
        }
        for (int i = 0; i < 12; i++) {
            parkingFloor.carSpots[i] = new ParkingSpot(2, carPrices);
        }
        for (int i = 0; i < 26; i++) {
            parkingFloor.bikeSpots[i] = new ParkingSpot(1, bikePrices);
        }
    }

    public void defaultFloor(String floorKey) {
        ParkingFloor parkingFloor = new ParkingFloor();
        for (int i = 0; i < 100; i++) {
            parkingFloor.bikeSpots[i] = new ParkingSpot(1, bikePrices);

        }
        for (int i = 0; i < 50; i++) {
            parkingFloor.carSpots[i] = new ParkingSpot(2, carPrices);
        }
    }

    public void initalizeFloors(String floorKey) {
        if (this.masterKey == floorKey) {
            System.out.println("Enter the number of floors: ");
            Scanner scanner = new Scanner(System.in);
            int floors = scanner.nextInt();
            numFloors = floors;
            parkingFloors = new ParkingFloor[floors];
            for (int i = 0; i < floors; i++) {
                parkingFloors[i] = new ParkingFloor();
                System.out.print("Enter the number of bike spots for FLOOR NUMBER " + i + 1 + " ");
                int numBikeSpots = scanner.nextInt();
                parkingFloors[i].initializeBikeSpots(numBikeSpots);
                System.out.print("Enter the number of car spots for FLOOR NUMBER " + i + 1 + " ");
                int numCarSpots = scanner.nextInt();
                parkingFloors[i].initializeCarSpots(numCarSpots);
                System.out.print("Enter the number of spots for the handicapped for FLOOR NUMBER " + i + 1 + " ");
                int numHandicappedSpots = scanner.nextInt();
                parkingFloors[i].initializeHandicappedSpots(numHandicappedSpots);
                System.out.print("Enter the number of truck spots for the FLOOR NUMBER " + i + 1 + " ");
                int numTruckSpots = scanner.nextInt();
                parkingFloors[i].initializeTruckSpots(numTruckSpots);
                System.out.print("Enter the number of EV spots for the FLOOR NUMBER " + i + 1 + " ");
                int numEVSpots = scanner.nextInt();
                parkingFloors[i].initializeEVSpots(numEVSpots);
            }
        }
    }

    public ParkingSpot getSpot(int floor, int spot, String vehicleType) {
        if (vehicleType.equals("car")) {
            return parkingFloors[floor - 1].carSpots[spot - 1];
        } else if (vehicleType.equals("bike")) {
            return parkingFloors[floor - 1].bikeSpots[spot - 1];
        } else if (vehicleType.equals("truck")) {
            return parkingFloors[floor - 1].truckSpots[spot - 1];
        } else if (vehicleType.equals("handicapped")) {
            return parkingFloors[floor - 1].handicappedSpots[spot - 1];
        }
        return null;
    }

    public void assignSpot(String vehicleType) {
        Pattern vehiclePattern = Pattern.compile("[A-Z]{2}\s[0-9]{2}\s[A-Z]{2}\s[0-9]{4}");
        Pattern phonePattern = Pattern.compile("[1-9]{1}[0-9]{9}");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the vehicle number: ");
        String vehicleNumber = scanner.nextLine();
        while (!vehiclePattern.matcher(vehicleNumber).matches()) {
            System.out.print("Please enter a valid vehicle number: ");
            vehicleNumber = scanner.nextLine();
        }
        long entry = System.currentTimeMillis();
        System.out.print("Enter your phone number: ");
        String phone = scanner.nextLine();
        while (!phonePattern.matcher(phone).matches()) {
            System.out.print("Please enter a valid mobile number: ");
            phone = scanner.nextLine();
        }
        for (int floor = 0; floor < numFloors; floor++) {
            for (int j = 0; j < parkingFloors[floor].totalNumSpots(); j++) {
                if (vehicleType.equals("bike")) {
                    for (int k = 0; k < parkingFloors[floor].numBikeSpots; k++) {
                        if (!parkingFloors[floor].bikeSpots[k].isOccupied) {
                            recordDetails(name, vehicleNumber, entry, phone);
                            dummyBalanceGenerator(entry);
                            parkingFloors[floor].bikeSpots[k].assignSpot(entry);
                            System.out.println(
                                    "You have been assigned " + "FLOOR NUMBER " + floor + 1 + " SPOT NUMBER " + k + 1);
                            System.out.println("Your parking ID is: " + entry);
                            return;
                        }
                        System.out.println("Sorry the lot is full!");
                        return;
                    }
                } else if (vehicleType.equals("car")) {
                    for (int k = 0; k < parkingFloors[floor].numCarSpots; k++) {
                        if (!parkingFloors[floor].carSpots[k].isOccupied) {
                            recordDetails(name, vehicleNumber, entry, phone);
                            dummyBalanceGenerator(entry);
                            parkingFloors[floor].carSpots[k].assignSpot(entry);
                            System.out.println(
                                    "You have been assigned " + "FLOOR NUMBER " + floor + 1 + " SPOT NUMBER " + k + 1);
                            System.out.println("Your parking ID is: " + entry);
                            return;
                        }
                        System.out.println("Sorry the lot is full!");
                        return;
                    }
                } else if (vehicleType.equals("truck")) {
                    for (int k = 0; k < parkingFloors[floor].numTruckSpots; k++) {
                        if (!parkingFloors[floor].truckSpots[k].isOccupied) {
                            recordDetails(name, vehicleNumber, entry, phone);
                            dummyBalanceGenerator(entry);
                            parkingFloors[floor].truckSpots[k].assignSpot(entry);
                            System.out.println(
                                    "You have been assigned " + "FLOOR NUMBER " + floor + 1 + " SPOT NUMBER " + k + 1);
                            System.out.println("Your parking ID is: " + entry);
                            return;
                        }
                        System.out.println("Sorry the lot is full!");
                        return;
                    }
                } else if (vehicleType.equals("handicapped")) {
                    for (int k = 0; k < parkingFloors[floor].numHandicappedSpots; k++) {
                        if (!parkingFloors[floor].handicappedSpots[k].isOccupied) {
                            recordDetails(name, vehicleNumber, entry, phone);
                            dummyBalanceGenerator(entry);
                            parkingFloors[floor].handicappedSpots[k].assignSpot(entry);
                            System.out.println(
                                    "You have been assigned " + "FLOOR NUMBER " + floor + 1 + " SPOT NUMBER " + k + 1);
                            System.out.println("Your parking ID is: " + entry);
                            return;
                        }
                        else if (!parkingFloors[floor].carSpots[k].isOccupied){
                            recordDetails(name, vehicleNumber, entry, phone);
                            dummyBalanceGenerator(entry);
                            parkingFloors[floor].handicappedSpots[k].assignSpot(entry);
                            System.out.println(
                                    "You have been assigned " + "FLOOR NUMBER " + floor + 1 + " SPOT NUMBER " + k + 1);
                            System.out.println("Your parking ID is: " + entry);
                            return;
                        }
                    }
                } else {
                    System.out.println("Sorry, we don't allow parking for vehicle type of yours!");
                    return;
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
        assignmentSystem.initalizeFloors("topSecret");
        assignmentSystem.assignSpot("car");
        System.out.println(assignmentSystem.parkingFloors[0].handicappedSpots[0].isOccupied);
    }
}

import java.util.Scanner;

public class ParkingFloor {
    private double[] carPrices = { 20, 10, 5 };
    private double[] bikePrices = { 10, 5, 5 };
    private double[] evPrices = { 50, 25, 15 };
    private double[] truckPrices = { 0, 0, 0 };
    private boolean isBlocked = false;
    int numSpots;
    ParkingSpot[] carSpots;
    ParkingSpot[] bikeSpots;
    ParkingSpot[] evSpots;
    ParkingSpot[] truckSpots;
    ParkingSpot[] handicappedSpots;
    int numCarSpots;
    int numBikeSpots;
    int numEVSpots;
    int numTruckSpots;
    int numHandicappedSpots;

    public void blockFloor(Admin admin) {
        Scanner scanner = new Scanner(System.in);
        String key = scanner.nextLine();
        if (key == admin.getKey()) {
            this.isBlocked = true;
        }
        scanner.close();
    }

    void initializeCarSpots(int capacity) {
        this.numCarSpots = capacity;
        carSpots = new ParkingSpot[capacity];
        for (int i = 0; i < capacity; i++) {
            carSpots[i] = new ParkingSpot(2, carPrices);
        }
    }

    void initializeBikeSpots(int capacity) {
        this.numBikeSpots = capacity;
        bikeSpots = new ParkingSpot[capacity];
        for (int i = 0; i < capacity; i++) {
            bikeSpots[i] = new ParkingSpot(1, bikePrices);
        }
    }

    void initializeTruckSpots(int capacity) {
        this.numTruckSpots = capacity;
        truckSpots = new ParkingSpot[capacity];
        for (int i = 0; i < capacity; i++) {
            truckSpots[i] = new ParkingSpot(4, truckPrices);
        }
    }

    void initializeEVSpots(int capacity) {
        this.numEVSpots = capacity;
        evSpots = new ParkingSpot[capacity];
        for (int i = 0; i < capacity; i++) {
            evSpots[i] = new ParkingSpot(2, evPrices);
        }
    }

    void initializeHandicappedSpots(int capacity) {
        this.numHandicappedSpots = capacity;
        handicappedSpots = new ParkingSpot[capacity];
        for (int i = 0; i < capacity; i++) {
            handicappedSpots[i] = new ParkingSpot(2, carPrices);
        }
    }

    int totalNumSpots() {
        return this.numBikeSpots + this.numCarSpots + this.numEVSpots + this.numHandicappedSpots + this.numTruckSpots;
    }
}
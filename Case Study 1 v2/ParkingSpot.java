import java.util.Scanner;

public class ParkingSpot {
    boolean isOccupied = false;
    long entryTime;
    int space;
    long id; // customer id
    double[] priceFactor;

    ParkingSpot(int space, double[] priceFactor) {
        this.space = space;
        this.priceFactor = priceFactor;
    }

    public void assignSpot(long id) {
        this.isOccupied = true;
        this.id = id;
    }

    public void blockSpot(Admin admin){
        System.out.println("Enter the security key: ");
        Scanner scanner = new Scanner(System.in);
        String key = scanner.nextLine();
        if(key == admin.getKey()){
            this.isOccupied = true;
        }
        scanner.close();
    }
}

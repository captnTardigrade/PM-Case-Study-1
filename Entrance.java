public class Entrance
{
    Scanner sc=new Scanner(System.in);
    System.out.println("Enter vehicle type")
    String vehicleType=sc.next();
    long entryTime = System.currentTimeMillis();
    //assign spot based on the first available spot
    //print pricing
    Displayboard.displayboard();
    String regno=assaignregNo(vehicleType);
    public static String assaignregNo(String s)
    {
        static char i=0;
        String str=s.concat('i');
        i++;
    }
    parking spot.assaignspot(regno);
}

import java.util.Scanner;

public class Admin {
    private String key;
    public void setKey(String key){
        System.out.println("Enter the previous key: ");
        Scanner scanner = new Scanner(System.in);
        String prevKey = scanner.nextLine();
        if(prevKey == this.key){
            this.key = key;
            System.out.println("Updated the key successfully.");
        }
        else{
            System.out.println("Wrong security key. Please try again.");
        }
    }
    public String getKey(){
        return this.key;
    }
}

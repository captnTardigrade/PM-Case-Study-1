import java.util.*;
import java.util.regex.*; 

class ExitSystem{
  int bill;
  
  void DeleteDetailsFromDatabase(){}
  
  void changeStatusOfParkingSpot(){}
  
  void showbill(int time){
    int factor; 
    //assign the value to variable bill
  }
  
   
  
  //card validation:
public static boolean IscardValid(String str) 
{ 
  String regex = "^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})$"; 
  Pattern p = Pattern.compile(regex); 
 
  if (str == null) { 
    return false; 
    }  
  Matcher m = p.matcher(str); 
    return m.matches(); 
}
	
  
  
  //pin validation:
public static boolean IsPinValid(String str1) 
    { 
        String regex = "^[1-9][0-9]{5}$";
        Pattern p1 = Pattern.compile(regex); 
 
        if (str == null) { 
            return false; 
        }  
  
        Matcher m1 = p1.matcher(str1); 
        return m1.matches(); 
    } 
	
  
  //database ...?
  
	
  // payment done by using a card
  void cardPayment(){
        Scanner sc= new Scanner(System.in);
    System.out.println("please enter your card number:");
    while(1){   
	String cardNumber= sc.nextLine();
        
       if(cardIsValid(cardNumber)){
         break;
        }
      else{
        System.out.println("Please enter valid card number");
      } 
    }
	  
    while(1){
    
    System.out.println("please enter pin:");
    while(1){   
	String PinNumber= sc.nextLine();
        
       if(IsPinValid(PinNumber)){
         break;
        }
      else{
        System.out.println("Please enter valid pin:");
      } 
    }
        
    System.out.println("Redirecting to your bank gateway...");
      
     if(dummyRandomBank()){
       System.out.println("Transaction successful! Have a great day!");
       break;
     }
    while(!dummyRandomBank()){
       System.out.println("Transaction unsuccessful. Please try again.");
    }
  }
    //Adding randomness to ver
    boolean dummyRandomBank(){
        double randomNum = ThreadLocalRandom.current().nextDouble();
        if(randomNum>=0.98){
            return false;
        }
        return true;
    }
  
  }
}

class ParkingAttendant extends ExitSystem{
  
  void cashPayment(double amount){
        if(bill == amount){
            System.out.println("Have a great day!");
        }
        else if(bill>amount){
            System.out.println("An amount of " + (bill - amount) + "must be paid");
        }
        else{
            System.out.println("Here's your change : " + (amount - bill));
            System.out.println("Have a great day!");
        }
    }
  
    //other tasks that the attendant has to perform?
  
}

import java.util.*;

class ExitSystem{
  int bill;
  
  void DeleteDetailsFromDatabase(){}
  
  void changeStatusOfParkingSpot(){}
  
  void showbill(int time){
    int factor; 
    //assign the value to variable bill
  }
  
   
  
  //card validation:
  public static int size(long ccno)
	{
		long temp=ccno;
		int sum=0;
		while(temp!=0)
		{
			temp/=10;
			sum++;
		}
	    return sum;
	}
	public static long[] arrayconverter(long ccno)
	{
		int n=size(ccno), k=n-1;;
		long a[]=new long[n];
		for(int i=0;i<n;i++) 
		{
			a[k--]=ccno%10;
			ccno/=10;
	    }
		return a;
	}
	public static boolean match(long ccno)
	{
		long a[]=arrayconverter(ccno);
		return(a[0]*10+a[1]==37||a[0]==4||a[0]==5||a[0]==6);
	}
	public static int oddsum(long ccno)
	{
		int sum=0;
		long a[]=arrayconverter(ccno);
		for(int i=size(ccno)-1;i>=0;i-=2)
		{
			sum+=a[i];
		}
		return sum;
	}
	public static long doublenumber(long a)
	{
		if(a<=9)
			return a;
		else
			return a/10+a%10;
	}
	public static int evendoublesum(long ccno)
	{
		int sum=0;
		long a[]=arrayconverter(ccno);
		for(int i=size(ccno)-2;i>=0;i-=2)
		{
			
			sum+=doublenumber(2*a[i]);
		}
		return sum;
	}
	public static boolean IscardValid(long ccno)
	{
		return (size(ccno) >= 13 && size(ccno) <= 16 )&& match(ccno)
				&& ((evendoublesum(ccno) + oddsum(ccno)) % 10 == 0);
	}
  
  
  
  //pin validation:
  public static int sizeof(int pin)
	{
		int temp=pin;
		int sum=0;
		while(temp!=0)
		{
			temp/=10;
			sum++;
		}
	    return sum;
	}
	public static int[] arrayconverter1(int pin)
	{
		int n=size(pin), k=n-1;;
		int arr[]=new int[n];
		for(int i=0;i<n;i++) 
		{
			arr[k--]=pin%10;
			pin/=10;
	    }
		return arr;
	}
	public static boolean startdigit(int pin)
	{
		int a[]=arrayconverter1(pin);
		return(a[0]!=0);
	}
	
	public static boolean IsPinValid(int pin)
	{
		return(sizeof(pin)==6&&startdigit(pin));
	}
  
  
  
  
  //database ...?
  
  
  

  // payment done by using a card
  void cardPayment(){
        Scanner sc= new Scanner(System.in);
    System.out.println("please enter your card number:");
    while(1){   
       int cardNumber=  sc.nextInt();
        
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
       int PinNumber=  sc.nextInt();
        
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

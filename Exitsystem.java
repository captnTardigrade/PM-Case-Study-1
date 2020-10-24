
import java.util.Scanner;
class validcc
{
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
	public static boolean isvalidccno(long ccno)
	{
		return (size(ccno) >= 13 && size(ccno) <= 16 )&& match(ccno)
				&& ((evendoublesum(ccno) + oddsum(ccno)) % 10 == 0);
	}
}
/*The valid pin code of India must satisfy the following conditions.
1.It can be only six digits.
2.It should not start with zero.
3.First digit of the pin code must be from 1 to 9.
4.Next five digits of the pin code may range from 0 to 9.*/
class validpin extends validcc
{
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
	
	public static boolean isvalidpin(int pin)
	{
		return(sizeof(pin)==6&&startdigit(pin));
	}
}
class paymentmodule extends validpin
{
	public static int pricing(String vehicletype,int time)
	{
		int x=0;
		if(vehicletype.equalsIgnoreCase("car") )
		{
			if(time<=1)
	    		x=20;
	    	else if(time>1&&time<=3)
	            x=20+10*(time-1);
	    	else
	    		x=20+10*2+5*(time-3);
		}
		else if(vehicletype.equalsIgnoreCase("electric car") )
		{
			if(time<=1)
	    		x=30;
	    	else if(time>1&&time<=3)
	            x=30+20*(time-1);
	    	else
	    		x=30+20*2+10*(time-3);
		}
		else if(vehicletype.equalsIgnoreCase("truck") )
		{
			if(time<=1)
	    		x=25;
	    	else if(time>1&&time<=3)
	            x=25+20*(time-1);
	    	else
	    		x=25+20*2+10*(time-3);
		}
		else if(vehicletype.equalsIgnoreCase("van") )
		{
			if(time<=1)
	    		x=20;
	    	else if(time>1&&time<=3)
	            x=20+10*(time-1);
	    	else
	    		x=20+10*2+5*(time-3);
		}
		else if(vehicletype.equalsIgnoreCase("motor cycle") )
		{
			if(time<=1)
	    		x=15;
	    	else if(time>1&&time<=3)
	            x=10+7*(time-1);
	    	else
	    		x=15+10*2+7*(time-3);
		}
		else
			System.out.println("invalid input!!");
		return x;
	}
    public static void payment(String vehicletype,int time) 
    {
    	Scanner sc=new Scanner(System.in);
    	System.out.println("Choose one thing:");
    	System.out.println("1.cash");
    	System.out.println("2.card");
    	int n=sc.nextInt();
    	int attempt = 3;
    	int x=pricing(vehicletype,time);
        if(n==2)
    	{
    		System.out.println("Please enter ccno:");
    		long ccno=sc.nextLong();
    		int pin;
    		if(isvalidccno(ccno))
    		{
    			System.out.println("please enter pin:");
    			pin=sc.nextInt();
    			if(isvalidpin(pin))
    			{
    				System.out.println("Your payment is successful!!");
    				System.out.println("you are charged"+x+"rupees");
    			}
    			while(!isvalidpin(pin)&&attempt!=0)
    			{
    				System.out.println("Invalid PIN entered!. " + --attempt + " attempts remaining.");
    				if(attempt!=0)
    				{
    					System.out.println("please enter pin again:");
                        pin=sc.nextInt();
            			if(isvalidpin(pin))
            			{
            				System.out.println("Your payment is successful!!");
            				System.out.println("you are charged"+ x+"rupees");
            				System.out.println("Thanks for using group 10 parkinglot");
            			}
    				}
    				else
    				{
    	                System.out.println("your card has locked!");
    	                break;
                    }
    		    }
    		}
    		else
    			System.out.println("Invalid creditcard number!!try again");
    	 }
    	else
    	{
    		System.out.println("please pay"+ x+"rupees");	
    		System.out.println("Thanks for using group 10 parkinglot");
    	}
     }
}


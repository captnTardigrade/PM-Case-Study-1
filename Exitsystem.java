import java.util.Scanner;
public class paymentmodule
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
	public static boolean isvalid(long ccno)
	{
		return (size(ccno) >= 13 && size(ccno) <= 16 )&& match(ccno)
				&& ((evendoublesum(ccno) + oddsum(ccno)) % 10 == 0);
	}
    public static void payment(int time) 
    {
    	Scanner sc=new Scanner(System.in);
    	System.out.println("Choose one thing:");
    	System.out.println("1.cash");
    	System.out.println("2.card");
    	int n=sc.nextInt();
    	int x;
    	if(time<=1)
    		x=20;
    	else if(time>1&&time<=3)
            x=20+10*(time-1);
    	else
    		x=20+10*2+5*(time-3);
    	if(n==2)
    	{
    		System.out.println("Please enter ccno:");
    		long ccno=sc.nextLong();
    	    System.out.println((isvalid(ccno))?"Your payment is successful":
    			"entered credit card no.is invalid!please try again");
    	    System.out.println("you are charged"+ x+"rupees");
    	}
    	else
    	  System.out.println("please pay"+ x+"rupees");	
	}
}

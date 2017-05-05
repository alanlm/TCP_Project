
import java.io.IOException;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.util.Random;

public class TCPserver {
	
	public static BigInteger fibValue = BigInteger.ZERO; 
	public static int primeValue = 2; 
	public static int randValue = 0; 

	public static void main(String[] args) 
	{
		int port = 2500; 
        
        ServerSocket serverSocket = null; 
        
        try { 
			// creating socket on the following port 
	        serverSocket = new ServerSocket(port);
	        System.out.println("Server accpeting connection on port: " + port);
	        System.out.println("Waiting for client(s) to connect . . .");
	        
	        // start server thread 
	        while(true) {
	        	TCPserverThread clientThread = new TCPserverThread(serverSocket.accept()); 
		        clientThread.start();
	        }
        } catch(IOException e) {
        	e.printStackTrace();
        	System.out.println("Exception caught when trying to listen on port " 
        			+ port + " or listening for a connection");
        	System.out.println(e.getMessage());
        }

	} // end of main 
	
	//Dominick
	public static BigInteger fibonacci(BigInteger n)
	{
		BigInteger result = BigInteger.ZERO; 
		BigInteger n1 = BigInteger.ZERO;
		BigInteger n2 = BigInteger.ZERO;
		if(n.equals(BigInteger.ZERO))
		{
			result = BigInteger.ZERO;
		}

		if(n.equals(BigInteger.ONE))
		{
			result = BigInteger.ONE;
		}
		else
		{
			n1 = BigInteger.ONE;
			n2 = BigInteger.ZERO;
			for (BigInteger i = BigInteger.ONE; i.compareTo(n) < 0 ; i = i.add(BigInteger.ONE))

			{
				result = n1.add(n2);
				n2 = n1;
				n1 = result;
			}	
		}
		return result;
	}
	
	  public static BigInteger nextEvenFib(BigInteger n)
	  {
		BigInteger evenFib = BigInteger.ZERO;
		BigInteger temp = BigInteger.ONE;
		int i = 0;
		while(temp.mod(BigInteger.valueOf(2)).compareTo(BigInteger.ZERO)!=0)
		  {
			  temp = fibonacci(n.add(BigInteger.valueOf(i)));
			  i++;
		  }
		  evenFib = temp;
		  temp = temp.add(BigInteger.ONE);
		  i++;
		return evenFib;
	  }
		  
	//Steph
	public static int nextPrime(int prime)
	{
		boolean isPrime = false;

		int start = 2;

		while (!isPrime)
		{
			prime += 1;
			int m = (int) Math.ceil(Math.sqrt(prime));

			isPrime = true;
			for (int i = start; i <= m; i++)
			{
				if (prime % i == 0)
				{
					isPrime = false;
					break;
				}	
			}	
		}
		return prime;	
	}
		
	//Lori
	public static int nextLargerRand(int s) 
	{
		int big = 0;
		int small = s;
		Random gen = new Random();
		do {
			big = gen.nextInt() + small;
			if (big < 0) {
				big += Integer.MAX_VALUE;
			}
		} while (small > big);
		return big;
	}
	
} // end of TCPserver class 

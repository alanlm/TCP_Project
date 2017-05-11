
import java.io.IOException;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.util.Random;

public class TCPserver {
	
	public static BigInteger fibValue; 
	public static int primeValue; 
	public static BigInteger randValue; 

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
		  if(n == null)
			  n = BigInteger.ONE; 
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
		if(prime == 0)
			prime = 2; 
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
	public static BigInteger nextLargerRand(BigInteger s) 
	{
		if(s.compareTo(BigInteger.ZERO) == 0)
		{
			s = BigInteger.ONE;
		}
		BigInteger big = BigInteger.ZERO;
		BigInteger small = s;
		Random gen = new Random();
		do {
			big = BigInteger.valueOf(gen.nextInt()).add(small);
			if (big.compareTo(BigInteger.ZERO) < 0) 
			{
				big = big.add(BigInteger.valueOf(Integer.MAX_VALUE));
			}
		} while (small.compareTo(big) > 0);
		return big;
	}
	
} // end of TCPserver class 

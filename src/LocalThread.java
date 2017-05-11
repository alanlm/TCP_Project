import java.math.BigInteger;
import java.util.concurrent.Callable;


public class LocalThread implements Callable<String>{

	private String request = ""; 
	private BigInteger value;
	
	public LocalThread(String r, BigInteger v) {
		request = r; 
		value = v;
	}
	
	public String call() throws Exception {
		System.out.println("LocalThread: request = " + request);
		switch(request){
			case "nextOdd": System.out.println("LocalThread: nextOdd case");
				BigInteger nextOddValue = nextOdd(value); 
				System.out.println("LocalThread: nextOdd case return value = " + nextOddValue);
				TCPclient.curOdd = nextOddValue; 
				request = String.valueOf(nextOddValue); 
				break;
			case "nextEven": System.out.println("LocalThread: nextEven case");
				BigInteger nextEvenValue = nextEven(value); 
				System.out.println("LocalThread: nextEven case return value = " + nextEvenValue);
				TCPclient.curEven = nextEvenValue; 
				request = String.valueOf(nextEvenValue); 
				break;
		}
		System.out.println("LocalThread: return value = " + request);
		return request;
	}
	
	public BigInteger nextOdd (BigInteger n)
	{
		//String msg = "";
		BigInteger odd = BigInteger.ONE;
		//int odd = 1;
		BigInteger two = new BigInteger("2");
		if (n.mod(two).compareTo(BigInteger.ZERO) == 0)
		{
			while (n.mod(two).compareTo(BigInteger.ZERO) == 0)
			{
				n = n.add(BigInteger.ONE);
			}
			odd  = n;
		}
		else 
		{
			odd = n.add(two);
		}
		//msg = "The Next Even Number is: " + odd;
		return odd;
	}
    
    public BigInteger nextEven (BigInteger n)
	{
		//String msg = "";
		BigInteger even = BigInteger.ZERO;
		BigInteger two = new BigInteger("2");
		
		if (n.mod(two).compareTo(BigInteger.ZERO) != 0)//n %2 != 0)
		{
			while (n.mod(two).compareTo(BigInteger.ZERO) != 0)
			{
				n = n.add(BigInteger.ONE);
			}
		even  = n;
		}
		else 
		{
			even = n.add(two);
		}
		//msg = "The Next Even Number is: " + even;
		return even;
	}

}

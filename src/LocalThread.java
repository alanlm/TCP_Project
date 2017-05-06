import java.math.BigInteger;
import java.util.concurrent.Callable;


public class LocalThread implements Callable<String>{

	private String request = ""; 
	private TCPclient mClient;
	
	public LocalThread(String r, TCPclient client) {
		request = r; 
		mClient = client;
	}
	
	public String call() throws Exception {
		switch(request){
		case "nextOdd":
			BigInteger nextOddValue = nextOdd(mClient.getOdd());
			mClient.setOdd(nextOddValue);
			request = String.valueOf(nextOddValue); 
			break;
		case "nextEven":
			BigInteger nextEvenValue = nextEven(mClient.getEven());
			mClient.setEven(nextEvenValue);
			request = String.valueOf(nextEvenValue); 
			break;
		}
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

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;

public class RuntimeThr implements Callable <String>
{
	// Thread Safe Queues that store the client requests and the order of response 
		private BlockingQueue<String> requestQue = new LinkedBlockingQueue<String>(); 
		private BlockingQueue<String> returnQue = new LinkedBlockingQueue<String>(); 
		private BigInteger newVal = BigInteger.ZERO;
		private String newRequest = "";
		public static BigInteger curFib, curEven, CurOdd;
		public static int curPrime, curRand; 
		
		public RuntimeThr(String request, BigInteger curVal) throws InterruptedException
		{
			requestQue.put(request);
			newVal = curVal;
		}

		public String call() throws Exception 
		{
			String msg = "";
			NetworkThread t1;
			LocalThread t2;
			
			
			 new NetworkThread(requestQue.poll());
			
			switch(newRequest)
			{
				case "nextFib":
				{
					t1 = new NetworkThread("nextFib");
					newRequest = requestQue.take();
					msg = (t1.call());
					returnQue.put(msg);
				}
				break;
				case "nextPrime":
				{
					t1 = new NetworkThread("nextPrime");
					newRequest = requestQue.take();
					msg = (t1.call());
					returnQue.put(msg);
				}
				break;
				case "nextRand":
				{
					t1 = new NetworkThread("nextRand");
					newRequest = requestQue.take();
					msg = (t1.call());
					returnQue.put(msg);
				}
				break;
				case "nextEven":
				{
					t2 = new LocalThread("nextFib",null);
					newRequest = requestQue.take();
					msg = (t2.call());
					returnQue.put(msg);
				}
				break;
				case "nextOdd":
				{
					t2 = new LocalThread("nextFib",null);
					newRequest = requestQue.take();
					msg = (t2.call());
					returnQue.put(msg);
				}
				break;
			
			}
			
			
			return returnQue.poll();
		}
}

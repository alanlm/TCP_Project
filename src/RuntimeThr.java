import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RuntimeThr implements Callable <String>
{
	// Thread Safe Queues that store the client requests and the order of response 
		private BlockingQueue<String> requestQue = new LinkedBlockingQueue<String>(); 
		private BlockingQueue<String> returnQue = new LinkedBlockingQueue<String>(); 
		private BigInteger newVal = BigInteger.ZERO;
		private String newRequest = "";
		private Lock  lock = new ReentrantLock();
	// Declare the Global Values of the current values to be updated and passed from thread to thread
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
					lock.lock(); 
					try{
					// pass the global value and request type into the thread constructor
					t1 = new NetworkThread("nextFib"); //take in newVal
					newRequest = requestQue.take();
					msg = (t1.call());
					returnQue.put(msg);
					newVal = newVal.add(BigInteger.ZERO);// this needs to be changed to account for updated value
					}finally{
						lock.unlock();
					}
				}
				break;
				case "nextPrime":
				{
					lock.lock(); 
					try
					{
					// pass the global value and request type into the thread constructor
					t1 = new NetworkThread("nextPrime");//take in newVal
					newRequest = requestQue.take();
					msg = (t1.call());
					returnQue.put(msg);
					newVal = newVal.add(BigInteger.ZERO);// this needs to be changed to account for updated value
					} finally{
						lock.unlock();
					}
				}
				break;
				case "nextRand":
				{
					lock.lock(); 
					try{
					// pass the global value and request type into the thread constructor
					t1 = new NetworkThread("nextRand");//take in newVal
					newRequest = requestQue.take();
					msg = (t1.call());
					returnQue.put(msg);
					newVal = newVal.add(BigInteger.ZERO);// this needs to be changed to account for updated value
					}finally{
						lock.unlock();
					}
				}
				break;
				case "nextEven":
				{
					lock.lock(); 
					try{
					// pass the global value and request type into the thread constructor
					t2 = new LocalThread("nextEven",null);//take in newVal
					newRequest = requestQue.take();
					msg = (t2.call());
					returnQue.put(msg);
					newVal = newVal.add(BigInteger.ZERO);// this needs to be changed to account for updated value
				}finally{
					lock.unlock();
					}
				}
				break;
				case "nextOdd":
				{
					lock.lock(); 
					try{
					// pass the global value and request type into the thread constructor
					t2 = new LocalThread("nextOdd",null);//take in newVal
					newRequest = requestQue.take();
					msg = (t2.call());
					returnQue.put(msg);
					newVal = newVal.add(BigInteger.ZERO);// this needs to be changed to account for updated value
					}finally{
						lock.unlock();
					}
				}
				break;
			
			}
			
			return returnQue.poll();
		}
}

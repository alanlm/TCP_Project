import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RuntimeThr implements Runnable 
{
	// Thread Safe Queues that store the client requests and the order of response 
		private static BlockingQueue<String> requestQue;// = new LinkedBlockingQueue<String>(); 
		private static BlockingQueue<String> returnQue;// = new LinkedBlockingQueue<String>(); 
		private Lock  lock = new ReentrantLock();
	// Declare the Global Values of the current values to be updated and passed from thread to thread
		public  BigInteger curEven, curOdd;
		
		public RuntimeThr(BlockingQueue<String> rqQue, BlockingQueue<String> rtQue,BigInteger even, BigInteger odd)
		{
			requestQue = rqQue;
			returnQue = rtQue;	
			curEven = even;
			curOdd = odd;
		}

		public  void run() 
		{
			String msg = "";
			String key = "";
			NetworkThread t1;
			LocalThread t2;
			key = requestQue.peek();
			System.out.println("RuntimeThr: key = " + key);
			
			switch(key)
			{
				case "nextFib": System.out.println("RuntimeThr: nextFib case");
				{
					lock.lock(); 
					try
					{
						key = requestQue.take();
						if (key == "nextFib")
						{
							t1 = new NetworkThread(key); //take in newVal
							msg = (t1.call());
							System.out.println("RuntimeThr: nextFib return value " + msg);
							returnQue.put(msg);
						}
					} catch (Exception e) 
					{
						e.printStackTrace();
					}finally
					{
						lock.unlock();
					}
				}
				break;
				case "nextPrime": System.out.println("RuntimeThr: nextPrime case");
				{
					lock.lock(); 
					try
					{
						key = requestQue.take();
						if (key == "nextPrime")
						{
							t1 = new NetworkThread(key); //take in newVal
							msg = (t1.call());
							System.out.println("RuntimeThr: nextPrime return value " + msg);
							returnQue.put(msg);
						}
					} catch (Exception e) 
					{
						e.printStackTrace();
					}finally
					{
						lock.unlock();
					}
				}
				break;
				case "nextRand": System.out.println("RuntimeThr: nextRand case");
				{
					lock.lock(); 
					try
					{
						key = requestQue.take();
						if (key == "nextRand")
						{
							t1 = new NetworkThread(key); //take in newVal
							msg = (t1.call());
							System.out.println("RuntimeThr: nextRand return value " + msg);
							returnQue.put(msg);
						}
					} catch (Exception e) 
					{
						e.printStackTrace();
					}finally
					{
						lock.unlock();
					}
				}
				break;
				case "nextEven": System.out.println("RuntimeThr: nextEven case");
				{
					lock.lock(); 
					try
					{
						key = requestQue.take();
						if (key == "nextEven")
						{
							t2 = new LocalThread(key,curEven); //take in newVal
							msg = (t2.call());
							System.out.println("RuntimeThr: nextEven return value " + msg);
							returnQue.put(msg);
						}
					} catch (Exception e) 
					{
						e.printStackTrace();
					}finally
					{
						lock.unlock();
					}
				}
				break;
				case "nextOdd": System.out.println("RuntimeThr: nextOdd case");
				{
					lock.lock(); 
					try
					{
						key = requestQue.take();
						if (key == "nextOdd")
						{
							t2 = new LocalThread(key,curOdd); //take in newVal
							msg = (t2.call());
							System.out.println("RuntimeThr: nextOdd return value " + msg);
							returnQue.put(msg);
						}
					} catch (Exception e) 
					{
						e.printStackTrace();
					}finally
					{
						lock.unlock();
					}
				}
				break;
			
			}
		}
}

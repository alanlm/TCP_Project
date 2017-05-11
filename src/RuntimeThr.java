import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class RuntimeThr implements Runnable 
{
	// Thread Safe Queues that store the client requests and the order of response 
		private static BlockingQueue<String> requestQue;// = new LinkedBlockingQueue<String>(); 
		private static BlockingQueue<String> returnQue;// = new LinkedBlockingQueue<String>(); 
		
		public RuntimeThr(BlockingQueue<String> rqQue, BlockingQueue<String> rtQue)
		{
			requestQue = rqQue;
			returnQue = rtQue;	
		}

		public  void run() 
		{
			String msg = "";
			String key = "";
			NetworkThread t1;
			LocalThread t2;
			do
			{
				System.out.println("=============================================="
						+ "\nRequestQue: " + requestQue.toString()
								+ "\n=============================================="); 
			key = requestQue.peek();

			switch(key)
			{
				case "nextFib": 
				{
					try
					{
						key = requestQue.take();
						if (key == "nextFib")
						{
							t1 = new NetworkThread(key); //take in newVal
							msg = (t1.call());
							System.out.println("RuntimeThr: nextFib return value is " + msg);
							returnQue.put(msg);
						}
					} catch (Exception e) 
					{
						e.printStackTrace();
					}
				}
				break;
				case "nextPrime": 
				{
					try
					{
						key = requestQue.take();
						if (key == "nextPrime")
						{
							t1 = new NetworkThread(key); //take in newVal
							msg = (t1.call());
							System.out.println("RuntimeThr: nextPrime return value is " + msg);
							returnQue.put(msg);
						}
					} catch (Exception e) 
					{
						e.printStackTrace();
					}
				}
				break;
				case "nextRand": 
				{
					try
					{
						key = requestQue.take();
						if (key == "nextRand")
						{
							t1 = new NetworkThread(key); //take in newVal
							msg = (t1.call());
							System.out.println("RuntimeThr: nextRand return value is " + msg);
							returnQue.put(msg);
						}
					} catch (Exception e) 
					{
						e.printStackTrace();
					}
				}
				break;
				case "nextEven": 
				{
					try
					{
						key = requestQue.take();
						if (key == "nextEven")
						{
							t2 = new LocalThread(key); //take in newVal
							msg = (t2.call());
							System.out.println("RuntimeThr: nextEven return value is " + msg);
							returnQue.put(msg);
						}
					} catch (Exception e) 
					{
						e.printStackTrace();
					}
				}
				break;
				case "nextOdd": 
				{
					try
					{
						key = requestQue.take();
						if (key == "nextOdd")
						{
							t2 = new LocalThread(key); //take in newVal
							msg = (t2.call());
							System.out.println("RuntimeThr: nextOdd return value is " + msg);
							returnQue.put(msg);
						}
					} catch (Exception e) 
					{
						e.printStackTrace();
					}
				}
				break;
				default: 
					try {
					throw new IOException("Request Queue is empty");
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
			System.out.println("=============================================="
					+ "\nReturnQue: " + returnQue.toString()
							+ "\n=============================================="); 
			
			
			if(returnQue.isEmpty()) {
				System.out.println("All responses have been sent . . .");
			}
			}while(true);
		}
}

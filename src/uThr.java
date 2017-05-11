import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class uThr extends Thread
{
	private static BlockingQueue<String> requestQue;// = new LinkedBlockingQueue<String>(); 
	private static BlockingQueue<String> returnQue;// = new LinkedBlockingQueue<String>(); 
	public uThr(BlockingQueue<String> rqQue, BlockingQueue<String> rtQue)
	{
		requestQue = rqQue;
		returnQue = rtQue;
		
	}
	
	public void run()
	{
		String msg = "";
		Random rand = new Random();
		int result = 0;
		
		for (int i = 0; i < 20; i++)
		{
			result = rand.nextInt(5) + 2;
			switch (result) 
			{
				case 2: System.out.println("uThr: request = nextEven");
					try 
					{
						requestQue.put("nextEven");
						msg = returnQue.take();
						System.out.println("uThr: return value from runtime = " + msg);
					} catch (InterruptedException e) 
					{
						e.printStackTrace();
					}
				break;
				case 3: System.out.println("uThr: request = nextOdd");
					
					try 
					{
						requestQue.put("nextOdd");
						msg = returnQue.take();
						System.out.println("uThr: return value from runtime = " + msg);
					} catch (InterruptedException e) 
					{
					e.printStackTrace();
					}
					break;
				case 4: System.out.println("uThr: request = nextFib");
					try 
					{
						requestQue.put("nextFib");
						msg = returnQue.take();
						System.out.println("uThr: return value from runtime = " + msg);
					} catch (InterruptedException e) 
					{
					e.printStackTrace();
					}
					break;
				case 5: System.out.println("uThr: request = nextRand");
					try 
					{
						requestQue.put("nextRand");
						msg = returnQue.take();
						System.out.println("uThr: return value from runtime = " + msg);
					} catch (InterruptedException e) 
					{
					e.printStackTrace();
					}
					break;
				case 6: System.out.println("uThr: request = nextPrime");
					try 
					{
						requestQue.put("nextPrime");
						msg = returnQue.take();
						System.out.println("uThr: return value from runtime = " + msg);
					} catch (InterruptedException e) 
					{
					e.printStackTrace();
					}
					break;
				default: System.out.println("uThr: request = Invalid");
					// do nothing
					break;
			}
		}
	}
}

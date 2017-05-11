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
			result = rand.nextInt() % 5;
			switch (result) 
			{
				case 0:
					try 
					{
						requestQue.put("nextEven");
						msg = returnQue.take();
						System.out.println(msg);
					} catch (InterruptedException e) 
					{
						e.printStackTrace();
					}
				break;
				case 1:
					
					try 
					{
						requestQue.put("nextOdd");
						msg = returnQue.take();
						System.out.println(msg);
					} catch (InterruptedException e) 
					{
					e.printStackTrace();
					}
					break;
				case 2:
					try 
					{
						requestQue.put("nextFib");
						msg = returnQue.take();
						System.out.println(msg);
					} catch (InterruptedException e) 
					{
					e.printStackTrace();
					}
					break;
				case 3:
					try 
					{
						requestQue.put("nextRand");
						msg = returnQue.take();
						System.out.println(msg);
					} catch (InterruptedException e) 
					{
					e.printStackTrace();
					}
					break;
				case 4:
					try 
					{
						requestQue.put("nextPrime");
						msg = returnQue.take();
						System.out.println(msg);
					} catch (InterruptedException e) 
					{
					e.printStackTrace();
					}
					break;
				default:
					// do nothing
					break;
			}
		}
	}
}

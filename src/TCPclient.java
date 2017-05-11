import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class TCPclient 
{
	private static BlockingQueue<String> requestQue = new LinkedBlockingQueue<String>(); 
	private static BlockingQueue<String> returnQue = new LinkedBlockingQueue<String>(); 
	public static BigInteger curEven; 
	public static BigInteger curOdd;
	
	public static void main(String[] args)
	{
		System.out.println("Starting Client Requests...");
		uThr u1 = new uThr(requestQue,returnQue);
		uThr u2 = new uThr(requestQue,returnQue);
		uThr u3 = new uThr(requestQue,returnQue);
		uThr u4 = new uThr(requestQue,returnQue);
		uThr u5 = new uThr(requestQue,returnQue);
		uThr u6 = new uThr(requestQue,returnQue);
		uThr u7 = new uThr(requestQue,returnQue);
		uThr u8 = new uThr(requestQue,returnQue);
		RuntimeThr runThr = new RuntimeThr(requestQue,returnQue);
		ExecutorService exec = Executors.newFixedThreadPool(10);
		exec.submit(u1);
		exec.submit(u2);
		exec.submit(u3);
		exec.submit(u4);
		exec.submit(u5);
		exec.submit(u6);
		exec.submit(u7);
		exec.submit(u8);
		exec.submit(runThr);
		exec.execute(u1);
		exec.execute(u2);
		exec.execute(u3);
		exec.execute(u4);
		exec.execute(u5);
		exec.execute(u6);
		exec.execute(u7);
		exec.execute(u8);
		exec.execute(runThr);
		exec.shutdown();
		try 
		{
			exec.awaitTermination(10, TimeUnit.MINUTES);
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		System.out.println("All requests have been sent...");
		
	} // end of main 
	
} // end of TCPclient class

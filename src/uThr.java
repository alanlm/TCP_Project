import java.util.*;

public class uThr extends Thread{
	//no idea if it was going to be its own class or not
	private RuntimeThr queue = null;
	
	public uThr(RuntimeThr q) {
		queue = q;
	}
	
	public void run() {
		Random rand = new Random();
		int result = rand.nextInt() % 5;
		
		for (int i = 0; i < 20; i++) {
			switch (result) {
				case 0:
					//since the other threads are passing via string
					//still wasn't told if returning automatically or 
					//if it needed to grab the information manually
					//or how I'm passing this information for that matter
					//System.out.println(queue.Enqueue("nextEven"));
					break;
				case 1:
					//System.out.println(queue.Enqueue("nextOdd"));
					break;
				case 2:
					//System.out.println(queue.Enqueue("nextEvenFib"));
					break;
				case 3:
					//System.out.println(queue.Enqueue("nextLargerRand"));
					break;
				case 4:
					//System.out.println(queue.Enqueue("nextPrime"));
					break;
				default:
					break;
			}
			//change to actual function name once ready
			//System.out.println(queue.Enqueue(rand.NextInt() % 5));
		}
	}
}


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;

public class NetworkThread implements Callable<String> {
	
	private String request = ""; 
	
	public NetworkThread(String r) {
		request = r; 
	} // end of constructor
	
	@Override
	public String call() throws Exception {
		String host = "192.168.1.2";//"192.168.1.5"; 
		int port = 2500; 
		
		Socket serverSocket = null; 
		PrintWriter toServer; 
		BufferedReader fromServer; 
		String response = ""; 
		
		try {
			// Socket to connect to server 
			serverSocket = new Socket(host, port);
			
			// sending request to server
			toServer = new PrintWriter(
					serverSocket.getOutputStream(), true); 
			System.out.println("Client sending: " + request);
			toServer.println(request);
			System.out.println("Client request sent!\n");
			
			// reading response from server 
			fromServer = new BufferedReader(
					new InputStreamReader(serverSocket.getInputStream()));
			System.out.println("Waiting for server to respond . . ."); 
			response = fromServer.readLine(); 
			System.out.println("Server's Response: " + response + "\n");
			
			// TODO give response from server to return queue 
			return response; 
		} catch(UnknownHostException e) {
			e.printStackTrace();
			System.err.println("Don't know about host " + host); 
			
		} catch(IOException e) {
			e.printStackTrace();
			System.err.println("Couldn't get I/O for the connection to " + host);
		} 
		return "ERROR! Could not get a response from server"; 
	} // end of call method 

} // end of class 

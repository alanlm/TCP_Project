
import java.io.*;
import java.math.BigInteger;
import java.net.*; 

public class TCPserverThread extends Thread {
	
	private Socket clientSocket = null; 
	
	public TCPserverThread(Socket socket) {
		clientSocket = socket; 
	}
	
	public void run() {
		System.out.println("\nClient(s) connected to the server"); 
		
		try { 
            while(true) {
            	// read client's request
                BufferedReader fromClient = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
                System.out.println("Waiting for client's request . . .");
                String request = fromClient.readLine(); 
                System.out.println("Client's Request: " + request);
                
                // process client's request 
                System.out.println("Processing client's request . . .");
                String response = ""; 
                switch(request) {
                case "nextFib": // TODO: nextEvenFib
                	BigInteger nextFibValue = TCPserver.nextEvenFib(TCPserver.fibValue.add(BigInteger.ONE)); 
                	TCPserver.fibValue = nextFibValue; 
                	response = String.valueOf(nextFibValue); 
                	break;
                case "nextRand": // TODO: nextLargerRand
                	int nextRandValue = TCPserver.nextLargerRand(TCPserver.randValue); 
                	TCPserver.randValue = nextRandValue; 
                	response = String.valueOf(nextRandValue); 
                	break; 
                case "nextPrime": // TODO: nextPrime
                	int nextPrimeValue = TCPserver.nextPrime(TCPserver.primeValue); 
                	TCPserver.primeValue = nextPrimeValue; 
                	response = String.valueOf(nextPrimeValue); 
                	break; 
                }
                
                // send server's response 
                PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream(), true);
                System.out.println("Server sending: " + response);
                toClient.println(response);
                System.out.println("Server's response sent!\n");
                toClient.flush();
            }
        } catch(IOException e) {
        	e.printStackTrace();
        	System.out.println("Exception caught when trying to listen on port " 
        			+ clientSocket.getPort() + " or listening for a connection");
        	System.out.println(e.getMessage());
        } // end of try catch 
	} // end of run method 
} // end of TCPserverThread class  
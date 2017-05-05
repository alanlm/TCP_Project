
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
                case "fib":
                case "fibonacci": 
                case "nextevenfib":
                case "nextEvenFib": // TODO: nextEvenFib
                	response = TCPserver.nextEvenFib(TCPserver.fibValue); 
                	break;
                case "rand":
                case "largerRand":
                case "largerrand":
                case "nextlargerrand":
                case "nextLargerRand": // TODO: nextLargerRand
                	int nextRandValue = TCPserver.nextLargerRand(TCPserver.randValue); 
                	TCPserver.randValue = nextRandValue; 
                	response = String.valueOf(nextRandValue); 
                	break; 
                case "prime":
                case "nextprime":
                case "nextPrime": // TODO: nextPrime
                	int nextPrimeValue = TCPserver.nextPrime(TCPserver.primeValue); 
                	TCPserver.primeValue = nextPrimeValue; 
                	response = String.valueOf(nextPrimeValue); 
                	break; 
                default: // unknown request 
                	response = "ERROR: Unknown Request! Cannot Process . . ."; 
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
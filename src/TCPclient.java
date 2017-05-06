
import java.io.*; 
import java.math.BigInteger;
import java.net.*; 

public class TCPclient {
	public static BigInteger odd = BigInteger.ONE;
	public static BigInteger even = BigInteger.valueOf(2);
	
	public BigInteger getEven(){
		return even;
	}
	public BigInteger getOdd(){
		return odd;
	}
	public void setEven(BigInteger nextEven){
		even = nextEven;
	}
	public void setOdd(BigInteger nextOdd){
		nextOdd = odd;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub


	} // end of main 

} // end of TCPclient class

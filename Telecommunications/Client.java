/**
 * 
 */
package cs.tcd.ie;

import java.net.DatagramSocket;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import tcdIO.*;

/**
 *
 * Client class
 * 
 * An instance accepts user input 
 *
 */
public class Client extends Node {
	static final int DEFAULT_SRC_PORT = 50000;//50000
	static final int DEFAULT_DST_PORT = 50001;//50001
	static final int DEFAULT_MID_DST_PORT = 40789;//40789
	static final int FALSE_PORT=40788;
	static final String DEFAULT_DST_NODE = "localhost";	

	Terminal terminal;
	InetSocketAddress dstAddress;
	DatagramPacket resendCopy;
	DatagramPacket [] copySent;
	boolean done=false;
	boolean sent=false;
	int srcPort, segmentNumber, times;

	/**
	 * Constructor
	 * 	 
	 * Attempts to create socket at given port and create an InetSocketAddress for the destinations
	 */
	Client(Terminal terminal, String dstHost, int dstPort, int srcPort) {
		try {
			this.terminal= terminal;
			dstAddress= new InetSocketAddress(dstHost, dstPort);
			socket= new DatagramSocket();
			this.srcPort=socket.getLocalPort();
			socket.setSoTimeout(5000);
			listener.go();	
			segmentNumber=0;times=0;
			copySent=new DatagramPacket[0];
		}
		catch(java.lang.Exception e) {e.printStackTrace();}
	}


	/**
	 * Assume that incoming packets contain a String and print the string.
	 * @throws Exception 
	 * if receive NACK, send the right copy back to server 
	 * else notify to stop waiting
	 */
	public synchronized void onReceipt(DatagramPacket packet) throws Exception{
		try {
			StringContent content= new StringContent(packet);
			String fullContent = content.toString();
			String [] contentToBeExtracted = fullContent.split(delimiter);
			terminal.println(fullContent);
			terminal.println("Received "+contentToBeExtracted[0]+":"+contentToBeExtracted[1]);
			if (contentToBeExtracted[0].equalsIgnoreCase("NACK"))
			{
				socket.send(copySent[Integer.parseInt(contentToBeExtracted[1])]);
				terminal.println("Resending packet "+contentToBeExtracted[1]);
				segmentNumber=Integer.parseInt(contentToBeExtracted[1]);
			}
			else 
			{
				this.notify();
				segmentNumber++;
				this.sent=false;
			}
		} catch (IOException e) {
			e.printStackTrace();		
		}
	}

	/*
	 * packageResend method 
	 * resends a copy of the package if timeout exception is called 
	 * if nothing has been sent, this method is not called
	 */
	@Override
	public void packageResend(){
		try {
			if (sent==true){
				terminal.println("TIMEOUT!!!");
				socket.send(resendCopy);
				terminal.println("resending");	
				times++;
				//test to see if package goes to the right gateway and ack comes back to end the loop
				if(times==2)
				{
					this.dstAddress=new InetSocketAddress(DEFAULT_DST_NODE,DEFAULT_MID_DST_PORT);
					this.resendCopy.setSocketAddress(dstAddress);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();		
		}
	}
	
	private DatagramPacket [] storeNewPackage(DatagramPacket packet){
		DatagramPacket [] temp = new DatagramPacket[copySent.length+1];
		System.arraycopy(copySent, 0, temp, 0, copySent.length);
		temp[temp.length-1]=packet;
		return temp;
	}
	
	/**
	 * Sender Method
	 * 
	 */
	public synchronized void start() throws Exception {
		DatagramPacket packet= null;
		byte[] payload= null;
		byte[] header= null;
		byte[] buffer= null;
		String message;

		while(!done){
			//a copy of the packet stored for the NACK response
			if (packet!=null){this.copySent=storeNewPackage(packet);}
			message = terminal.readString("String to send: ");
			//terminate program based on inputs
			if (message.isEmpty()||message.equalsIgnoreCase("done"))
			{
				this.done=true;
				terminal.println("goodbye");
				socket.close();
			}
			else 
			{
				//payload consists of message in the package, package number for acknowledgement, and port number of socket
				//each separated by a delimiter
				message = message + delimiter + segmentNumber + delimiter + DEFAULT_DST_PORT;
				payload=message.getBytes();
				header= new byte[PacketContent.HEADERLENGTH];
				buffer= new byte[header.length + payload.length];
				System.arraycopy(header, 0, buffer, 0, header.length);
				System.arraycopy(payload, 0, buffer, header.length, payload.length);
				terminal.println("Sending packet...");
				//byte []destination=dstAddress.getAddress().getAddress();
				packet=new DatagramPacket(buffer, buffer.length, dstAddress);
				//packet=new DatagramPacket (buffer, socket.getLocalPort(),destination,DEFAULT_DST_PORT,segmentNumber);
				//a copy stored for the timeout exception to resend the lost package
				resendCopy=packet;
				//testing if the system returns the right acknowledgement, which is NACK
				if (segmentNumber==2)
				{
					segmentNumber=4;
					continue;
				}
				socket.send(packet);
				//initiate timeout check
				sent=true;
				terminal.println("Packet sent");
				this.wait();
			}
		}
	}

	/**
	 * Test method
	 * 
	 * Sends a packet to a given address
	 */
	public static void main(String[] args) {
		try {					
			Terminal terminal= new Terminal("Client");		
			(new Client(terminal, DEFAULT_DST_NODE, DEFAULT_MID_DST_PORT, DEFAULT_SRC_PORT)).start();
			
			/*
			 * check time out by sending the packet purposely to the wrong socket.
			 */
			//(new Client(terminal, DEFAULT_DST_NODE, FALSE_PORT, DEFAULT_SRC_PORT)).start();
		} catch(java.lang.Exception e) {e.printStackTrace();}
	}

}
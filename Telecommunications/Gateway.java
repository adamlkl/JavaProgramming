package cs.tcd.ie;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

import tcdIO.Terminal;

public class Gateway extends Node{
	
	static final int DEFAULT_PORT=40789;
	static final String DEFAULT_DST_NODE = "localhost";	
	Terminal terminal;
	InetSocketAddress dstAddress;
	String node;
	
	Gateway(Terminal terminal,String dstHost, int srcPort)
	{
		try {
		this.terminal=terminal;
		socket=new DatagramSocket(srcPort);
		this.node=dstHost;
		listener.go();
		}
		catch(java.lang.Exception e) {e.printStackTrace();}
	}

	/*receive package from clients or server 
	 * get the port number in the package and use it to determine the address of the package destination.
	 * swap the port number in the package with the origin of the packet.
	 */
	
	@Override
	public void onReceipt(DatagramPacket packet) {
		try {
			StringContent content= new StringContent(packet);
			String contentToBeSplit = content.toString();
			String [] contentSplitted = contentToBeSplit.split(delimiter);
			terminal.println(contentSplitted[1]+": "+contentSplitted[0]);
			int dstPort = Integer.parseInt(contentSplitted[2]);
			DatagramPacket response;
			response= (new StringContent(contentSplitted[0]+delimiter+contentSplitted[1]+delimiter+packet.getPort())).toDatagramPacket();
			response.setSocketAddress(new InetSocketAddress(node,dstPort));
			socket.send(response);
		}
		catch(Exception e) {e.printStackTrace();}
	}

	@Override
	public void packageResend() {
		// TODO Auto-generated method stub
	}
	
	public synchronized void start()
	{
		try {
			terminal.println("Waiting for contact");
			this.wait();
		} catch(java.lang.Exception e) {e.printStackTrace();}
	}

	public static void main(String[] args) {
		try {					
			Terminal terminal = new Terminal("gateway");
			(new Gateway(terminal,DEFAULT_DST_NODE,DEFAULT_PORT)).start();
		} catch(java.lang.Exception e) {e.printStackTrace();}
	}

}

package cs.tcd.ie;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashMap;

import tcdIO.Terminal;

public class Server extends Node {
	static final int DEFAULT_PORT = 50001;//50001
	static int seqNumber;
	Terminal terminal;
	HashMap<Integer, Integer> hmap;

	/*
	 * 
	 */
	Server(Terminal terminal, int port) {
		try {
			this.terminal= terminal;
			socket= new DatagramSocket(port);
			listener.go();
			hmap = new HashMap<Integer, Integer>();
		}
		catch(java.lang.Exception e) {e.printStackTrace();}
	}

	/**
	 * Assume that incoming packets contain a String and print the string.
	 * use a hash map to store the seq numbers of each client and the port number as the key.
	 */
	public void onReceipt(DatagramPacket packet) throws Exception{
		try {
			StringContent content= new StringContent(packet);
			String contentToBeSplit = content.toString();
			String [] contentSplitted = contentToBeSplit.split(delimiter);
			//contains message
			String message = contentSplitted[0];
			//contentSplitted[1] contains segment number 
			terminal.println("Package"+contentSplitted[1]+": "+message);
			DatagramPacket response;
			//contentSplitted[2] contains portnumber of client
			seqNumber=check(Integer.parseInt(contentSplitted[2]));

			//return ack
			if (Integer.parseInt(contentSplitted[1])==seqNumber)
			{
				terminal.println("Correct Package Received");
				seqNumber++;
				hmap.put(Integer.parseInt(contentSplitted[2]), seqNumber);
				response= (new StringContent("ACK"+delimiter+seqNumber+delimiter+contentSplitted[2])).toDatagramPacket();
			}
			
			//returns nack 
			else 
			{
				terminal.println("Incorrect packeage received. Package that should be received is PACKAGE" + seqNumber);
				response= (new StringContent("NACK"+delimiter+seqNumber+delimiter+contentSplitted[2])).toDatagramPacket();
			}
			response.setSocketAddress(packet.getSocketAddress());
			socket.send(response);
		}
		catch(Exception e) {e.printStackTrace();}
	}

	/*
	 * create a key and a default value of 0 if empty hash map or key not found, return 0 
	 * else return value stored in the key
	 */
	public Integer check (int key)
	{
		if (!hmap.isEmpty()&&hmap.containsKey(key))
		{
			return hmap.get(key);
		}
		else{
			hmap.put(key,0);
			return (hmap.get(key));	
		}
	}
	
	@Override
	public void packageResend() {
	}

	public synchronized void start() throws Exception {
		terminal.println("Waiting for contact");
		this.wait();
	}

	/*
	 * 
	 */
	public static void main(String[] args) {
		try {					
			Terminal terminal= new Terminal("Server");
			(new Server(terminal, DEFAULT_PORT)).start();
			terminal.println("Program completed");
		} catch(java.lang.Exception e) {e.printStackTrace();}
	}
}
package udpdemo;

import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Scanner;

import java.io.IOException;

// client side
public class UDPClient {
	
	public static void main(String[] args) throws IOException {
	
		// IP address to send data to
		InetAddress IPA = InetAddress.getLocalHost();
				
		// port used
		int port = 21176;
		
		// socket to send and receive data out of
		DatagramSocket socket = new DatagramSocket();
		
		// scanner for user input
		Scanner scanner = new Scanner(System.in);
		
		// message to send
		String msg = "";
		
		// prompt user for message
		System.out.print("What would you like to say?: ");
		msg = scanner.nextLine();
		
		// send message
		send(msg, IPA, socket, port);
		
		// print response
		System.out.println("Server: " + receive(socket));
		
		// close socket and scanner
		socket.close();
		scanner.close();
		
		// end runtime so port can be used again
		System.exit(0);
	}
	
	public static void send(String msg, InetAddress IPA, DatagramSocket socket, int port) throws IOException  { // send message to server
		// convert data into sendable bytes
		byte[] dataToSend = String.valueOf(msg).getBytes();
				
		// packet to send
		DatagramPacket packetToSend = new DatagramPacket(dataToSend, dataToSend.length, IPA, port);
				
		// send packet
		socket.send(packetToSend);
		System.out.println("You sent: " + msg);
	}

	public static String receive(DatagramSocket socket) throws IOException { // receive message from server
		// data to receive
		byte[] receivedData = new byte[1024]; // 1024 is an arbitrary length
				
		// packet to receive
		DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
				
		// receive packet
		socket.receive(receivedPacket);
		
		return new String(receivedPacket.getData());
	}
}

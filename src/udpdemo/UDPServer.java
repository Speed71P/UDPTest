package udpdemo;

import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;

import java.io.IOException;

// server side
public class UDPServer {

	public static void main(String[] args) throws IOException {

		// IP address
		InetAddress IPA = InetAddress.getLocalHost();

		// port used
		int port = 21176;

		// socket to receive and send data out of
		DatagramSocket socket = new DatagramSocket(port);

		// message to send
		String msg = "Handshake Achieved";
		

		// send message to client
		send(msg, IPA, socket, receive(socket)); // because the send function relies on acquiring the same port it's received message was from, it grabs it directly

		// close socket
		socket.close();

		// end runtime so port can be used again
		System.exit(0);
	}

	public static void send(String msg, InetAddress IPA, DatagramSocket socket, DatagramPacket receivedPacket) throws IOException { // send message to client
		// convert data into sendable bytes
		byte[] dataToSend = String.valueOf(msg).getBytes();

		// packet to send
		DatagramPacket packetToSend = new DatagramPacket(dataToSend, dataToSend.length, IPA, receivedPacket.getPort());

		// send packet
		socket.send(packetToSend);
	}

	public static DatagramPacket receive(DatagramSocket socket) throws IOException { // receive message from client
		// data to receive
		byte[] receivedData = new byte[1024]; // 1024 is an arbitrary length

		// packet to receive
		DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);

		// receive packet

		socket.receive(receivedPacket);

		return receivedPacket;
	}
}

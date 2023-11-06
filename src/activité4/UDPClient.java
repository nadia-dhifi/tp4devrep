package activité4;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.*;



public class UDPClient {
	final static int PORT = 1234;
	private static byte[] buffer = new byte[1024];
	public static void main(String[] args) {
		System.out.println("Donner votre nom");
		Scanner scanner = new Scanner(System.in);
		String username = scanner.nextLine();
		try {
			DatagramSocket socket = new DatagramSocket();
			DatagramPacket packet = new DatagramPacket(username.getBytes(), username.length(), InetAddress.getByName("localhost"), PORT);
			socket.send(packet);
			
			DatagramPacket receiveData = new DatagramPacket(buffer, buffer.length);
			socket.receive(receiveData);
			
			System.out.println(new String(receiveData.getData(),0,receiveData.getLength()));
			socket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}

}
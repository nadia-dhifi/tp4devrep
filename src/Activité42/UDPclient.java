package Activité42;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPclient {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1"; // Adresse IP du serveur
        int serverPort = 1234;

        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress server = InetAddress.getByName(serverAddress);

            // Envoyer une demande au serveur
            String request = "DemandeHeure";
            byte[] requestData = request.getBytes();
            DatagramPacket requestPacket = new DatagramPacket(requestData, requestData.length, server, serverPort);
            socket.send(requestPacket);

            // Recevoir la réponse du serveur
            byte[] responseData = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length);
            socket.receive(responsePacket);

            String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
            System.out.println("Heure actuelle reçue du serveur : " + response);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

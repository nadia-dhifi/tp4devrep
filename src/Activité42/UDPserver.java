package Activité42;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UDPserver {
    public static void main(String[] args) {
        int port = 1234;

        try {
            DatagramSocket socket = new DatagramSocket(port);
            System.out.println("Serveur UDP en attente sur le port " + port);

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);

                String clientRequest = new String(receivePacket.getData(), 0, receivePacket.getLength());

                if (clientRequest.equals("DemandeHeure")) {
                    Date currentDate = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String dateAndTime = dateFormat.format(currentDate);

                    byte[] responseBuffer = dateAndTime.getBytes();
                    DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length,
                            receivePacket.getAddress(), receivePacket.getPort());

                    socket.send(responsePacket);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


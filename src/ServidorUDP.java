import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServidorUDP {
    public static void main(String[] args) throws IOException {
        byte[] bufer = new byte[1024]; //bufer que recibirá el datagram del cliente
        DatagramSocket socket = new DatagramSocket(1234);

        //esperando al datagram:
        System.out.println("Esperando al datagrama...");
        DatagramPacket paqueteRecibir = new DatagramPacket(bufer, bufer.length);
        socket.receive(paqueteRecibir); //recibiendo datagrama

        //int bytesRecibidos = paqueteRecibir.getLength(); //obtengo el número de bytes
        String paqueteDef = new String(paqueteRecibir.getData()); //obtengo String
        String mayus = paqueteDef.toUpperCase();

        //creo contenedor
        byte[] mensajeMayus = new byte[1024];
        //meto mensaje en contenedor
        mensajeMayus = mayus.getBytes();
        DatagramPacket envio = new DatagramPacket(mensajeMayus, mensajeMayus.length, paqueteRecibir.getAddress(), paqueteRecibir.getPort());

        //Envío de datagram:
        socket.send(envio);

    }
}

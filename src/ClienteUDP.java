import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ClienteUDP {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);

        InetAddress destino = InetAddress.getLocalHost();
        int port = 1234; //puerto al que enviar el mensaje, el del servidor
        DatagramSocket socket = new DatagramSocket(34567); //Puerto local, el del cliente
        String texto = "";
        do {
            //contenedor del mensaje más o menos
            byte[] mensaje = new byte[1024];

            //mensaje, digamos, en bruto
            System.out.printf("Introduce una cadena de texto:");
            texto = scan.nextLine();
            mensaje = texto.getBytes();

            //Datagram a ENVIAR:
            DatagramPacket envio = new DatagramPacket(mensaje, mensaje.length, destino, port);


            //Envío de datagram:
            socket.send(envio);

            byte[] bufer = new byte[1024]; //bufer que recibirá el datagram del cliente
            //esperando al datagram:
            System.out.println("Esperando al datagrama...");
            DatagramPacket paqueteRecibir = new DatagramPacket(bufer, bufer.length);
            socket.receive(paqueteRecibir); //recibiendo datagrama
            System.out.println("Recibido datagrama:");
            String mensajeRecibido = new String(paqueteRecibir.getData(), 0, paqueteRecibir.getLength(), StandardCharsets.UTF_8);
            System.out.println(mensajeRecibido);

        }while(!texto.equals("*"));
        socket.close();
    }
}

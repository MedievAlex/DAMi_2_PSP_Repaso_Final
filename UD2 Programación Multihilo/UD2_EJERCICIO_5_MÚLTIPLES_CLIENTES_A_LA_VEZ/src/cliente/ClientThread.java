package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
Modifica el ejercicio “Cliente-Servidor con múltiples clientes” 
de la UD1 para que los clientes puedan ser atendidos al mismo tiempo.".
**/

public class ClientThread extends Thread {
    private final Socket socket;
    private final int idCliente;
    
    public ClientThread(Socket socket, int idCliente) {
        this.socket = socket;
        this.idCliente = idCliente;
    }
    
    public void run() {
        try (
            Socket s = socket;
            ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream())
        ) {
            System.out.println("[CLIENTE] Cliente " + idCliente + " conectado.");

            String mensaje = (String) entrada.readObject();
            System.out.println("[CLIENTE] Cliente " + idCliente + " dice: " + mensaje);

            salida.writeObject("[CLIENTE] Saludos desde el servidor al cliente " + idCliente);

            System.out.println("[CLIENTE] Cliente " + idCliente + " desconectado.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("[CLIENTE: Error] " + idCliente + ": " + e.getMessage());
        }
    }
}
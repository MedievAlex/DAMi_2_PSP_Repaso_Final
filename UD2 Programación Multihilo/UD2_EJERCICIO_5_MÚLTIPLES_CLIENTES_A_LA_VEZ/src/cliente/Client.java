package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
Modifica el ejercicio “Cliente-Servidor con múltiples clientes” 
de la UD1 para que los clientes puedan ser atendidos al mismo tiempo.".
**/

public class Client {

    private final int PUERTO = 1234;
    private final String HOST = "localhost";

    public static void main(String[] args) {
        Client client = new Client();
        client.iniciar();
    }

    public void iniciar() {
        try (
                Socket client = new Socket(HOST, PUERTO);
                ObjectInputStream entrada = new ObjectInputStream(client.getInputStream());
                ObjectOutputStream salida = new ObjectOutputStream(client.getOutputStream())
            ) {
            System.out.println("[CLIENTE] Conexión realizada con el servidor.");
            salida.writeObject("[CLIENTE] Hola servidor, soy un cliente.");
            System.out.println("[CLIENTE] Recibido: " + (String) entrada.readObject());
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println("[CLIENTE: Error] " + ex);
        }
    }
}
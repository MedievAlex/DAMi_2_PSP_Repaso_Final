package servidor;

import java.net.ServerSocket;
import java.net.Socket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
Crea un programa Cliente y un programa Servidor. El Cliente debe conectarse 
con el Servidor, y al hacerlo el Servidor le pasa el mensaje “Hola, ¡Cliente!”.
Es el Cliente quien muestra el saludo.
**/

public class Servidor {
    private final int PUERTO = 5000;

    public static void main(String[] args) {
        Servidor s = new Servidor();
        s.iniciar();
    }

    public void iniciar() {
        ServerSocket servidor = null;
        Socket cliente = null;
        ObjectInputStream entrada = null;
        ObjectOutputStream salida = null;

        try {
        	// [SERVIDOR] Espera conexion de un cliente
            servidor = new ServerSocket(PUERTO);
            System.out.println("Esperando conexiones del cliente...");

            // [CLIENTE] Cliente conectado a Servidor
            cliente = servidor.accept();
            System.out.println("Cliente conectado.");

            // [SERVIDOR] Crea flujo de entrada y salida
            salida = new ObjectOutputStream (cliente.getOutputStream());
            entrada = new ObjectInputStream(cliente.getInputStream());
           
            // [SERVIDOR] Envia mensaje
            salida.writeObject("Hola ¡Cliente!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
 		if (entrada != null)
                    entrada.close();
                if (salida != null)
                    salida.close();
                if (servidor != null)
                    servidor.close();
                if (cliente != null)
                    cliente.close();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
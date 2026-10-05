package cliente;

import java.net.Socket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.io.IOException;

/**
Crea un programa Cliente y un programa Servidor. El Cliente debe conectarse 
con el Servidor, y al hacerlo el Servidor le pasa el mensaje “Hola, ¡Cliente!”.
Es el Cliente quien muestra el saludo.
**/

public class Cliente {
    private final int PUERTO = 5000;
    private final String IP = "127.0.0.1";

    public static void main(String[] args) {
        Cliente c = new Cliente();
        c.iniciar();
    }

    public void iniciar() {
        Socket cliente = null;
        ObjectInputStream entrada = null;
        ObjectOutputStream salida = null;

        String mensaje;

        try {
        	// [CLIENTE] Cliente conectado a Servidor
            cliente = new Socket(IP, PUERTO);
            System.out.println("Conexión realizada con servidor");
            
            // [CLIENTE] Crea flujo de entrada y salida
            salida = new ObjectOutputStream(cliente.getOutputStream());
            entrada = new ObjectInputStream(cliente.getInputStream());
            
            // [SERVIDOR] Envia mensaje
            mensaje = (String) entrada.readObject();
            System.out.println(mensaje);
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (entrada != null) {
                    entrada.close();
                }
                if (salida != null) {
                    salida.close();
                }
                if (cliente != null) {
                    cliente.close();
                }
            } catch (IOException e) {
                System.err.println("[Error] " + e.getMessage());
            }
            System.out.println("Fin cliente");
        }
    }
}
package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
Modifica el ejercicio anterior para que el servidor no finalice 
cuando envía el mensaje al cliente, sino que se quede esperando 
al siguiente cliente.
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
            cliente = new Socket(IP, PUERTO);
            System.out.println("Conexión realizada con servidor");

            salida = new ObjectOutputStream(cliente.getOutputStream());
            entrada = new ObjectInputStream(cliente.getInputStream());

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
                e.printStackTrace();
            }
            System.out.println("Fin cliente");
        }
    }
}
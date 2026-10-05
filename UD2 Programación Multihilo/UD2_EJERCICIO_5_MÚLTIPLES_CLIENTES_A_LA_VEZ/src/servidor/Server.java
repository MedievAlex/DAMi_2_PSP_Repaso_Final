package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import cliente.ClientThread;

/**
Modifica el ejercicio “Cliente-Servidor con múltiples clientes” 
de la UD1 para que los clientes puedan ser atendidos al mismo tiempo.".
**/

public class Server {
    
    private final int PUERTO = 1234;
    private int CONTADOR = 1;
    
    public static void main(String[] args) {
        Server server = new Server();
        server.iniciar();
    }
    
    public void iniciar() {
        try (ServerSocket server = new ServerSocket(PUERTO)) {
            System.out.println("[SERVER] Servidor iniciado. Esperando conexiones en el puerto " + PUERTO);

            while (true) {
                Socket cliente = server.accept();
                System.out.println("[SERVER] Nueva conexión entrante...");

                ClientThread hilo = new ClientThread(cliente, CONTADOR++);
                hilo.start();
            }

        } catch (IOException ex) {
            System.err.println("[SERVER: Error] " + ex.getMessage());
        }
    }
}
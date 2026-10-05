package cliente;

import java.net.Socket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.io.IOException;

/**
Crea un programa Cliente y un programa Servidor. 
	• El Cliente debe de conectarse con el Servidor, y al hacerlo, el Servidor 
	le comunicará que la conexión se ha realizado con éxito. Este mensaje lo 
	mostrará el cliente. 
	• El Servidor mostrará el saludo del Cliente conectado en cada momento. 
	• El Servidor terminará cuando se hayan conectado 3 Clientes.
**/

public class Cliente {

	public static void main(String[] args) {
		Cliente cliente = new Cliente();
		cliente.iniciar();
	}

	public void iniciar() {
		Socket clienteSocket = null; // Se crea el Socket del CLIENTE

		ObjectOutputStream salida; // Declarar siempre primero
		ObjectInputStream entrada; // Declarar siempre segundo

		try {
			clienteSocket = new Socket("localhost", 1224); // Se conecta al Servidor

			salida = new ObjectOutputStream(clienteSocket.getOutputStream()); // Declarar siempre primero
			entrada = new ObjectInputStream(clienteSocket.getInputStream()); // Declarar siempre segundo

			String mensaje = (String) entrada.readObject(); // Se lee el Mensaje recibido del Servidor
			System.out.println(mensaje); // Se muestra por consola el Mensaje

			// System.out.println((String) entrada.readObject()); // Se puede hacer todo en
			// una misma linea de codigo

			salida.writeObject("[CLIENTE] Hola Servidor.");

			entrada.close(); // Cerrar siempre primero
			salida.close(); // Cerrar siempre segundo

		} catch (ClassNotFoundException e) {
			System.err.println("[CLIENTE] Error: " + e.getMessage());

		} catch (IOException e) {
			System.err.println("[CLIENTE] Error: " + e.getMessage());
		}

		if (clienteSocket != null) {
			try {
				clienteSocket.close(); // Cerrar siempre al final
			} catch (IOException e) {
				System.err.println("[CLIENTE] Error: " + e.getMessage());
			}
		}
	}
}

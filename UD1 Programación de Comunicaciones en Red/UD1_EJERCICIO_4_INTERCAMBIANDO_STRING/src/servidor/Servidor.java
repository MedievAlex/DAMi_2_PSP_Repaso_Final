package servidor;

import java.net.ServerSocket;
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

public class Servidor {

	public static void main(String[] args) {
		Servidor servidor = new Servidor();
		servidor.iniciar();
	}

	public void iniciar() {
		ServerSocket serverSocket = null; // Se crea el Socket del SERVIDOR
		Socket clienteSocket = null; // Se crea un Socket para un CLIENTE

		ObjectOutputStream salida = null; // Declarar siempre primero
		ObjectInputStream entrada = null; // Declarar siempre segundo

		int clientes = 0;

		try {
			do {
				serverSocket = new ServerSocket(1224); // Se le adjudica un Puerto al Servidor (EXCEPCION)
				System.out.println("[SERVIDOR] Esperando conexiones."); // Se muestra por consola el Mensaje
				clienteSocket = serverSocket.accept(); // Se queda en modo ESCUCHA

				clientes++;

				salida = new ObjectOutputStream(clienteSocket.getOutputStream()); // Declarar siempre primero
																					// (EXCEPCION)
				entrada = new ObjectInputStream(clienteSocket.getInputStream()); // Declarar siempre segundo (EXCEPCION)

				salida.writeObject("[SERVIDOR] Conexion exitosa.");
				System.out.println("[SERVIDOR] Cliente " + clientes + " conectado.");

				String mensaje = (String) entrada.readObject(); // Se lee el Mensaje recibido del Servidor
				System.out.println(mensaje); // Se muestra por consola el Mensaje

				// System.out.println((String) entrada.readObject()); // Se puede hacer todo en
				// una misma linea de codigo

				entrada.close(); // Cerrar siempre primero
				salida.close(); // Cerrar siempre segundo

			} while (clientes < 3);

		} catch (IOException e) {
			System.out.println("[SERVIDOR] Error: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("[SERVIDOR] Error: " + e.getMessage());
		} finally {
			try {
				if (entrada != null) {
					entrada.close();
				}
				if (salida != null) {
					salida.close();
				}
				if (serverSocket != null) {
					serverSocket.close();
				}
				if (clienteSocket != null) {
					clienteSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("[SERVIDOR] Fin del servicio.");
		}
	}
}

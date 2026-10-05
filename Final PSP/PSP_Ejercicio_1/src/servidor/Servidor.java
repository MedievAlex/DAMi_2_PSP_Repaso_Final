package servidor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import model.Usuario;

public class Servidor {
	private static final int PUERTO = 5005;

	private static ServerSocket servidor = null;
	private static Socket cliente = null;
	private static ObjectInputStream entrada = null;
	private static ObjectOutputStream salida = null;
	private static Usuario usuario;
	private static ArrayList<Usuario> usuarios;
	private static int conexiones = 0;

	public static void Iniciar() {

		try {
			servidor = new ServerSocket(PUERTO);
			System.out.println("Esperando conexiones del cliente...");
			usuarios = new ArrayList<Usuario>();

			while (conexiones != 4) {
				System.out.println("--------------------------");
				cliente = servidor.accept();
				conexiones++;
				System.out.println("Cliente nº" + conexiones + " conectado.");
				salida = new ObjectOutputStream(cliente.getOutputStream());
				entrada = new ObjectInputStream(cliente.getInputStream());

				usuario = (Usuario) entrada.readObject();
				usuarios.add(usuario);

				System.out.println("Usuario nº" + conexiones + ": " + usuario.toString());

				salida.writeObject(usuarios);
			}

			System.out.println("--------------------------");
			System.out.println("Servicio finalizado.");
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
				if (servidor != null) {
					servidor.close();
				}
				if (cliente != null) {
					cliente.close();
				}
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		Iniciar();
	}
}

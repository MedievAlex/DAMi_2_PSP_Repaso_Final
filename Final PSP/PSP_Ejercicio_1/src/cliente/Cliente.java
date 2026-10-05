package cliente;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import model.Usuario;

public class Cliente {
	private static final int PUERTO = 5005;
	private static final String IP = "127.0.0.1";

	private static Scanner sc = new Scanner(System.in);

	private static Usuario usuario;
	private static String nombre;
	private static int puntuacion;
	private static ArrayList<Usuario> usuarios;

	public static void Iniciar() {
		Socket cliente = null;
		ObjectInputStream entrada = null;
		ObjectOutputStream salida = null;

		try {
			cliente = new Socket(IP, PUERTO);

			salida = new ObjectOutputStream(cliente.getOutputStream());
			entrada = new ObjectInputStream(cliente.getInputStream());

			System.out.print("Nombre: ");
			nombre = sc.nextLine();
			System.out.print("Edad: ");
			puntuacion = sc.nextInt();

			usuario = new Usuario(nombre, puntuacion);
			salida.writeObject(usuario);
			System.out.println("--------------------------");

			usuarios = (ArrayList<Usuario>) entrada.readObject();

			System.out.println("Lista de Usuarios:");
			for (int i = 0; i < usuarios.size(); i++) {
				usuarios.get(i).Print();
			}
			System.out.println("--------------------------");

			usuario.TiempoDeConexion();
			System.out.println("Cliente finalizado.");

		} catch (Exception e) {
			System.out.println("[Error]: " + e.getMessage());
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
			} catch (Exception e) {
				System.out.println("[Error]: " + e.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		Iniciar();
	}
}

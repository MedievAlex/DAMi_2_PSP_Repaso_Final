package client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import model.Jugador;

/**
Elabora un programa Cliente-Servidor en Java que permita gestionar un pequeño ranking de
jugadores, donde cada cliente enviará su información y recibirá la lista actualizada de todos los
participantes.
La aplicación debe cumplir los siguientes requisitos:
1. Clase Jugador:
	• Representará a cada participante del ranking.
	• Contendrá al menos los siguientes datos:
		• Nombre del jugador.
		• Puntuación obtenida.
		• Tiempo de conexión.
		• El tiempo de conexión se utilizará solo dentro de la aplicación y no debe formar
		parte de la información intercambiada entre cliente y servidor.
		• El tiempo de conexión será de tipo java.sql.Timestamp y se calculará de la
		siguiente forma:
		Timestamp tiempoConexion = new Timestamp(System.currentTimeMillis());
2. Servidor:
	• Permitirá la conexión de varios clientes, con un máximo de 3.
	• El servidor terminará cuando se hayan conectado 3 clientes y se mostrará un
	mensaje adecuado.
	• Cada cliente enviará un objeto con la información de su jugador.
	• El servidor almacenará los datos recibidos y enviará al cliente la lista completa de
	jugadores registrados hasta el momento.
	• El servidor deberá mantenerse activo mientras funcione correctamente y finalizar de
	forma controlada en caso de producirse un error o interrupción.
	• Todos los recursos utilizados en la comunicación deberán cerrarse correctamente al
	finalizar la conexión o al producirse un error.
3. Cliente:
	• Pedirá al usuario su nombre y su puntuación.
	• Enviará la información correspondiente a un objeto de tipo Jugador al servidor.
	• Recibirá del servidor la lista de jugadores actualizada y la mostrará por pantalla
	incluido el tiempo de conexión.
	• Todos los recursos utilizados en la comunicación deberán cerrarse correctamente al
	finalizar la ejecución o si ocurre algún error.
	• Cuando el cliente finalice deberá mostrarse el siguiente mensaje “Cliente finalizado”.
4. Comportamiento general:
	• Cada ejecución del cliente representa a un jugador distinto.
	• El servidor gestionará correctamente las conexiones y los datos enviados.
	• Los datos de los jugadores deberán transmitirse y actualizarse de forma adecuada.
**/

public class Cliente {
	private static final int PUERTO = 5005;
	private static final String IP = "127.0.0.1";

	private static Scanner sc = new Scanner(System.in);

	private static Jugador jugador;
	private static String nombre;
	private static int puntuacion;
	private static ArrayList<Jugador> jugadores;

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
			System.out.print("Puntuacion: ");
			puntuacion = sc.nextInt();

			jugador = new Jugador(nombre, puntuacion);
			salida.writeObject(jugador);
			System.out.println("--------------------------");

			jugadores = (ArrayList<Jugador>) entrada.readObject();

			System.out.println("Lista de jugadores:");
			for (int i = 0; i < jugadores.size(); i++) {
				jugadores.get(i).Print();
			}
			System.out.println("--------------------------");
			
			jugador.TiempoDeConexion();
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

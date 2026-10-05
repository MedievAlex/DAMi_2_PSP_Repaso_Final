package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

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

public class Servidor {

	private static final int PUERTO = 5005;

	private static ServerSocket servidor = null;
	private static Socket cliente = null;
	private static ObjectInputStream entrada = null;
	private static ObjectOutputStream salida = null;
	private static Jugador jugador;
	private static ArrayList<Jugador> jugadores;
	private static int conexiones = 0;

	public static void Iniciar() {

		try {
			servidor = new ServerSocket(PUERTO);
			System.out.println("Esperando conexiones del cliente...");
			jugadores = new ArrayList<Jugador>();

			while (conexiones != 3) {
				System.out.println("--------------------------");
				cliente = servidor.accept();
				conexiones++;
				System.out.println("Cliente nº" + conexiones + " conectado.");
				salida = new ObjectOutputStream(cliente.getOutputStream());
				entrada = new ObjectInputStream(cliente.getInputStream());

				jugador = (Jugador) entrada.readObject();
				jugadores.add(jugador);

				System.out.println("Jugador nº" + conexiones + ": " + jugador.toString());

				salida.writeObject(jugadores);
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

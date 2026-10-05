package model;

import java.io.Serializable;
import java.sql.Timestamp;

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

public class Jugador implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nombre;
	private int puntuacion;
	private transient Timestamp tiempoConexion;

	public Jugador(String nombre, int puntuacion) {
		this.nombre = nombre;
		this.puntuacion = puntuacion;
	}

	public void TiempoDeConexion() {
		tiempoConexion = new Timestamp(System.currentTimeMillis());
		System.out.println("Tiempo de conexión: " + tiempoConexion);
	}

	public void Print() {
		System.out.println("- Jugador " + nombre + " con puntuacion " + puntuacion + ".");
	}
	
	@Override
	public String toString() {
		return "Jugador " + nombre + " [puntuacion=" + puntuacion + ", tiempoConexion=" + tiempoConexion + "]";
	}
}
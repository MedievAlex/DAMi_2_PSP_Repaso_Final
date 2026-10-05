package servidor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import model.Deportivo;
import model.Propietario;

/**
Crea una aplicación que pida al usuario y luego almacene datos en 
un fichero sobre un deportivo. Sobre el deportivo preguntaremos al 
usuario sobre los siguientes datos:
    • String matricula.
    • String marca.
    • double depósito.
    • String modelo.
Esta información se almacena primero en una clase llamada Deportivo.
Aparte de la clase anterior también existirá otra clase, llamada 
Propietario, con dos atributos: el nombre y teléfono del propietario.
También le pediremos al usuario que nos proporcione estos datos.
Suponemos que cada deportivo solo tiene un propietario y cada 
propietario un solo deportivo. 
En el fichero guardaremos únicamente la siguiente información:
    • String matricula.
    • String marca.
    • String modelo.
    • String propietario.
Después de cerrar el fichero lo abriremos de nuevo en modo lectura y 
mostraremos su contenido por pantalla.
¿Qué pasa cuando le pides que muestre el tamaño del depósito 
(excepción, error...), que no está guardado en el fichero?
Reescribe el programa anterior suponiendo que añadimos dos nuevos 
atributos a la clase Propietario:
    • int edad.
    • String paisNacimiento.
sabiendo que la edad queremos guardarlo en el fichero, pero el país.
¿Qué ocurre si el orden en que escribes el nombre y la edad (writeObject) 
no coincide con el orden de lectura (readObject)?
**/

public class Servidor {
	private final int PUERTO = 5000;

	public static void main(String[] args) {
		Servidor s = new Servidor();
		s.iniciar();
	}

	public void iniciar() {
		ServerSocket servidor = null;
		Socket cliente = null;
		ObjectInputStream entrada = null;
		ObjectOutputStream salida = null;

		String directoryPath = "data/";
		File fichero = new File(directoryPath + "deportivo.dat");

		Deportivo deportivo = null;
		Propietario propietario = null;
		
		try {
			// [SERVIDOR] Espera conexion de un cliente
			servidor = new ServerSocket(PUERTO);
			System.out.println("Esperando conexiones del cliente...");

			// [CLIENTE] Cliente conectado a Servidor
			cliente = servidor.accept();
			System.out.println("Cliente conectado.");

			// [SERVIDOR] Crea flujo de entrada y salida
			salida = new ObjectOutputStream(cliente.getOutputStream());
			entrada = new ObjectInputStream(cliente.getInputStream());

			// [SERVIDOR] Envia mensajes
			salida.writeObject("Hola ¡Cliente!");
			salida.writeObject("Introduce los siguientes datos del Deportivo.");
			
			deportivo = datosDeportivo(entrada, salida);
			
			propietario = datosPropietario(entrada, salida);

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			try {
				if (entrada != null)
					entrada.close();
				if (salida != null)
					salida.close();
				if (servidor != null)
					servidor.close();
				if (cliente != null)
					cliente.close();
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}

	/*********************************************************************************************************/

	// Pide los datos del Deportivo
	public static Deportivo datosDeportivo(ObjectInputStream entrada, ObjectOutputStream salida) {
		Deportivo deportivo = null;
		String matricula, marca, modelo;
		double deposito;

		try {
			// [SERVIDOR] Pide el dato
			salida.writeObject("Introduzca la matricula del Deportivo: ");

			// [CLIENTE] Introduce el dato pedido
			matricula = (String) entrada.readObject();

			// [SERVIDOR] Pide el dato
			salida.writeObject("Introduzca la marca del Deportivo: ");

			// [CLIENTE] Introduce el dato pedido
			marca = (String) entrada.readObject();

			// [SERVIDOR] Pide el dato
			salida.writeObject("Introduzca el modelo del Deportivo: ");

			// [CLIENTE] Introduce el dato pedido
			modelo = (String) entrada.readObject();
			
			// [SERVIDOR] Valida los Strings
			salida.writeObject("OK");

			// [SERVIDOR] Pide el dato
			salida.writeObject("Introduzca la capacidad del deposito del Deportivo: ");

			// [CLIENTE] Introduce el dato pedido
			deposito = (double) entrada.readObject();
			
			deportivo = new Deportivo(matricula, marca, modelo, deposito);

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Error: " + e.getMessage());
		}

		return deportivo;
	}
	
	// Pide los datos del Propietario
	public static Propietario datosPropietario(ObjectInputStream entrada, ObjectOutputStream salida) {
		Propietario propietario = null;
		String nombre, paisNacimiento;
		int telefono, edad;

		try {
			// [SERVIDOR] Pide el dato
			salida.writeObject("El nombre completo del Propietario: ");

			// [CLIENTE] Introduce el dato pedido
			nombre = (String) entrada.readObject();

			// [SERVIDOR] Pide el dato
			salida.writeObject("Introduzca el pais de nacimiento del Propietario: ");

			// [CLIENTE] Introduce el dato pedido
			paisNacimiento = (String) entrada.readObject();
			
			// [SERVIDOR] Valida los Strings
			salida.writeObject("OK");

			// [SERVIDOR] Pide el dato
			salida.writeObject("Introduzca el telefono del Propietario: ");

			// [CLIENTE] Introduce el dato pedido
			telefono = (int) entrada.readObject();

			// [SERVIDOR] Pide el dato
			salida.writeObject("Introduzca la edad del Propietario: ");

			// [CLIENTE] Introduce el dato pedido
			edad = (int) entrada.readObject();
			
			propietario = new Propietario(nombre, telefono, edad, paisNacimiento);

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Error: " + e.getMessage());
		}

		return propietario;
	}

}
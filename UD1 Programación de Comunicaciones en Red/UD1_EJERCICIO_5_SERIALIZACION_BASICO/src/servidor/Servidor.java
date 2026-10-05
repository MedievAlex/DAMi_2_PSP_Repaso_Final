package servidor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import model.Persona;

/**
Se desea desarrollar una aplicación cliente-servidor en Java que permita 
enviar y recibir objetos a través de sockets TCP.
	1. Define una clase llamada Persona que tenga los siguientes atributos:
    	• String nombre.
    	• int edad.
    	• String contraseña (este atributo no debe transmitirse al enviar 
    	el objeto).
    2. Implementa un servidor TCP que:
    	• Espere conexiones de un cliente.
    	• Reciba un objeto Persona enviado desde el cliente.
    	• Muestre por consola los datos recibidos, comprobando que uno de 
    	los atributos no ha sido transmitido.
    3. Implementa un cliente TCP que:
    	• Cree un objeto Persona con datos reales (incluyendo nombre, edad 
    	y una contraseña).
    	• Envíe dicho objeto al servidor.
    4. Verifica que, al recibir el objeto en el servidor, los atributos 
    llegan correctamente salvo el que no debe transmitirse.
    5. Si el cliente y el servidor se ejecutan en máquinas diferentes, ¿qué 
    debes tener en cuenta respecto a la clase Persona para que la transmisión 
    de objetos funcione correctamente en ambos lados de la aplicación?
    6. Ahora quieres añadir a la clase Persona un atributo de tipo Dirección. 
    La clase Dirección está definida de la siguiente forma:
    	• String calle.
    	• String ciudad.
    	• int codigoPostal.

El cliente crea una Persona con su correspondiente dirección y la envía al servidor.
Sin embargo, en tiempo de ejecución se lanza la excepción:
java.io.NotSerializableException: Direccion
Prueba y responde a las siguientes preguntas:
    1. ¿Por qué ocurre este error?
    2. ¿Qué modificación habría que hacer en la clase Direccion para que el 
    objeto Persona pueda enviarse correctamente?
    3. ¿Qué pasaría si la clase Direccion tuviera dentro otro objeto como atributo? 
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
        
        Persona persona;

        try {
        	// [SERVIDOR] Espera conexion de un cliente
            servidor = new ServerSocket(PUERTO);
            System.out.println("Esperando conexiones del cliente...");

            // [CLIENTE] Cliente conectado a Servidor
            cliente = servidor.accept();
            System.out.println("Cliente conectado.");

            // [SERVIDOR] Crea flujo de entrada y salida
            salida = new ObjectOutputStream (cliente.getOutputStream());
            entrada = new ObjectInputStream(cliente.getInputStream());
           
            // [CLIENTE] Envia los datos
            persona = (Persona) entrada.readObject();
            
            // [SERVIDOR] Muestra los datos
            System.out.println(persona.toString());

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
}

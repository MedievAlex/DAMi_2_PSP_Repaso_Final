package cliente;

import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.io.IOException;

/**
Crea un programa Cliente y un programa Servidor.
El Cliente debe de conectarse con el Servidor, y al hacerlo, el Servidor le pedirá una contraseña.
Si la contraseña es incorrecta, le indicará el siguiente mensaje:
    • “La contraseña introducida no es correcta.”
Si la contraseña es correcta, le indicará el siguiente mensaje:
    • “Bienvenido.”
    • “¿Qué desear hacer?”
    • “1) Sumar a b”
    • “2) Multiplicar a b”
El cliente indicará la operación que quiere realizar, y el Servidor le devolverá el resultado de dicha operación.
**/

public class ClienteAccionesServidor {
    private final int PUERTO = 5000;
    private final String IP = "127.0.0.1";

    public static void main(String[] args) {
    	ClienteAccionesServidor cliente = new ClienteAccionesServidor();
        cliente.iniciar();
    }

    public void iniciar() {
        Socket cliente = null;
        ObjectInputStream entrada = null;
        ObjectOutputStream salida = null;

        String mensaje, clave, opcion, numA, numB;
        boolean error;

        try {
            // [CLIENTE] Se conecta al Servidor
            cliente = new Socket(IP, PUERTO);
            System.out.println("Conexión realizada con servidor");

            // [CLIENTE] Se inician los flujos de Entrada y Salida
            salida = new ObjectOutputStream(cliente.getOutputStream());
            entrada = new ObjectInputStream(cliente.getInputStream());

            // [SERVIDOR] Se pide la contraseña
            mensaje = (String) entrada.readObject();
            System.out.println(mensaje);
            
            // [CLIENTE] Se introduce la contraseña
            clave = introducirCadena();
            salida.writeObject(clave);

            // [SERVIDOR] Se pide la Opción a elegir
            mensaje = (String) entrada.readObject();
            System.out.println(mensaje);

            do {
            	error = false;
	            // [CLIENTE] Se introduce la Opción elegida
	            opcion = introducirCadena();
	            salida.writeObject(opcion);
	            
	            // [SERVIDOR] Devuelve la confirmacion
	            error = (boolean) entrada.readObject();
            }while(error);

            // [SERVIDOR] Se pide el valor A
            mensaje = (String) entrada.readObject();

            // [CLIENTE] Se introduce el valor A
            numA = introducirCadena();
            salida.writeObject(numA);

            // [SERVIDOR] Se pide el valor B
            mensaje = (String) entrada.readObject();
            
            // [CLIENTE] Se introduce el valor B
            numB = introducirCadena();
            salida.writeObject(numB);

            // [SERVIDOR] Muestra el resultado
            mensaje = (String) entrada.readObject();
            System.out.println(mensaje);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
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
                if (cliente != null) {
                    cliente.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Fin cliente");
        }
    }

    /*********************************************************************************************************/

    //Lee una cadena(String) y la devuelve
	public static String introducirCadena() { 
		String cadena = "";
		boolean error;
		InputStreamReader entrada = new InputStreamReader(System.in);
		BufferedReader teclado = new BufferedReader(entrada);

		do{
			error = false;
			try {
				cadena = teclado.readLine();
			} catch (IOException e) {
				System.err.println("[ERROR] Error en la entrada de datos.");
				error = true;
			}
		}while (error);
		return cadena;
	}
}
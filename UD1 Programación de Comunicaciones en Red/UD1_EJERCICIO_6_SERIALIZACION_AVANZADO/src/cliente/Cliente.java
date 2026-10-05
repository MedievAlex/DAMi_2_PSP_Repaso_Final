package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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

public class Cliente {
    private final int PUERTO = 5000;
    private final String IP = "127.0.0.1";

    public static void main(String[] args) {
    	Cliente cliente = new Cliente();
        cliente.iniciar();
    }

    public void iniciar() {
        Socket cliente = null;
        ObjectInputStream entrada = null;
        ObjectOutputStream salida = null;

        String mensaje, datoTxt;
        double datoNum;

        try {
            // [CLIENTE] Se conecta al Servidor
            cliente = new Socket(IP, PUERTO);
            System.out.println("Conexión realizada con servidor");

            // [CLIENTE] Se inician los flujos de Entrada y Salida
            salida = new ObjectOutputStream(cliente.getOutputStream());
            entrada = new ObjectInputStream(cliente.getInputStream());

            // [SERVIDOR] Envia mensajes
            System.out.println((String) entrada.readObject());
            System.out.println((String) entrada.readObject());
            

            // [SERVIDOR] Pide el dato
            mensaje = (String) entrada.readObject();
            
            do {
            	// [CLIENTE] Muestra el mensaje
                System.out.print(mensaje);
                
            	// [CLIENTE] Introduce el dato pedido
                datoTxt = introducirCadena();
                salida.writeObject(datoTxt);
                
                // [SERVIDOR] Envia mensaje
                mensaje = (String) entrada.readObject();
            } while(!mensaje.equals("OK"));

            // [SERVIDOR] Pide el dato
            mensaje = (String) entrada.readObject();

            // [CLIENTE] Introduce el dato
            datoNum = leerDouble();
            salida.writeObject(datoNum);


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

    //Muestra el mensaje y luego lee el numero(float) introducido si es entre los valores
	public static double leerDouble() { 
		double num = 0;
		boolean error;

		do{
			error = false;
			try{
				num = Double.parseDouble(introducirCadena());
			}catch (NumberFormatException e){
				System.err.println("[ERROR] Valor no numerico.");
				System.out.print("Introduce de nuevo: ");
				error = true;
			}
		}while (error);
		return num;
	}	
}
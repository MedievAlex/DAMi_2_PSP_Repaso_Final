package servidor;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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

public class ServidorAccionesServidor {
    private final int PUERTO = 5000;
    private final String clave = "abc";

    public static void main(String[] args) {
    	ServidorAccionesServidor servidor = new ServidorAccionesServidor();
        servidor.iniciar();
    }

    public void iniciar() {
        ServerSocket servidor = null;
        Socket cliente = null;
        ObjectInputStream entrada = null;
        ObjectOutputStream salida = null;

        String mensaje, opcion;
        float numA, numB;

        try {
            // [SERVIDOR] Se inicializa el Servidor
            servidor = new ServerSocket(PUERTO);
            System.out.println("[SERVIDOR] Esperando conexiones del cliente...");

            // [CLIENTE] Se conecta al Servidor
            cliente = servidor.accept();
            System.out.println("[SERVIDOR] Cliente conectado");

            // [SERVIDOR] Se inician los flujos de Entrada y Salida
            salida = new ObjectOutputStream(cliente.getOutputStream());
            entrada = new ObjectInputStream(cliente.getInputStream());

            // [SERVIDOR] Se pide la contraseña
            salida.writeObject("[SERVIDOR] Introduce contraseña:");

            // [CLIENTE] Se introduce la contraseña
            mensaje = (String) entrada.readObject();

            if (mensaje.equals(clave)) {
                // [SERVIDOR] Se pide la Opción a elegir
                salida.writeObject("[SERVIDOR] Bienvenido.\nQué desear hacer?\nA) Sumar a + b\nB) Multiplicar a x b ");

                // [CLIENTE] Se introduce la Opción elegida
                opcion = verificarCadena(entrada, salida, "A", "B");

                // [SERVIDOR] Se pide el valor A
                salida.writeObject("[SERVIDOR] Introduzca el valor de A: ");

                // [CLIENTE] Se introduce el valor A
                numA = verificarFloat(entrada);

                // [SERVIDOR] Se pide el valor B
                salida.writeObject("[SERVIDOR] Introduzca el valor de B: ");

                // [CLIENTE] Se introduce el valor B
                numB = verificarFloat(entrada);

                switch(opcion){
                    case "A":
                        // [SERVIDOR] Muestra el resultado
                        salida.writeObject(String.valueOf(numA + numB));
                        break;
                    case "B":
                        // [SERVIDOR] Muestra el resultado
                        salida.writeObject(String.valueOf(numA * numB));
                        break;
                }
            } else {
                salida.writeObject("[SERVIDOR] Error: Contraseña incorrecta.");
            }
        } catch (IOException e) {
            System.out.println("[SERVIDOR] Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("[SERVIDOR] Error: " + e.getMessage());
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
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("[SERVIDOR] Fin del servicio.");
        }
    }

    /*********************************************************************************************************/

    //Lee una cadena(String) y la verifica
	public static String verificarCadena(ObjectInputStream entrada, ObjectOutputStream salida, String opcionA, String opcionB) { 
		boolean error;
        String opcion = "";
        
        do{
            error = false;
            try {
                // [CLIENTE] Se introduce la Opción elegida
                opcion = (String) entrada.readObject();

                if (!opcion.equalsIgnoreCase(opcionA) || !opcion.equalsIgnoreCase(opcionB)){
                    System.err.println("[SERVIDOR] Error: La opcion introducida no es correcta");
                    System.out.print("[SERVIDOR] Introduce "+opcionA+" o "+opcionB+": ");
                    error = true;
                    
                    // [SERVIDOR] Devuelve la confirmacion
                    salida.writeObject(error);
                }
            } catch (IOException e) {
                System.err.println("[SERVIDOR] Error: Error en la entrada de datos.");
                error = true;
            } catch (ClassNotFoundException e) {
                System.err.println("[SERVIDOR] Error: " + e.getMessage());
			}
        }while (error);

        return opcion;
	}

    //Lee un numero(float) y verifica que está en el rango
	public static float verificarFloat(ObjectInputStream entrada) { 
		boolean error;
        float num = 0;
        
         do{
            error = false;
            try{
                // [CLIENTE] Se introduce el valor A
                num = Float.parseFloat((String) entrada.readObject());
                
            }catch (NumberFormatException e){
                System.err.println("[SERVIDOR] Error: Valor no numerico.");
                System.out.print("[SERVIDOR] Introduce de nuevo: ");
                error = true;
            } catch (ClassNotFoundException e) {
                System.err.println("[SERVIDOR] Error: " + e.getMessage());
			} catch (IOException e) {
                System.err.println("[SERVIDOR] Error: " + e.getMessage());
			}
            if(num == 0){
                System.err.println("[SERVIDOR] Error: Numero inválido.");
                System.out.print("[SERVIDOR] Introduce un número que no sea 0: ");
                error = true;
            }
        }while (error);

        return num;
	}
}
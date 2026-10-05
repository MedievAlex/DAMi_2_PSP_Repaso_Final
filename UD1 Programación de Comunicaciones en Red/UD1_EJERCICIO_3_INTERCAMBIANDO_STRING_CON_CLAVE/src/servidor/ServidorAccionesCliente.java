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

public class ServidorAccionesCliente {
    private final int PUERTO = 5000;
    private final String clave = "abc";

    public static void main(String[] args) {
    	ServidorAccionesCliente servidor = new ServidorAccionesCliente();
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
                opcion = (String) entrada.readObject();

                // [SERVIDOR] Se pide el valor A
                salida.writeObject("[SERVIDOR] Introduzca el valor de A: ");

                // [CLIENTE] Se introduce el valor A
                numA = (float) entrada.readObject();

                // [SERVIDOR] Se pide el valor B
                salida.writeObject("[SERVIDOR] Introduzca el valor de B: ");

                // [CLIENTE] Se introduce el valor B
                numB = (float) entrada.readObject();

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
            System.err.println("[SERVIDOR] Error: " + e.getMessage());
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
}
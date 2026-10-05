package servidor;

import java.net.ServerSocket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
Modifica el ejercicio anterior para que el servidor no finalice 
cuando envía el mensaje al cliente, sino que se quede esperando 
al siguiente cliente.
**/

public class ServidorConWhile {
    private final int PUERTO = 5000;

    public static void main(String[] args) {
        ServidorConWhile s = new ServidorConWhile();
        s.iniciar();
    }

    public void iniciar() {
        // ServerSocket servidor = null;
        Socket cliente = null;
        ObjectInputStream entrada = null;
        ObjectOutputStream salida = null;

        try (ServerSocket servidor = new ServerSocket(PUERTO)){
        	while (true){
	            System.out.println("Esperando conexiones del cliente...");
	            
	            try{
		            cliente = servidor.accept();
		            System.out.println("Cliente conectado.");
					
		            salida = new ObjectOutputStream (cliente.getOutputStream());
		            entrada = new ObjectInputStream(cliente.getInputStream());
		           
		            salida.writeObject("Hola ¡Cliente!");

	            } catch (Exception e) {
	            	System.out.println("Error: " + e.getMessage());
	            } finally {
	            	try {	 
  				if (entrada != null)
		                    entrada.close();
		                if (salida != null)
		                    salida.close();           		
		                if (cliente != null)
		                    cliente.close();             
	            	} catch (Exception e) {
	            		System.out.println("Error: " + e.getMessage());
	            	}
	            }
        	}    
    	} catch (IOException e) {
        	e.printStackTrace();
    	}   
    }
}
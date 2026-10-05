package calculoLento;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
Revisar archivos descomprimidos del ejemplo principal.rar dado.
**/

public class PrincipalHilosCalculoLento {

    public static void main(String[] args) {
    	// Hilos extends Thread
        ThreadCalculoLento hilo1 = null;
        ThreadCalculoLento hilo2 = null;
        
        // Sin hilos, secuencial
    	doWorkOneSecond();
    	doWorkOneSecond();
       
    	// Con hilos, en paralelo
        hilo1 = new ThreadCalculoLento();
        hilo2 = new ThreadCalculoLento();

        hilo1.start();
        hilo2.start();
        
        try {
        	hilo1.join();
			hilo2.join();
		} catch (InterruptedException e) {
            System.err.println("[MAIN] Error: " + e.getMessage());
		}
    }

    public static void doWorkOneSecond() {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] input = "Trabajo pesado para ~1 segundo".getBytes();
            byte[] result = input;

            // Bucle calibrado a "orden de magnitud" para un portátil i7
            // (ajustable: 50_000_000 a 100_000_000 iteraciones suele dar ~1s)
            for (int i = 0; i < 80_000_000; i++) {
                md.update(result);
                result = md.digest();
            }

            // Usar el resultado para que el compilador no elimine el cálculo
            System.out.println("Último hash: " + (result[0] & 0xff));
        } catch (NoSuchAlgorithmException e) {
            System.err.println("[MAIN] Error: " + e.getMessage());
        }
    }
}
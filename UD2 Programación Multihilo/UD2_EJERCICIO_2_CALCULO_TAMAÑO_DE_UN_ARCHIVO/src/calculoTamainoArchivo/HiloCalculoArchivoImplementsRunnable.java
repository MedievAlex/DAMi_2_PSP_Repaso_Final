package calculoTamainoArchivo;

import java.io.FileInputStream;

import java.io.IOException;

/**
Hay que calcular el tamaño en bytes de un archivo. Para ello tenemos este método:
public static void procesarArchivo(String nombreArchivo){
	 try (FileInputStream fis = new FileInputStream(nombreArchivo)){
	 	int byteLeido;
	 	int contador = 0;
	 	System.out.println(nombreArchivo+" abierto");
	 	// Lee el archivo byte a byte
	 	while ((byteLeido = fis.read()) != -1)
	 		contador++;
	 	System.out.println("Archivo " + nombreArchivo+ " tiene " + contador + " bytes.");
	 } catch (IOException e) {
	 	System.err.println("Error al procesar el archivo: " + e.getMessage());
	 }
 }
Se trata de hacer un programa que use el método anterior (haciendo cambios sobre él) 
y aprovechando lo aprendido sobre hilos calcule el tamaño de dos ficheros.
A tener en cuenta:
    • Desde main() se crearán y lanzarán dos hilos que recibirán nombreArchivo, que 
    tendrá este formato: "C://Users//bego//Downloads//Nota simple.jpg".
    • Será main quien muestre el tamaño del archivo, no el hilo.
**/

public class HiloCalculoArchivoImplementsRunnable implements Runnable {
    private Thread thread = null;
    private String nombreArchivo;
    private int cantidadArchivo;

    public HiloCalculoArchivoImplementsRunnable(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
        //thread = new Thread(this);
        //thread.start();
    }

    public void setThread(Thread thread) {
		this.thread = thread;
	}

    public Thread getThread() {
		return thread;
	}

    public void setCantidadArchivo(int cantidad) {
		this.cantidadArchivo = cantidad;
	}

	public int getCantidadArchivo() {
		return cantidadArchivo;
	}

    @Override
    public void run() {
        procesarArchivo(nombreArchivo);
    }

    public void procesarArchivo(String nombreArchivo){ // No puede ser estático
        try (FileInputStream fis = new FileInputStream(nombreArchivo)){
            int byteLeido = 0;
            int contador = 0;

            System.out.println("[HILO Implements Runnable] Archivo " + nombreArchivo +" abierto.");

            // Lee el archivo byte a byte
            while (byteLeido != -1) {
            	byteLeido = fis.read();
            	contador++;
            }

            setCantidadArchivo(contador);
            //System.out.println("[HILO Implements Runnable] Archivo " + nombreArchivo+ " tiene " + cantidadArchivo + " bytes.");

        } catch (IOException e) {
            System.err.println("[HILO Implements Runnable: Error] " + e.getMessage());
        }
    }
}
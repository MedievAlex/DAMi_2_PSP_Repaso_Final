package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		ProcessBuilder builder = null;
		Process process = null;

		BufferedReader reader = null;
		File archivoSalida = null;

		String directory = "src/";
		String line;
		int exitCode;

		try {
			// Crear un proceso muestre mostrara la versión de Java instalada en el sistema
			builder = new ProcessBuilder("java", "-version");

			archivoSalida = new File(directory + "salida.txt");
			builder.redirectErrorStream(true); // Para que saque errores o versiones
			builder.redirectOutput(archivoSalida);

			// Iniciar el proceso
			process = builder.start();

			// Capturar la salida del proceso
			reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}

			// Esperar a que el proceso termine y obtener el código de salida
			exitCode = process.waitFor();
			System.out.println("Proceso terminado con código de salida: " + exitCode);

			if (exitCode == 0) {
				System.out.println("Programa ejecutado correctamente.");
				System.out.println("Salida almacenada en resultado.txt.");
			} else {
				System.out.println("Se ha producido un error durante la ejecucion del programa.");
			}
		} catch (IOException e) {
			System.err.println("[ERROR] " + e.getMessage());
		} catch (InterruptedException e) {
			System.err.println("[ERROR] " + e.getMessage());
		}
	}
}
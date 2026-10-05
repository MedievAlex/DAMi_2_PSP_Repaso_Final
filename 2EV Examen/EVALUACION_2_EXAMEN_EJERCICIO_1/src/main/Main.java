package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
Desarrolla una aplicación en Java que sea capaz de ejecutar un programa externo desde el
propio código y almacenar el resultado de su ejecución en un fichero de texto.
La aplicación deberá ejecutar el programa java con el argumento -version, de forma que se muestre
la versión de Java instalada en el sistema.
➔ Requisitos:
	1. El programa Java debe lanzar la ejecución de java -version. (3pto)
	2. Toda la información generada por el programa ejecutado, tanto la salida estándar como los
	posibles mensajes de error, debe guardarse en un fichero llamado resultado.txt, ubicado
	en el directorio del proyecto. (2pto)
	3. La aplicación debe esperar a que el programa externo finalice antes de terminar su
	propia ejecución. (3pto)
	4. Una vez finalizada la ejecución, el programa Java mostrará por consola un mensaje
	indicando que el proceso ha terminado y que la salida se ha almacenado
	correctamente. (1pto)
➔ Consideraciones:
	• La ejecución del programa externo debe realizarse desde Java, sin intervención manual por
	parte del usuario.
	• La solución debe gestionar adecuadamente posibles errores durante la ejecución del
	proceso. (1pto)
	• No está permitido ejecutar el programa directamente desde la línea de comandos.
	• Hay que hacer uso de la clase Java vista en clase.
➔ Ejemplo de salida esperada por consola:
	Programa ejecutado correctamente.
	Salida almacenada en resultado.txt.
**/

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
			//builder = new ProcessBuilder("java", "-version").redirectOutput(archivoSalida).redirectError(archivoSalida);
			builder = new ProcessBuilder("java", "-version");

			archivoSalida = new File(directory + "resultado.txt");
			builder.redirectErrorStream(true); // Para que saque errores o versiones
			builder.redirectOutput(archivoSalida);
			builder.redirectError(archivoSalida);

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

			if (exitCode == 1) {
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

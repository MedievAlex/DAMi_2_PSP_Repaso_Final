package killOrOpenProcess;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import java.io.IOException;

/**
Mata un proceso en caso de que exista, sino, lo inicia.
**/

public class MainKillOrOpenProcess {
    
    public static void main(String[] args) {
    	// Nombre del proceso a terminar o iniciar
    	//String processName = "Notepad.exe";
    	String processName = "taskmgr.exe";
    	
    	System.out.println("Directorio de Inicio: " + System.getProperty("user.home"));
	    System.out.println("Directorio de Trabajo: " + System.getProperty("user.dir"));
	    System.out.println("Nombre de Usuario: " + System.getProperty("user.name"));
	    
	    searchProcess(processName);
    }

    // Método para buscar si el proceso existe
    public static void searchProcess(String processName) {
    	List<String> pids;
        
        try {
            // Obtenemos el listado de procesos
            pids = getProcessIds(processName);

            if (pids.isEmpty()) {
                System.out.println("No se encontraron procesos con el nombre: " + processName);
                startProcess(processName);
            } else {
                // Finalizamos los procesos con los PIDs obtenidos
                for (String pid : pids) {
                    terminateProcess(pid);
                    System.out.println("Proceso con PID " + pid + " terminado.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para obtener los PIDs de los procesos con el nombre dado
    private static List<String> getProcessIds(String processName) throws Exception {
        ProcessBuilder builder;
        Process process;
        BufferedReader reader;
        String line;
    	List<String> pids = new ArrayList<>();

        // Ejecutamos el comando tasklist para listar procesos (Windows)
        builder = new ProcessBuilder("tasklist.exe", "/fo", "csv", "/nh");
        process = builder.start();

        // Leemos la salida del comando
        reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        
        while ((line = reader.readLine()) != null) {
            // Filtramos las líneas que contienen el nombre del proceso
            if (line.contains(processName)) {
                // El PID es el segundo campo en la salida de tasklist (CSV)
                String[] fields = line.split("\",\"");
                if (fields.length > 1) {
                    String pid = fields[1].replaceAll("\"", "");
                    pids.add(pid);
                }
            }
        }
        return pids;
    }
    
    public static void startProcess(String processName) {
    	try {
            /* 
            Definir el comando y sus argumentos por separado:
            "cmd.exe" es el ejecutable
            "/c" ejecuta el comando siguiente y termina
            "start" abre una nueva ventana de consola
    	    */ 
            // String[] command = {"cmd.exe", "/c", "start"}; 
            String[] command = {processName}; 
            
            ProcessBuilder builder = new ProcessBuilder(command);
            
            builder.start();
            
            System.out.println(processName + " iniciado correctamente.");
            
        } catch (IOException e) {
			System.err.println("[ERROR] " + e.getMessage());
        }
    }

    // Método para terminar un proceso basado en su PID
    private static void terminateProcess(String pid) throws Exception {
        // Ejecutamos el comando taskkill para finalizar el proceso (Windows)
        ProcessBuilder builder = new ProcessBuilder("taskkill", "/PID", pid, "/F");
        builder.start();
    }
}
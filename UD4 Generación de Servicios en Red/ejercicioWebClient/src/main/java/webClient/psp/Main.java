package webClient.psp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import webClient.psp.client.AlumnoClient;
import webClient.psp.model.Alumno;

import java.util.List;

@SpringBootApplication
public class Main implements CommandLineRunner {
    
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
    
    @Override
    public void run(String... args) {
        AlumnoClient client = new AlumnoClient();
        
        System.out.println("=== CLIENTE ALUMNOS ===\n");
        
        System.out.println("1. Todos los alumnos:");
        List<Alumno> alumnos = client.getAllAlumnos();
        alumnos.forEach(System.out::println);
        
        System.out.println("\n2. Total de alumnos: " + client.getAlumnosCount());
        
        System.out.println("\n3. Alumnos de DAM1:");
        List<Alumno> dam1 = client.getAlumnosByCurso("DAM1");
        dam1.forEach(System.out::println);
        
        System.out.println("\n4. Alumno con ID 1:");
        Alumno alumno = client.getAlumnoById(1);
        if (alumno != null) {
            System.out.println(alumno);
        }
        
        System.out.println("\n5. Creando nuevo alumno...");
        Alumno nuevo = new Alumno();
        nuevo.setId(99);
        nuevo.setNombre("Pedro");
        nuevo.setCurso("DAM2");
        
        try {
            Alumno creado = client.createAlumno(nuevo);
            System.out.println("Creado: " + creado);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        System.out.println("\n6. Actualizando alumno...");
        Alumno actualizado = new Alumno(1, "Ane (Modificado)", "DAM1");
        Alumno resultado = client.updateAlumno(1, actualizado);
        if (resultado != null) {
            System.out.println("Actualizado: " + resultado);
        }
        
        System.out.println("\n7. Eliminando alumno con ID 2...");
        boolean eliminado = client.deleteAlumno(2);
        System.out.println("Eliminado: " + eliminado);
        
        System.out.println("\n=== FIN DEL PROGRAMA ===");
    }
}
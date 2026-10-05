package jugadoresLimitados;

import java.util.ArrayList;
import java.util.Scanner;

import model.Cofre;

/**
Desarrolla un juego multijugador simple donde varios jugadores 
intentan recoger monedas de un cofre compartido. Cada jugador 
es representado por un hilo, y el cofre tiene un número limitado 
de monedas (10).
Cuando un jugador recoge una moneda, se debe asegurar que otro 
ugador no pueda tomar la misma moneda al mismo tiempo. Para 
simular el proceso de recoger moneda puedes usar el siguiente 
código:
	long startTime = System.nanoTime();
	while (System.nanoTime() - startTime < 500_000_000) {
		double x = Math.sqrt(Math.random());
	}
Cuando las monedas se acaben, se mostrará un mensaje por consola 
indicándolo y finalizará el programa.
**/

public class MainCofreImplementsRunnable {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cofre cofre = new Cofre();
        ArrayList<HiloJugadorImplementsRunnable> jugadores = new ArrayList<>();
        HiloJugadorImplementsRunnable hiloJugador = null;

        int cntJugadores = 2;
        int restantes, recogidas;
        boolean vacio, error;

        // Pide la cantidad de Jugadores que participarán
        System.out.println("[MAIN] Cantidad de Jugadores: ");
		do{
			error = false;
			try{
				cntJugadores = Integer.parseInt(scanner.nextLine());
			}catch (NumberFormatException e){
				System.err.println("[ERROR] Valor no numerico.");
				System.out.println("Introduce de nuevo: ");
				error = true;
			}
		}while (error);
		
		scanner.close();

        // Crea tantos hilos como cantidad de Jugadores
        do{
            hiloJugador = new HiloJugadorImplementsRunnable(cofre);
			jugadores.add(hiloJugador);

            System.out.println("[MAIN] Jugador " + jugadores.size() + " preparado.");
		}while (jugadores.size() < cntJugadores);

        vacio = cofre.getVacio();

        do{
            for (int i = 1; !vacio ; i++) {
                hiloJugador = jugadores.get(i - 1);
                hiloJugador.recogerMonedas();
                
                try {
                    hiloJugador.getThread().join();

                    recogidas = hiloJugador.getRecogidas();
                    restantes = cofre.getMonedas();

                    System.out.println("[HILO Extends Thread] ¡Jugador " + i + " recogió " + recogidas + " moneda/s! Quedan " + restantes + " monedas restantres.");
                    
                    vacio = cofre.getVacio();

                } catch (InterruptedException e) {
                    System.err.println("[MAIN: Error] " + e.getMessage());
                }

                if(i == jugadores.size()){
                    i = 0;
                }
            }
        }while(!vacio);
        
        System.out.println("[MAIN] Fin del juego.");
    }
}
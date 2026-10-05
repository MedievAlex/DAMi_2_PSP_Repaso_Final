package jugadoresIlimitados;

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

public class MainCofreExtendsThread {

    public static void main(String[] args) {
        Cofre cofre = new Cofre();
        HiloJugadorExtendsThread hiloJugador = null;

        int jugador = 0;
        int restantes, recogidas;
        boolean vacio = cofre.getVacio();

        do{
            jugador ++;

            hiloJugador = new HiloJugadorExtendsThread(cofre);

            try {
                hiloJugador.join();

                recogidas = hiloJugador.getRecogidas();
                restantes = cofre.getMonedas();

                System.out.println("[HILO Extends Thread] ¡Jugador " + jugador + " recogió " + recogidas + " moneda/s! Quedan " + restantes + " monedas restantres.");

                vacio = cofre.getVacio();

            } catch (InterruptedException e) {
                System.err.println("[MAIN: Error] " + e.getMessage());
            }

        }while(!vacio);
        
        System.out.println("[MAIN] Fin del juego.");
    }
}
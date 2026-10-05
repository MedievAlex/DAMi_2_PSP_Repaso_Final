package jugadoresLimitados;

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

public class HiloJugadorExtendsThread extends Thread {
    private Cofre cofre;
    private int monedas;
    private int recogidas;

    public HiloJugadorExtendsThread(Cofre cofre) {
        this.cofre = cofre;
        this.monedas = 0;
        this.recogidas = 0;
        this.start();
    }

    public void setMonedas(int cantidad) {
		this.monedas = cantidad;
	}

	public int getMonedas() {
		return monedas;
	}
    public void setRecogidas(int cantidad) {
		this.recogidas = cantidad;
	}

	public int getRecogidas() {
		return recogidas;
	}

    @Override
    public void run() {
        //recogerMonedas();
    }

    public void recogerMonedas() {
    	this.recogidas = cofre.recogerMonedas();
    	this.monedas =  this.monedas + this.recogidas;
	}
}
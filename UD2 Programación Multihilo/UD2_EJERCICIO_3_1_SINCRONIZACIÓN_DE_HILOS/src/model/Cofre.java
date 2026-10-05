package model;

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

public class Cofre {
    private int monedas;
    private boolean vacio;

    public Cofre() {
        this.monedas = 10;
        this.vacio = false;
        System.out.println("[COFRE] ¡Recoged mis " + getMonedas() + " monedas!");
    }

    public void setMonedas(int cantidad) {
		this.monedas = cantidad;
	}

	public int getMonedas() {
		return monedas;
	}

    public void setVacio(boolean vacio) {
		this.vacio = vacio;
	}

	public boolean getVacio() {
		return vacio;
	}

    public synchronized int recogerMonedas() { // No puede ser estático
        int recogidas = 0;
        
        if(!vacio){
            recogidas = (int) (Math.floor(Math.random()*(getMonedas() + 1)));

            setMonedas(getMonedas() - recogidas);
            verificarMonedas();
        }

        return recogidas;
    }

    public void verificarMonedas(){ // No puede ser estático
        if(monedas == 0){
            vacio = true;
        }
    }
}
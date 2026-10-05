/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import java.awt.Color;

import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

/**
Elabora un programa que simule una carrera de caballos con barras de progreso
Cada caballo se representará con una barra de progreso, que avanzará de forma gradual hasta
alcanzar la meta.
La carrera comenzará al pulsar un botón, y los caballos irán avanzando poco a poco hasta que uno
de ellos llegue al final.
Cuando uno de los caballos alcance la meta, el programa mostrará un mensaje indicando cuál ha
sido el ganador y se detendrá el avance de las barras.
La aplicación debe cumplir los siguientes requisitos:
1. Ser ThreadSafe
2. La ventana principal debe contener:
	• Varias barras de progreso (JProgressBar) con los nombres de los caballos.
	• Un botón “Iniciar carrera”.
	• Un espacio o etiqueta para mostrar el nombre del ganador.
3. Al pulsar el botón, las barras deben comenzar a avanzar hasta que una de ellas llegue al
final. El avance será aleatorio y por tanto diferente entre una barra y otra.
4. Cuando una de las barras se complete al 100% o, lo que es lo mismo, llegue al final, el resto
de las barras deben detenerse.
5. El programa debe identificar cuál llegó primero y mostrarlo como ganador.
6. La sincronización debe ser de método, tal y como lo hemos visto en clase.

Cómo usar una JProgressBar
	Una barra de progreso sirve para representar visualmente un porcentaje de avance entre un mínimo
	y un máximo.
	Por ejemplo:
		JProgressBar barra = new JProgressBar(0, 100); // mínimo 0, máximo 100
		barra.setValue(0); // valor inicial
		barra.setStringPainted(true); // muestra el número en la barra
		// Simulación de avance
		for (int i = 0; i <= 100; i += 10) {
			barra.setValue(i); // actualiza el valor
			Thread.sleep(200); // pausa breve para ver el efecto
		}
	Cada llamada a setValue(int) actualiza la posición de la barra, mostrando el progreso en pantalla.
	Para que cada caballo avance de forma aleatoria y desincronizada, haciendo que la carrera sea
	impredecible podéis utilizar la siguiente formula:
		//Avance aleatorio de la barra
		final int nuevoValor = barra.getValue() + (int)(Math.random()*10);
		barra.setValue(Math.min(nuevoValor, 100));
		//Para que la carrera sea impredecible
		Thread.sleep((int)(Math.random()*150 + 50));
	Utilizar la clase CarreraCaballosGUI2 que se proporciona en los recursos del examen como base
	para la resolución del problema.
**/

public class ThreadCaballo implements Runnable {
	private Thread thread = null;
	private JProgressBar barra;

	public ThreadCaballo(JProgressBar barra) {
		this.barra = barra;
		thread = new Thread(this);
		thread.start();
	}
	public Thread getThread() {
		return thread;
	}
	
	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public JProgressBar getBarra() {
		return barra;
	}

	public void setBarra(JProgressBar barra) {
		this.barra = barra;
	}

	@Override
	public void run() {
		barra.setValue(0); // valor inicial
		barra.setStringPainted(true); // muestra el número en la barra

		System.out.println("[Horse start]");
		
		do {
			try {
				// Simulación de avance
				for (int i = 0; i <= 100; i += 10) {
					SwingUtilities.invokeLater(() -> {
						int nuevoValor = nuevoValor = barra.getValue() + (int)(Math.random()*10);
						barra.setValue(Math.min(nuevoValor, 100));
					}); // actualiza el valor
					
					Thread.sleep((int)(Math.random()*150 + 50));
				}
			} catch (InterruptedException e) {
				System.err.println("[ERROR] " + e.getMessage());
			}
		}while(barra.getValue() < 100);
	}
}
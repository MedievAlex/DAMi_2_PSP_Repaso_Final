package view;

import javax.swing.*;

import java.awt.EventQueue;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import thread.ThreadCaballo;

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

public class CarreraCaballosGUI2 {
    private static JFrame frame;
    private static JProgressBar[] barras;
    private static ThreadCaballo[] caballos;
    private static JButton btnIniciar;
    private static JLabel lblGanador;
    private static ThreadCaballo caballo;
    private boolean carreraIniciada = false;

    public static void main(String[] args) {
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CarreraCaballosGUI2 window = new CarreraCaballosGUI2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }

    public CarreraCaballosGUI2() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Carrera de Caballos 🐎");
        frame.setBounds(100, 100, 500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        barras = new JProgressBar[5];
        for (int i = 0; i < barras.length; i++) {
            barras[i] = new JProgressBar(0, 100);
            barras[i].setBounds(50, 30 + (i * 30), 400, 25);
            frame.getContentPane().add(barras[i]);
            barras[i].setStringPainted(true);
            barras[i].setString("Caballo " + (i + 1));
        }

        btnIniciar = new JButton("Iniciar carrera");
        btnIniciar.setBounds(180, 200, 150, 30);
        frame.getContentPane().add(btnIniciar);

        lblGanador = new JLabel("");
        lblGanador.setHorizontalAlignment(SwingConstants.CENTER);
        lblGanador.setBounds(50, 240, 400, 20);
        frame.getContentPane().add(lblGanador);

        btnIniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> btnIniciar.setEnabled(false));
            	carreraIniciada = true;
            	caballos = new ThreadCaballo[barras.length];
            	
            	for (int i = 0; i < barras.length; i++) {
            		caballos[i] = new ThreadCaballo(barras[i]);
                }
            }
        });

        terminarCarrera(1);
        
    }

    private synchronized void terminarCarrera(int numeroCaballo) {
        if (carreraIniciada) {
            carreraIniciada = false;
            
            lblGanador.setText("El ganador es el Caballo N-" + numeroCaballo);
            
            for (ThreadCaballo cab : caballos) {
                cab.getThread().interrupt();
            }
            
            SwingUtilities.invokeLater(() -> btnIniciar.setEnabled(true));
        }
    }
}
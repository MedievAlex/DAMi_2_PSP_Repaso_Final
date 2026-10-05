package view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import thread.ThreadContador;

public class ContadoresGUI extends JFrame {
	private JLabel contador1Label;
	private JLabel contador2Label;
	private JLabel estadoLabel;
	private JButton iniciarButton;
	private ThreadContador hiloContador1, hiloContador2;
	private boolean terminado = false;

	public ContadoresGUI() {
		setTitle("Contadores Concurrentes");
		setSize(400, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Panel principal
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 1, 5, 5));

		// Etiquetas para los contadores
		contador1Label = new JLabel("Contador 1: 0");
		contador2Label = new JLabel("Contador 2: 0");
		estadoLabel = new JLabel("Estado: Esperando...");
		iniciarButton = new JButton("Iniciar");

		// Añadir al panel
		panel.add(contador1Label);
		panel.add(contador2Label);
		panel.add(estadoLabel);
		panel.add(iniciarButton);

		add(panel);

		iniciarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				empezar();
			}
		});
	}

	// Getters para que los alumnos puedan actualizar desde los hilos
	public JLabel getContador1Label() {
		return contador1Label;
	}

	public JLabel getContador2Label() {
		return contador2Label;
	}

	public JLabel getEstadoLabel() {
		return estadoLabel;
	}

	public JButton getIniciarButton() {
		return iniciarButton;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			ContadoresGUI gui = new ContadoresGUI();
			gui.setVisible(true);
		});
	}

	public void empezar() {
		terminado = false;

		estadoLabel.setText("");

		hiloContador1 = new ThreadContador(contador1Label, this, 1);
		hiloContador2 = new ThreadContador(contador2Label, this, 2);

		hiloContador1.start();
		hiloContador2.start();
	}

	public synchronized void terminar(int id) {
		if (!terminado) {
			terminado = true;
			estadoLabel.setText("¡Contador " + id + " ha sido el primero en finalizar!");
		}
	}

	public synchronized boolean getTerminado() {
		return terminado;
	}
}
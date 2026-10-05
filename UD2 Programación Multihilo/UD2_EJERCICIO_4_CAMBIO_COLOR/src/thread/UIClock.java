package thread;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;

import java.awt.FlowLayout;

import javax.swing.JButton;

import java.awt.Dimension;

import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
Tenemos 2 botones que gestionan el cronómetro. Cada segundo se debe incrementar un segundo lo que 
se muestra en la etiqueta, teniendo en cuenta que cuando llegamos a 60 segundos hay que incrementar 
un minuto, y si además llegamos a 60 minutos, se volverá a iniciar todo a 0. El botón Start pone 
en marcha el hilo. El botón Stop para la ejecución del hilo. Una vez parado el cronómetro, si se 
vuelve a pulsar el botón Iniciar, se comienza a contar desde 00:00.

Se pide modificar el color de las dos etiquetas (JLabel) asociadas a las flechas aleatoriamente de 
forma indefinida.
**/

public class UIClock extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JLabel labelClock;
	private JButton btnStart;
	private JButton btnStop;

	private JLabel labelFlechaIz;
	private JLabel labelFlechaDer;
	private ThreadClock threadClock;
    private ThreadColor threadColor;

    // Launch the aplication
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIClock frame = new UIClock();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Creates the frame
	public UIClock() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 141);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel2 = new JPanel();
		contentPane.add(panel2, BorderLayout.SOUTH);
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnStart = new JButton("Start");
		panel2.add(btnStart);

		btnStop = new JButton("Stop");
		btnStop.setEnabled(false);
		panel2.add(btnStop);

		JPanel panel1 = new JPanel();
		contentPane.add(panel1, BorderLayout.NORTH);
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		labelFlechaIz = new JLabel("<<");
		labelFlechaIz.setFont(new Font("Tahoma", Font.BOLD, 30));
		panel1.add(labelFlechaIz);

		labelClock = new JLabel("00:00");
		labelClock.setFont(new Font("Tahoma", Font.PLAIN, 30));
		labelClock.setHorizontalAlignment(SwingConstants.CENTER);
		labelClock.setPreferredSize(new Dimension(100, 50));
		panel1.add(labelClock);

		labelFlechaDer = new JLabel(">>");
		labelFlechaDer.setFont(new Font("Tahoma", Font.BOLD, 30));
		panel1.add(labelFlechaDer);

		// Action listeners
		btnStart.addActionListener(this);	
		btnStop.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		JButton sourceButton = (JButton) e.getSource();
		if (sourceButton == btnStart) {
			this.start();
		} else if (sourceButton == btnStop) {
			this.stop();
		} 
	}

	private void start() {
		threadClock  = new ThreadClock(labelClock, "Counter clock");
        threadColor  = new ThreadColor(labelFlechaIz, labelFlechaDer, "Label color");

		threadClock.start();
        threadColor.start();
					
		btnStart.setEnabled(false);
		btnStop.setEnabled(true);
	}
	
	private void stop() {
		threadClock.stop();
        threadColor.stop();
		threadClock.initialize();
		btnStart.setEnabled(true);
		btnStop.setEnabled(false);
	}
}
package thread;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.util.Random;

/**
Tenemos 2 botones que gestionan el cronómetro. Cada segundo se debe incrementar un segundo lo que 
se muestra en la etiqueta, teniendo en cuenta que cuando llegamos a 60 segundos hay que incrementar 
un minuto, y si además llegamos a 60 minutos, se volverá a iniciar todo a 0. El botón Start pone 
en marcha el hilo. El botón Stop para la ejecución del hilo. Una vez parado el cronómetro, si se 
vuelve a pulsar el botón Iniciar, se comienza a contar desde 00:00.

Se pide modificar el color de las dos etiquetas (JLabel) asociadas a las flechas aleatoriamente de 
forma indefinida.
**/

public class ThreadColor implements Runnable {
	private JLabel labelIz;
	private JLabel labelDer;
	private int pause = 1;
	public Thread threadColor;
	private Random random = new Random();

	public ThreadColor(JLabel labelIz, JLabel labelDer, String name) {
		this.labelIz = labelIz;
		this.labelDer = labelDer;
		initialize();
	}

	public void initialize() {
		threadColor = new Thread(this, "Color");
	}

	public void start() {
		threadColor.start();
	}

	@Override
	public void run() {
		try {
			while (pause != 3) {
				SwingUtilities.invokeLater(() -> {
					labelIz.setForeground(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
					labelDer.setForeground(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
				});

				Thread.sleep(1000 + random.nextInt(3000));
			}
		} catch (InterruptedException e) {
			System.out.println("ThreadColor interrumpido: " + e.getMessage());
		}
	}

	public void stop() {
		this.pause = 3;
	}
}

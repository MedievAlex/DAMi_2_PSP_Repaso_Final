package thread;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
Tenemos 2 botones que gestionan el cronómetro. Cada segundo se debe incrementar un segundo lo que 
se muestra en la etiqueta, teniendo en cuenta que cuando llegamos a 60 segundos hay que incrementar 
un minuto, y si además llegamos a 60 minutos, se volverá a iniciar todo a 0. El botón Start pone 
en marcha el hilo. El botón Stop para la ejecución del hilo. Una vez parado el cronómetro, si se 
vuelve a pulsar el botón Iniciar, se comienza a contar desde 00:00.

Se pide modificar el color de las dos etiquetas (JLabel) asociadas a las flechas aleatoriamente de 
forma indefinida.
**/

public class ThreadClock implements Runnable {
	private JLabel label;
	private int min;
	private int seg;
	private int pause = 1;
	public Thread threadClock;

	public ThreadClock(JLabel label, String name) {
		this.label = label;
		initialize();
	}

	public void initialize() {
		seg = 0;
		min = 0;
		this.display();
	}

	public void run() {
		try {
			while (pause != 3) {
				if (pause == 1) {
					Thread.sleep(1000);
					this.increment();
					this.display();
				}

			}
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}

	public void start() {
		System.out.println("Starting ");
		threadClock = new Thread(this, "Clock");
		threadClock.start();
	}

	public void stop() {
		this.pause = 3;
	}

	private void display() {
		String strSeg = Integer.toString(seg);
		String strMin = Integer.toString(min);

		if (strSeg.length() == 1)
			strSeg = "0" + strSeg;
		if (strMin.length() == 1)
			strMin = "0" + strMin;
		String texto = strMin + ":" + strSeg;

		SwingUtilities.invokeLater(() -> label.setText(texto));
	}

	private void increment() {
		if (pause != 3) {
			seg++;
			if (seg == 60) {
				seg = 0;
				min++;
				if (min == 60) {
					min = 0;
				}
			}
		}
	}
}

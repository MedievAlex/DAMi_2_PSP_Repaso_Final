package thread;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import view.ContadoresGUI;

public class ThreadContador extends Thread {
	private JLabel contador;
	private ContadoresGUI ventana;
	private int id;
	int valor = 0;

	public ThreadContador(JLabel contador, ContadoresGUI ventana, int id) {
		this.contador = contador;
		this.ventana = ventana;
		this.id = id;
		this.valor = 0;
	}

	@Override
	public void run() {

		while (true) {
			valor += (int) (Math.random() * 5);
			valor = Math.min(valor, 100);

			SwingUtilities.invokeLater(() -> {
				contador.setText("" + valor);
			});

			if (valor == 100 || ventana.getTerminado()) {
				break;
			}

			try {
				ThreadContador.sleep((int) (Math.random() * 150 + 50));
			} catch (InterruptedException ex) {
				System.out.println(ex.getMessage());
			}
		}

		ventana.terminar(id);
	}
}

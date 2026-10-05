import javax.swing.*;
import java.awt.*;

public class ContadoresGUI extends JFrame {

    private JLabel contador1Label;
    private JLabel contador2Label;
    private JLabel estadoLabel;
    private JButton iniciarButton;

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
}

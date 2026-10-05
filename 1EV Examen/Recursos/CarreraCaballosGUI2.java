import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.*;

public class CarreraCaballosGUI2 {

    private JFrame frame;
    private JProgressBar[] barras;
    private JButton btnIniciar;
    private JLabel lblGanador;

    public static void main(String[] args) {
         CarreraCaballosGUI2 window = new CarreraCaballosGUI2();
         window.frame.setVisible(true);
                
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
                
            }
        });
    }
}

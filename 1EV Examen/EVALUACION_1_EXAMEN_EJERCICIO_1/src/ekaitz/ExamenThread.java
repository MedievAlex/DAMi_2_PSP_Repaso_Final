package ekaitz;

import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class ExamenThread extends Thread
{
    private JProgressBar barra;
    private CarreraCaballosGUI2 ventana;
    private int id;
    
    public ExamenThread(JProgressBar barra, CarreraCaballosGUI2 ventana, int id)
    {
        this.barra = barra;
        this.ventana = ventana;
        this.id = id;
    }

	@Override
    public void run()
    {
        barra.setValue(0);
        barra.setStringPainted(true);
        
        while(true)
        {
            final int nuevoValor = barra.getValue() + (int)(Math.random()*10);
            int numero = Math.min(nuevoValor, 100);
            
            SwingUtilities.invokeLater(() -> {
                barra.setValue(numero);
            });
            
            if (numero == 100 || ventana.getTerminado())
            {
                break;
            }

            try
            {
                Thread.sleep((int)(Math.random()*150+50));
            } catch (InterruptedException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
        
        ventana.terminar(id);
    }
}

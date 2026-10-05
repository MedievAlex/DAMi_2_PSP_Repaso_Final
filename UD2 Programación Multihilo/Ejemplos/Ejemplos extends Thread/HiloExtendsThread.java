public class HiloExtendsThread extends Thread{
      private String name;
    
    // Constructores
    public HiloExtendsThread() { // El hilo se ejecuta nada más crearlo
        this.name = "hilo independiente";
        this.start();
    }

    public HiloExtendsThread(int num) { // El hilo tiene que ser iniciado a mano
        this.name = "hilo " + num;
    }
    
    // Metodos
    public void run(){
        System.out.println("[HILO Extends Thread] ¡Hola, soy el " + name + " y voy a contar hasta 10!");
        for (int i = 1; i < 11; i++){
            System.out.println("[HILO Extends Thread] " + i);
        }
    }
}
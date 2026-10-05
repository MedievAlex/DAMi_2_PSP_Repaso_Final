public class HiloImplementsRunnable implements Runnable{
      private Thread thread = null;
      private String name;
    
    // Constructores
    public HiloImplementsRunnable() { // El hilo se ejecuta nada más crearlo
        this.name = "hilo independiente";
        thread = new Thread(this);
        thread.start();
    }

    public HiloImplementsRunnable(int num) { // El hilo tiene que ser iniciado a mano
        this.name = "hilo " + num;
    }

    // Getter-Setter
    public void setThread(Thread thread) {
		this.thread = thread;
	}

    public Thread getThread() {
		return thread;
	}
    
    // Metodos
    public void run(){
        System.out.println("[HILO Implements Runnable] ¡Hola, soy el " + name + " y voy a contar hasta 10!");
        for (int i = 1; i < 11; i++){
            System.out.println("[HILO Implements Runnable] " + i);
        }
    }
}
class MainImplementsRunnable {
    public static void main(String[] args) {
        HiloImplementsRunnable hiloImplementsRunnable = null;
        Thread thread = null;
        int count = 1;
        
        System.out.println("[MAIN] Iniciaremos los hilos con implements Runnable.");
        
        hiloImplementsRunnable = new HiloImplementsRunnable();
        System.out.println("[MAIN] ¡Hola hilo Independiente!");

        do{
            hiloImplementsRunnable = new HiloImplementsRunnable(count);

            thread = new Thread(hiloImplementsRunnable);
            hiloImplementsRunnable.setThread(thread);

            hiloImplementsRunnable.getThread().start();
            thread.start();
            
            System.out.println("[MAIN] ¡Hola hilo " + count + "!");

            count ++;
        }while(count < 3);
        
        try { // Tiene que estar dentro de un Try-Catch
            // thread.sleep(2000); // Espera los segundos indicados
            thread.join(); // Espera a que termine el hilo para continuar
            // thread.wait(); // [synchronized] Espera indefinidamente hasta despertarlo (notify)
            // thread.notify(); // [synchronized] Activa el hilo en espera (wait)
        } catch (InterruptedException e) { 
            System.err.println("[Error] " + e.getMessage());
        }

        System.out.println("[MAIN] Fin del programa.");
    }
}
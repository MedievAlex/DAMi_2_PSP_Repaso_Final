class MainExtendsThread {
    public static void main(String[] args) {
        HiloExtendsThread hiloExtendsThread = null;
        int count = 1;
        
        System.out.println("[MAIN] Iniciaremos los hilos con extends Thread.");
        
        hiloExtendsThread = new HiloExtendsThread();
        System.out.println("[MAIN] ¡Hola hilo Independiente!");

        do{
            hiloExtendsThread = new HiloExtendsThread(count);
            hiloExtendsThread.start();
            System.out.println("[MAIN] ¡Hola hilo " + count + "!");

            count ++;
        }while(count < 3);
                
        try { // Tiene que estar dentro de un Try-Catch
            // hiloExtendsThread.sleep(2000); // Espera los segundos indicados
            hiloExtendsThread.join(); // Espera a que termine el hilo para continuar
            // hiloExtendsThread.wait(); // [synchronized] Espera indefinidamente hasta despertarlo (notify)
            // hiloExtendsThread.notify(); // [synchronized] Activa el hilo en espera (wait)
        } catch (InterruptedException e) { 
            System.err.println("[Error] " + e.getMessage());
        }
        
        System.out.println("[MAIN] Fin del programa.");
    }
}
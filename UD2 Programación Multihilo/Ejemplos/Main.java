class Main {
    public static void main(String[] args) {
        HiloExtendsThread hiloExtendsThread = null;
        HiloImplementsRunnable hiloImplementsRunnable = null;
        Thread thread = null;
        
        int count = 1;
        String type;

        //type = "Thread";
        type = "Runnable";
        
        switch(type){
            case "Thread":
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
                
                break;
            case "Runnable":
                System.out.println("[MAIN] Iniciaremos los hilos con implements Runnable.");

                hiloImplementsRunnable = new HiloImplementsRunnable();
                System.out.println("[MAIN] ¡Hola hilo Independiente!");
        
                do{
                    hiloImplementsRunnable = new HiloImplementsRunnable(count);
                    thread = new Thread(hiloImplementsRunnable);
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
                
                break;    
        }
        
        System.out.println("[MAIN] Fin del programa.");
    }
}
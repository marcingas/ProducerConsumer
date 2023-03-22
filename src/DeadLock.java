public class DeadLock {
    public static Object lock1 = new Object();
    public static Object lock2 = new Object();

    public static void main(String[] args) {
        new Thread1().start();
        new Thread2().start();

    }
    private static class Thread1 extends Thread{
        public void run(){
            //prolbem with deadLock will occur when changed lock1-> lock2 here and:
           synchronized (lock1){
                System.out.println("Thread 1: Has lock1");
                try{
                    Thread.sleep(100);

                }catch (InterruptedException e){
                }
                System.out.println("Thread 1: I'm waiting for lock2");
               //prolbem with deadLock will occur when changed lock2-> lock1 here
                synchronized (lock2){
                    System.out.println("THread 1: Has lock1 and lock2");
                }
                System.out.println("Thread 1: released lock2");
            }
            System.out.println("Thread 1: released lock1. Exiting...");
        }
    }
    private static class Thread2 extends Thread{
        public void run(){
            synchronized (lock1){
                System.out.println("Thread 2: Has lock1");
                try{
                    Thread.sleep(100);

                }catch (InterruptedException e){
                }
                System.out.println("Thread 2: I'm waiting for lock2");
                synchronized (lock2){
                    System.out.println("THread 2: Has lock1 and lock2");
                }
                System.out.println("Thread 2: released lock2");
            }
            System.out.println("Thread 2: released lock1. Exiting...");
        }
    }
}

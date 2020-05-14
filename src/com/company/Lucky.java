package com.company;

public class Lucky {
    static final int THREADS = 3;
    static final int MAX = 999999;
    static int count = 0;

    static Object lockObject = new Object();

    static class LuckyThread extends Thread {
        int id;


        public LuckyThread(int id){
            this.id = id;
        }
        @Override
        public void run() {  //за запуск ран отвечает операц система  (нужна очередь запуска потоков) (блокировка, ограничить доступ к участку )
                                                                 // (synch - участок кода доступный одному треду)
            for (int i = 0; i < MAX; i++) {
                if (i % THREADS == id)
                    if ((i % 10) + (i / 10) % 10 + (i / 100) % 10 == (i / 1000)
                            % 10 + (i / 10000) % 10 + (i / 100000) % 10) {
                        System.out.println(i + " " + id);
                        synchronized (lockObject) {
                            count++;
                        }
                    }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new LuckyThread(0);
        Thread t2 = new LuckyThread(1);
        Thread t3 = new LuckyThread(2);
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("Total: " + count + "  ");
    }
}

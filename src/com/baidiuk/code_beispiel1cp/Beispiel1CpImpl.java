package com.baidiuk.code_beispiel1cp;/* 051030-1 Programmiersprachen und -konzepte WS 2017/18
/* 051030-1 Programmiersprachen und -konzepte WS 2017/18
/**
 * @author Baidiuk Oleh
 * Matrikelnummer: 01468396
 */

import java.util.List;
import java.util.ArrayList;


// ------------------------------------------------------------------------
public class Beispiel1CpImpl implements Beispiel1Cp {
// ------------------------------------------------------------------------

    public Thread setup(Clock clock, Object obj, List<Thread> threads) {


        // ****************************************
        Thread mythread = new Thread(() -> {
            // run-Methode:

            System.out.println("*** TIMED_WAITING: ***");
            clock.continueAt(0.75);
            System.out.println("-> mythread: sleep");
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
            }
            System.out.println("*** BLOCKED: ***");
            // ...

            clock.continueAt(1.75);
            synchronized (obj) {
            }

            System.out.println("*** WAITING: ***");
            // ...
            clock.continueAt(3.75);
            synchronized (obj) {
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("*** WAITING - BLOCKED: ***");
            // ...
            System.out.println("-> mythread: wait");
            clock.continueAt(5.75);
            synchronized (obj) {
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            clock.continueAt(6.75);
            synchronized (obj) {
            }

            System.out.println("*** WAITING - BLOCKED (interrupt status set): ***");
            // ...

            System.out.println("-> mythread: wait");
            clock.continueAt(8.75);
            synchronized (obj) {
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            clock.continueAt(9.75);
            synchronized (obj) {
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            System.out.println("*** TERMINATED: ***");
        }, "mythread");
        // **************************************

        threads.add(new Thread(() -> { // weiterer Thread
            clock.continueAt(1.6);
            synchronized (obj) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                }
            }
        }));

        threads.add(new Thread(() -> { // eventuell weiterer Thread
            clock.continueAt(4.1);
            synchronized (obj) {
                obj.notify();
                System.out.println("-> notify");
            }
        }));

        threads.add(new Thread(() -> { // eventuell weiterer Thread
            clock.continueAt(6.1);
            synchronized (obj) {
                obj.notify();
                System.out.println("-> notify");
            }
            clock.continueAt(6.6);
            synchronized (obj) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                }
            }
        }));


//
        threads.add(new Thread(() -> { // eventuell weiterer Thread
            clock.continueAt(9.1);
            synchronized (obj) {
                obj.notify();
            }
        }));


        threads.add(new Thread(() -> { // eventuell weiterer Thread
            clock.continueAt(9.8);
            System.out.println("-> interrupt");
            synchronized (obj) {
                try {
                    obj.notify();
                    mythread.interrupt();
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }));


        return mythread;
    }

}
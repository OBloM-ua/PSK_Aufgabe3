package com.baidiuk.code_beispiel1cp;/* 051030-1 Programmiersprachen und -konzepte WS 2017/18
 * Concurrent Programming
 * Hans Moritsch 2017-12-17
 */
import java.util.List;
import java.util.ArrayList;

// ------------------------------------------------------------------------
public class TestBeispiel1Cp {
// ------------------------------------------------------------------------

    public static void main(String[] arg) {

        Clock clock = new Clock();
        clock.setDigits(1);

        Object obj  = new Object();

        ArrayList<Thread> threads = new ArrayList<Thread>();

        Thread mythread = new Beispiel1CpImpl().setup(clock, obj, threads);

        clock.setStartTime(); 

        clock.show("begin");
        for (Thread t: threads)
            t.start();
        mythread.start();

        clock.continueAt(1.0);              
        clock.show(mythread);

        clock.continueAt(2.0);              
        clock.show(mythread);

        clock.continueAt(4.0);              
        clock.show(mythread);

        clock.continueAt(6.0);              
        clock.show(mythread);

        clock.continueAt(7.0);              
        clock.show(mythread);

        clock.continueAt(9.0);              
        clock.show(mythread);

        clock.continueAt(10.0);              
        clock.show(mythread);

        clock.continueAt(11.0);              
        clock.show(mythread);

        try {
            mythread.join();
            for (Thread t: threads)
                t.join();
            } catch (InterruptedException ex) { };

        clock.show("end");
        }

    }


package com.baidiuk.code_beispiel1cp;/* 051030-1 Programmiersprachen und -konzepte WS 2017/18
 * Concurrent Programming
 * Hans Moritsch 2017-12-17
 */
import java.util.List;
import java.util.ArrayList;

// ------------------------------------------------------------------------
interface Beispiel1Cp {
// ------------------------------------------------------------------------
    Thread setup(Clock clock, Object obj, List<Thread> threads);
    }

package bank.service;

public class AService {


    int findIndex(int[] a, int x) {
        if (x<a[0]) return 0;
        if (x>=a[a.length-1]) return a.length-1;
        for (int i = 0; i < a.length; i++) {
            if (a[i]>=x) return i;
        }

        return -1; //never happens
    }

    /**
     * Sprawdza czy punkt (xx,yy) jest wewnątrz (lub na skraju) kwadratu o
     * lewym górnym rogu w (x,y) i boku side.
     *
     */
    boolean isInsideSquare(int x, int y, int side, int xx, int yy) {
        //do napisania
        return true;
    }


    boolean isWindowOk(int[] a, int windowSize, int maxSum) {
        return true;
    }

    public int add(int a, int b) {
        return a + b;
    }



}

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
        if (xx<x) return false;
        if (xx>x+side) return false;
        if (yy<y-side) return false;  //poniżej dolnej krawędzi
        if (yy>y) return false;
        return true;
    }


    boolean isWindowOk(int[] a, int windowSize, int maxSum) {
        //zrobić dwie pętle
        //pierwsza po pozycji startowej
            //druga po kolejnych elementach; w czasie drugiej wyliczać ich sume
            //jeśli suma po drugiej pętli jest większa od `maxSum` to zwrócić "false"

        for (int i = 0; i + windowSize - 1 < a.length; i++) {
            int sum = 0;
            for (int j = 0; j < windowSize; j++) {
                sum += a[i + j];
            }
            if (sum>maxSum) return false;
        }
        return true;
    }

    /*
     * Spawdza wzajemne położenie dwóch kwadratów; każdy zadany jest przez położenie jego lewego
     * górnego rogu ( (x,y) dla pierwszego, i (x1,y1) dla drugiego), oraz długość boku, `side`.
     *
     * Metoda spradza, czy środki kwadratów są bliżej niż `distance` (wtedy zwraca `true`).
     *
     */
    boolean collidesWith(int x, int y, int x1, int y1, int side, double distance) {
        double centerX = x + 0.5 * side;
        double centerY = y - 0.5 * side;
        double centerX1 = x1 + 0.5 * side;
        double centerY1 = y1 - 0.5 * side;

        double current = Math.sqrt((centerX - centerX1) * (centerX - centerX1) +
                (centerY - centerY1) * (centerY - centerY1));

        return (current < distance);
    }

    /*
     * Zadane są dwa odczyty stopera, każdy zawiera liczbę minut i liczbę sekund. Obliczyć ile sekund minęło
     * między obydwoma odczytami.
     */
    int differenceInSeconds(int mins1, int sec1, int min2, int sec2) {
        return 0;
    }

    public int add(int a, int b) {
        return a + b;
    }



}

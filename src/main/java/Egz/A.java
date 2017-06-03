package Egz;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Random;

import static java.lang.System.currentTimeMillis;

public class A {
    public static void main(String[] args) {
        int MX = 300_000;
        long st = currentTimeMillis();
        Deque<Integer> w = new ArrayDeque<>();
        Random r = new Random();
        for (int i = 0; i < MX; i++) {
            int x = r.nextInt(100_000);
            if (x%2==0) w.addFirst(x);
            else w.addLast(x);
        }
        ArrayList<Integer> aw = new ArrayList<>(w);
        for (int i = 0; i < MX; i++) {
            int x = r.nextInt(1);
            if (x%2==0) aw.remove(aw.size()-1);
            else aw.remove(0);
        }
        long en = currentTimeMillis();
        System.out.println((en-st) + "[ms]");




    }
}

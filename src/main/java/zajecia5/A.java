package zajecia5;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

class B implements Comparable<B> {
    int x;
    int y;

    //to trzeba napisać
    @Override
    public int compareTo(B other) {
        if (this.x < other.x) return -1;
        if (this.x > other.x) return 1;
        if (this.y < other.y) return -1;
        if (this.y > other.y) return 1;
        return 0;
    }
}

class Dana {
    int x;
    String nazwa;
}


public class A {
    public static void main(String[] args) {
//        List<Integer> ll = Arrays.asList(1, 5, 6, 3, 1);
//        Collections.sort(ll);
//        System.out.println(ll);

        //pierwsza instancja klasy Dana
        Dana w = new Dana();
        w.x = 11;
        w.nazwa = "abc";

        //druga instancja klasy Dana
        Dana g = new Dana();
        g.x = 8;
        g.nazwa = "xxx";

        System.out.println("w.x=" + w.x);
        System.out.println("g.x=" + g.x);
    }
}

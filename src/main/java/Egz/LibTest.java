package Egz;

import com.google.common.collect.Sets;
import lombok.Data;

import java.util.Set;

@Data
class DDD {
    int x;
}

public class LibTest {

    public static void main(String[] args) {
        Set<Integer> ss = Sets.newHashSet();  //wykorzystanie Guava

        DDD ddd = new DDD();    //Wykorzytanie Lombok
        ddd.setX(12);
        System.out.println(ddd);

        String user = "User334411";
        System.out.println(user.contains("111"));
    }
}

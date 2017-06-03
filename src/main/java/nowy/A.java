package nowy;

import com.google.common.collect.Sets;

import java.util.Set;

public class A {
    public static void main(String[] args) {
        //Tworzenie nowego hashseta przy pomocy funkcji google/guava:
        Set<Integer> zbior = Sets.newHashSet(1, 4, 6, 8);
        System.out.println(zbior);
    }

}

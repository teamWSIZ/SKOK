package bank.model;

import lombok.Data;

@Data
public class Klient implements Comparable<Klient> {
    int id;
    String nazwisko;

    @Override
    public int compareTo(Klient o) {
        Klient current = this;
        return current.getNazwisko().compareTo(o.getNazwisko());
    }
}

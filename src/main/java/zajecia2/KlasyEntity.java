package zajecia2;

import lombok.Data;


@Data
class Book {
    int id;
    String autor;
    String tytul;
}


public class KlasyEntity {
    public static void main(String[] args) {
        User user1 = new User();
        user1.id = 1;
        user1.name = "Abra";
        user1.pesel = "X1";

        User user2 = new User(1, "Kadabra", "X2");


        System.out.println(user1);
        System.out.println(user2);

        System.out.println(user1.equals(user2));
        System.out.println(user1.equals(user1));


        Book b = new Book();
        b.setId(1123);
        b.setAutor("Gombrowicz");

        System.out.println(b);


    }
}

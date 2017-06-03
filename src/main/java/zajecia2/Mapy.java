package zajecia2;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Mapy {
    public static void main(String[] args) {
        Map<Integer, User> users = new HashMap<>();

        users.put(1, new User(1, "Aaa", null));
        users.put(2, new User(2, "Baa", null));
        users.put(3, new User(3, "Daa", null));
        users.put(4, new User(4, "Caa", null));


        System.out.println(users.containsKey(2));  //true

        System.out.println(users.get(2));


        UUID.randomUUID().toString().substring(0, 5);  //[.....]

        for(int id : users.keySet()) {
            User u = users.get(id);
            System.out.println(u);
        }

        for(User u : users.values()) {
            if (u.id %2 == 0) {
                System.out.println(u);   //wypisuje userow z parzystym id
            }
        }



    }
}

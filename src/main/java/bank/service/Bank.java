package bank.service;

import bank.model.Klient;
import bank.model.Konto;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private AuthService authService;

    private List<Klient> klienci;
    private List<Konto> konta;
    int generatorNrKonta;    //licznik kont (tworzy idkont)
    int generatorNrKlienta;  //licznik klientów

    public Bank(AuthService authService) {
        klienci = new ArrayList<>();
        konta = new ArrayList<>();
        generatorNrKonta = 1;
        generatorNrKlienta = 1;
        this.authService = authService;
    }

    //Funkcja do testów banku; tworzy przykładowych klientów i konta
    public void init() {
        addClient("Abu Mazen");
        addClient("Xi Wuhao");
        addClient("Francesco Bueno");
        createAccoutForClient(1);
        createAccoutForClient(2);
        createAccoutForClient(3);
        createAccoutForClient(3);

        for(Konto k : konta) {
            depositFunds(k.getIdkonta(), 10);
        }

    }

    //Dodawanie klientów; uwaga: id klienta musi być unikalne i ustawione!
    @Deprecated
    public void addClient(Klient k) {
        klienci.add(k);
    }


    public void transferFunds(int idKontaDawcy, int idKontaBiorcy, int funds) {
        //sprawdzic czy oba konta istnieja
        //sprawdzic czy dawca ma odpowiedni stan konta
        //wykonac transfer
    }



    public Klient addClient(String nazwaKlienta) {
        Klient nowy = new Klient();
        nowy.setId(generatorNrKlienta);
        generatorNrKlienta++;
        nowy.setNazwisko(nazwaKlienta);
        klienci.add(nowy);
        return nowy;
    }

    //Tworzy nowe konto związane z klientem o podanym id
    public void createAccoutForClient(int idklienta) {
        Konto k = new Konto();
        k.setIdklienta(idklienta);
        k.setStankonta(0);
        k.setIdkonta(generatorNrKonta);
        generatorNrKonta++;
        konta.add(k);
    }

    public List<Konto> getAccountsForClient(int idklienta) {
        List<Konto> wynik = new ArrayList<>();
        for(Konto k : konta) {
            if (k.getIdklienta()==idklienta) {
                wynik.add(k);
            }
        }
        return wynik;
    }

    //Dodaje fundusze `funds` do konta o podanym id
    public void depositFunds(int idkonta, int funds) {
        // 1) funds > 0
        // 2) musi istniec konto o idkonta
        // jeśli nie 1 i 2 to wyrzuć wyjątek
        if (funds<=0) throw new RuntimeException("Nie można wpłacać ujemnych sum");
        Konto docelowe = getKontoByKontoId(idkonta);
        if (docelowe==null) throw new RuntimeException("Nie ma takiego konta");
        docelowe.setStankonta(docelowe.getStankonta() + funds);

    }

    public void withdrawFunds(int idkonta, int funds) {
        if (funds<0) throw new RuntimeException("Nie można wypłacać ujemnych sum");
        Konto docelowe = getKontoByKontoId(idkonta);
        if (docelowe==null) throw new RuntimeException("Nie ma takiego konta");
        int stankonta = docelowe.getStankonta();
        if (stankonta<funds) {
            throw new RuntimeException("Nie można wypłacić więcej niż stan konta");
        }
        docelowe.setStankonta(stankonta - funds);
    }

    public Konto getKontoByKontoId(int kontoid) {
        for(Konto k : konta) {
            if (k.getIdkonta()==kontoid) return k;
        }
        return null;
    }

    public List<Klient> getKlienci() {
        return klienci;
    }

    public List<Konto> getKonta() {
        return konta;
    }

    public void removeAccount(int kontoid) {
        Konto toRemove = null;
        for(Konto k : konta) {
            if (k.getIdkonta()==kontoid) toRemove = k;
        }
        konta.remove(toRemove);
    }

    public void removeClient(int klientId) {
        Klient toRemove = null;
        for(Klient k : klienci) {
            if (k.getId()==klientId) {
                toRemove = k;
                for(Konto kk : konta) {
                    if (kk.getIdklienta()==klientId) removeAccount(kk.getIdkonta());
                }
            }
        }
        klienci.remove(toRemove);
    }

    @Override
    public String toString() {
        String res = "----------------------------------";
        for(Klient k : klienci) {
            res += "\n" + k.toString();
        }
        res += "\n----------------------------------";
        for(Konto k : konta) {
            res += "\n" + k.toString();
        }
        res += "\n----------------------------------";
        return res;
    }
}

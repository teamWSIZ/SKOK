package bank.service;

import bank.model.Klient;
import bank.model.Konto;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private AuthService authService;

    private List<Klient> klienci;
    private List<Konto> konta;
    int nastepnyNrKonta;
    int nastepnyNrKlienta;

    public Bank(AuthService authService) {
        klienci = new ArrayList<>();
        konta = new ArrayList<>();
        nastepnyNrKonta = 1;
        nastepnyNrKlienta = 1;
        this.authService = authService;
    }

    public void init() {
        addClient("Abu Mazen");
        addClient("Xi Wuhao");
        addClient("Francesco Bueno");
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
        nowy.setId(nastepnyNrKlienta);
        nastepnyNrKlienta++;
        nowy.setNazwisko(nazwaKlienta);
        klienci.add(nowy);
        return nowy;
    }

    public void createAccoutForClient(int idklienta) {
        Konto k = new Konto();
        k.setIdklienta(idklienta);
        k.setStankonta(0);
        k.setIdkonta(nastepnyNrKonta);
        nastepnyNrKonta++;
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
}

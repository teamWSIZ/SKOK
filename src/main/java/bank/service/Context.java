package bank.service;

public class Context {
    static Bank bank;
    static AuthService authService;


    public static Bank getBank() {
        if (bank==null) {
            bank = new Bank(getAuthService());
            bank.init();
        }
        return bank;
    }

    public static AuthService getAuthService() {
        if (authService==null) {
            authService = new AuthService();
        }
        return authService;
    }



}

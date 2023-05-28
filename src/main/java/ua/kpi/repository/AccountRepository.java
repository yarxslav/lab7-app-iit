package ua.kpi.repository;

import ua.kpi.model.Account;

import java.util.ArrayList;

public class AccountRepository {

    private ArrayList<Account> accounts = new ArrayList<Account>() {{
        add(new Account(1, 100.0, "Petro", "Vovk", "12345678"));
        add(new Account(2, 1250.0, "Ivan", "Melnyk", "87654321"));
        add(new Account(3, 7234.33, "Maxym", "Vlasenko", "45362718"));
        add(new Account(4, 560.50, "Daria", "Komar", "09876543"));
        add(new Account(5, 176.98, "Iryna", "Chaika", "34567890"));
    }};

    public ArrayList<Account> findAll() {
        return accounts;
    }

    public Account findById(Integer id) {

        for (Account account : accounts) {
            if (account.getId().equals(id.hashCode())) {
                return account;
            }
        }

        return null;
    }

    public Account findByNumber(String number) {

        for (Account account : accounts) {
            if (account.getNumber().equals(number)) {
                return account;
            }
        }

        return null;
    }

    public Account findByOwner(String ownerName, String ownerSurname) {

        for (Account account : accounts) {
            if (account.getOwnerName().equals(ownerName) && account.getOwnerSurname().equals(ownerSurname)) {
                return account;
            }
        }

        return null;
    }
}

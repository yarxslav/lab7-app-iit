package ua.kpi.service;

import org.fluentd.logger.FluentLogger;
import ua.kpi.model.Account;
import ua.kpi.model.Transaction;
import ua.kpi.repository.AccountRepository;

import java.util.HashMap;
import java.util.Map;

public class AccountService {

    private final AccountRepository accountRepository;
    private final FluentLogger fl = FluentLogger.getLogger("myapp", "127.0.0.1", 24224);

    public AccountService() {
        this.accountRepository = new AccountRepository();
    }

    public boolean topUp(String accountNumber, Double amount) {

        Account account = accountRepository.findByNumber(accountNumber);

        if (account != null) {
            account.setBalance(account.getBalance() + amount);
            Transaction trx = new Transaction(accountNumber, accountNumber, amount);
            Map<String, Object> trxInfo = new HashMap<>();
            trxInfo.put("TRX ID", trx.getId());
            trxInfo.put("TRX Source Number", trx.getFrom());
            trxInfo.put("TRX Destination Number", trx.getTo());
            trxInfo.put("TRX Amount", trx.getAmount());
            trxInfo.put("TRX Creation Timestamp", trx.getCreatedAt());
            fl.log("TRX Info", trxInfo);
            return true;
        } else {
            System.out.println("There is no account with such number.");
            return false;
        }
    }

    public boolean withdraw(String accountNumber, Double amount) {

        Account account = accountRepository.findByNumber(accountNumber);

        if (account != null) {
            if (account.getBalance() > amount) {
                account.setBalance(account.getBalance() - amount);
                Transaction trx = new Transaction(accountNumber, accountNumber, amount);
                Map<String, Object> trxInfo = new HashMap<>();
                trxInfo.put("TRX ID", trx.getId());
                trxInfo.put("TRX Source Number", trx.getFrom());
                trxInfo.put("TRX Destination Number", trx.getTo());
                trxInfo.put("TRX Amount", trx.getAmount());
                trxInfo.put("TRX Creation Timestamp", trx.getCreatedAt());
                fl.log("TRX Info", trxInfo);
                return true;
            } else {
                System.out.println("You do not have enough funds");
                return false;
            }
        } else {
            System.out.println("There is no account with such number.");
            return false;
        }
    }

    public boolean transfer(String fromNum, String toNum, Double amount) {

        Account from = accountRepository.findByNumber(fromNum);
        Account to = accountRepository.findByNumber(toNum);

        if (from.equals(to)) {
            System.out.println("Account numbers should be different!");
            return false;
        }

        if (from != null && to != null) {

            if (from.getBalance() > amount) {
                from.setBalance(from.getBalance() - amount);
                to.setBalance(to.getBalance() + amount);
                Transaction trx = new Transaction(fromNum, toNum, amount);
                Map<String, Object> trxInfo = new HashMap<>();
                trxInfo.put("TRX ID", trx.getId());
                trxInfo.put("TRX Source Number", trx.getFrom());
                trxInfo.put("TRX Destination Number", trx.getTo());
                trxInfo.put("TRX Amount", trx.getAmount());
                trxInfo.put("TRX Creation Timestamp", trx.getCreatedAt());
                fl.log("TRX Info", trxInfo);
                return true;
            } else {
                System.out.println("Account " + fromNum + " does not have enough funds.");
                return false;
            }

        } else {
            System.out.println("There is no account with such number.");
            return false;
        }
    }


    public Double getBalance(String accountNumber) {

        Account account = accountRepository.findByNumber(accountNumber);

        if (account != null) {
            return account.getBalance();
        } else {
            System.out.println("There is no account with such number.");
            return null;
        }
    }
}

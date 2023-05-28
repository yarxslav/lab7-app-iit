package ua.kpi.controller;

import ua.kpi.service.AccountService;
import ua.kpi.view.View;

public class FrontController {


    private final AccountService accountService;

    public FrontController() {
        this.accountService = new AccountService();
    }

    public void init() {

        while (true) {
            View.showMenu();

            String action = View.getActionInput();

            if (action.equals("5")) {
                break;
            }

            switch (action) {
                case "1":
                    topUp(View.getAccountNumber(), Double.parseDouble(View.getSum()));
                    break;
                case "2":
                    withdraw(View.getAccountNumber(), Double.parseDouble(View.getSum()));
                    break;
                case "3":
                    transfer(View.getAccountNumber(), View.getAccountNumber(), Double.parseDouble(View.getSum()));
                    break;
                case "4":
                    checkBalance(View.getAccountNumber());
                    break;
                default:
                    break;
            }
        }


    }

    private void checkBalance(String accountNumber) {

        Double balance = accountService.getBalance(accountNumber);

        if (balance != null) {
            View.balance(accountNumber, balance);
        }

    }

    private void topUp(String accountNumber, Double sum) {

        if (sum >= 0.0) {
            if (accountService.topUp(accountNumber, sum)) {
                View.success();
            } else {
                View.error();
            }
        } else {
            View.error();
        }

    }

    private void withdraw(String accountNumber, Double sum) {

        if (sum >= 0.0) {
            if (accountService.withdraw(accountNumber, sum)) {
                View.success();
            } else {
                View.error();
            }
        } else {
            View.error();
        }
    }

    private void transfer(String from, String to, Double sum) {

        if (sum >= 0.0) {
            if (accountService.transfer(from, to, sum)) {
                View.success();
            } else {
                View.error();
            }
        } else {
            View.error();
        }
    }
}

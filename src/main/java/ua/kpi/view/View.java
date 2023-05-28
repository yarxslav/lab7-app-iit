package ua.kpi.view;

import java.util.Scanner;

public class View {

    private static Scanner sc = new Scanner(System.in);

    public static void showMenu() {
        System.out.println("Please, select a desired action: " +
                "\n1. Top up my balance." +
                "\n2. Withdraw funds." +
                "\n3. Transfer to another account." +
                "\n4. Check balance." +
                "\n5. Quit.\n");
    }

    public static String getActionInput() {

        String input = sc.nextLine();

        if (input.matches("\\d{1}")) {
            return input;
        } else {
            return ("Wrong input. Try again!\n");
        }
    }

    public static String getAccountNumber() {

        System.out.println("Please, specify the account number:");
        String input = sc.nextLine();

        if (input.matches("\\d{8}")) {
            return input;
        } else {
            return ("Wrong account number. Try again!\n");
        }
    }

    public static String getSum() {
        System.out.println("Please, specify the desired amount:");

        return sc.nextLine();
    }

    public static void error() {
        System.out.println("Ooops. There is a little misunderstanding between us.\n");
    }

    public static void success() {
        System.out.println("Operation was successful. Thank you for using our service.\n");
    }

    public static void balance(String accountNumber, Double balance) {
        System.out.println("Account " + accountNumber + ": " + balance + " UAH\n");
    }
}

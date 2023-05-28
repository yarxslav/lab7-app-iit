package ua.kpi.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Account {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private Integer id;
    private String number;
    private Double balance;
    private String ownerName;
    private String ownerSurname;
    private Timestamp createdAt;

    public Account(Integer id, Double balance, String ownerName, String ownerSurname, String number) {
        this.id = id;
        this.balance = balance;
        this.ownerName = ownerName;
        this.ownerSurname = ownerSurname;
        this.number = number;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    public Integer getId() {
        return id.hashCode();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        if (balance >= 0.0) {
            this.balance = balance;
        } else {
            System.out.println("Balance can not be negative");
        }
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerSurname() {
        return ownerSurname;
    }

    public void setOwnerSurname(String ownerSurname) {
        this.ownerSurname = ownerSurname;
    }

    public String getCreatedAt() {
        return sdf.format(createdAt);
    }

    @Override
    public String toString() {
        return "Account: " +
                "ID = " + id +
                ", balance = " + balance +
                ", owner name = '" + ownerName + '\'' +
                ", owner surname='" + ownerSurname + '\'' +
                ", created at = " + sdf.format(createdAt);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {

        if (number.matches("\\d{8}")) {
            this.number = number;
        } else {
            System.out.println("Number should be 8 digits.");
        }

    }
}

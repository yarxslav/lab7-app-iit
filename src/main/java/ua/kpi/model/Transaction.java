package ua.kpi.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Transaction {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static Integer counter = 0;

    private Integer id;
    private String from;
    private String to;
    private Double amount;
    private Timestamp createdAt;

    public Transaction(String from, String to, Double amount) {
        this.id = counter + 1;
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    public Integer getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public Double getAmount() {
        return amount;
    }

    public String getCreatedAt() {
        return sdf.format(createdAt);
    }
}

package com.example.paymentreport_att2;

public class PaymentInformation {
    private String room_;
    private String note_;
    private String amount_;
    private String date_;
    private String mop_;
    private String account_;

    // Constructor with parameters
    public PaymentInformation(String room, String note, String amount, String date, String mop, String account) {
        this.room_ = room;
        this.note_ = note;
        this.amount_ = amount;
        this.date_ = date;
        this.mop_ = mop;
        this.account_ = account;
    }


    // Getter for room_
    public String getRoom() {
        return room_;
    }

    // Setter for room_
    public void setRoom(String room) {
        this.room_ = room;
    }

    // Getter for room_
    public String getNote() {
        return note_;
    }

    // Setter for room_
    public void setNote(String note) {
        this.note_ = note;
    }


    // Getter for amount_
    public String getAmount() {
        return amount_;
    }

    // Setter for amount_
    public void setAmount(String amount) {
        this.amount_ = amount;
    }

    // Getter for date_
    public String getDate() {
        return date_;
    }

    // Setter for date_
    public void setDate(String date) {
        this.date_ = date;
    }

    // Getter for mop_
    public String getMOP() {
        return mop_;
    }

    // Setter for mop_
    public void setMOP(String mop) {
        this.mop_ = mop;
    }

    // Getter for account_
    public String getAccount() {
        return account_;
    }

    // Setter for account_
    public void setAccount(String account) {
        this.account_ = account;
    }
}

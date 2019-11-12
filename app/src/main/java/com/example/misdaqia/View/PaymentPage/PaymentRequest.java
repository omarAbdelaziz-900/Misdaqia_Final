package com.example.misdaqia.View.PaymentPage;

public class PaymentRequest {

    /**
     * amount : 92
     * cardNumber : 4200000000000000
     * cardExpireMonth : 10
     * cardexpiryYear : 2020
     * cardCvv : 100
     * cardType : VISA
     * userId : 37
     */

    private int amount;
    private long cardNumber;
    private int cardExpireMonth;
    private int cardexpiryYear;
    private int cardCvv;
    private String cardType;
    private int userId;

    public PaymentRequest(int amount, long cardNumber, int cardExpireMonth, int cardexpiryYear, int cardCvv, String cardType, int userId) {
        this.amount = amount;
        this.cardNumber = cardNumber;
        this.cardExpireMonth = cardExpireMonth;
        this.cardexpiryYear = cardexpiryYear;
        this.cardCvv = cardCvv;
        this.cardType = cardType;
        this.userId = userId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCardExpireMonth() {
        return cardExpireMonth;
    }

    public void setCardExpireMonth(int cardExpireMonth) {
        this.cardExpireMonth = cardExpireMonth;
    }

    public int getCardexpiryYear() {
        return cardexpiryYear;
    }

    public void setCardexpiryYear(int cardexpiryYear) {
        this.cardexpiryYear = cardexpiryYear;
    }

    public int getCardCvv() {
        return cardCvv;
    }

    public void setCardCvv(int cardCvv) {
        this.cardCvv = cardCvv;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

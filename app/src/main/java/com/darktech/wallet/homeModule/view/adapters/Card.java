package com.darktech.wallet.homeModule.view.adapters;

public class Card {
    private String cardNumber;
    private String expirationDate;
    private String nameInCard;
    private Double balance;

    public Card() {
    }

    public Card(String cardNumber, String expirationDate, String nameInCard, Double balance) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.nameInCard = nameInCard;
        this.balance = balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getNameInCard() {
        return nameInCard;
    }

    public void setNameInCard(String nameInCard) {
        this.nameInCard = nameInCard;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}

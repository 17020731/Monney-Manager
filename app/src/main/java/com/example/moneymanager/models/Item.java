package com.example.moneymanager.models;

public class Item {
    private String type;
    private boolean isExpense;
    public Item(String type) {
        this.type = type;
    }
    public Item(String type, boolean isExpense){
        this.type = type;
        this.isExpense = isExpense;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isExpense() {
        return isExpense;
    }

    public void setExpense(boolean expense) {
        isExpense = expense;
    }
}

package com.github.badaccuracyid.objects;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BankAccount implements IAccount {

    private int balance;

    @Override
    public void deposit(int amount) {
        balance += amount;
    }

    @Override
    public void withdraw(int amount) {
        balance -= amount;
    }

    @Override
    public int getBalance() {
        return this.balance;
    }
}

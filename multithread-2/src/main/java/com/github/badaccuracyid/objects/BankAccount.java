package com.github.badaccuracyid.objects;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BankAccount implements IAccount {

    private int balance;

    @Override
    public void deposit(int amount) {
        balance += amount;
        System.out.println("Added " + amount + " | Balance: " + balance);
    }

    @Override
    public void withdraw(int amount) {
        balance -= amount;
        System.out.println("Taken " + amount + " | Balance: " + balance);
    }

    @Override
    public int getBalance() {
        return this.balance;
    }
}

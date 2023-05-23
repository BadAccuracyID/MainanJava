package com.github.badaccuracyid.objects;

public interface IAccount {

    void deposit(int amount);

    void withdraw(int amount);

    int getBalance();

}

package com.github.badaccuracyid;

import com.github.badaccuracyid.objects.BankAccount;
import com.github.badaccuracyid.tasks.Expense;
import com.github.badaccuracyid.tasks.Income;

import java.util.Scanner;

public class Main {

    private final Scanner scanner = new Scanner(System.in);

    public Main() {
        BankAccount account1 = new BankAccount(1000);

        // Create a thread that will withdraw 500 from account1 after 1 second
        Thread expenseThread = new Thread(new Expense(account1, 100, 1000));
        expenseThread.start();

        Thread incomeThread = new Thread(new Income(account1, scanner, 200));
        incomeThread.setDaemon(true);
        incomeThread.start();
    }

    public static void main(String[] args) {
        // Playing with synchronized methods
        new Main();
    }


}

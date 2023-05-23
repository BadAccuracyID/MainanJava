package com.github.badaccuracyid;

import com.github.badaccuracyid.objects.BankAccount;
import com.github.badaccuracyid.tasks.Drawer;
import com.github.badaccuracyid.tasks.Expense;
import com.github.badaccuracyid.tasks.InputReader;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public Main() {
        BankAccount account1 = new BankAccount(2000);

        // Create a thread that will withdraw 500 from account1 after 1 second
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(
                new Expense(account1, 10, 1000),
                1, 1, TimeUnit.SECONDS
        );

        // Create a drawer task
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(
                new Drawer(account1),
                0, 250, TimeUnit.MILLISECONDS
        );

        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(
                new InputReader(this),
                0, 125, TimeUnit.MILLISECONDS
        );
    }

    public static void main(String[] args) {
        // Playing with synchronized methods
        new Main();
    }

    public void processInput(String input) {
        if (input.equalsIgnoreCase("exit")) {
            System.exit(0);
        }
    }
}

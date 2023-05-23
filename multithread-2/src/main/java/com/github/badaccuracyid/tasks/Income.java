package com.github.badaccuracyid.tasks;

import com.github.badaccuracyid.objects.IAccount;
import lombok.AllArgsConstructor;

import java.util.Scanner;

@AllArgsConstructor
public class Income implements Runnable {

    private final IAccount account;
    private final Scanner scanner;
    private final int amount;

    @Override
    public void run() {
        while (true) {
            String input = scanner.nextLine();
            System.out.println("Input: " + input);
            if (input.equalsIgnoreCase("work")) {
                account.deposit(amount);
            } else if (input.equalsIgnoreCase("exit")) {
                break;
            } else if (input.equalsIgnoreCase("kill")) {
                System.exit(0);
            } else {
                System.out.println("Invalid input!");
            }
        }

    }
}

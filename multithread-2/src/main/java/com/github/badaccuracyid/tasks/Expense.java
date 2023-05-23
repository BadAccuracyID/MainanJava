package com.github.badaccuracyid.tasks;

import com.github.badaccuracyid.objects.IAccount;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Expense implements Runnable {

    private final IAccount account;
    private final int amount;
    private final long delay;

    @Override
    public void run() {
        while (account.getBalance() > 0) {
            account.withdraw(amount);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

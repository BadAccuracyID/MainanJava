package com.github.badaccuracyid.tasks;

import com.github.badaccuracyid.objects.IAccount;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Drawer implements Runnable {

    private final IAccount account;

    @Override
    public void run() {
//        Utils.clearScreen();
        System.out.println("Current balance: " + account.getBalance());
    }
}

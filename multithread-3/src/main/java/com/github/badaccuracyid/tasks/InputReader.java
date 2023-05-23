package com.github.badaccuracyid.tasks;

import com.github.badaccuracyid.Main;

import java.util.Scanner;


public class InputReader implements Runnable {
    private final Main main;
    private Scanner scanner;

    public InputReader(Main main) {
        this.main = main;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run() {
        String input = scanner.nextLine();
        main.processInput(input);

    }
}

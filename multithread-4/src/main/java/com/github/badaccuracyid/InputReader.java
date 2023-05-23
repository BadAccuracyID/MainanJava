package com.github.badaccuracyid;

import java.util.Scanner;

public class InputReader implements Runnable {

    private volatile String input;

    public static void main(String[] args) throws InterruptedException {
        // Start the input reader thread
        InputReader inputReader = new InputReader();
        Thread readerThread = new Thread(inputReader);
        readerThread.start();

        // Print statements thread
        Thread printThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Print statement " + i);
                try {
                    Thread.sleep(1000); // Delay to simulate print statement execution
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        printThread.start();

        // Wait for both threads to finish
        printThread.join();
        readerThread.join();

        // Access the input entered by the user
        String userInput = inputReader.getInput();
        System.out.println("User input: " + userInput);
    }

    public String getInput() {
        return input;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
    }
}

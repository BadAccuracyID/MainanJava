package com.github.badaccuracyid;

import com.github.badaccuracyid.impl.Manager;
import com.github.badaccuracyid.objects.ASExecutor;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    private final Scanner scanner = new Scanner(System.in);
    private final Manager manager = new Manager();
    private int i = 0;

    private Main() {
        manager.addExecutor(
                ASExecutor.ASExecutorBuilder.builder()
                        .executor(() -> Executors.newSingleThreadScheduledExecutor())
                        .task(() -> {
                            System.out.println("Hello World!");
                            System.out.println("i = " + i);
                            i++;
                            return null;
                        })
                        .delay(0)
                        .period(1)
                        .timeUnit(TimeUnit.SECONDS)
                        .runningCondition((ignored) -> i < 3)
                        .build()
        );

        Executors.newSingleThreadScheduledExecutor()
                .scheduleAtFixedRate(() -> {
                    System.out.println("Enter 'stop' to stop the executor");
                    System.out.println("Enter 'kill' to kill the application");
                    System.out.println("Enter anything else to continue");
                    System.out.print("> ");
                    String input = scanner.nextLine();
                    if (input.equalsIgnoreCase("kill")) {
                        System.exit(0);
                    } else if (input.equalsIgnoreCase("stop")) {
                        i = 3;
                    } else {
                        i = 0;
                    }
                }, 0, 1, TimeUnit.SECONDS);
    }


    public static void main(String[] args) {
        new Main();
    }
}

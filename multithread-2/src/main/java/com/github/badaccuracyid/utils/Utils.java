package com.github.badaccuracyid.utils;

import java.io.IOException;

public class Utils {

    public static void clearScreen() {
        try {
            String os = "linux";
            try {
                os = System.getProperty("os.name");
            } catch (Exception ignored) {
            }

            Process start;
            if (os.contains("windows")) {
                ProcessBuilder processBuilder;
                processBuilder = new ProcessBuilder("cmd", "/c", "cls");
                start = processBuilder.start();
            } else {
                ProcessBuilder processBuilder;
                processBuilder = new ProcessBuilder("clear");
                start = processBuilder.inheritIO().start();
            }

            start.waitFor();
        } catch (InterruptedException | IOException ignored) {
            for (int i = 0; i < 100; i++) {
                System.out.println();
            }
        }
    }
}

package com.github.badaccuracyid;

import com.github.badaccuracyid.car.CarStrong;
import com.github.badaccuracyid.car.CarWeak;
import com.github.badaccuracyid.car.Wheel;
import com.github.badaccuracyid.car.Window;

import java.lang.ref.WeakReference;

public class Main {

    public Main() {
        weakCar();
        strongCar();
    }

    public static void main(String[] args) {
        new Main();
    }

    private void weakCar() {
        CarWeak carWeak = new CarWeak(new Wheel(), new Window());

        WeakReference<Wheel> wheel = carWeak.getWheel();
        WeakReference<Window> window = carWeak.getWindow();

        System.out.println("Wheel is null: " + (wheel.get() == null));
        System.out.println("Window is null: " + (window.get() == null));

        // destroy car
        carWeak = null;

        System.gc();
        System.out.println("\nGC called\n");

        // print
        System.out.println("Wheel is null: " + (wheel.get() == null));
        System.out.println("Window is null: " + (window.get() == null));
    }

    private void strongCar() {
        CarStrong carStrong = new CarStrong(new Wheel(), new Window());

        Wheel wheel = carStrong.getWheel();
        Window window = carStrong.getWindow();

        System.out.println("Wheel is null: " + (wheel == null));
        System.out.println("Window is null: " + (window == null));

        // destroy car
        carStrong = null;

        System.gc();
        System.out.println("\nGC called\n");

        // print
        System.out.println("Wheel is null: " + (wheel == null));
        System.out.println("Window is null: " + (window == null));
    }
}

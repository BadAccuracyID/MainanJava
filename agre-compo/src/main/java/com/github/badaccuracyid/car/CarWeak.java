package com.github.badaccuracyid.car;

import lombok.Data;

import java.lang.ref.WeakReference;

@Data
public class CarWeak {

    private WeakReference<Wheel> wheel;
    private WeakReference<Window> window;

    public CarWeak(Wheel wheel, Window window) {
        this.wheel = new WeakReference<>(wheel);
        this.window = new WeakReference<>(window);
    }

}

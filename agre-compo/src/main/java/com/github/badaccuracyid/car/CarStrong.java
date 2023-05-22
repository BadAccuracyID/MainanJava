package com.github.badaccuracyid.car;

import lombok.Data;

@Data
public class CarStrong {

    private Wheel wheel;
    private Window window;

    public CarStrong(Wheel wheel, Window window) {
        this.wheel = wheel;
        this.window = window;
    }
}

package com.airbnb.lottie.utils;

public class MeanCalculator {
    private int n;
    private float sum;

    public void add(float f) {
        this.sum += f;
        this.n++;
        int i = this.n;
        if (i == Integer.MAX_VALUE) {
            this.sum /= 2.0f;
            this.n = i / 2;
        }
    }
}

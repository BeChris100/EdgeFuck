package com.edge_fuck;

import java.util.Scanner;

public class MainClass {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Number num = new Number() {
            @Override
            public int intValue() {
                return 0;
            }

            @Override
            public long longValue() {
                return 0;
            }

            @Override
            public float floatValue() {
                return 0;
            }

            @Override
            public double doubleValue() {
                return 0;
            }
        };
    }

}

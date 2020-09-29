package com.envy.threading.util;

import com.envy.threading.entity.Ship;
import com.envy.threading.entity.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ThreadsStart {
    private static final int SHIPS_ACCEPT = 5;
    private static final int SHIPS_DELIVER = 5;
    private static final String[] NAMES = new String[]{"Victory", "Nautilus", "misfortune"};
    static private List<Ship> ships = new ArrayList<>();

    public static void main(String[] args) {
        Random random = new Random();
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < SHIPS_ACCEPT; i++) {
            ships.add(new Ship(NAMES[random.nextInt(NAMES.length)], random.nextInt(6), random.nextInt(6), Task.ACCEPT_CARGO));
        }
        for (int i = 0; i < SHIPS_DELIVER; i++) {
            ships.add(new Ship(NAMES[random.nextInt(NAMES.length)], random.nextInt(6), random.nextInt(6), Task.DELIVER));
        }

        for (int i = 0; i < SHIPS_DELIVER + SHIPS_ACCEPT; i++) {
            threads.add(new Thread(ships.get(i)));
        }

        try {
            for (Thread thread : threads) {
                thread.start();
                Thread.sleep(random.nextInt(200));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

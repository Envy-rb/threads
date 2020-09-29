package com.envy.threading.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Port {
    private static final Port INSTANCE = new Port();
    private static final int DOCKS_COUNT = 3;
    private static final int CAPACITY = 10;
    private int fulness;
    private final List<Dock> dockList;

    private Port() {
        dockList = new ArrayList<>(DOCKS_COUNT);
        for (int i = 0; i < DOCKS_COUNT; i++) {
            dockList.add(new Dock());
        }
    }

    public static Port getInstance() {
        return INSTANCE;
    }

    public List<Dock> getDockList() {
        return Collections.unmodifiableList(dockList);
    }

    public int getFulness() {
        return fulness;
    }

    public boolean addContainer(){
        boolean isAdded = false;

        if (CAPACITY - fulness > 0){
            fulness++;
            isAdded = true;
        }

        return isAdded;
    }

    public boolean removeContainer(){
        boolean isRemoved = false;

        if (fulness > 0){
            fulness--;
            isRemoved = true;
        }

        return isRemoved;
    }

}

package com.envy.threading.entity;

public class Dock extends Parental {
    private boolean isBusy;

    public Dock(){
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void registerShip(){
        isBusy = true;
    }

    public void unregisterShip(){
        isBusy = false;
    }
}

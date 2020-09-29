package com.envy.threading.entity;

import com.envy.threading.entity.state.State;
import com.envy.threading.entity.state.impl.EnteringtImpl;

public class Ship extends Parental implements Runnable {

    private Task task;
    private State state;
    private String name;
    private int holdAmount;
    private int containerValue;

    public Ship(String name, int holdAmount, int containerValue, Task task) {
        this.name = name;
        this.holdAmount = holdAmount;
        this.containerValue = containerValue;
        this.task = task;
        state = new EnteringtImpl();
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHoldAmount() {
        return holdAmount;
    }

    public void setHoldAmount(int holdAmount) {
        this.holdAmount = holdAmount;
    }

    public int getContainerValue() {
        return containerValue;
    }

    public void addContainer() {
        containerValue++;
    }

    public void removeContainer() {
        containerValue--;
    }

    public void doAction() {
        state.doAction(this);
    }

    public void nextState(State state) {
        this.state = state;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            doAction();
        }
    }
}

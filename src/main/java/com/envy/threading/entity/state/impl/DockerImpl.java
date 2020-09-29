package com.envy.threading.entity.state.impl;

import com.envy.threading.entity.Ship;
import com.envy.threading.entity.Task;
import com.envy.threading.entity.state.State;
import com.envy.threading.service.PortManager;
import org.apache.logging.log4j.Level;

public class DockerImpl implements State {
    @Override
    public void doAction(Ship ship) {
        PortManager manager = PortManager.getInstance();
        manager.arriveDock();
        logger.log(Level.INFO,String.format("{%s} arrived", ship.getName()));
        if (ship.getTask() == Task.DELIVER) {
            ship.nextState(new UnloadImpl());
        }
        if (ship.getTask() == Task.ACCEPT_CARGO) {
            ship.nextState(new UploadImpl());
        }
    }
}

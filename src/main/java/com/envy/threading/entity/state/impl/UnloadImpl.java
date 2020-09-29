package com.envy.threading.entity.state.impl;

import com.envy.threading.entity.Ship;
import com.envy.threading.entity.state.State;
import com.envy.threading.service.PortManager;
import org.apache.logging.log4j.Level;

public class UnloadImpl implements State {
    @Override
    public void doAction(Ship ship) {
        PortManager manager = PortManager.getInstance();
        if (manager.loadingStorage(ship)){
            ship.nextState(new LeavingImpl());
        }
        logger.log(Level.INFO, String.format("{%s}, unloaded", ship.getName()));
    }
}

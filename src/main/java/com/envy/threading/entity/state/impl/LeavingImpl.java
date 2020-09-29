package com.envy.threading.entity.state.impl;

import com.envy.threading.entity.Ship;
import com.envy.threading.entity.state.State;
import com.envy.threading.service.PortManager;
import org.apache.logging.log4j.Level;

public class LeavingImpl implements State {
    @Override
    public void doAction(Ship ship) {
        PortManager manager = PortManager.getInstance();
        manager.leaveDock();
        logger.log(Level.INFO, String.format("{%s} leaving", ship.getName()));
        Thread.currentThread().interrupt();
    }
}

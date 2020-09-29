package com.envy.threading.entity.state.impl;

import com.envy.threading.entity.Ship;
import com.envy.threading.entity.state.State;
import org.apache.logging.log4j.Level;

public class EnteringtImpl implements State {
    @Override
    public void doAction(Ship ship) {
        logger.log(Level.INFO,String.format("{%s} entered port ", ship.getName()));
        ship.nextState(new DockerImpl());
    }
}

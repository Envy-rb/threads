package com.envy.threading.entity.state;

import com.envy.threading.entity.Ship;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface State {
    public static Logger logger = LogManager.getLogger();

    void doAction(Ship ship);
}

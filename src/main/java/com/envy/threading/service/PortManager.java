package com.envy.threading.service;

import com.envy.threading.entity.Dock;
import com.envy.threading.entity.Port;
import com.envy.threading.entity.Ship;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PortManager {
    private static final Logger logger = LogManager.getLogger();
    private static final PortManager INSTANCE = new PortManager();
    private final Port port = Port.getInstance();
    private final Lock cargoLock = new ReentrantLock();
    private final Lock arriveDockLock = new ReentrantLock();
    private final Lock leaveDockLock = new ReentrantLock();

    private PortManager() {
    }

    public static PortManager getInstance() {
        return INSTANCE;
    }

    public void arriveDock() {
        boolean isArrived = false;
        arriveDockLock.lock();
        List<Dock> dockList = port.getDockList();
        while (!isArrived) {
            for (Dock dock : dockList) {
                if (!dock.isBusy()) {
                    dock.registerShip();
                    isArrived = true;
                    break;
                }
            }
        }
        arriveDockLock.unlock();
    }

    public boolean loadingStorage(Ship ship) {
        while (ship.getContainerValue() > 0) {
            try {
                cargoLock.lock();
                if (port.addContainer()) {
                    ship.removeContainer();
                    logger.log(Level.INFO, String.format("{%s} unloading {%s} {%s}", ship.getName(), ship.getContainerValue(), port.getFulness()));

                    TimeUnit.SECONDS.sleep(2);

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cargoLock.unlock();
        }

        return true;
    }

    public boolean shipmentStorage(Ship ship) {
        while (ship.getHoldAmount() - ship.getContainerValue() > 0) {
            try {
                cargoLock.tryLock(100, TimeUnit.SECONDS);
                if (port.removeContainer()) {
                    ship.addContainer();
                    logger.log(Level.INFO, String.format("{%s} uploading {%s} {%s}", ship.getName(), ship.getContainerValue(), port.getFulness()));
                    TimeUnit.SECONDS.sleep(2);

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cargoLock.unlock();
        }

        return true;
    }

    public void leaveDock() {
        List<Dock> dockList = port.getDockList();
        leaveDockLock.lock();
        for (Dock dock : dockList) {
            if (dock.isBusy()) {
                dock.unregisterShip();
                break;
            }
        }
        leaveDockLock.unlock();
    }
}

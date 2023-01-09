package agh.ics.oop.simulation;

import java.util.concurrent.atomic.AtomicBoolean;

public class Mutex {
    private final AtomicBoolean lock;
    private final Object mutex;

    public Mutex(boolean lock) {
        this.lock = new AtomicBoolean(lock);
        this.mutex = new Object();
    }

    public void step() {
        if (lock.get()) synchronized(mutex) {
            try {
                mutex.wait();
            } catch (InterruptedException ignored) {}
        }
    }

    public void lock() {
        lock.set(true);
    }

    public void unlock() {
        lock.set(false);

        synchronized(mutex) {
            mutex.notify();
        }
    }

    public boolean isLocked() {
        return lock.get();
    }
}

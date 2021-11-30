package com.jfreyberger;

import java.util.HashMap;

import javax.swing.SwingWorker;

public abstract class SingletonSwingWorker extends SwingWorker {

    abstract void initAndGo();

    private static HashMap<Class, SingletonSwingWorker> workers;
    public static void runWorker(SingletonSwingWorker newInstance) {
        if(workers == null) {
            workers = new HashMap<>();
        }
        if(!workers.containsKey(newInstance.getClass()) || workers.get(newInstance.getClass()).isDone()) {
            workers.put(newInstance.getClass(), newInstance);
            newInstance.initAndGo();
        }
    }
}
package main.omnihome.reactive.devices;

import main.omnihome.reactive.observer.Observer;

public class SmartAlarm implements Observer {
    @Override
    public void update() {
        System.out.println("SmartAlarm received motion event.!");

    }


}
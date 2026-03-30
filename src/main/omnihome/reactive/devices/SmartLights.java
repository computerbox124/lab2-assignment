package main.omnihome.reactive.devices;

import main.omnihome.reactive.observer.Observer;

public class SmartLights implements Observer {
    private boolean on;

    @Override
    public void update() {
        System.out.println("SmartLights received motion event!");
        turnOn();
    }

    public void turnOn()
    {
        on = true;
        System.out.println("SmartLights turning on...");
    }

    public void turnOff()
    {
        on = false;
        System.out.println("SmartLights turning off...");
    }

    public boolean isOn()
    {
        return on;
    }

}
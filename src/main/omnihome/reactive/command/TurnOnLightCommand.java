package main.omnihome.reactive.command;

import main.omnihome.reactive.devices.SmartLights;

public class TurnOnLightCommand implements Command {
    private final SmartLights smartLights;

    public TurnOnLightCommand(SmartLights smartlights){
        this.smartLights = smartlights;
    }

    @Override
    public void execute() {
        smartLights.turnOn();
    }

    @Override
    public void undo() {
        smartLights.turnOff();
    }
}
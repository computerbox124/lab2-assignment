package main.omnihome.reactive.devices;

import main.omnihome.reactive.observer.Observer;
import main.omnihome.reactive.strategy.AlertStrategy;
import main.omnihome.reactive.strategy.LoudSirenStrategy;
import main.omnihome.reactive.strategy.SilentPushStrategy;

import java.util.HashMap;
import java.util.Map;

public class SmartAlarm implements Observer {
    private final Map<String, AlertStrategy> strategyRegistry;
    private AlertStrategy currentStrategy;
    private boolean armed;

    public SmartAlarm() {
        this.strategyRegistry = new HashMap<>();
        strategyRegistry.put("SILENT", new SilentPushStrategy());
        strategyRegistry.put("SIREN", new LoudSirenStrategy());
        this.currentStrategy = strategyRegistry.get("SILENT");
        this.armed = false;
    }

    @Override
    public void update() {
        System.out.println("SmartAlarm received motion event.");
        currentStrategy.executeAlert();
    }

    public void setAlertStrategy(String mode) {
        AlertStrategy selectedStrategy = strategyRegistry.get(mode);

        if (selectedStrategy == null) {
            throw new IllegalArgumentException("Unknown alert strategy: " + mode);
        }

        currentStrategy = selectedStrategy;
        System.out.println("SmartAlarm strategy changed to " + mode + ".");
    }

}
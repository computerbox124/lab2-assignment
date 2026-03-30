package main.omnihome.reactive.sensors;

import main.omnihome.reactive.observer.Observer;
import main.omnihome.reactive.observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class MotionSensor implements Subject {
    private final List<Observer> observers;

    public MotionSensor() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer observer) {
        if (observer != null) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void detectMotion() {
        System.out.println("MotionSensor detected motion.");
        notifyObservers();
    }
}
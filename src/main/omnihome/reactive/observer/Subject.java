package main.omnihome.reactive.observer;

public interface Subject {
    void raddObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
package main.omnihome.reactive.command;

public interface Command {
    void execute();
    void undo();
}
package main.omnihome.reactive.command;

public class NoCommand implements Command {
    @Override
    public void execute() {
        System.out.println("No command assigned to this button.");
    }

    @Override
    public void undo() {
        System.out.println("Nothing to undo.");
    }
}
package main.omnihome.reactive.command;

public class SmartRemote {
    private final Command[] commands;
    private final Command noCommand;
    private Command lastCommand;


    public SmartRemote(int slotCount) {
        this.noCommand = new NoCommand();
        this.commands = new Command[slotCount];
        this.lastCommand = noCommand;
        for (int i = 0; i < commands.length; i++) {
            commands[i] = noCommand;
        }
    }

    public void setCommand(int slot, Command command) {
        if (!isValidSlot(slot)) {
            System.out.println("Invalid button slot.");
            return;
        }
        commands[slot] = command == null ? noCommand : command;
    }

    public void pressButton(int slot) {
        if (!isValidSlot(slot)) {
            System.out.println("Invalid button slot.");
            return;
        }
        commands[slot].execute();
        lastCommand = commands[slot];
    }

    public void pressUndo() {
        lastCommand.undo();
        lastCommand = noCommand;
    }

    private boolean isValidSlot(int slot) {
        return slot >= 0 && slot < commands.length;
    }
}
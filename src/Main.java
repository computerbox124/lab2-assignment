import main.omnihome.reactive.command.ArmAlarmCommand;
import main.omnihome.reactive.command.SmartRemote;
import main.omnihome.reactive.command.TurnOnLightCommand;
import main.omnihome.reactive.devices.SmartAlarm;
import main.omnihome.reactive.devices.SmartLights;
import main.omnihome.reactive.sensors.MotionSensor;

public class Main {
    public static void main(String[] args) {
        System.out.println("------- Smart Hub OmniHome initialization starts ------ ");
        MotionSensor motionSensor = new MotionSensor();
        SmartLights smartLights = new SmartLights();
        SmartAlarm smartAlarm = new SmartAlarm();

        System.out.println("Setting alarm strategy to SILENT.");
        smartAlarm.setAlertStrategy("SILENT");

        System.out.println("Subscribing smart devices to the motion sensor.");
        motionSensor.addObserver(smartLights);
        motionSensor.addObserver(smartAlarm);

        System.out.println();
        System.out.println("First motion test:");
        motionSensor.detectMotion();

        System.out.println();
        System.out.println("Changing alarm strategy to SIREN.");
        smartAlarm.setAlertStrategy("SIREN");

        System.out.println("Second motion test:");
        motionSensor.detectMotion();

        System.out.println();
        System.out.println("Testing the smart remote:");
        SmartRemote smartRemote = new SmartRemote(4);
        smartRemote.setCommand(0, new TurnOnLightCommand(smartLights));
        smartRemote.setCommand(1, new ArmAlarmCommand(smartAlarm));

        System.out.println("Pressing button 0.");
        smartRemote.pressButton(0);

        System.out.println("Pressing button 1.");
        smartRemote.pressButton(1);

        System.out.println("Pressing undo.");
        smartRemote.pressUndo();
    }
}
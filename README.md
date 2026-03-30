# OmniHome Smart Hub - Lab 2
This is a simple Java lab project. The goal is to show design patterns using a smart home example.
It combines the Observer, Strategy, and Command patterns in one program.

## Goal

The system supports three behaviors:

- a motion sensor broadcasts events to multiple smart devices
- the alarm changes its alert behavior at runtime by switching strategy (`SILENT` or `SIREN`)
- a smart remote controls devices through commands and supports undo

## Implemented patterns

### 1. Observer Pattern

Used to connect one sensor to multiple smart devices.

- `Subject` defines `addObserver`, `removeObserver`, and `notifyObservers`
- `Observer` defines `update`
- `MotionSensor` is the concrete subject
- `SmartLights` and `SmartAlarm` are concrete observers

When `detectMotion()` is called on `MotionSensor`, all subscribed observers react immediately.

### 2. Strategy Pattern

Used to change the alarm response without changing the alarm class itself.

- `AlertStrategy` defines `executeAlert()`
- `SilentPushStrategy` sends a silent notification
- `LoudSirenStrategy` sounds the siren
- `SmartAlarm` stores the current strategy and switches it through a `Map<String, AlertStrategy>`

The alarm can change from `SILENT` to `SIREN` at runtime through direct map lookup.
This strategy selection is independent from arming/disarming the alarm.

### 3. Command Pattern

Used to decouple the remote control from the smart devices.

- `Command` defines `execute()` and `undo()`
- `TurnOnLightCommand` controls `SmartLights`
- `ArmAlarmCommand` controls `SmartAlarm`
- `SmartRemote` stores commands in button slots
- `NoCommand` is the safe default command for empty slots and empty undo state

The remote only executes command objects. It does not know device details.

## Project structure

```text
src/
  Main.java
  main/omnihome/reactive/
    command/
      ArmAlarmCommand.java
      Command.java
      NoCommand.java
      SmartRemote.java
      TurnOnLightCommand.java
    devices/
      SmartAlarm.java
      SmartLights.java
    observer/
      Observer.java
      Subject.java
    sensors/
      MotionSensor.java
    strategy/
      AlertStrategy.java
      LoudSirenStrategy.java
      SilentPushStrategy.java
```

## Main simulation flow

`Main.java` runs the assignment scenario in this order:

1. Create `MotionSensor`, `SmartLights`, and `SmartAlarm`
2. Set the alarm strategy to `SILENT`
3. Subscribe both devices to the sensor
4. Trigger motion once
5. Change the alarm strategy to `SIREN`
6. Trigger motion again
7. Create a `SmartRemote`
8. Assign button `0` to the light command
9. Assign button `1` to the alarm command
10. Press button `0`
11. Press button `1`
12. Press undo

## How to compile and run

```bash
mkdir -p out
javac -d out $(find src -name "*.java")
java -cp out Main
```

## Example output

```text
------- Smart Hub OmniHome initialization starts ------ 
Setting alarm strategy to SILENT.
SmartAlarm strategy changed to SILENT.
Subscribing smart devices to the motion sensor.

First motion test:
MotionSensor detected motion.
SmartLights received motion event!
SmartLights turning on...
SmartAlarm received motion event.
Sending silent push notification to homeowner's phone.

Changing alarm strategy to SIREN.
SmartAlarm strategy changed to SIREN.
Second motion test:
MotionSensor detected motion.
SmartLights received motion event!
SmartLights turning on...
SmartAlarm received motion event.
SOUNDING 120dB SIREN!

Testing the smart remote:
Pressing button 0.
SmartLights turning on...
Pressing button 1.
SmartAlarm is armed now.
Pressing undo.
SmartAlarm is disarmed now.
```
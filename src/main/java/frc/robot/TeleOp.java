package frc.robot;

public class TeleOp{
    private static TeleOp instance;
    private static XBoxController driver, manip;

    public static TeleOp getInstance() {
		if (instance == null)
			instance = new TeleOp();
		return instance;
    }

    public TeleOp(){
        driver = new XBoxController(0);
        manip = new XBoxController(1);
    }
    
    public static void run(){
        Limelight.refresh();
        DriveTrain.arcadeDrive(
            Utils.expoDeadZone(driver.getLeftStickXAxis()), 
            Utils.expoDeadZone(driver.getRightStickYAxis())
        );
        Elevator.lift(Utils.expoDeadZone(manip.getLeftStickYAxis()));

        Elevator.rollerFlip(manip.getLeftBumper());
        Elevator.clawFlipUp(manip.getRightBumper());
    }


}
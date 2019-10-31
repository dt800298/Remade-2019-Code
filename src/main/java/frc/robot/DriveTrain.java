package frc.robot;


import com.ctre.phoenix.motorcontrol.*;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class DriveTrain{
    public static DriveTrain instance;
    private static CANSparkMax rightFront,  leftFront, rightMiddle, leftMiddle, rightBack, leftBack;

    public static DriveTrain getInstance() {
		if (instance == null)
			instance = new DriveTrain();
		return instance;
    }

    public DriveTrain(){
        rightFront = new CANSparkMax(Constants.DT_TALON_RIGHTFRONT, MotorType.kBrushless);
        leftFront = new CANSparkMax(Constants.DT_TALON_LEFTFRONT, MotorType.kBrushless);
        rightMiddle = new CANSparkMax(Constants.DT_TALON_LEFTMIDDLE, MotorType.kBrushless);
        leftMiddle = new CANSparkMax(Constants.DT_TALON_RIGHTMIDDLE, MotorType.kBrushless);
        rightBack = new CANSparkMax(Constants.DT_TALON_RIGHTBACK, MotorType.kBrushless);
        leftBack = new CANSparkMax(Constants.DT_TALON_LEFTBACK, MotorType.kBrushless);




    }

    public static void drive(double leftPower, double rightPower){
        rightFront.set(rightPower);
        rightMiddle.set(rightPower);
        rightBack.set(rightPower);
        leftFront.set(leftPower);
        leftMiddle.set(leftPower);
        leftBack.set(leftPower);
        


    }
    public static void arcadeDrive(double fwd, double tur) {
		
		drive(Utils.ensureRange(fwd + tur, -1d, 1d), Utils.ensureRange(fwd - tur, -1d, 1d));
	}
}
package frc.robot;

import com.ctre.phoenix.motorcontrol.*;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Solenoid;



public class Elevator
{
    public static Elevator instance;
    public static CANSparkMax  left, right;
    public static Solenoid claw, rollerBar;


    public static Elevator getInstance() {
		if (instance == null)
			instance = new Elevator();
		return instance;
    }

    public Elevator()
    {
        left = new CANSparkMax(Constants.ELEVATOR_SPARKMAX_LEFT, MotorType.kBrushless);
        right = new CANSparkMax(Constants.ELEVATOR_SPARKMAX_RIGHT, MotorType.kBrushless);
        claw = new Solenoid(Constants.SOLENOID_WHATCH);
        rollerBar = new Solenoid(Constants.SOLENOID_ROLLERBAR);
    }
    

    
    public static void lift(double power){
        left.set(power);
        right.set(-power);

    }

    //whatch flip
    public static void clawFlipUp(boolean isPressed)
    {
        claw.set(isPressed);
    }

    public static void rollerFlip(boolean isPressed)
    {
        rollerBar.set(isPressed);

    }

    public static double getEncoders(){
        return right.getEncoder().getPosition();
    }










}

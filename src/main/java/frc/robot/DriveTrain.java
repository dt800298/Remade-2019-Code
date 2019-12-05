package frc.robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import com.revrobotics.CANPIDController;
import edu.wpi.first.wpilibj.SPI;
import com.ctre.phoenix.motorcontrol.*;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class DriveTrain implements PIDOutput{
    public static DriveTrain instance;
    private static CANSparkMax rightFront,  leftFront, rightMiddle, leftMiddle, rightBack, leftBack;
    public static AHRS gyro;
    private static PIDController gyropid;
    

    public static DriveTrain getInstance() {
		if (instance == null)
			instance = new DriveTrain();
         return instance;
        

        
    }

    public DriveTrain(){
        rightFront = new CANSparkMax(Constants.DT_TALON_RIGHTFRONT, MotorType.kBrushless);
        leftFront = new CANSparkMax(Constants.DT_TALON_LEFTFRONT, MotorType.kBrushless);
        rightMiddle = new CANSparkMax(Constants.DT_TALON_RIGHTMIDDLE, MotorType.kBrushless);
        leftMiddle = new CANSparkMax(Constants.DT_TALON_LEFTMIDDLE, MotorType.kBrushless);
        rightBack = new CANSparkMax(Constants.DT_TALON_RIGHTBACK, MotorType.kBrushless);
        leftBack = new CANSparkMax(Constants.DT_TALON_LEFTBACK, MotorType.kBrushless);

        gyro = new AHRS(SPI.Port.kMXP);

        gyropid = new PIDController(0.5, 0, 0, gyro, this);
        gyropid.setInputRange(-180d, 180d);
        gyropid.setOutputRange(-1.0, 1.0);
        gyropid.setAbsoluteTolerance(2d);

        gyropid.setContinuous(true);

       
    }

    public void pidWrite(double epic){

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
    

    public static double getRoll(){
        return gyro.getRoll();
    }
    public static double getYaw(){
        return gyro.getYaw();
    }
    public static double getPitch(){
        return gyro.getPitch();
    }

    public static void turnToAngle(double angle){
       
        if(!gyropid.isEnabled()){
            System.out.println("PID Enabled");
            //leds when figured out
            gyropid.reset();
            gyropid.enable();
        }
        gyropid.setSetpoint(angle);
        //moved from top
    }

    
    //why change integral value??
    public static void pidrite(double output) {

        if (Math.abs(gyropid.getError()) < 5d) {
            gyropid.setPID(gyropid.getP(), 0, gyropid.getD());
        } else {
            gyropid.setPID(gyropid.getP(), 0, gyropid.getD());
        }

        
        DriveTrain.arcadeDrive(output, 0);

    }

    public static void pidDisable(){
        System.out.println("PID Disabled");
        gyropid.disable();

    }

    public static void pidEnable(){
        gyropid.enable();
    }

    public static boolean ispidEnabled(){
        return gyropid.isEnabled();
    }

    public static double getPid(){

        return gyropid.get();
    }

    public static void lineUp(){
        Limelight.refresh();
        //double target = DriveTrain.getAHRS() + Limelight.getTx();
        double target = 45;
        DriveTrain.pidrite(gyropid.get());
      
        
        //if( (Limelight.getTx() <= 3d || Limelight.getTx() >= -3d)){
        //    DriveTrain.pidDisable();
        //}
    }






        
    }

package frc.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTable;

public class Limelight {
    public static Limelight instance;

    public static NetworkTable nt;

    public static double tx = 0;
    public static double ty = 0;
    public static double tv = 0;
    public static double ta = 0;


    public static Limelight getInstance() {
		if (instance == null)
			instance = new Limelight();
        return instance;
    }

    public Limelight(){
        nt = NetworkTableInstance.getDefault().getTable("limelight");
    }

    public static void refresh(){
        nt = NetworkTableInstance.getDefault().getTable("limelight");
        tx = nt.getEntry("tx").getDouble(0);
        ty = nt.getEntry("ty").getDouble(0);
        tv = nt.getEntry("tv").getDouble(0);
        ta = nt.getEntry("ta").getDouble(0);


    }

    

    

    public static double getTx(){
        return tx;
    }
    public static double getTy(){
        return ty;
    }
    public static double getTv(){
        return tv;
    }
    public static double getTa(){
        return ta;
    }

    public static void lineUp(){
        Limelight.refresh();
        double target = DriveTrain.getAHRS() + Limelight.getTx();
        DriveTrain.turnToAngle(target);
        System.out.println("TARGET: " + target);
        System.out.println("AHRS: " + DriveTrain.getAHRS());
        if( (Limelight.getTx() >= 5d || Limelight.getTx() <= -5d)){
            DriveTrain.pidDisable();
        }
    }



}
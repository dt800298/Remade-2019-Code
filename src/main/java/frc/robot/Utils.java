package frc.robot;

public class Utils{
    public static double expoDeadZone(double input){
        if(-.05>input){
            return -1.108*(input+.05)*(input+.05);

        }
        else if(.05<input){
            return 1.108*(input-.05)*(input+.05);
        }
        else{
            return 0;
        }
    }
    public static double ensureRange(double v, double min, double max) {
		return Math.min(Math.max(v, min), max);
	}

}
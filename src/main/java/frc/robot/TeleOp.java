package frc.robot;

import javax.sound.sampled.Line;

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
       

        //Manip controls

        Elevator.lift(Utils.expoDeadZone(manip.getLeftStickYAxis()));
        Elevator.rollerFlip(manip.getLeftBumper());
        Elevator.clawFlipUp(manip.getRightBumper());


        //driver controls

        PIDLineup();

        
    }


    //Driver controls

    public static void noPIDlineup(boolean leftBumper)
    {
        double error = Limelight.getTx();
        if(leftBumper == true){

            if(error >= 7)
            {
                DriveTrain.drive(0, 0.1);
            }

            if(error <= -7)
            {
                DriveTrain.drive(0, -0.1);
            }
        }
    }

    public static void PIDLineup()
    {
        if(driver.getLeftBumper()){
            //change to a "hasvalidtargets" function
              if(Limelight.getTv() == 1){
                  if(driver.getLeftBumper()){
  
                      if(Limelight.getTx() <= 6d && Limelight.getTx() >= -6d){
                          DriveTrain.arcadeDrive(0, 0.2);
                      }
                      else{
                          Limelight.lineUp();
                      }
                   }else{
                       if(DriveTrain.ispidEnabled()){
                           DriveTrain.pidDisable();
                       }
                       DriveTrain.arcadeDrive(Utils.expoDeadzone(driver.getLeftStickXAxis()), driver.getRightStickYAxis());)
                   }
              }
          }

    }
    



    //Manip controls



}
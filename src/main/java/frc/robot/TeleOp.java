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

    public static void noPIDLineup(boolean leftBumper)
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
              //if(Limelight.getTv() == 1){
                  if(driver.getLeftBumper()){
  
                      /*if(Limelight.getTx() <= 3d && Limelight.getTx() >= -3d){
                          DriveTrain.arcadeDrive(0, 0.1);
                      }
                      */
                      //else{
                          DriveTrain.pidEnable();
                          DriveTrain.lineUp();
                          System.out.println("Lineup activated");
                     // }
                   }else{
                       if(DriveTrain.ispidEnabled()){
                           DriveTrain.pidDisable();
                           
                               
                           
                       }
                      
                   }
             // }
          }
        else{
            DriveTrain.arcadeDrive(Utils.expoDeadZone(driver.getLeftStickXAxis()), Utils.expoDeadZone(driver.getRightStickYAxis()));
        }

    }
    



    //Manip controls



}
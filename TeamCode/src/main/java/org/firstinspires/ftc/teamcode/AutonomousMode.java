package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Autonomous(name="IROBOT")

public class AutonomousMode extends LinearOpMode{
  public void runOpMode(){
    Bot craig = new Bot(hardwareMap.get(DcMotor.class, "flMotor"),hardwareMap.get(DcMotor.class, "frMotor"),hardwareMap.get(DcMotor.class, "blMotor"),hardwareMap.get(DcMotor.class, "brMotor"));
    craig.initMotors();
    waitForStart();
    
    int robotPosition = 0;
    
    //When moving sideways fl(rev) has to move back, fr has to move back bl has to move forward and br(rev) has to move forward()
    while(opModeIsActive()){
      robotPosition = 100;
      ElapsedTime timer = new ElapsedTime();
      double[] positions = {0.25,0.25,0.25,0.25}; 
      
      //telemetry.addLine("The encoder is right here:"+craig.getPosition());
      
      /*while(!(timer.resolution.() > 199)){
        craig.setSpeed(speeds); 
      }*/
      
      //craig.setPosition(positions);
      craig.setSpeed(positions);
    }
  }
  // todo: write your code here
}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "areyouahumanQUESTIONMARK")
public class TeleOpMode extends LinearOpMode {
  public void runOpMode(){
    Bot deez = new Bot(hardwareMap.get(DcMotor.class, "backMotor"),hardwareMap.get(DcMotor.class, "leftMotor"),hardwareMap.get(DcMotor.class, "rightMotor"),hardwareMap.get(DcMotor.class, "frontMotor"));
    deez.initMotors();
    waitForStart();
    
    double leftAnalogY = 0;
    double leftAnalogX = 0;
    double rightAnalogX = 0;

    double maxSpeed = 1;
    double speedMultiplier = 0.75;
    double robotSpeed = 0;

    while(opModeIsActive()){
      robotSpeed = maxSpeed*speedMultiplier;
      
      rightAnalogX = gamepad1.right_stick_x;
      leftAnalogY = gamepad1.left_stick_y;
      leftAnalogX = gamepad1.left_stick_x;
      
      //double[] speeds = {robotSpeed*(leftAnalogY-leftAnalogX-rightAnalogX),robotSpeed*(leftAnalogY-leftAnalogX+rightAnalogX),robotSpeed*(leftAnalogY+leftAnalogX+rightAnalogX),robotSpeed*(leftAnalogY+leftAnalogX-rightAnalogX)};
      //double[] speeds = {robotSpeed*(leftAnalogY-rightAnalogX),robotSpeed*(leftAnalogY+rightAnalogX),robotSpeed*(leftAnalogY+rightAnalogX),robotSpeed*(leftAnalogY-rightAnalogX)};
    
      double[] speeds = {robotSpeed*(leftAnalogX+rightAnalogX),robotSpeed*(leftAnalogY-rightAnalogX),robotSpeed*(leftAnalogX-rightAnalogX),robotSpeed*(leftAnalogY+rightAnalogX)};
    
      deez.setSpeed(speeds);
      }
    }
}

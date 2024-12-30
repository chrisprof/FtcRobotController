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
    Bot craig = new Bot(hardwareMap.get(DcMotor.class, "rightMotor"),hardwareMap.get(DcMotor.class, "leftMotor"),hardwareMap.get(DcMotor.class, "liftMotor"));
    craig.initLift();

    waitForStart();
    while(opModeIsActive()){
      craig.moveLiftTo(1500);
      stop();
    }
  }
}
package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "human mode")
public class TeleOpMode extends LinearOpMode {
  public void runOpMode(){

    Bot deez = new Bot(hardwareMap.get(DcMotor.class, "rightMotor"),hardwareMap.get(DcMotor.class, "leftMotor"),hardwareMap.get(DcMotor.class, "liftMotor"));
    deez.initWheels();
    deez.initLift();

    //Servo Variables and Properties
    Servo scoringArm = hardwareMap.get(Servo.class,"scoringArm");
    Servo scoringArmClaw = hardwareMap.get(Servo.class,"scoringArmClaw");
    Servo intakeArm = hardwareMap.get(Servo.class,"intakeArm");
    Servo intakeArmClaw = hardwareMap.get(Servo.class,"intakeArmClaw");

    double servoIncrement = 0.005;
    double clawIncrement;

    double scoringArmInput;
    double intakeArmInput;
    double scoringArmClawInput;
    double intakeArmClawInput;

    double scoringArmLimit = 0.8;
    double scoringArmClawLimit = 0.55;
    double intakeArmLimit = 0.8;
    double intakeArmClawLimit = 0.225;

    double intakeArmRealFloor = intakeArm.MIN_POSITION+0.04;
    double intakeArmFloorOffset = intakeArm.MIN_POSITION+0.1;

    scoringArm.setPosition(scoringArm.MIN_POSITION);
    intakeArm.setPosition(intakeArmFloorOffset);
    scoringArmClaw.setPosition(scoringArmClawLimit);
    intakeArmClaw.setPosition(intakeArmClawLimit);

    //double wheelSpeed;
    double rightWheelSpeed;
    double leftWheelSpeed;
    double speedMultiplier = 0.75;

    //Lift Variables
    int liftPosition;
    int floor=250;
    int ceil=2100;
    double liftInput=0;
    double liftPower=0;

    double inputMultiplier=0.25;
    double powerConstant=0.00125;

    waitForStart();
    while(opModeIsActive()){

      //wheelSpeed = Math.sqrt(square(gamepad1.left_stick_y)+square(gamepad1.left_stick_x));
      rightWheelSpeed=gamepad1.left_stick_y+gamepad1.left_stick_x;
      leftWheelSpeed=gamepad1.left_stick_y-gamepad1.left_stick_x;
      deez.setWheelSpeed(rightWheelSpeed,leftWheelSpeed);

      //Servo Variables Init/Reset
      scoringArmClawLimit=0.55;
      clawIncrement=0.05;

      scoringArmInput=0;
      intakeArmInput=0;
      scoringArmClawInput = clawIncrement*gamepad1.right_trigger;
      intakeArmClawInput = servoIncrement*gamepad1.left_trigger;

      //Lift Variables Init/Reset
      liftInput=0;
      liftPosition = deez.liftMotor.getCurrentPosition();

      if(gamepad1.a){
        intakeArmInput+=servoIncrement;
      }

      if(gamepad1.b){
        scoringArmInput+=servoIncrement;
        scoringArmClawLimit=0.36;

        clawIncrement=0.2;
      }

      boolean isScoringArmActive = gamepad1.b||scoringArm.getPosition()>0||scoringArmClaw.getPosition()==scoringArmClaw.MIN_POSITION;

      if(isScoringArmActive){
        intakeArmClawInput=0;
        intakeArmInput=0;

        intakeArm.setPosition(intakeArmRealFloor);
      }

      if(scoringArm.getPosition()+scoringArmInput <= scoringArmLimit && scoringArmInput > 0){
        scoringArm.setPosition(scoringArm.getPosition()+scoringArmInput);
      }

      else{
        scoringArm.setPosition(scoringArm.getPosition()-servoIncrement);
      }

      scoringArmClaw.setPosition(scoringArmClaw.getPosition()-scoringArmClawInput);

      if(scoringArmClaw.getPosition()+clawIncrement<=scoringArmClawLimit && scoringArmClawInput==0){
        scoringArmClaw.setPosition(scoringArmClaw.getPosition()+clawIncrement);
      }

      if(scoringArmClaw.getPosition()>scoringArmClawLimit){
        scoringArmClaw.setPosition(scoringArmClawLimit);
      }

      if(intakeArm.getPosition()+intakeArmInput <= intakeArmLimit && intakeArmInput > 0){
        intakeArm.setPosition(intakeArm.getPosition()+intakeArmInput);
      }

      else if(intakeArm.getPosition()-servoIncrement>=intakeArmFloorOffset){
        intakeArm.setPosition(intakeArm.getPosition()-servoIncrement);
      }

      if(intakeArm.getPosition()<intakeArmFloorOffset&&!isScoringArmActive){
        intakeArm.setPosition(intakeArmFloorOffset);
      }

      intakeArmClaw.setPosition(intakeArmClaw.getPosition()-intakeArmClawInput);

      if(intakeArmClaw.getPosition()+intakeArmClawInput <= intakeArmClawLimit && intakeArmClawInput==0){
        intakeArmClaw.setPosition(intakeArmClaw.getPosition()+servoIncrement);
      }

      //Lift Control Processing

      //Switch these to gamepad2 in actual comp
      if(gamepad1.dpad_up){
        liftInput+=1;
      }

      if(gamepad1.dpad_down)
      {
        liftInput-=1;
      }

      if(liftPosition>floor && liftInput==0) {
        liftPower = powerConstant;
      }

      else if(liftPosition<ceil*0.9){
        liftPower = liftInput;
      }
      else if (liftPosition<ceil) {
        liftPower = liftInput*inputMultiplier;
      }

      else{
        liftPower = powerConstant;
      }

      if(liftInput<0 && liftPosition>0) {
        liftPower = liftInput;
      }

      deez.setLiftSpeed(liftPower);

      if(liftPosition<100&&liftInput==0){
        deez.resetLiftEncoder();
      }

      telemetry.addData("Scoring Arm Servo Position:",scoringArm.getPosition());
      telemetry.addData("Scoring Arm Claw Servo Position:",scoringArmClaw.getPosition());
      telemetry.addData("Intake Arm Servo Position:",intakeArm.getPosition());
      telemetry.addData("Intake Arm Claw Servo Position:",intakeArmClaw.getPosition());
      telemetry.addData("Lift Encoder Position:",deez.liftMotor.getCurrentPosition());
      telemetry.addData("Lift Input:", liftInput);
      telemetry.update();

      if(isStopRequested()){
        deez.moveLiftTo(0);
      }
    }
  }
  double square(double num){
    return num*num;
  }
}

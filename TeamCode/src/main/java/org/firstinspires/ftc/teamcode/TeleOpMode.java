package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "human mode")
public class TeleOpMode extends LinearOpMode {
  public void runOpMode(){

    Bot deez = new Bot(hardwareMap.get(DcMotor.class, "rightMotor"),hardwareMap.get(DcMotor.class, "leftMotor"),hardwareMap.get(DcMotor.class, "liftMotor"));
    deez.initWheels();

    Servo scoringArm = hardwareMap.get(Servo.class,"scoringArmServo");
    Servo scoringArmClaw = hardwareMap.get(Servo.class,"scoringArmClaw");
    Servo intakeArm = hardwareMap.get(Servo.class,"intakeArm");
    Servo intakeArmClaw = hardwareMap.get(Servo.class,"intakeArmClaw");

    double servoIncrement = 0.005;
    int liftIncrement = 1;

    double scoringArmInput=0;
    double intakeArmInput=0;
    double scoringArmClawInput=0;
    double intakeArmClawInput=0;
    int liftInput=0;

    double scoringArmLimit = /*0.3875*/0.3875*2;
    double scoringArmClawLimit = /*0.3875*/0.3875*2;
    double intakeArmLimit = /*0.3875*/0.3875*1.5;
    double intakeArmClawLimit = /*0.3875*/0.3875*1.5;
    int liftLimit=180;

    scoringArm.setPosition(scoringArm.MIN_POSITION);
    intakeArm.setPosition(intakeArm.MIN_POSITION);
    scoringArmClaw.setPosition(scoringArm.MIN_POSITION);
    intakeArmClaw.setPosition(intakeArm.MIN_POSITION);

    double robotSpeed;
    double maxSpeed = 1;
    double speedMultiplier = 0.75;

    waitForStart();
    while(opModeIsActive()){

      robotSpeed = maxSpeed*speedMultiplier;
      deez.setWheelSpeed((gamepad1.left_stick_y-gamepad1.left_stick_x)*robotSpeed,(gamepad1.left_stick_y+gamepad1.left_stick_x)*robotSpeed);

      intakeArmClawInput = servoIncrement*gamepad1.left_trigger;
      scoringArmClawInput = servoIncrement*gamepad1.right_trigger;

      liftInput=liftIncrement*Math.round(gamepad1.left_stick_y);

      if(gamepad1.a){
        intakeArmInput+=servoIncrement;
      }

      if(gamepad1.b){
        scoringArmInput+=servoIncrement;
      }

      if(scoringArm.getPosition()+scoringArmInput <= scoringArmLimit && scoringArmInput > 0){
        scoringArm.setPosition(scoringArm.getPosition()+scoringArmInput);
      }

      else{
        scoringArm.setPosition(scoringArm.getPosition()-servoIncrement);
      }

      if(intakeArm.getPosition()+intakeArmInput <= intakeArmLimit && intakeArmInput > 0){
        intakeArm.setPosition(intakeArm.getPosition()+intakeArmInput);
      }

      else{
        intakeArm.setPosition(intakeArm.getPosition()-servoIncrement);
      }

      if(scoringArmClaw.getPosition()+scoringArmClawInput <= scoringArmClawLimit && scoringArmClawInput > 0){
        scoringArmClaw.setPosition(scoringArmClaw.getPosition()+scoringArmClawInput);
      }

      else{
        scoringArmClaw.setPosition(scoringArm.getPosition()-servoIncrement);
      }

      if(intakeArmClaw.getPosition()+intakeArmClawInput <= intakeArmClawLimit && intakeArmClawInput > 0){
        intakeArmClaw.setPosition(intakeArmClaw.getPosition()+intakeArmClawInput);
      }

      else{
        intakeArmClaw.setPosition(intakeArmClaw.getPosition()-servoIncrement);
      }

      if(deez.liftMotor.getCurrentPosition()+(liftIncrement*liftInput) <= liftLimit && liftInput > 0){
        deez.liftMotor.setTargetPosition(deez.liftMotor.getCurrentPosition()+(liftIncrement*liftInput));
      }

      else{
        deez.liftMotor.setTargetPosition(deez.liftMotor.getCurrentPosition()-liftIncrement);
      }

      telemetry.addData("Scoring Arm Servo Position:",scoringArm.getPosition());
      telemetry.addData("Scoring Arm Claw Servo Position:",scoringArmClaw.getPosition());
      telemetry.addData("Intake Arm Servo Position:",intakeArm.getPosition());
      telemetry.addData("Intake Arm Claw Servo Position:",intakeArmClaw.getPosition());
      telemetry.addData("Lift Encoder Position:",deez.liftMotor.getCurrentPosition());
      telemetry.update();
      }
    }
}

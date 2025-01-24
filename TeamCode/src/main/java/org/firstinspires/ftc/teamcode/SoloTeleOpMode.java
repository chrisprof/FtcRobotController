package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name = "solo mode")
public class SoloTeleOpMode extends LinearOpMode {
    public void runOpMode(){

        FtcDashboard db = FtcDashboard.getInstance();
        Telemetry dbtelemetry = db.getTelemetry();

        Bot deez = new Bot(hardwareMap);
        deez.init();

        //Servo Variables
        double servoIncrement = 0.005;
        double clawIncrement;

        double scoringArmInput;
        double intakeArmInput;
        double scoringArmClawInput;
        double intakeArmClawInput;

        double scoringArmLimit = 0.8;
        double scoringArmClawLimit = 0.55;
        double intakeArmLimit = 0.8;
        double intakeArmClawLimit = 0.22;

        double intakeArmRealFloor = deez.intakeArm.MIN_POSITION+0.04;
        double intakeArmFloorOffset = deez.intakeArm.MIN_POSITION+0.1;

        deez.scoringArm.setPosition(deez.scoringArm.MIN_POSITION);
        deez.intakeArm.setPosition(intakeArmFloorOffset);
        deez.scoringArmClaw.setPosition(scoringArmClawLimit);
        deez.intakeArmClaw.setPosition(intakeArmClawLimit);

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
                scoringArmClawLimit=0.28;

                clawIncrement=0.2;
            }

            boolean isScoringArmActive = gamepad1.b||deez.scoringArm.getPosition()>0;

            if(isScoringArmActive){
                intakeArmClawInput=0;
                intakeArmInput=0;

                deez.intakeArm.setPosition(intakeArmRealFloor);
            }

            if(deez.scoringArm.getPosition()+scoringArmInput <= scoringArmLimit && scoringArmInput > 0){
                deez.scoringArm.setPosition(deez.scoringArm.getPosition()+scoringArmInput);
            }

            else{
                deez.scoringArm.setPosition(deez.scoringArm.getPosition()-servoIncrement);
            }

            deez.scoringArmClaw.setPosition(deez.scoringArmClaw.getPosition()-scoringArmClawInput);

            if(deez.scoringArmClaw.getPosition()+clawIncrement<=scoringArmClawLimit && scoringArmClawInput==0){
                deez.scoringArmClaw.setPosition(deez.scoringArmClaw.getPosition()+clawIncrement);
            }

            if(deez.scoringArmClaw.getPosition()>scoringArmClawLimit){
                deez.scoringArmClaw.setPosition(scoringArmClawLimit);
            }

            if(deez.intakeArm.getPosition()+intakeArmInput <= intakeArmLimit && intakeArmInput > 0){
                deez.intakeArm.setPosition(deez.intakeArm.getPosition()+intakeArmInput);
            }

            else if(deez.intakeArm.getPosition()-servoIncrement>=intakeArmFloorOffset){
                deez.intakeArm.setPosition(deez.intakeArm.getPosition()-servoIncrement);
            }

            if(deez.intakeArm.getPosition()<intakeArmFloorOffset&&!isScoringArmActive){
                deez.intakeArm.setPosition(intakeArmFloorOffset);
            }

            deez.intakeArmClaw.setPosition(deez.intakeArmClaw.getPosition()-intakeArmClawInput);

            if(deez.intakeArmClaw.getPosition()+intakeArmClawInput <= intakeArmClawLimit && intakeArmClawInput==0){
                deez.intakeArmClaw.setPosition(deez.intakeArmClaw.getPosition()+servoIncrement);
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

            dbtelemetry.addData("Scoring Arm Servo Position:",deez.scoringArm.getPosition());
            dbtelemetry.addData("Scoring Arm Claw Servo Position:",deez.scoringArmClaw.getPosition());
            dbtelemetry.addData("Intake Arm Servo Position:",deez.intakeArm.getPosition());
            dbtelemetry.addData("Intake Arm Claw Servo Position:",deez.intakeArmClaw.getPosition());
            dbtelemetry.addData("Lift Encoder Position:",deez.liftMotor.getCurrentPosition());
            dbtelemetry.addData("Lift Encoder Target Position:",deez.liftMotor.getTargetPosition());
            dbtelemetry.addData("Lift Input:", liftInput);
            dbtelemetry.update();
        }
    }
}

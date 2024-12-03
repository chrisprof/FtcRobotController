package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp

public class SlidesTest extends LinearOpMode{
    public void runOpMode(){
        Servo armServo = hardwareMap.get(Servo.class,"armServo");
        Servo clawServo = hardwareMap.get(Servo.class,"clawServo");
        DcMotor slidesMotor = hardwareMap.get(DcMotor.class,"backMotor");

        double speedMultiplier = 0.001;

        double input;
        double leftTrigger;

        double armLimit = 0.3875;

        double swivelIncrement = 0.25;

        armServo.setPosition(armServo.MIN_POSITION);
        clawServo.setPosition(clawServo.MIN_POSITION);

        waitForStart();
        while(opModeIsActive())
        {
            leftTrigger=gamepad1.left_trigger;

            input = speedMultiplier*leftTrigger;

            if(gamepad1.a){
                input+=speedMultiplier;
            }

            if(armServo.getPosition()+input <= armLimit && input > 0){
                armServo.setPosition(armServo.getPosition()+input);
            }

            else{
                armServo.setPosition(armServo.getPosition()-speedMultiplier);
            }

            slidesMotor.setPower(0.4);
            telemetry.addData("Arm Servo Position:",armServo.getPosition());
            telemetry.addData("Input:",input);
            telemetry.update();
        }
    }
}
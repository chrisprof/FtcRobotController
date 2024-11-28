package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp

public class ServoTest extends LinearOpMode{
    public void runOpMode(){
        Servo armServo1 = hardwareMap.get(Servo.class,"armServo1");
        Servo armServo2 = hardwareMap.get(Servo.class,"armServo2");
        Servo clawServo = hardwareMap.get(Servo.class,"clawServo");
        
        double speedMultiplier = 0.001;
        
        double input;
        
        double leftAnalogX;
        double analogLT;
        double analogRT;
        
        double proceedingJointMultiplier = 4;
        double jointLimit = 0.4;
        
        double swivelIncrement = 0.25;
        
        armServo1.setPosition(armServo1.MIN_POSITION);
        armServo2.setPosition(armServo1.MIN_POSITION);
        clawServo.setPosition(clawServo.MIN_POSITION);
        
        waitForStart();
        while(opModeIsActive())
        {
            analogLT = gamepad1.left_trigger;
            analogRT = gamepad1.right_trigger;
            //leftAnalogX = gamepad1.left_stick_x;
            
            input = speedMultiplier*analogLT;
            /*
            if(armServo1.getPosition()+input <= jointLimit || input < 0){
                armServo1.setPosition(armServo1.getPosition()+input);    
            }
            
            if(armServo2.getPosition()+input <= jointLimit || input < 0){
                armServo2.setPosition(armServo2.getPosition()+(input*proceedingJointMultiplier));
            }*/
            
            armServo1.setPosition(analogLT);
            armServo2.setPosition(analogLT);

            clawServo.setPosition(analogRT);
            
            telemetry.addData("Servo1 Position:",armServo1.getPosition());
            telemetry.addData("Servo2 Position:",armServo2.getPosition());
            telemetry.addData("Input:",input);
            telemetry.update();
        }
    }
}

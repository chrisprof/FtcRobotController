package org.firstinspires.ftc.teamcode;


import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp
public class slidesTest extends LinearOpMode {
    public void runOpMode(){

        FtcDashboard dashboard = FtcDashboard.getInstance();
        Telemetry dashboardTelemetry = dashboard.getTelemetry();

        Bot deez = new Bot(hardwareMap.get(DcMotor.class, "rightMotor"),hardwareMap.get(DcMotor.class, "leftMotor"),hardwareMap.get(DcMotor.class, "liftMotor"));
        deez.initLift();

        int liftPosition;
        int floor=250;
        int ceil=2100;
        double liftInput;
        double liftPower;

        double inputMultiplier=0.25;
        double powerConstant=0.00125;

        waitForStart();
        while(opModeIsActive()){
            liftPower=0;
            liftInput=-gamepad1.left_stick_y;

            liftPosition = deez.liftMotor.getCurrentPosition();

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

            dashboardTelemetry.addData("Lift Encoder Position:",deez.liftMotor.getCurrentPosition());
            dashboardTelemetry.addData("Lift Input:", liftInput);
            dashboardTelemetry.addData("Lift Power:", liftPower);
            dashboardTelemetry.update();

            /*
            if(gamepad1.dpad_up){
                deez.moveLiftTo(1000);
            }

            if(gamepad1.dpad_down)
            {
                deez.moveLiftTo(0);
            }

            if(liftGetPosition<=25)
            {
                deez.moveLiftTo(0);
                resetLiftEncoders();
            }
            */
        }
    }
}

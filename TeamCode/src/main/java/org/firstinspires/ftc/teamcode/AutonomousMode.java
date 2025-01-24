package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Autonomous(name="IROBOT")

public class AutonomousMode extends LinearOpMode{
  public void runOpMode(){
    FtcDashboard db = FtcDashboard.getInstance();
    Telemetry dbtelemetry = db.getTelemetry();

    Bot craig = new Bot(hardwareMap);
    craig.init();

    waitForStart();

    ElapsedTime timer=new ElapsedTime();

    while(opModeIsActive()){
        if(timer.seconds()<5){
            craig.moveLiftTo(1500, 0.75);
        }
        else{
            craig.moveLiftTo(0, 1);
        }

        dbtelemetry.addData("Lift Target Position:", craig.liftMotor.getTargetPosition());
        dbtelemetry.addData("Timer:", timer.time());
        dbtelemetry.update();
    }
  }
}
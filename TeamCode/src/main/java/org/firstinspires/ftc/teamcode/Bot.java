/*package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor.RunMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gyroscope;

public class Bot{
    private Blinker control_Hub;
    private DcMotor backMotor;
    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private DcMotor frontMotor;
    private Gyroscope imu;

    public Bot(DcMotor fMotor, DcMotor rMotor,DcMotor bMotor,DcMotor lMotor){
        this.frontMotor=fMotor;
        this.rightMotor=rMotor;
        this.backMotor=bMotor;
        this.leftMotor=lMotor;
    }

    //Universal Functions
    public void setSpeed(double[] motorSpeeds){
        frontMotor.setPower(motorSpeeds[0]);
        rightMotor.setPower(motorSpeeds[1]);
        backMotor.setPower(motorSpeeds[2]);
        leftMotor.setPower(motorSpeeds[3]);
    }

    public void setSlidesSpeed(double speed){
        leftMotor.setPower(speed);
    }

    public void initMotors() {
        frontMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public boolean isStopped(){
        return !(frontMotor.isBusy() && leftMotor.isBusy() && rightMotor.isBusy() && backMotor.isBusy());
    }

    public void setPosition(int[] targetPos){
        
        frontMotor.setTargetPosition(targetPos[0]);
        leftMotor.setTargetPosition(targetPos[1]);
        rightMotor.setTargetPosition(targetPos[2]);
        backMotor.setTargetPosition(targetPos[3]);
    }
    
    pWublic void resetEncoders(){
        frontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    
    //Autonomous Functions
    public void initMotorsAutonomous(){
        initMotors();
        
        frontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        
        frontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    */
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor.RunMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Bot {
    private Blinker control_Hub;

    public DcMotor rightMotor;
    public DcMotor leftMotor;
    public DcMotor liftMotor;

    Servo scoringArm;
    Servo scoringArmClaw;
    Servo intakeArm;
    Servo intakeArmClaw;

    HardwareMap hwMap;
    private Gyroscope imu;

    public Bot(HardwareMap rhwMap) {
        this.hwMap = rhwMap;
    }

    public void init(){
        rightMotor = hwMap.get(DcMotor.class,"rightMotor");
        leftMotor = hwMap.get(DcMotor.class,"leftMotor");
        liftMotor = hwMap.get(DcMotor.class,"liftMotor");

        scoringArm = hwMap.get(Servo.class,"scoringArm");
        scoringArmClaw = hwMap.get(Servo.class,"scoringArmClaw");
        intakeArm = hwMap.get(Servo.class,"intakeArm");
        intakeArmClaw = hwMap.get(Servo.class,"intakeArmClaw");

        initLift();
        initWheels();
    }

    //Universal Functions
    public void setWheelSpeed(double rSpeed, double lSpeed) {
        rightMotor.setPower(rSpeed);
        leftMotor.setPower(lSpeed);
    }

    public void setLiftSpeed(double speed) {
        liftMotor.setPower(speed);
    }

    public void initWheels() {
        rightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public boolean isStopped() {
        return !(rightMotor.isBusy() && leftMotor.isBusy());
    }

    public void initLift() {
        resetLiftEncoder();
        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void resetLiftEncoder() {
        liftMotor.setMode(RunMode.STOP_AND_RESET_ENCODER);
        liftMotor.setMode(RunMode.RUN_USING_ENCODER);
    }


    public void moveLiftTo(int position){
        liftMotor.setTargetPosition(position);
        liftMotor.setMode(RunMode.RUN_TO_POSITION);
    }
    public void resetWheelEncoders() {
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    /*
    public void setPosition(int[] targetPos){

        frontMotor.setTargetPosition(targetPos[0]);
        leftMotor.setTargetPosition(targetPos[1]);
        rightMotor.setTargetPosition(targetPos[2]);
        backMotor.setTargetPosition(targetPos[3]);
    }

    public void resetEncoders(){
        frontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    //Autonomous Functions
    public void initMotorsAutonomous(){
        initMotors();

        frontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    */
    }

}
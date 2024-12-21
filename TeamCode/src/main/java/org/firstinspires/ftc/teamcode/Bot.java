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

public class Bot {
    private Blinker control_Hub;

    public DcMotor rightMotor;
    public DcMotor leftMotor;
    public DcMotor liftMotor;
    private Gyroscope imu;

    public Bot(DcMotor rMotor, DcMotor lMotor, DcMotor lift_Motor) {
        this.rightMotor = rMotor;
        this.leftMotor = lMotor;
        this.liftMotor = lift_Motor;
    }

    //Universal Functions
    public void setWheelSpeed(double rSpeed, double lSpeed) {
        rightMotor.setPower(rSpeed);
        leftMotor.setPower(lSpeed);
    }

    public void setSlidesSpeed(double speed) {
        leftMotor.setPower(speed);
    }

    public void initWheels() {
        rightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public boolean isStopped() {
        return !(rightMotor.isBusy() && leftMotor.isBusy());
    }

    public void initSlides() {
        liftMotor.setMode(RunMode.STOP_AND_RESET_ENCODER);
        liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void resetEncoders() {
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
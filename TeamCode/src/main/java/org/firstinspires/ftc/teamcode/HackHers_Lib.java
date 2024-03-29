package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.openftc.easyopencv.OpenCvWebcam;

public class HackHers_Lib {
    //public SensorMRGyro gyro ;
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;
    public OpenCvWebcam webcam;
    public DcMotorEx arm;
    public CRServo claw;

    public Servo airClaw;



    //public Telemetry telemetry;

    public HackHers_Lib(DcMotor fl, DcMotor fr, DcMotor bl, DcMotor br, OpenCvWebcam wc, DcMotorEx ar, CRServo cl, Servo apl){
        this.frontLeft= fl;
        this.frontRight = fr;
        this.backLeft= bl;
        this.backRight = br;
        this.arm = ar;
       this.claw = cl;
       // this.telemetry = t;
        this.webcam = wc;
        this.airClaw = apl;
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setDirection(DcMotor.Direction.REVERSE);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }



    public void driveRaw(float fl, float fr, float bl, float br){
        frontLeft.setPower(fl);
        frontRight.setPower(fr);
        backLeft.setPower(bl);
        backRight.setPower(br);
    }
    public void setMotorPower(DcMotor motor, float power){
        motor.setPower(power);
    }
    public void setServoPower(CRServo servo, double power) {servo.setPower(power); }

    //public void setServoPower(Servo servo, double power) {servo.setPosition(power); }

    public void omniDrive(float v, float h, float r){
        float[] sum = PaulMath.omniCalc(v, h, r);
        driveRaw(sum[0], sum[1], sum[2], sum[3]);
    }



    public void driveTurnLeftPosition(int tick, double power){
        frontLeft.setTargetPosition(frontLeft.getCurrentPosition()-tick);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setTargetPosition(frontLeft.getCurrentPosition()-tick);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setTargetPosition(frontLeft.getCurrentPosition()-tick);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setTargetPosition(frontLeft.getCurrentPosition()-tick);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        turnRight(power);
    }
    public void driveTurnRightPosition(int tick, double power){
        frontLeft.setTargetPosition(frontLeft.getCurrentPosition()+tick);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setTargetPosition(frontLeft.getCurrentPosition()+tick);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setTargetPosition(frontLeft.getCurrentPosition()+tick);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setTargetPosition(frontLeft.getCurrentPosition()+tick);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        turnRight(power);
    }
    public void driveGoBackwardPosition(int tick, double power){
        frontLeft.setTargetPosition(frontLeft.getCurrentPosition()-tick);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setTargetPosition(frontRight.getCurrentPosition()-tick);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setTargetPosition(backRight.getCurrentPosition()-tick);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setTargetPosition(backLeft.getCurrentPosition()-tick);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        this.frontLeft.setPower(power);
        this.frontRight.setPower(power);
        this.backLeft.setPower(power);
        this.backRight.setPower(-power);

        telemetry.log().add("This is calling the go back method");
    }

    public void driveGoForwardPosition(int tick, double power){
        frontLeft.setTargetPosition(frontLeft.getCurrentPosition()+tick);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setTargetPosition(frontLeft.getCurrentPosition()-tick);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setTargetPosition(frontLeft.getCurrentPosition()+tick);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setTargetPosition(frontLeft.getCurrentPosition()-tick);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        turnRight(power);
    }

    public void driveStrafeRightPosition(int tick, double power){
        frontLeft.setTargetPosition(frontLeft.getCurrentPosition()+tick);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setTargetPosition(frontLeft.getCurrentPosition()+tick);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setTargetPosition(frontLeft.getCurrentPosition()-tick);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setTargetPosition(frontLeft.getCurrentPosition()-tick);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        strafeRight(power);
    }

    public void driveStrafeLeftPosition(int tick, double power){
        frontLeft.setTargetPosition(frontLeft.getCurrentPosition()-tick);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setTargetPosition(frontLeft.getCurrentPosition()-tick);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setTargetPosition(frontLeft.getCurrentPosition()+tick);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setTargetPosition(frontLeft.getCurrentPosition()+tick);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        strafeLeft(power);
    }

    public void goForward(double power){
        this.frontLeft.setPower(power);
        this.frontRight.setPower(-power);
        this.backLeft.setPower(-power);
        this.backRight.setPower(-power);//change AUTO POWer here
    }

    public void goBackward(double power){
        this.frontLeft.setPower(power);
        this.frontRight.setPower(power);
        this.backLeft.setPower(power);
        this.backRight.setPower(power);
    }

    public void turnLeft(double power){
        this.frontLeft.setPower(power);
        this.frontRight.setPower(-power);
        this.backLeft.setPower(power);
        this.backRight.setPower(-power);
    }

    public void turnRight(double power){
        this.frontLeft.setPower(-power);
        this.frontRight.setPower(power);
        this.backLeft.setPower(-power);
        this.backRight.setPower(power);
    }

    public void strafeRight(double power){
        this.frontLeft.setPower(-power);
        this.frontRight.setPower(power);
        this.backLeft.setPower(power);
        this.backRight.setPower(-power);
    }

    public void strafeLeft(double power){
        this.frontLeft.setPower(power);
        this.frontRight.setPower(-power);
        this.backLeft.setPower(-power);
        this.backRight.setPower(power);
    }

    public void Stop(){
        this.frontLeft.setPower(0);
        this.frontRight.setPower(0);
        this.backLeft.setPower(0);
        this.backRight.setPower(0);
    }

    public boolean isBusy(){
        if(frontLeft.isBusy()|| frontRight.isBusy()|| backLeft.isBusy()|| backRight.isBusy()){
            return true;
        }
        return false;
    }

    public void Open(){
        this.claw.setDirection(DcMotorSimple.Direction.FORWARD);
        this.claw.setPower(-1);
        telemetry.addLine("UP DPAD");
        telemetry.update();
    }

    public void Close(){
        this.claw.setDirection(DcMotorSimple.Direction.FORWARD);
        this.claw.setPower(-1);

    }

    public void armUp(){

        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);




    }
    public void armStop(){
        this.arm.setPower(0.00);
    }

    public void armDown(){
        this.arm.setPower(0.25);
    }


//    public void DriveForwardToDist(double dist, double power){
//        while((distance1.getDistance(DistanceUnit.CM)<=dist && distance2.getDistance(DistanceUnit.CM)<=dist)){
//            goForward(power);
//        }
//    }

//    public void DriveBackwardToDist(double dist){
//        while((distance1.getDistance(DistanceUnit.CM)<=dist && distance2.getDistance(DistanceUnit.CM)<=dist)){
//            goBackward(.2);
//        }
//    }

}
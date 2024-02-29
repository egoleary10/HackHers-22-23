package org.firstinspires.ftc.teamcode;


import static java.lang.Math.PI;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.ElapsedTime.Resolution;



import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvWebcam;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;



@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class BarbarasWorldFamousTeleOp extends OpMode {
    private HackHers_Lib everything;
    DcMotorEx fL;
    DcMotorEx fR;
    DcMotorEx bL;
    DcMotorEx bR;
    DcMotorEx ar;
    private OpenCvWebcam wc;

    CRServo cl; //this lie

    Servo apl;

    public void init() {
        fL = hardwareMap.get(DcMotorEx.class, "fL");
        fR = hardwareMap.get(DcMotorEx.class, "fR");
        bL = hardwareMap.get(DcMotorEx.class, "bl");
        bR = hardwareMap.get(DcMotorEx.class, "bR");
        ar = hardwareMap.get(DcMotorEx.class, "ar");
        //cl = hardwareMap.get(Servo.class, "cl");  //this lie
        cl = hardwareMap.get(CRServo.class, "cl");  //this lie
        apl = hardwareMap.get(Servo.class, "apl");

        int webcamID = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        wc = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.getAll(WebcamName.class).get(0), webcamID);

        everything = new HackHers_Lib(fL, fR, bL, bR, wc, ar, cl, apl);//this lie
        ar.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ar.setDirection(DcMotor.Direction.FORWARD);
        ar.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        ar.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }



    @Override
    public void loop() {

        everything.omniDrive(gamepad1.right_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_x);
        int currentArmPosition = ar.getCurrentPosition();
        if (gamepad1.dpad_down) {
            cl.setDirection(DcMotorSimple.Direction.FORWARD);
            cl.setPower(1);
            telemetry.addLine("OPEN CLAW");
            telemetry.update();
        }
        if (gamepad1.dpad_up) {
            cl.setDirection(DcMotorSimple.Direction.FORWARD);
            cl.setPower(-1);
            telemetry.addLine("CLOSE CLAW");
            telemetry.update();
        }

        if (gamepad1.b) {//GOES DOWN / BACKWARDS BIG
            ar.setTargetPosition(0);
            ar.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            ar.setVelocity(1300);
        }


        if (gamepad1.y) { //STOPS
            ar.setVelocity(0);
        }

        if (gamepad1.a) { //GOES DOWN / BACKWARDS INCREMENTALLY
            ar.setTargetPosition(ar.getCurrentPosition()+70);
            ar.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            ar.setVelocity(1450);
        }

        if (gamepad1.x) { //GOES UP / INCREMENTALLY
            ar.setTargetPosition(ar.getCurrentPosition()-70);
            ar.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            ar.setVelocity(1700);
        }

        if (gamepad1.dpad_left) {//GOES DOWN / BACKWARDS BIG
            apl.setPosition(0.5);
            telemetry.addLine("OPEN 45");
            telemetry.update();
        }

        if (gamepad1.dpad_right) {
            //apl.setDirection(DcMotorSimple.Direction.FORWARD);
            apl.setPosition(0);
            telemetry.addLine("OPEN 0");
            telemetry.update();
        }

        if (gamepad1.right_bumper) { //GOES UP BIG ALL AT ONCE
            ar.setTargetPosition(-5000);
            ar.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            ar.setVelocity(1300);
              if(ar.getCurrentPosition()== ar.getTargetPosition()){
                ar.setVelocity(0);
            }
        }



        telemetry.addData("arm motor encoder", currentArmPosition);
        telemetry.update();
    }
}


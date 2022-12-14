package org.firstinspires.ftc.teamcode;


import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvWebcam;


@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class BarbarasWorldFamousTeleOp extends OpMode {
    private HackHers_Lib everything;
    DcMotor fL;
    DcMotor fR;
    DcMotor bL;
    DcMotor bR;
    private OpenCvWebcam wc;
    DcMotor ls;
    Servo cl;
    Rev2mDistanceSensor ds1;
    Rev2mDistanceSensor ds2;
    //comment for the sake of existing

    public void init() {
        fL = hardwareMap.get(DcMotor.class, "fl");
        fR = hardwareMap.get(DcMotor.class, "fR");
        bL = hardwareMap.get(DcMotor.class, "bl");
        bR = hardwareMap.get(DcMotor.class, "bR");

        int webcamID = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        wc = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.getAll(WebcamName.class).get(0), webcamID);
        ls = hardwareMap.get(DcMotor.class, "ls");
        cl = hardwareMap.get(Servo.class,"cl");
        ds1 = hardwareMap.get(Rev2mDistanceSensor.class, "ds1");
        ds2 = hardwareMap.get(Rev2mDistanceSensor.class, "ds2");
        everything = new HackHers_Lib(fL, fR, bL, bR, ls, cl, ds1, ds2, wc);

    }


    @Override
    public void loop() {
        everything.omniDrive(gamepad1.right_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_x);

        if (gamepad1.dpad_down) {
            everything.setMotorPower(fL, 1);
        }
        if (gamepad1.dpad_up) {
            everything.setMotorPower(fR, 1);
        }
        if (gamepad1.dpad_right) {
            everything.setMotorPower(bL, 1);
        }
        if (gamepad1.dpad_left) {
            everything.setMotorPower(bR, -1);
        }
        if (gamepad1.y) {
            everything.setMotorPower(ls, 1);
        }
        if (gamepad1.a) {
            everything.setMotorPower(ls, -1);
        }
        if (gamepad1.right_bumper) {
            everything.setServoPower(cl, 0.8);
        }
        if (gamepad1.left_bumper) {
            everything.setServoPower(cl, 0.65);
        }

        everything.setMotorPower(fL, 0);
        everything.setMotorPower(fR, 0);
        everything.setMotorPower(bL, 0);
        everything.setMotorPower(bR, 0);
        everything.setMotorPower(ls, 0);
    }
}

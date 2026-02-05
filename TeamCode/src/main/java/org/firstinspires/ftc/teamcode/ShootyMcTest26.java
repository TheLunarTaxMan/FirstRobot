package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import java.lang.annotation.Target;


@TeleOp

public class ShootyMcTest26 extends LinearOpMode {
//    private Gyroscope imu;
//public DcMotor frontRightWheel;
//    public DcMotor frontLeftWheel;
//    public DcMotor backRightWheel;
//    public DcMotor backLeftWheel;
//    private DcMotor HopperMotor;
    private DcMotorEx Flywheel;
//    private Servo HopperServo;
    private DigitalChannel digitalTouch;
    private DistanceSensor sensorColorRange;


    public double HighVelocity = 1500;
    public double LowVelocity = 800;
    public double TargetVelocity = HighVelocity;
    public double P = 0;
    public double F = 0;
    double[] stepSizes = {10, 1, 0.1, 0.01, 0.001};
    int stepIndex = 1;

    @Override
    public void runOpMode() {
//        imu = hardwareMap.get(Gyroscope.class, "imu");
//        frontRightWheel = hardwareMap.get(DcMotor.class, "rightFront");
//        frontLeftWheel = hardwareMap.get(DcMotor.class, "leftFront");
//        backRightWheel = hardwareMap.get(DcMotor.class, "rightBack");
//        backLeftWheel = hardwareMap.get(DcMotor.class, "leftBack");

//        HopperMotor = hardwareMap.get(DcMotor.class, "HopperMotor");
        Flywheel = hardwareMap.get(DcMotorEx.class, "Flywheel");
//        HopperServo = hardwareMap.get(Servo.class, "HopperServo");
        //digitalTouch = hardwareMap.get(DigitalChannel.class, "digitalTouch");
        //sensorColorRange = hardwareMap.get(DistanceSensor.class, "sensorColorRange");

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        Flywheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(P, 0, 0, F);
        Flywheel.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);

        // Wait for the game to start (driver presses PLAY)



        waitForStart();

        // run until the end of the match (driver presses STOP)

        while (opModeIsActive()) {
//            Flywheel.setPower(gamepad1.right_trigger - gamepad1.left_trigger);

            if (gamepad1.yWasPressed())
            {
                if(TargetVelocity == HighVelocity)
                {
                    TargetVelocity = LowVelocity;
                }
                else
                {
                    TargetVelocity = HighVelocity;
                }
            }

            if (gamepad1.bWasPressed())
            {
                stepIndex = (stepIndex+1) % stepSizes.length;
            }

            if (gamepad1.dpadLeftWasPressed())
            {
                F += stepSizes[stepIndex];
            }

            if (gamepad1.dpadRightWasPressed())
            {
                F -= stepSizes[stepIndex];
            }

            if (gamepad1.dpadUpWasPressed())
            {
                P += stepSizes[stepIndex];
            }

            if (gamepad1.dpadDownWasPressed())
            {
                P -= stepSizes[stepIndex];
            }


            PIDFCoefficients newpidfCoefficients = new PIDFCoefficients(P, 0, 0, F);
            Flywheel.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);

            Flywheel.setVelocity(TargetVelocity);

            double curVelocity = Flywheel.getVelocity();
            double error = TargetVelocity - curVelocity;

            telemetry.addData("P value: ", P);
            telemetry.addData("F value: ", F);
            telemetry.addData("error value: ", error);
            telemetry.addData("Target velocity: ", TargetVelocity);
            telemetry.addData("Current velocity: ", curVelocity);
            telemetry.addData("Step size: ", stepSizes[stepIndex]);
            telemetry.update();
        }
    }
}
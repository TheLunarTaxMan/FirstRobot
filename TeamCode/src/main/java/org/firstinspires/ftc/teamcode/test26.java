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

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;


@TeleOp

public class test26 extends LinearOpMode {
//    private Gyroscope imu;
//    private DcMotor motorR;
//    private DcMotor motorL;
//    private DcMotor HopperMotor;
    private DcMotorEx Flywheel;
//    private Servo HopperServo;
//    private DigitalChannel digitalTouch;
//    private DistanceSensor sensorColorRange;
//

    @Override
    public void runOpMode() {
//        imu = hardwareMap.get(Gyroscope.class, "imu");
//        motorR = hardwareMap.get(DcMotor.class, "motorR");
//        motorL = hardwareMap.get(DcMotor.class, "motorL");
//        HopperMotor = hardwareMap.get(DcMotor.class, "HopperMotor");
        Flywheel = hardwareMap.get(DcMotorEx.class, "Flywheel");
//        HopperServo = hardwareMap.get(Servo.class, "HopperServo");
        //digitalTouch = hardwareMap.get(DigitalChannel.class, "digitalTouch");
        //sensorColorRange = hardwareMap.get(DistanceSensor.class, "sensorColorRange");

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        double P = 2.5;
        double F = 0.5;

        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(P, 0.1, 0.2, F);
        Flywheel.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);

        // run until the end of the match (driver presses STOP)

        while (opModeIsActive()) {
           Flywheel.setVelocity((gamepad1.right_trigger - gamepad1.left_trigger)*1800);


            telemetry.addData("Current position", Flywheel.getCurrentPosition());

            telemetry.update();
        }
    }
}
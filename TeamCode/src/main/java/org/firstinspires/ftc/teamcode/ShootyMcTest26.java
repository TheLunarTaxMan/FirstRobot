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


@TeleOp

public class ShootyMcTest26 extends LinearOpMode {
//    private Gyroscope imu;
    private DcMotor motorR;
    private DcMotor motorL;
    private DcMotor HopperMotor;
    private DcMotorEx Flywheel;
    private Servo HopperServo;
    private DigitalChannel digitalTouch;
    private DistanceSensor sensorColorRange;


    public double HighVelocity = 1500;
    public double LowVelocity = 800;
    public double TargetVelocity = HighVelocity;
    public double P = 0;
    public double F = 0;
    double[] stepSizes = {10, 1, 0.1, 0.01, 0.001};
    double stepIndex = 1;

    @Override
    public void runOpMode() {
//        imu = hardwareMap.get(Gyroscope.class, "imu");
        motorR = hardwareMap.get(DcMotor.class, "motorR");
        motorL = hardwareMap.get(DcMotor.class, "motorL");
        HopperMotor = hardwareMap.get(DcMotor.class, "HopperMotor");
        Flywheel = hardwareMap.get(DcMotorEx.class, "Flywheel");
        HopperServo = hardwareMap.get(Servo.class, "HopperServo");
        //digitalTouch = hardwareMap.get(DigitalChannel.class, "digitalTouch");
        //sensorColorRange = hardwareMap.get(DistanceSensor.class, "sensorColorRange");

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        Flywheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(P, 0, 0, F);
        Flywheel.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);

        // Wait for the game to start (driver presses PLAY)

        if (gamepad1.yWasPressed())
        {

        }


        waitForStart();

        // run until the end of the match (driver presses STOP)

        while (opModeIsActive()) {
            Flywheel.setPower(gamepad1.right_trigger - gamepad1.left_trigger);
            telemetry.addLine("press triangle to wave, right trigger for flywheel, press square to spin the feeder. left stick to move and pressing the right stick ");
            telemetry.update();
        }
    }
}
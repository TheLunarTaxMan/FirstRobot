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
public DcMotor frontRightWheel;
    public DcMotor frontLeftWheel;
    public DcMotor backRightWheel;
    public DcMotor backLeftWheel;
    private DcMotorEx Flywheel;

    private DcMotor HopperMotor;
//    private Servo HopperServo;
//    private DigitalChannel digitalTouch;
//    private DistanceSensor sensorColorRange;
//

    @Override
    public void runOpMode() {
//        imu = hardwareMap.get(Gyroscope.class, "imu");
        frontRightWheel = hardwareMap.get(DcMotor.class, "rightFront");
        frontLeftWheel = hardwareMap.get(DcMotor.class, "leftFront");
        backRightWheel = hardwareMap.get(DcMotor.class, "rightBack");
        backLeftWheel = hardwareMap.get(DcMotor.class, "leftBack");
        Flywheel = hardwareMap.get(DcMotorEx.class, "Flywheel");
        HopperMotor = hardwareMap.get(DcMotor.class, "HopperMotor");

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

        boolean fast = true;
        boolean HopperMotorOn= false;
        while (opModeIsActive()) {
            if(this.gamepad1.xWasPressed()){
                if (HopperMotorOn) {
                    HopperMotor.setPower(0);
                    HopperMotorOn = false;
                }else {
                    HopperMotor.setPower(0.8);
                    HopperMotorOn = true;
                }
            }

            if (fast) {
                Flywheel.setVelocity((gamepad1.right_trigger - gamepad1.left_trigger) * 1800);
            }
            else {
                Flywheel.setVelocity((gamepad1.right_trigger - gamepad1.left_trigger) * 900);
            }
            if (gamepad1.leftBumperWasPressed())
            {
                fast = !fast;
            }
            telemetry.addData("Current position", Flywheel.getCurrentPosition());
            telemetry.addData("it fastttttt?", fast);

            telemetry.update();
        }
    }
}
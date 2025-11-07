package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;


@TeleOp

public class test1 extends LinearOpMode {
    private Gyroscope imu;
    private DcMotor motorR;
    private DcMotor motorL;
    private DcMotor HopperMotor;
    private DcMotor Flywheel;
    private Servo HopperServo;
    private DigitalChannel digitalTouch;
    private DistanceSensor sensorColorRange;


    @Override
    public void runOpMode() {
        imu = hardwareMap.get(Gyroscope.class, "imu");
        motorR = hardwareMap.get(DcMotor.class, "motorR");
        motorL = hardwareMap.get(DcMotor.class, "motorL");
        HopperMotor = hardwareMap.get(DcMotor.class, "HopperMotor");
        Flywheel = hardwareMap.get(DcMotor.class, "Flywheel");
        HopperServo = hardwareMap.get(Servo.class, "HopperServo");
        //digitalTouch = hardwareMap.get(DigitalChannel.class, "digitalTouch");
        //sensorColorRange = hardwareMap.get(DistanceSensor.class, "sensorColorRange");

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        double tgtPower = 0;
        double tgtPowerx = 0;
        boolean servoOpen = false;
        while (opModeIsActive()) {
            if (this.gamepad1.x) HopperMotor.setPower(-0.5);
            else HopperMotor.setPower(0);
            if (this.gamepad1.triangle)
            {
                if (servoOpen) {
                    HopperServo.setPosition(1);
                    servoOpen = false;
                }
                else {
                    HopperServo.setPosition(0.5);
                    servoOpen = true;
                }
            }

            if (this.gamepad1.right_trigger != 0)
            {
                Flywheel.setPower(1);
                sleep(1000);
                Flywheel.setPower(0);

            }
            if (this.gamepad1.right_stick_button) HopperServo.setPosition(0.5);
            tgtPower = -this.gamepad1.left_stick_y;
            tgtPowerx = -this.gamepad1.left_stick_x;
            motorR.setPower(tgtPower + tgtPowerx);
            motorL.setPower(-tgtPower + tgtPowerx);
            telemetry.addData("Target Power", tgtPower);
            telemetry.addData("Motor Power", motorR.getPower());
            telemetry.addData("Servo position", HopperServo.getPosition());
            telemetry.addData("Servo open", servoOpen);
            telemetry.addData("Status", "Running");
            telemetry.addData("IMU", imu.getAngularVelocity(AngleUnit.RADIANS));
            telemetry.addLine("press triangle to wave, press square to spin the feeder. left stick to move and pressing the right stick ");
            telemetry.update();
        }
    }
}
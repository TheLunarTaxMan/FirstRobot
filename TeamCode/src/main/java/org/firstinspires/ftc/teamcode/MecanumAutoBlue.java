package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Line;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class MecanumAutoBlue extends LinearOpMode {
    public DcMotorEx frontRightWheel;
    public DcMotorEx frontLeftWheel;
    public DcMotorEx backRightWheel;
    public DcMotorEx backLeftWheel;
    private DcMotor HopperMotor;

    private DcMotorEx Flywheel;

    private Servo HopperServo;

    @Override
    public void runOpMode()
    {
        frontRightWheel = hardwareMap.get(DcMotorEx.class, "rightFront");
        frontLeftWheel = hardwareMap.get(DcMotorEx.class, "leftFront");
        backRightWheel = hardwareMap.get(DcMotorEx.class, "rightBack");
        backLeftWheel = hardwareMap.get(DcMotorEx.class, "leftBack");
        HopperMotor = hardwareMap.get(DcMotor.class, "HopperMotor");
        HopperServo = hardwareMap.get(Servo.class, "HopperServo");
        Flywheel = hardwareMap.get(DcMotorEx.class, "Flywheel");
        backLeftWheel.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeftWheel.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        double Strafe = 0;
        double Forward = 0;
        double Rotation = 0;

//        ForwardTest();
//        StrafeTest();
        TestyThing();
        telemetry.addData("this should go 45 degrees up and right", 0);
    }
    public void ForwardTest()
    {
        LinearPower(0.5);
        sleep(500);
        LinearPower(0);
    }
    public void TestyThing()
    {
        LinearPower(-0.8);
        sleep(750);
        LinearPower(0);
        Flywheel.setVelocity(3000);
        sleep(1000);
        HopperServo.setPosition(0.8);
        sleep(1000);
        HopperServo.setPosition(1);
        sleep(1000);
        HopperServo.setPosition(0.8);
        sleep(1000);
        HopperServo.setPosition(1);
        sleep(1000);
        HopperServo.setPosition(0.8);
        sleep(1000);
        HopperServo.setPosition(1);
        sleep(1000);
        HopperMotor.setPower(1);
        LinearPower(1);
        sleep(100);
        LinearPower(-1);
        sleep(100);
        LinearPower(0);
        sleep(100);
        LinearPower(1);
        sleep(100);
        LinearPower(-1);
        sleep(100);
        LinearPower(0);
        sleep(100);
        HopperMotor.setPower(0);
        sleep(800);
        HopperServo.setPosition(0.8);
        sleep(700);
        HopperServo.setPosition(1);
        sleep(700);
        Flywheel.setPower(0);
        sleep(100);

        FancyPower(0.9, -Math.PI/2);
        sleep(1500);
        LinearPower(0);

    }
    public void StrafeTest()
    {
        FancyPower(0.5, Math.PI/4);
        sleep(500);
        LinearPower(0);
    }
    public void FancyPower(double pwr, double angle)
    {
        double lateralPower = pwr * Math.sin(angle);
        double forwardPower = pwr * Math.cos(angle);

        frontRightWheel.setVelocity(-(forwardPower - lateralPower) * 2000);
        backRightWheel.setVelocity(-(forwardPower + lateralPower) * 1000);
        frontLeftWheel.setVelocity(-(forwardPower + lateralPower) * 2000);
        backLeftWheel.setVelocity(-(forwardPower - lateralPower) * 1000);
    }
    public void LinearPower(double pwr)
    {
        frontRightWheel.setPower(-pwr);
        frontLeftWheel.setPower(-pwr);
        backLeftWheel.setPower(-pwr);
        backRightWheel.setPower(-pwr);
    }
}

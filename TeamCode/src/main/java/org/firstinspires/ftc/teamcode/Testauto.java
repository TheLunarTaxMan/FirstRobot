package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;



@Autonomous


public class Testauto extends LinearOpMode{
    private Gyroscope imu;
    private DcMotor motorR;
    private DcMotor motorL;
    private DcMotor HopperMotor;
    private DcMotor Flywheel;
    private DigitalChannel digitalTouch;
    private DistanceSensor sensorColorRange;
    private Servo servoTest;


    public void runOpMode() {
        motorR = hardwareMap.get(DcMotor.class, "motorR");
        motorL = hardwareMap.get(DcMotor.class, "motorL");
        Flywheel = hardwareMap.get(DcMotor.class, "Flywheel");
        motorR.setPower(0.5);
        motorL.setPower(-0.5);
        sleep(2000);
        motorR.setPower(0.5);
        motorL.setPower(0.5);
        sleep(1000);
        motorL.setPower(0);
        motorR.setPower(0);
        Flywheel.setPower(1);
        sleep(1000);
        Flywheel.setPower(0);
    }
}
package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@Autonomous
public class BallQuickFire extends  LinearOpMode{

    private DcMotorEx Flywheel;
    private DcMotorEx HopperMotor;
    private Servo HopperServo;

    private int flywheelspeed = 850;
    public void runOpMode()
    {
        Flywheel = hardwareMap.get(DcMotorEx.class, "Flywheel");
        HopperMotor = hardwareMap.get(DcMotorEx.class, "HopperMotor");
        HopperServo = hardwareMap.get(Servo.class, "HopperServo");
        waitForStart();
        TestyThing();
    }

    public void TestyThing()
    {

        Flywheel.setVelocity(flywheelspeed);
        HopperServo.setPosition(1);
        HopperMotor.setPower(0);
        sleep(2000);
        HopperServo.setPosition(0.8);
        sleep(800);
        HopperServo.setPosition(1);
        sleep(1000);

        HopperServo.setPosition(0.8);
        sleep(600);
        HopperServo.setPosition(1);
        sleep(1000);

        HopperMotor.setPower(-1);
        sleep(300);
        HopperMotor.setPower(1);
        sleep(100);
        HopperServo.setPosition(0.8);
        sleep(600);
        HopperServo.setPosition(1);
        sleep(1000);

        Flywheel.setVelocity(0);
        HopperMotor.setPower(0);
        HopperServo.setPosition(1);


    }
}

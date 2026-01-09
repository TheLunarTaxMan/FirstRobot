package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
public class MecanumAutoTest26 extends LinearOpMode {
    public DcMotor frontRightWheel;
    public DcMotor frontLeftWheel;
    public DcMotor backRightWheel;
    public DcMotor backLeftWheel;

    @Override
    public void runOpMode()
    {
        frontRightWheel = hardwareMap.get(DcMotor.class, "rightFront");
        frontLeftWheel = hardwareMap.get(DcMotor.class, "leftFront");
        backRightWheel = hardwareMap.get(DcMotor.class, "rightBack");
        backLeftWheel = hardwareMap.get(DcMotor.class, "leftBack");
        backLeftWheel.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();
        double Strafe = 0;
        double Forward = 0;
        double Rotation = 0;

        ForwardTest();

        telemetry.addData("FWD", Forward);
        telemetry.addData("STR", Strafe);
        telemetry.addData("ROT", Rotation);
        telemetry.addData("Left stick for axis movement, right stick for rotation", ' ');
    }
    public void ForwardTest()
    {
        LinearPower(0.5);
        sleep(500);
        LinearPower(0);
    }
    public void LinearPower(double pwr)
    {
        frontRightWheel.setPower(pwr);
        frontLeftWheel.setPower(-pwr);
        backLeftWheel.setPower(-pwr);
        backRightWheel.setPower(pwr);
    }
}

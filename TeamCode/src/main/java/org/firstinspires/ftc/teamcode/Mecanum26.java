package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Mecanum26 extends LinearOpMode {
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
        backLeftWheel = hardwareMap.get(DcMotor.class, "rightFront");
        waitForStart();
        double Strafe = 0;
        double Forward = 0;
        double Rotation = 0;
        while (opModeIsActive())
        {
            Forward = this.gamepad1.left_stick_y;
            Strafe = this.gamepad1.left_stick_x;
            Rotation = this.gamepad1.right_stick_x;
            frontRightWheel.setPower(Forward - Rotation - Strafe);
            backRightWheel.setPower(Forward - Rotation + Strafe);
            frontLeftWheel.setPower(Forward + Rotation + Strafe);
            backLeftWheel.setPower(Forward + Rotation - Strafe);
        }
        telemetry.addData("FWD", Forward);
        telemetry.addData("STR", Strafe);
        telemetry.addData("ROT", Rotation);
        telemetry.addData("Left stick for axis movement, right stick for rotation", ' ');
    }
}

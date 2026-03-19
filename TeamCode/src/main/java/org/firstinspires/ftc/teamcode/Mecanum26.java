package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class Mecanum26 extends LinearOpMode {
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

        waitForStart();
        double Strafe = 0;
        double Forward = 0;
        double Rotation = 0;
        backLeftWheel.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeftWheel.setDirection(DcMotorSimple.Direction.REVERSE);
        boolean HopperMotorOn= false;
        boolean HopperServoUp = false;
        boolean fast = true;
        while (opModeIsActive())
        {
            Forward = this.gamepad1.left_stick_y;
            Strafe = this.gamepad1.left_stick_x;
            Rotation = -this.gamepad1.right_stick_x;
            /*if (this.gamepad1.a){
                frontLeftWheel.setPower(0.4);
            } else if (this.gamepad1.x) {
                frontRightWheel.setPower(0.4);
            }else if (this.gamepad1.y){
                backRightWheel.setPower(0.4);
            }else if (this.gamepad1.b){
                backLeftWheel.setPower(0.4);
            }else {*/
                frontRightWheel.setVelocity((Forward - Rotation - Strafe)*2800);
                backRightWheel.setVelocity((Forward - Rotation + Strafe)*2800*2);
                frontLeftWheel.setVelocity((Forward + Rotation + Strafe)*2800);
                backLeftWheel.setVelocity((Forward + Rotation - Strafe)*2800*2);
            //}
            if (this.gamepad1.rightBumperWasPressed()){
                if (HopperServoUp) {
                    HopperServo.setPosition(1);
                }else{
                    HopperServo.setPosition(0.8);
                }
                HopperServoUp = !HopperServoUp;

            }
            Flywheel.setPower((gamepad1.right_trigger - gamepad1.left_trigger));
/*
            if (fast) {
                //Flywheel.setVelocity((gamepad1.right_trigger - gamepad1.left_trigger) * 1800);
                Flywheel.setPower((gamepad1.right_trigger - gamepad1.left_trigger));
            }
            else {
                Flywheel.setVelocity((gamepad1.right_trigger - gamepad1.left_trigger) * 3000);
            }
            if (gamepad1.rightBumperWasPressed())
            {
                fast = !fast;
            }*/

            if (this.gamepad1.dpadDownWasPressed())
            {
                TruffleShuffle();
            }

            if(this.gamepad1.leftBumperWasPressed()){
                if (HopperMotorOn) {
                    HopperMotor.setPower(0);
                    HopperMotorOn = false;
                }else {
                    HopperMotor.setPower(1);
                    HopperMotorOn = true;
                }
            }

            telemetry.addData("Left stick for axis movement, right stick for rotation", ' ');
            telemetry.addData("Left bumper to toggle the feed in motor", ' ');
            telemetry.addData("Right bumper to toggle the hopper servo", ' ');
            telemetry.addData("Right trigger to spin up the flywheel (HOLD)", ' ');
            telemetry.addData("Down on the d-pad to get the ball from the feeder (truffle shuffle)", ' ');
            telemetry.addData("Hopper servo is at: " , HopperServo.getPosition());

        }

    }
    public void TruffleShuffle()
    {
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
        HopperMotor.setPower(0);
    }
    public void LinearPower(double pwr)
    {
        frontRightWheel.setPower(-pwr);
        frontLeftWheel.setPower(-pwr);
        backLeftWheel.setPower(-pwr);
        backRightWheel.setPower(-pwr);
    }
}

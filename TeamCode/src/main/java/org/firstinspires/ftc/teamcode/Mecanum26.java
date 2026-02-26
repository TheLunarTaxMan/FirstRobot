package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class Mecanum26 extends LinearOpMode {
    public DcMotor frontRightWheel;
    public DcMotor frontLeftWheel;
    public DcMotor backRightWheel;
    public DcMotor backLeftWheel;

    private DcMotor HopperMotor;

    private Servo HopperServo;

    @Override
    public void runOpMode()
    {
        frontRightWheel = hardwareMap.get(DcMotor.class, "rightFront");
        frontLeftWheel = hardwareMap.get(DcMotor.class, "leftFront");
        backRightWheel = hardwareMap.get(DcMotor.class, "rightBack");
        backLeftWheel = hardwareMap.get(DcMotor.class, "leftBack");
        HopperMotor = hardwareMap.get(DcMotor.class, "HopperMotor");
        HopperServo = hardwareMap.get(Servo.class, "HopperServo");
        waitForStart();
        double Strafe = 0;
        double Forward = 0;
        double Rotation = 0;
        backLeftWheel.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeftWheel.setDirection(DcMotorSimple.Direction.REVERSE);
        boolean HopperMotorOn= false;
        boolean HopperServoUp = false;
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
                frontRightWheel.setPower(Forward - Rotation - Strafe);
                backRightWheel.setPower(Forward - Rotation + Strafe);
                frontLeftWheel.setPower(Forward + Rotation + Strafe);
                backLeftWheel.setPower(Forward + Rotation - Strafe);
            //}
            /*if (this.gamepad1.rightBumperWasPressed()){
                if (HopperServoUp) {
                    HopperServo.setPosition(1);
                }else{
                    HopperServo.setPosition(0.88888888);
                }
                HopperServoUp = !HopperServoUp;

            }*/


            if(this.gamepad1.leftBumperWasPressed()){
                if (HopperMotorOn) {
                    HopperMotor.setPower(0);
                    HopperMotorOn = false;
                }else {
                    HopperMotor.setPower(0.8);
                    HopperMotorOn = true;
                }
            }

            telemetry.addData("FWD", Forward);
            telemetry.addData("STR", Strafe);
            telemetry.addData("ROT", Rotation);
            telemetry.addData("Left stick for axis movement, right stick for rotation", ' ');
            telemetry.addData("Hopper servo" , HopperServo.getPosition());

        }

    }
}

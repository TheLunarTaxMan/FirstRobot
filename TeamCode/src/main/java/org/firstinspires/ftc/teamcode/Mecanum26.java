package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
//import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp



public class Mecanum26 extends LinearOpMode {
 /*   public class Motor26{
        DcMotorEx motor;
        double Ki;
        double Kd;
        double Kp;

        double reference;

        double integralSum = 0;

        double lastError = 0;
        ElapsedTime timer = new ElapsedTime();
        Motor26(DcMotorEx Inmotor){
            motor = Inmotor;
        }

        void loop(){
            // obtain the encoder position
            double encoderPosition = motor.getVelocity();
            // calculate the error
            double error = reference - encoderPosition;

            // rate of change of the error
            double derivative = (error - lastError) / timer.seconds();

            // sum of all error over time
            integralSum = integralSum + (error * timer.seconds());

            double out = (Kp * error) + (Ki * integralSum) + (Kd * derivative);

            motor.setPower(out);

            lastError = error;

            // reset the timer for next time
            timer.reset();
        }
    }*/
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
        frontRightWheel.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        frontLeftWheel = hardwareMap.get(DcMotorEx.class, "leftFront");
        frontLeftWheel.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        backRightWheel = hardwareMap.get(DcMotorEx.class, "rightBack");
        backRightWheel.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        backLeftWheel = hardwareMap.get(DcMotorEx.class, "leftBack");
        backLeftWheel.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
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
        boolean fast = true;
        ElapsedTime ArmTimer = new ElapsedTime();
        while (opModeIsActive())
        {
            Forward = this.gamepad1.left_stick_y;
            Strafe = this.gamepad1.left_stick_x;
            Rotation = -this.gamepad1.right_stick_x;
            if (this.gamepad1.a){
                frontLeftWheel.setVelocity(-1000);
            } else if (this.gamepad1.x) {
                frontRightWheel.setVelocity(-1000);
            }else if (this.gamepad1.y){
                backRightWheel.setVelocity(-1000);
            }else if (this.gamepad1.b){
                backLeftWheel.setVelocity(-1000);
            }else {
            frontRightWheel.setVelocity((Forward - Rotation - (Strafe*1))*2800);
            backRightWheel.setVelocity((Forward - Rotation + (Strafe*1))*2800);
            frontLeftWheel.setVelocity((Forward + Rotation + (Strafe*1))*2800);
            backLeftWheel.setVelocity((Forward + Rotation - (Strafe*1))*2800);
            }

            if (this.gamepad1.rightBumperWasPressed()){
                HopperServo.setPosition(0.8);
                ArmTimer.reset();
            }
            if (ArmTimer.milliseconds() > 800){
                HopperServo.setPosition(1);
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
            if (gamepad1.leftBumperWasPressed())
            {
                fast = !fast;
            }*/

            if (this.gamepad1.dpadDownWasPressed())
            {
              //  TruffleShuffle();
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

            telemetry.addData("FWD", Forward);
            telemetry.addData("STR", Strafe);
            telemetry.addData("ROT", Rotation);
            telemetry.addData("is fast?",fast);
            telemetry.addData("BLS", backLeftWheel.getVelocity());
            telemetry.addData("BRS", backRightWheel.getVelocity());
            telemetry.addData("FLS", frontLeftWheel.getVelocity());
            telemetry.addData("FRS", frontRightWheel.getVelocity());
            telemetry.addData("Left stick for axis movement, right stick for rotation", ' ');
            telemetry.addData("Hopper servo" , HopperServo.getPosition());
            updateTelemetry(telemetry);
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

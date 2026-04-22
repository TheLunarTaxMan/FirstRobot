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
    public DcMotorEx frontRightWheel;
    public DcMotorEx frontLeftWheel;
    public DcMotorEx backRightWheel;
    public DcMotorEx backLeftWheel;

    private DcMotor HopperMotor;

    private DcMotorEx Flywheel;

    private Servo HopperServo;

    private double Ki = 1;
    private double Kd = 1;
    private double Kp = 1;

    private int reference = 850;

    double lastError = 0;

    double integralSum = 0;

    ElapsedTime timer = new ElapsedTime();
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
        boolean fast = false;
        boolean flywheelon = false;
        ElapsedTime ArmTimer = new ElapsedTime();
        while (opModeIsActive()) {
            Forward = this.gamepad1.left_stick_y;
            Strafe = this.gamepad1.left_stick_x;
            Rotation = -this.gamepad1.right_stick_x;

            frontRightWheel.setVelocity((Forward - Rotation - (Strafe * 1)) * 2800);
            backRightWheel.setVelocity((Forward - Rotation + (Strafe * 1)) * 2800);
            frontLeftWheel.setVelocity((Forward + Rotation + (Strafe * 1)) * 2800);
            backLeftWheel.setVelocity((Forward + Rotation - (Strafe * 1)) * 2800);


            if (this.gamepad1.rightBumperWasPressed() ) {
                HopperServo.setPosition(0.85);
                ArmTimer.reset();
            }
            if (ArmTimer.milliseconds() > 800) {
                HopperServo.setPosition(1);
            }


            if (flywheelon){
                if (fast) {
                    //Flywheel.setVelocity((gamepad1.right_trigger - gamepad1.left_trigger) * 1800);
                    //Flywheel.setVelocity((1) * 850);

                    double speed = Flywheel.getVelocity();
                    // calculate the error
                    double error = reference - speed;

                    double derivative = (error - lastError) / timer.seconds();

                    // sum of all error over time
                     integralSum = integralSum + (error * timer.seconds());

                    double out = (Kp * error) + (Ki * integralSum) + (Kd * derivative);

                    Flywheel.setPower(out);

                    timer.reset();

                } else {
                    Flywheel.setVelocity((1) * 750);
                }
            }else{
                Flywheel.setVelocity(0);
            }
            if (gamepad1.xWasPressed()) {
                fast = true;
            }
            if (gamepad1.yWasPressed()){
                fast = false;
            }
            if (gamepad1.bWasPressed()) {
                flywheelon = !flywheelon;
            }


            if (this.gamepad1.dpadDownWasPressed())
            {
              //  TruffleShuffle();
            }
            if(this.gamepad1.left_trigger > 0.2){
                HopperMotor.setPower(-1);
            }else if (this.gamepad1.right_trigger > 0.2){
                HopperMotor.setPower(1);
            }else{
                HopperMotor.setPower(0);
            }


            telemetry.addData("FWD", Forward);
            telemetry.addData("STR", Strafe);
            telemetry.addData("ROT", Rotation);
            telemetry.addData("FWS", Flywheel.getVelocity());
            telemetry.addData("isfast", fast);
            telemetry.addData("BLS", backLeftWheel.getVelocity());
            telemetry.addData("BRS", backRightWheel.getVelocity());
            telemetry.addData("FLS", frontLeftWheel.getVelocity());
            telemetry.addData("FRS", frontRightWheel.getVelocity());
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

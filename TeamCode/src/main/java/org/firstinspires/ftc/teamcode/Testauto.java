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


    public void runOpMode() {
        motorR = hardwareMap.get(DcMotor.class, "motorR");
        motorR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorL = hardwareMap.get(DcMotor.class, "motorL");
        motorL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        motorR.setPower(0.5);
        motorL.setPower(-0.5);
        sleep(2000);

        telemetry.addData("L motor position",motorL.getCurrentPosition());
        telemetry.addData("R motor position",motorR.getCurrentPosition());
        motorR.setPower(0.5);
        motorL.setPower(0.5);
        sleep(1000);

        motorR.setPower(0);
        motorL.setPower(0);

    }
}
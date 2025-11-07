package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class RoadRunnerTest extends LinearOpMode {
    private DcMotor motorL;
    private DcMotor motorR;
    private DcMotor HopperMotor;
    private DigitalChannel digitalTouch;
    private DistanceSensor sensorColorRange;
    private Servo servoTest;


    public void runOpMode() {
        motorR = hardwareMap.get(DcMotor.class, "motorR");
        motorL = hardwareMap.get(DcMotor.class, "motorL");
        motorR.setPower(0.5);
        motorL.setPower(-0.5);
        sleep(2000);
        motorR.setPower(0.5);
        motorL.setPower(0.5);
        sleep(1000);
        motorL.setPower(0);
        motorR.setPower(0);
    }
}

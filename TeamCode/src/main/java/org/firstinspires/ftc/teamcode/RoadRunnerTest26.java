package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TrajectoryBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class RoadRunnerTest26 extends LinearOpMode {
    private DcMotor HopperMotor;
    private DigitalChannel digitalTouch;
    private DistanceSensor sensorColorRange;
    private Servo servoTest;

    public class FlyWheel {


    }

    public void runOpMode() {

        Pose2d initialPos = new Pose2d(0, 0, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPos);

        TrajectoryActionBuilder intialTrajectory = drive.actionBuilder(initialPos)
                .waitSeconds(2)
                .lineToX(50)
                .strafeTo(new Vector2d(10,30))
                .strafeToLinearHeading(new Vector2d(-20,-30),-Math.PI/2)
                .splineTo(new Vector2d(20,10),2);

    }
}

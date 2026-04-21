package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class RoadRunnerRfar extends LinearOpMode {
    private DcMotorEx Flywheel;
    private DcMotorEx HopperMotor;
    private Servo HopperServo;
    private DigitalChannel digitalTouch;
    private DistanceSensor sensorColorRange;


    public  class FlyWheelclass {
        int flywheelspeed = 800;

        public FlyWheelclass(){

        }

        public Action end() {return new End();}

        public class End implements Action {

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                sleep(30000000);
                return false;
            }
        }
        public Action shootThree() { return new ShootThree(); }
        public class ShootThree implements Action {

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {

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
                sleep(400);
                HopperMotor.setPower(1);
                sleep(1000);
                HopperServo.setPosition(0.8);
                sleep(600);
                HopperServo.setPosition(1);
                sleep(1000);

                Flywheel.setVelocity(0);
                HopperMotor.setPower(0);
                HopperServo.setPosition(1);

                return(false);
            }
        }
    }

    public void runOpMode() {

        Pose2d initialPos = new Pose2d(62, 12, -Math.PI);
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPos);
        Flywheel = hardwareMap.get(DcMotorEx.class, "Flywheel");
        HopperMotor = hardwareMap.get(DcMotorEx.class, "HopperMotor");
        HopperServo = hardwareMap.get(Servo.class, "HopperServo");

        FlyWheelclass fw = new FlyWheelclass();
        waitForStart();
        TrajectoryActionBuilder tab1 = drive.actionBuilder(initialPos)
                .waitSeconds(2)
                .splineToLinearHeading(new Pose2d(-12,12, 3*Math.PI/4), 3*Math.PI/4);

        Action movement = tab1
                .build();

        Action moveagain =  drive.actionBuilder(initialPos)
                //shoot
                .waitSeconds(5)
                .strafeToLinearHeading(new Vector2d(-11, 20), Math.PI/2)
                //reload
                .strafeTo(new Vector2d(-11, 32))
//                        .strafeToLinearHeading(new Vector2d(-35, -35), -3*Math.PI/4)
                //shoot again
                .build();


        if (isStopRequested()) return;


        Actions.runBlocking(
                new SequentialAction(
                        movement
                        ,fw.shootThree()
                        ,moveagain

                ));
        return;
    }
}

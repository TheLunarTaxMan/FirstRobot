package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TrajectoryBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class RoadRunnerTest26 extends LinearOpMode {
    private DcMotorEx Flywheel;
    private DcMotorEx HopperMotor;
    private Servo HopperServo;
    private DigitalChannel digitalTouch;
    private DistanceSensor sensorColorRange;


    public  class FlyWheelclass {
        int flywheelspeed = 850;

        public FlyWheelclass(){

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
                sleep(300);
                HopperMotor.setPower(1);
                sleep(100);
                HopperServo.setPosition(0.8);
                sleep(600);
                HopperServo.setPosition(1);
                sleep(1000);

                Flywheel.setVelocity(0);
                HopperMotor.setPower(0);
                HopperServo.setPosition(1);

                return(true);
            }
        }


        public void TestyThing()
        {

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
            sleep(300);
            HopperMotor.setPower(1);
            sleep(100);
            HopperServo.setPosition(0.8);
            sleep(600);
            HopperServo.setPosition(1);
            sleep(1000);

            Flywheel.setVelocity(0);
            HopperMotor.setPower(0);
            HopperServo.setPosition(1);


        }
    }

    public void runOpMode() {

        Pose2d initialPos = new Pose2d(0, 0, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPos);
        Flywheel = hardwareMap.get(DcMotorEx.class, "Flywheel");
        HopperMotor = hardwareMap.get(DcMotorEx.class, "HopperMotor");
        HopperServo = hardwareMap.get(Servo.class, "HopperServo");

        FlyWheelclass fw = new FlyWheelclass();
        waitForStart();
        Action movement = drive.actionBuilder(initialPos)
                .waitSeconds(2)
                .splineToLinearHeading(new Pose2d(-12,-12, -3*Math.PI/4), -3*Math.PI/4)
                .build();

        Action moveagain =  drive.actionBuilder(initialPos)
                //shoot
                .waitSeconds(5)
                .strafeToLinearHeading(new Vector2d(-11, -20), -Math.PI/2)
                //reload
                .strafeTo(new Vector2d(-11, -32))
//                        .strafeToLinearHeading(new Vector2d(-35, -35), -3*Math.PI/4)
                //shoot again
                .build();

        Actions.runBlocking(
                new SequentialAction(
                        movement
                        ,fw.shootThree()
                        ,moveagain
                ));
    }
}

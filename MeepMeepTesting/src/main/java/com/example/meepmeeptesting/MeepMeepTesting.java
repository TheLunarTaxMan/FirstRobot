package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(0, 0, Math.PI/2))
                .waitSeconds(2)
                .turnTo(0)
                .lineToX(50)
                .strafeTo(new Vector2d(10,30))
                .strafeToLinearHeading(new Vector2d(-20,-30),-Math.PI/2)
                .strafeToSplineHeading(new Vector2d(20,10), Math.PI/2)
                .splineTo(new Vector2d(10,20), 40)
                .splineTo(new Vector2d(25,18), 40)
                .splineTo(new Vector2d(10,10), 40)
                .splineTo(new Vector2d(0,0), 40)
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_DECODE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
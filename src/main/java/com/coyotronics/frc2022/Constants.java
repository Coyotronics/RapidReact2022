// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.coyotronics.frc2022;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    private Constants() {
        /* disallow construction of this class */
        throw new UnsupportedOperationException("don't try to construct an instance of Constants");
    }

    /**
     * When competition mode is enabled, most throwable errors are disregarded.
     * Classes in the project should use the competition mode variable in order to
     * decide what their failure behavior should be.
     */
    public static final boolean kCompetitionMode = false;
    public static final boolean ksafetyMode = true; 
    public static final double kSafetyMultiplier = 0.5; //MAKE SURE BETWEEN 0 AND 1
    
    public static class Controller {
        public static final int LEFT_STICK_X = 0;
	    public static final int LEFT_STICK_Y = 1;
	    public static final int RIGHT_STICK_X = 4;
	    public static final int RIGHT_STICK_Y = 5;

        public static final int RIGHT_BUMPER = 6;
    }
    public static class Common {
        public static final int kController = 0;
    }

    public static class Auto {

    }
    public static class Drive {
        public static final int kRightFrontMotor = 13;
        public static final int kRightBackMotor = 12;
        public static final int kLeftFrontMotor = 14;
        public static final int kLeftBackMotor = 15;

        public static final int kRFMID = 0;
        public static final int kRBMID = 0;
        public static final int kLFMID = 0;
        public static final int kLBMID = 0;

        public enum DriveType {
            ARCADE, TANK;
        }
    }

    public static class Vars {
        public static Drive.DriveType cDriveType = Drive.DriveType.ARCADE;
    }
}

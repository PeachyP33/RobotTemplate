/*
 * FRC Team 5190
 * Green Hope Falcons
 */

package org.ghrobotics.frc2020

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard
import org.ghrobotics.frc2020.commands.AutoDriveCommand
import org.ghrobotics.frc2020.subsytems.DriveSubsystem
import org.ghrobotics.lib.mathematics.units.derivedunits.velocity
import org.ghrobotics.lib.mathematics.units.feet
import org.ghrobotics.lib.wrappers.FalconTimedRobot

object Robot : FalconTimedRobot() {

    // Constructor of the Robot class.
    init {
        // Register the DriveSubsystem into the SubsystemHandler.
        +DriveSubsystem
    }

    // Runs once when robot boots up
    override fun robotInit() {
        println("hmm")
    }

    // Runs once when autonomous period starts
    override fun autonomousInit() {
        //3 feet per second on each side, with timeout tells it to stop after 3 sec or else it keeps on going until disables
        //need .schedule() to make it run
        AutoDriveCommand(3.feet.velocity, 3.feet.velocity).withTimeout(3.0).schedule()
    }

    // Runs once when teleop period starts
    override fun teleopInit() {}

    // Runs once when robot is disabled
    override fun disabledInit() {}


    // Runs every 20 ms when robot is on
    override fun robotPeriodic() {
        Shuffleboard.update()
    }

    // Runs every 20 ms when autonomous is enabled
    override fun autonomousPeriodic() {}

    // Runs every 20 ms when teleop is enabled
    override fun teleopPeriodic() {}

    // Runs every 20 ms when robot is disabled
    override fun disabledPeriodic() {}
}
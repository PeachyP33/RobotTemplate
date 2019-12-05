/*
 * FRC Team 5190
 * Green Hope Falcons
 */

package org.ghrobotics.frc2020

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard
import org.ghrobotics.frc2020.commands.AutoDriveCommand
import org.ghrobotics.frc2020.commands.IntakeCommand
import org.ghrobotics.frc2020.commands.TeeterTotterCommand
import org.ghrobotics.frc2020.commands.TurretCommand
import org.ghrobotics.frc2020.subsytems.DriveSubsystem
import org.ghrobotics.frc2020.subsytems.Intake
import org.ghrobotics.frc2020.subsytems.Turret
import org.ghrobotics.lib.commands.sequential
import org.ghrobotics.lib.mathematics.units.derivedunits.velocity
import org.ghrobotics.lib.mathematics.units.feet
import org.ghrobotics.lib.wrappers.FalconTimedRobot

object Robot : FalconTimedRobot() {

    private val autoStartCommand = sequential{
        +AutoDriveCommand(2.feet.velocity, 2.feet.velocity, 6.0)
        +TeeterTotterCommand()
    }
    // Constructor of the Robot class.
    init {
        // Register the DriveSubsystem into the SubsystemHandler.
        +DriveSubsystem
        +Intake
        +Turret
    }

    // Runs once when robot boots up
    override fun robotInit() {
        println("hmm")
    }

    // Runs once when autonomous period starts
    override fun autonomousInit() {
        DriveSubsystem.gyro.reset()

        //AutoDriveCommand(3.feet.velocity, 3.feet.velocity).withTimeout(3.0).schedule()
        autoStartCommand.schedule()
        IntakeCommand(true).schedule()
        TurretCommand().schedule()
    }

    // Runs once when teleop period starts
    override fun teleopInit() {
        autoStartCommand.cancel()
    }

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
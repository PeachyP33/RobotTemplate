package org.ghrobotics.frc2020.commands

import edu.wpi.first.wpilibj.GenericHID
import edu.wpi.first.wpilibj.experimental.command.SendableCommandBase
import org.ghrobotics.frc2020.Controls
import org.ghrobotics.frc2020.subsytems.DriveSubsystem3
import org.ghrobotics.lib.wrappers.hid.getY

class TeleopDriveCommand3 : SendableCommandBase() {

    init{
        addRequirements(DriveSubsystem3)
    }

    val rightJoystick = Controls.driverController.getY(GenericHID.Hand.kRight)
    val leftJoystick = Controls.driverController.getY(GenericHID.Hand.kLeft)

    override fun execute() {
        DriveSubsystem3.set(leftJoystick(), rightJoystick())
    }

    override fun isFinished(): Boolean {
        return false
    }
}
package org.ghrobotics.frc2020.commands

import edu.wpi.first.wpilibj.GenericHID
import edu.wpi.first.wpilibj.experimental.command.Command
import edu.wpi.first.wpilibj.experimental.command.SendableCommandBase
import org.ghrobotics.frc2020.Controls
import org.ghrobotics.frc2020.subsytems.DriveSubsystem2
import org.ghrobotics.lib.wrappers.hid.getY
import org.ghrobotics.lib.wrappers.hid.kStickRight

class TeleopDriveCommand2 : SendableCommandBase() {

    init{
        addRequirements(DriveSubsystem2)
    }

    val leftJoystick = Controls.driverController.getY(GenericHID.Hand.kLeft)
    val rightJoystick = Controls.driverController.getY(GenericHID.Hand.kRight)


    override fun execute() {
        DriveSubsystem2.set(leftJoystick(), rightJoystick())
    }

    override fun isFinished(): Boolean {
        return false
    }
}
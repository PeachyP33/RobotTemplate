package org.ghrobotics.frc2020.commands

import edu.wpi.first.wpilibj.GenericHID
import edu.wpi.first.wpilibj.experimental.command.SendableCommandBase
import org.ghrobotics.frc2020.Controls
import org.ghrobotics.frc2020.subsytems.DriveSubsystem
import org.ghrobotics.lib.wrappers.hid.getX
import org.ghrobotics.lib.wrappers.hid.getY

class TeleopDriveCommand : SendableCommandBase() { //class extends base class

    init{

        addRequirements(DriveSubsystem)
    }

    val leftJoystick = Controls.driverController.getY(GenericHID.Hand.kLeft)
    val rightJoystick = Controls.driverController.getY(GenericHID.Hand.kRight)


    
    override fun execute() {

        DriveSubsystem.setOutput(-leftJoystick(), -rightJoystick())
        //DriveSubsystem.set(0.5, 0.5)
    }

    override fun isFinished(): Boolean {
        return false
    }

    fun setNuetral() {
        DriveSubsystem.setOutput(0.0, 0.0)

    }

}
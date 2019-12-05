package org.ghrobotics.frc2020.commands

import edu.wpi.first.wpilibj.GenericHID
import edu.wpi.first.wpilibj.experimental.command.SendableCommandBase
import org.ghrobotics.frc2020.Controls
import org.ghrobotics.frc2020.subsytems.DriveSubsystem
import org.ghrobotics.lib.wrappers.hid.getRawButton
import org.ghrobotics.lib.wrappers.hid.getX
import org.ghrobotics.lib.wrappers.hid.getY
import org.ghrobotics.lib.wrappers.hid.kX

class curvatureDriveCommand : SendableCommandBase() {

    init{
        addRequirements(DriveSubsystem)
    }

    //curve drive
    private val linearPercent = Controls.driverController.getY(GenericHID.Hand.kLeft)
    private val curvaturePercent = Controls.driverController.getX(GenericHID.Hand.kLeft)
    private val quickTurn = Controls.driverController.getRawButton(kX)

    override fun execute() {
        DriveSubsystem.curvatureDrive(
                -linearPercent(),
                curvaturePercent(),
                quickTurn()
        )
    }
}
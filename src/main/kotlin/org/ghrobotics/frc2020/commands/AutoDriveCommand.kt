package org.ghrobotics.frc2020.commands

import edu.wpi.first.wpilibj.experimental.command.SendableCommandBase
import org.ghrobotics.frc2020.subsytems.DriveSubsystem
import org.ghrobotics.lib.mathematics.units.derivedunits.LinearVelocity

class AutoDriveCommand(val left: LinearVelocity, val right: LinearVelocity) : SendableCommandBase() {

    init{
        addRequirements(DriveSubsystem)
    }

    override fun execute() {
        DriveSubsystem.setVelocity(left, right)

    }

    override fun end(interrupted: Boolean) {
        DriveSubsystem.set(0.0, 0.0)
    }
}
package org.ghrobotics.frc2020.commands

import com.kauailabs.navx.frc.AHRS
import edu.wpi.first.wpilibj.ADXRS450_Gyro
import edu.wpi.first.wpilibj.SPI
import edu.wpi.first.wpilibj.experimental.command.SendableCommandBase
import org.ghrobotics.frc2020.subsytems.DriveSubsystem
import org.ghrobotics.lib.commands.FalconCommand
//import org.ghrobotics.lib.mathematics.units.derived.LinearVelocity
import org.ghrobotics.lib.mathematics.units.derivedunits.LinearVelocity

class AutoDriveCommand(val left: LinearVelocity, val right: LinearVelocity, val gyroAngle: Double ) : FalconCommand(DriveSubsystem) {


    override fun execute() {
        DriveSubsystem.setVelocity(left, right)

    }

    override fun end(interrupted: Boolean) {
        DriveSubsystem.setOutput(0.0, 0.0)
    }

    override fun isFinished(): Boolean {
        return DriveSubsystem.gyro.angle >= gyroAngle
        
    }
}
package org.ghrobotics.frc2020.commands

import edu.wpi.first.wpilibj.GenericHID
import org.ghrobotics.frc2020.Controls
import org.ghrobotics.frc2020.subsytems.Turret
import org.ghrobotics.lib.commands.FalconCommand
import org.ghrobotics.lib.wrappers.hid.getY

class TurretCommand : FalconCommand(Turret){

    override fun execute() {
        Turret.spinTurret(output())
    }

    companion object {

        val output = Controls.driverController.getY(GenericHID.Hand.kRight)
    }
}
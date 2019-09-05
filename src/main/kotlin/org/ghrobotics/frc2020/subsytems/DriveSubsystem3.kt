package org.ghrobotics.frc2020.subsytems

import org.ghrobotics.frc2020.Constants
import org.ghrobotics.frc2020.commands.TeleopDriveCommand3
import org.ghrobotics.lib.commands.FalconSubsystem
import org.ghrobotics.lib.mathematics.units.amp
import org.ghrobotics.lib.mathematics.units.millisecond
import org.ghrobotics.lib.motors.ctre.FalconSRX

object DriveSubsystem3 : FalconSubsystem() {

    private val rightMaster = FalconSRX(Constants.kDriveRightMasterId, Constants.kDriveNativeUnitModel)
    private val rightSlave = FalconSRX(Constants.kDriveRightSlaveId, Constants.kDriveNativeUnitModel)

    private val leftMaster = FalconSRX(Constants.kDriveLeftMasterId, Constants.kDriveNativeUnitModel)
    private val leftSlave = FalconSRX(Constants.kDriveLeftSlaveId, Constants.kDriveNativeUnitModel)

    init{

        rightSlave.follow(rightMaster)
        leftSlave.follow(leftMaster)

        rightMaster.outputInverted = true
        rightSlave.outputInverted = true

        leftMaster.outputInverted = false
        leftSlave.outputInverted = false

        rightMaster.configCurrentLimit(true, FalconSRX.CurrentLimitConfig(
                90.amp,
                500.millisecond,
                38.amp

            )
        )

        leftMaster.configCurrentLimit(true, FalconSRX.CurrentLimitConfig(
                90.amp,
                500.millisecond,
                38.amp
            )
        )

        defaultCommand = TeleopDriveCommand3()
    }

    fun set(left : Double, right : Double){
        leftMaster.setDutyCycle(left)
        rightMaster.setDutyCycle(right)
    }
}